package com.summit.whms.intercepter;

import com.summit.whms.core.session.SessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SessionInterceptor extends HandlerInterceptorAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(LOGGER.isInfoEnabled()) {
        LOGGER.info("=======================<Interceptor Start>=======================");
            LOGGER.info("Request URL \t: " + request.getRequestURI());
        }

        boolean isSignIn = SessionManager.isSignIn(request);

        if(!isSignIn) {
            response.sendRedirect("/main/main");
            return false;
        }

        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if(LOGGER.isInfoEnabled()) {
            LOGGER.info("=======================<Interceptor End>========================\n");
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3) throws Exception {
        if(LOGGER.isInfoEnabled()) {
            LOGGER.info("Interceptor > afterCompletion");
        }
    }
}
