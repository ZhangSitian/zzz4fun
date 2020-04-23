package com.zzz.learn.common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
/**
 * 登录过滤器
 * @author yinjihuan
 *
 */
@WebFilter(filterName="LoginFilter",urlPatterns="*")
public class LoginFilter implements Filter {
    @Override
    public void destroy() {
        System.out.println("init destroy do ");
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("doFilter start ");
        chain.doFilter(request, response);
        System.out.println("doFilter end ");
    }
    @Override
    public void init(FilterConfig arg0) throws ServletException {
        System.out.println("init filter do ");
    }
}
