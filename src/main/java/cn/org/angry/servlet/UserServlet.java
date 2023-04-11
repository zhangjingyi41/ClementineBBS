package cn.org.angry.servlet;


import cn.org.angry.entity.Result;
import cn.org.angry.entity.User;
import cn.org.angry.service.UserService;
import cn.org.angry.service.impl.UserServiceImpl;
import cn.org.angry.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet(name = "UserServlet", urlPatterns = {
        "/login", "/register", "/logout"
})
public class UserServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        String methodName = path.substring(path.lastIndexOf("/")+1);
        System.out.println(path);
        System.out.println(methodName);
        try {
            Method method = getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//            resp.sendRedirect("404.html");
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    // 登陆
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        int c;
        // 读取请求数据
        while ((c = reader.read())!=-1){
            sb.append((char) c);
        }
        User user = JSONUtil.toObject(sb.toString(), User.class);
        Result result = userService.login(user);

        // 用户在线状态暂用session实现
        if(result.isOk()){
            User onlineUser = (User) result.getData();
            req.getSession().setAttribute("onlineUser", onlineUser);
        }

        PrintWriter writer = resp.getWriter();
        // 响应结果
        writer.print(result);

        writer.close();
        reader.close();
    }
    // 注册
    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        int c;
        while ((c = reader.read()) != -1){
            sb.append((char)c);
        }
        User user = JSONUtil.toObject(sb.toString(), User.class);
        Result result = userService.register(user);
        PrintWriter writer = resp.getWriter();
        writer.print(result);
        writer.close();
        reader.close();
    }
    // 退出登陆
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
