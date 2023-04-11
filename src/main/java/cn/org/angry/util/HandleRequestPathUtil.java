package cn.org.angry.util;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 根据请求的接口路径，执行servlet中对应的方法
 */
public class HandleRequestPathUtil {
    private HttpServlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public HandleRequestPathUtil(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response){
        this.servlet = servlet;
        this.request = request;
        this.response = response;
    }
    public void execute() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String servletPath = request.getServletPath();
        System.out.println("新的请求："+servletPath);
        String methodName = servletPath.substring(servletPath.lastIndexOf("/") + 1);
        Method method = servlet.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
        method.invoke(servlet,request,response);
    }
}
