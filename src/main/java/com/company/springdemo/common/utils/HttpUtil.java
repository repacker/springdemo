package com.company.springdemo.common.utils;

import okhttp3.*;
import okhttp3.FormBody.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.Map;
/**
 * @Auther: whs
 * @Date: 2018/6/20 14:19
 * @Description: okhttp3请求访问接口工具类
 */
public class HttpUtil {

    private final static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * @Description: 该方法get获取数据
     * @Param:
     * @return:
     */
    public static String httpGet(String url) {
        Request request = new Request.Builder().get().url(url).build();
        Call call = new OkHttpClient().newCall(request);
        try {
            Response response = call.execute();
            return response.body().string();
        } catch (IOException e) {
            logger.error("通知postToUrlByJsonDaiHou异常反馈：", e);
        }
        return null;
    }

    /**
     * @Description: 该方法通过key、value Map post提交请求
     * @Param:
     * @return:
     */
    public static String postToUrl(String url, Map<String, String> params) throws IOException {
        Builder builder = new Builder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        return response.body().string();
    }

    /** 
     * @Description: 该方法post传递json字符串
     * @Param: 
     * @return:
     */ 
    public static String postToUrl(String url, String paramsJson) throws IOException {
        //MediaType  设置Content-Type 标头中包含的媒体类型值
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , paramsJson);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        Response response = call.execute();
        return response.body().string();
    }

}
