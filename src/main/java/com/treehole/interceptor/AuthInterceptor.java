package com.treehole.interceptor;

import com.treehole.util.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String id = request.getParameter("userId");
        if (response.getStatus() == 200 && id == null) {
            response.getWriter().print(Result.error( "token错误，请重新登陆"));
            return false;
        }
        return true;
    }

}
