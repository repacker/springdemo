package com.company.springdemo.common.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okhttp3.FormBody.Builder;

import java.io.IOException;
import java.util.Map;

/**
 * @Auther: whs
 * @Date: 2018/6/20 14:19
 * @Description: okhttp3请求访问接口工具类
 */
@Slf4j
public class HttpUtil {

    /**
     * @Description: 该方法get获取数据，默认即get方法
     * @Param:
     * @return:
     */
    public static String httpGet(String url) throws IOException {
//        Request request = new Request.Builder().get().url(url).build();
        Request request = new Request.Builder().url(url).build();
//        log.info("httpGet header:" + request.headers().toString());
        Call call = new OkHttpClient().newCall(request);
        Response response = call.execute();
        return response.body().string();
    }

    /**
     * @Description: 该方法通过key、value Map post提交请求
     * @Param:
     * @return:
     */
    public static String postToUrlByMapParams(String url, Map<String, String> params) throws IOException {
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
    public static String postToUrlByJsonParams(String url, String paramsJson) throws IOException {
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
