package com.itheima.utils;


import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MyInterceptorl implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
      /*  System.out.println(handler);
        System.out.println("MyInterceptorl...");*/
        if (handler instanceof DefaultServletHttpRequestHandler){
            /*如果handler对象 和DefaultServletHttpRequestHandler 一样的话 就放行*/
            return true;
        }
        HttpSession session = request.getSession();
       if (session!=null&&session.getAttribute("user")!=null){
           return true;
       }else {

           try {
               /*如果session中没哟数据，那么直接重定向到index.jsp页面上*/
               response.sendRedirect(request.getContextPath()+"/index.jsp");
           } catch (IOException e) {
               e.printStackTrace();
           }
       }

        return false;
    }
}
