package com.tb.store.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 检查全局session对象中是否有uid数据，有则放行，没有则重新跳转到登录页
     * @param request   请求对象
     * @param response  响应对象
     * @param handler   处理器（url+Controller：映射）
     * @return 返回值为true表示放行当前的请求
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        通过HttpServletRequest对象来获取session对象
        Object obj = request.getSession().getAttribute("uid");
        if (obj==null){
//            重定向
            response.sendRedirect("/web/login.html");
            return false;
        }
        return  true;
    }
}
