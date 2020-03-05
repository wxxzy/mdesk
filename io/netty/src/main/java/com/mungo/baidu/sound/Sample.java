package com.mungo.baidu.sound;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author wangxingxiang
 * @Description
 * @date 2020/3/5 15:44
 */
public class Sample {
    //设置APPID/AK/SK
    public static final String APP_ID = "9535228";
    public static final String API_KEY = "8YUMqQdzRPbKd4B77QvUqrxnEeA51nq0";
    public static final String SECRET_KEY = "ZyPQeYIyH1O40ytwDUWEgMfOWHHN6Y1N";

    public static void main(String[] args) {
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        //System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 设置可选参数
        HashMap<String, Object> options = new HashMap<String, Object>();
        //语速，取值0-9，默认为5中语速
        options.put("spd", "5");
        //音调，取值0-9，默认为5中语调
        options.put("pit", "5");
        //发音人选择, 0为女声，1为男声，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女
        options.put("per", "4");
        //音量，取值0-15，默认为5中音量
        options.put("vol", "5");
        // 调用接口
        TtsResponse res = client.synthesis("今天的会议内容，" +
                "主要分为以下五个部分1、流感的流行性病学特点；2、流感的发病机制；" +
                "3、流感的诊断标准；4、抗流感病毒药物；5、总结。" +
                "首先，关于流感的流行性病学特点，人类自1889年以来，有详细记载的流感大流行共有6次，" +
                "其中距离我们最近的是2009年甲型H1N1流感。流感病毒极易发生重组和变异、" +
                "传播速度快，每年可引起季节性流行；并且流感的季节性呈多样化，" +
                "既有半年或全年周期流行也有全年循环流行。", "zh", 1, options);
        byte[] data = res.getData();
        JSONObject res1 = res.getResult();
        if (data != null) {
            try {
                Util.writeBytesToFileSystem(data, "output.mp3");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (res1 != null) {
            System.out.println(res1.toString(2));
        }

    }

    public void synthesis(AipSpeech client)
    {
        //TtsResponse res = client.synthesis("你好百度", "zh", 1, null);
        //System.out.println(res.getResult());

        // 设置可选参数
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("spd", "5");
        options.put("pit", "5");
        options.put("per", "4");
        TtsResponse res = client.synthesis("你好百度", "zh", 1, options);
        System.out.println(res.getResult());
        JSONObject result = res.getResult();    //服务器返回的内容，合成成功时为null,失败时包含error_no等信息
        byte[] data = res.getData();            //生成的音频数据
    }
}
