package cn.org.angry.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GlobalFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        if(request.getServletPath().lastIndexOf(".html")>=0){
            // 页面跳转响应为html
            response.setContentType("text/html;charset=UTF-8");
        }else {
            // 数据请求统一响应为json格式
            response.setContentType("application/json;charset=utf-8");
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
