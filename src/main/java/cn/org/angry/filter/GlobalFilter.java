package cn.org.angry.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class GlobalFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        System.out.println("global拦截到了"+request.getServletPath());
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        if(request.getServletPath().indexOf(".html")>0){
            response.setContentType("text/html;charset=UTF-8");
            System.out.println("是html，设置text/html;charset=UTF-8");
        }else {
            response.setContentType("application/json;charset=UTF-8");
            System.out.println("是数据接口，设置application/json;charset=UTF-8");
        }
        // 页面跳转响应为html


        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
