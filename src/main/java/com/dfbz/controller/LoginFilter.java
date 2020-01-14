package com.dfbz.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/student/*", "/studentTable.jsp"})
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("Filter Created");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (req.getSession().getAttribute("name") != null) {
            chain.doFilter(request, response);
        } else {
            System.out.println("攔截到非法用戶！");
            resp.sendRedirect("/index.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
