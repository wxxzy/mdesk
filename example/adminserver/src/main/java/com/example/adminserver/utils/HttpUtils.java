package com.example.adminserver.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.Configurable;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HttpUtils {
    @Autowired
    private CloseableHttpClient httpClient;

    private String doHttp(HttpRequestBase request, int socketTimeout) throws IOException {
        //设置超时时间
        if (socketTimeout > 0) {
            //获取原有配置
            //实际注入类型org.apache.http.impl.client.InternalHttpClient
            Configurable configClient = (Configurable) httpClient;
            RequestConfig.Builder custom = RequestConfig.copy(configClient.getConfig());
            //设置个性化配置
            RequestConfig config = custom.setSocketTimeout(socketTimeout).build();
            request.setConfig(config);
        }
        ResponseHandler<String> handler = new BasicResponseHandler();
        String response = httpClient.execute(request, handler);
        return response;
    }

    public String doPost(String url, Object paramsObj, int socketTimeout) throws IOException {
        HttpPost post = new HttpPost(url);
        StringEntity entity = new StringEntity(JSONObject.toJSONString(paramsObj), "UTF-8");
        post.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        post.setEntity(entity);
        return doHttp(post, socketTimeout);
    }
}
