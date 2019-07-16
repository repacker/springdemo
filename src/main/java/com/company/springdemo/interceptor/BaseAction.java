package com.company.springdemo.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 *
 * @author whs
 * @date 2019/3/20 12:52
 * @description
 */
public class BaseAction {

    /**
     * 将某个对象转换成json格式并发送到客户端
     * @param response
     * @param obj
     * @throws Exception
     */
    public void sendJsonMessage(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
//        String jsonType = request.getParameter("jsonType");
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteDateUseDateFormat));
        writer.close();
        response.flushBuffer();
    }

}
