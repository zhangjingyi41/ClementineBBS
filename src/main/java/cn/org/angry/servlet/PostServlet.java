package cn.org.angry.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PostServlet",urlPatterns = {
        // 查
        "/posts","/posts/comments",
        // 新增
        "/post/add", "/post/addComment", "/post/addSubComment",
        // 修改
        "/post/update",
        // 删除
        "/post/delete", "/post/deleteComment", "/post/deleteSubComment"
})
public class PostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
    // 查询文章
    public void posts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    // 查询文章下的评论
    public void comments(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    // 新增文章
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    // 新增评论
    public void addComment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    // 新增子评论
    public void addSubComment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    // 更新文章
    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    // 删除文章
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    // 删除评论
    public void deleteComment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
    // 删除子评论
    public void deleteSubComment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
