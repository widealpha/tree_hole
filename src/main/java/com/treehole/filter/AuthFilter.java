package com.treehole.filter;

import com.treehole.dao.TokenDao;
import com.treehole.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.*;

@WebFilter
@Component
public class AuthFilter implements Filter {
    @Autowired
    TokenDao tokenDao;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("text/html;charset=utf-8");
        String token = request.getHeader("token");
        System.out.println("token1:" + request.getHeader("token"));
        if (StringUtils.hasLength(token)){
            Integer id = tokenDao.getUserId(token);
            System.out.println("id1:" + id);
            Map<String, Object> map = new HashMap<>();
            map.put("userId", id);
            RequestParameterWrapper requestParameterWrapper = new RequestParameterWrapper(request);
            requestParameterWrapper.addParameters(map);
            filterChain.doFilter(requestParameterWrapper, servletResponse);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

}
