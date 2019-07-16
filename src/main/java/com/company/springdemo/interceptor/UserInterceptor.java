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
import com.company.springdemo.common.utils.CookieUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 校验及获取登录用户信息
 *
 * @author whs
 * @date 2018/5/15
 */
@Component
public class UserInterceptor extends BaseAction implements HandlerInterceptor {

    private final static Logger log = LoggerFactory.getLogger(UserInterceptor.class);

    @Autowired
    private Environment environment;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取当前登录用户cookie
        Cookie ssoCookie = CookieUtil.getCookie(request, environment.getProperty("login.cookieName"));
        log.info(request.getRequestURI());
        boolean isDebug = Boolean.valueOf(environment.getProperty("isDebug"));

        log.info("ssoCookie return isDebug : " + isDebug);
        if (isDebug) {
            return true;
        }

        if (ssoCookie == null) {
            sendJsonMessage(request, response, new RespEntity(RespCode.NOT_LOGIN));
            log.info("ssoCookie return false");
            return false;
        }
        log.info("ssoCookie.getValue() : " + ssoCookie.getValue());

        //todo 2.缓存当前登录用户信息

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
