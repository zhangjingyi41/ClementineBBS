package cn.org.angry.servlet;


import cn.org.angry.entity.Result;
import cn.org.angry.entity.User;
import cn.org.angry.service.UserService;
import cn.org.angry.service.impl.UserServiceImpl;
import cn.org.angry.util.HandleRequestPathUtil;
import cn.org.angry.util.JSONUtil;
import cn.org.angry.util.ReadReader;

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
        HandleRequestPathUtil requestPathUtil = new HandleRequestPathUtil(this, req, resp);
        try {
            requestPathUtil.execute();
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
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
        PrintWriter writer = resp.getWriter();
        User user = JSONUtil.toObject(ReadReader.read(reader), User.class);
        Result result = userService.login(user);
        // 用户在线状态暂用session实现
        if(result.isOk()){
            User onlineUser = (User) result.getData();
            req.getSession().setAttribute("onlineUser", onlineUser);
        }

        // 响应结果
        writer.print(JSONUtil.toString(result));
        writer.close();
        reader.close();
    }
    // 注册
    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        PrintWriter writer = resp.getWriter();
        User user = JSONUtil.toObject(ReadReader.read(reader), User.class);
        Result result = userService.register(user);
        writer.print(JSONUtil.toString(result));
        writer.close();
        reader.close();
    }
    // 退出登陆
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
