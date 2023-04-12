package cn.org.angry.servlet;

import cn.org.angry.entity.Comment;
import cn.org.angry.entity.Result;
import cn.org.angry.entity.SubComment;
import cn.org.angry.service.CommentService;
import cn.org.angry.service.impl.CommentServiceImpl;
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
import java.util.Map;

@WebServlet(urlPatterns = {
        // 全部评论（一页）     全部子评论（一页）
        "/comment/comments", "/comment/subComments",
        // 添加
        "/comment/add", "/comment/addSub",
        // 删除
        "/comment/del", "/comment/delSub"
})
public class CommentServlet extends HttpServlet {
    private CommentService commentService = new CommentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HandleRequestPathUtil requestPathUtil = new HandleRequestPathUtil(this, request, response);
        try {
            requestPathUtil.execute();
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public void comments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        PrintWriter writer = response.getWriter();
        Map<String, Object> map = JSONUtil.toMap(ReadReader.read(reader));
        Result result = commentService.getCommentsByPid((Integer) map.get("pid"), (Integer) map.get("pageIndex"), (Integer) map.get("count"));
        writer.print(JSONUtil.toString(result));
        writer.close();
        reader.close();
    }
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        PrintWriter writer = response.getWriter();
        Comment comment = JSONUtil.toObject(ReadReader.read(reader), Comment.class);
        Result result = commentService.addComment(comment);
        writer.print(JSONUtil.toString(result));
        writer.close();
        reader.close();
    }
    public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        PrintWriter writer = response.getWriter();
        Map<String, Object> map = JSONUtil.toMap(ReadReader.read(reader));
        Result result = commentService.deleteComment((Integer) map.get("cid"));
        writer.print(JSONUtil.toString(result));
        writer.close();
        reader.close();
    }
    public void subComments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        PrintWriter writer = response.getWriter();
        Map<String, Object> map = JSONUtil.toMap(ReadReader.read(reader));
        Result result = commentService.getSubCommentsByCId((Integer) map.get("cid"), (Integer) map.get("pageIndex"), (Integer) map.get("count"));
        writer.print(JSONUtil.toString(result));
        writer.close();
        reader.close();
    }
    public void addSub(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        PrintWriter writer = response.getWriter();
        SubComment subComment = JSONUtil.toObject(ReadReader.read(reader), SubComment.class);
        Result result = commentService.addSubComment(subComment);
        writer.print(JSONUtil.toString(result));
        writer.close();
        reader.close();
    }
    public void delSub(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        PrintWriter writer = response.getWriter();
        Map<String, Object> map = JSONUtil.toMap(ReadReader.read(reader));
        Result result = commentService.deleteSubComment((Integer) map.get("scid"));
        writer.print(JSONUtil.toString(result));
        writer.close();
        reader.close();
    }


}
