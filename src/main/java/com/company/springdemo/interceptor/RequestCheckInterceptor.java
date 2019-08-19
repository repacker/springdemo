/**
 * Copyright [2016-2017] [yadong.zhang]
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.company.springdemo.interceptor;

import com.company.springdemo.common.base.RespCode;
import com.company.springdemo.common.base.RespEntity;
import com.company.springdemo.common.utils.IllegalStrFilterUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * 校验及获取登录用户信息
 *
 * @author 请求数据安全校验
 * @date 2018/7/4
 */
@Component
@Slf4j
public class RequestCheckInterceptor extends BaseAction implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Enumeration<?> params = request.getParameterNames();
        //url校验
        String requestUrl = request.getRequestURI();
        String errorMsg = IllegalStrFilterUtil.isSafeUrl(requestUrl);
        if (StringUtils.isNotEmpty(errorMsg)) {
            sendJsonMessage(request, response, new RespEntity(RespCode.URL_NOT_ILLEGAL, errorMsg));
            return false;
        }
        //参数校验
        while (params.hasMoreElements()) {
            String paramKey = (String) params.nextElement();
            String paramValue = request.getParameter(paramKey);
            if (StringUtils.isNotBlank(paramValue)) {
                errorMsg = IllegalStrFilterUtil.isSafeParam(paramValue);
                if (StringUtils.isNotEmpty(errorMsg)) {
                    break;
                }
            }
        }

        if (StringUtils.isNotEmpty(errorMsg)) {
            sendJsonMessage(request, response, new RespEntity(RespCode.PARAMS_NOT_ILLEGAl, errorMsg));
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
