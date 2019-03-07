package org.interactivegraph.server

import java.io.{File, FileInputStream, InputStreamReader}
import java.util.Properties
import javax.servlet.ServletConfig
import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

import org.apache.commons.io.IOUtils
import org.interactivegraph.server.util.{JsonUtils, Logging, ServletContextUtils}
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
import org.springframework.context.support.FileSystemXmlApplicationContext

import scala.collection.{JavaConversions, mutable}

/**
  * Created by bluejoe on 2018/2/5.
  */

class ConnectorServlet extends HttpServlet with Logging {
  var _setting: Setting = _;
  var _commandRegistry: CommandExecutorRegistry = _;
  var _allowOrigin: Option[String] = None;

  override def init(servletConfig: ServletConfig) = {
    ServletContextUtils.setServletContext(servletConfig.getServletContext);

    //load conf file
    val path: String = servletConfig.getInitParameter("configFile")
    val configFile = Some(new File(path)).map { file =>
      if (file.isAbsolute) {
        file;
      }
      else {
        new File(servletConfig.getServletContext.getRealPath("/" + path));
      }
    }.get

    val is = new InputStreamReader(new FileInputStream(configFile), "utf-8");
    val ps = new Properties();
    ps.load(is);
    is.close();

    logger.info(s"using configuration: $configFile");
    val map = JavaConversions.propertiesAsScalaMap(ps);

    _allowOrigin = map.get("allowOrigin");
    val backendType = map("backendType").toLowerCase();

    val appctx = new FileSystemXmlApplicationContext();

    //enable expr like ${neo4j.boltUser}
    val placeholder = new PropertyPlaceholderConfigurer();
    placeholder.setProperties(ps);
    placeholder.setIgnoreUnresolvablePlaceholders(true);
    appctx.addBeanFactoryPostProcessor(placeholder);

    appctx.setConfigLocation(s"classpath:${backendType}.xml");
    appctx.refresh();

    _setting = appctx.getBean(classOf[Setting]);
    logger.info(s"loaded setting: ${_setting}");
    _commandRegistry = _setting._executors;
  }

  override def service(req: HttpServletRequest, resp: HttpServletResponse) = {
    _allowOrigin.foreach(resp.setHeader("Access-Control-Allow-Origin", _));
    super.service(req, resp);
  }

  override def doOptions(req: HttpServletRequest, resp: HttpServletResponse) = {
    super.doOptions(req, resp);
    resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
  }

  val _executorCache = mutable.Map[String, CommandExecutor]();

  def getCachedExecutor(command: String): CommandExecutor = {
    _executorCache.getOrElseUpdate(command, {
      val executor =
        try {
          val opt = _commandRegistry.executorOf(command);
          if (!opt.isDefined)
            throw new UnrecognizedCommandException(command);

          opt.get
        }
        catch {
          case e: Throwable => {
            e.printStackTrace();
            throw new UnrecognizedCommandException(command);
          }
        }

      executor.initialize(this._setting);
      executor;
    });
  }

  override def doPost(req: HttpServletRequest, resp: HttpServletResponse) = {
    val is = req.getInputStream;
    val body = IOUtils.toString(is, "utf-8");
    val requestBody = JsonUtils.parse(body).getAsJsonObject;
    logger.debug(requestBody)
    val command = req.getParameter("command");

    def sendError(msg: String): Unit = {
      resp.sendError(500, msg);
    }

    if (command == null) {
      sendError("no command in request");
    }
    else {
      try {
        val out = resp.getOutputStream;

        val ct: ContentTag = new ContentTag {
          def setContentType(ct: String) = resp.setContentType(ct);

          def setContentLength(len: Int) = resp.setContentLength(len);

          def setCharacterEncoding(en: String) = resp.setCharacterEncoding(en);
        };

        getCachedExecutor(command).execute(requestBody, ct, out);

        out.close();
      }
      catch {
        case e: Throwable => {
          e.printStackTrace();
          sendError(e.getMessage)
        };
      }
    }
  }
}

class UnknownBackendTypeException(typeName: String) extends
  RuntimeException(s"unknown backend type: $typeName") {

}