package cn.org.angry.servlet;

import cn.org.angry.entity.Post;
import cn.org.angry.entity.Result;
import cn.org.angry.service.PostService;
import cn.org.angry.service.impl.PostServiceImpl;
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

@WebServlet(name = "PostServlet",urlPatterns = {
        "/post/posts","/post/detail","/post/search",
        "/post/add",
        "/post/update",
        "/post/delete"
})
public class PostServlet extends HttpServlet {
    private PostService postService = new PostServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
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
    // 查询文章
    public void posts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        /*
        这里的数据结构应该是：
            {
                "pageIndex":1,
                "count":10
            }
         */
        Map<String, Object> map = JSONUtil.toMap(ReadReader.read(reader));
        Result result = postService.getPosts((Integer) map.get("pageIndex"), (Integer) map.get("count"));
        PrintWriter writer = response.getWriter();
        writer.print(JSONUtil.toString(result));
        writer.close();
        reader.close();
    }
    // 新增文章
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        PrintWriter writer = response.getWriter();
        /*
        这里请求的数据结构应该是
        {
            "id":0,
            "title":"",
            "content":"",
            "createTime":"2023-04-11 22:34:38",
            "uid":0,
            "user":null
        }
         */
        Post post = JSONUtil.toObject(ReadReader.read(reader), Post.class);
        Result result = postService.addPost(post);
        writer.print(JSONUtil.toString(result));
        writer.close();
        reader.close();
    }
    // 更新文章
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        PrintWriter writer = response.getWriter();
        Post post = JSONUtil.toObject(ReadReader.read(reader), Post.class);
        System.out.println(post);
//        更新业务方法暂未实现
        writer.close();
        reader.close();
    }
    // 删除文章
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        PrintWriter writer = response.getWriter();
        /*
            这里数据结构应该是
            {
                "pid":1
            }
         */
        Map<String, Object> map = JSONUtil.toMap(ReadReader.read(reader));
        Result result = postService.deletePost((Integer) map.get("pid"));
        writer.print(JSONUtil.toString(result));
        writer.close();
        reader.close();
    }
    // 文章详情
    public void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        PrintWriter writer = response.getWriter();
        /*
            这里数据结构应该是
            {
                "pid":1
            }
         */
        Map<String, Object> map = JSONUtil.toMap(ReadReader.read(reader));
        Result result = postService.getPostById((Integer) map.get("pid"));
        writer.print(JSONUtil.toString(result));
        writer.close();
        reader.close();
    }

    public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        PrintWriter writer = response.getWriter();
        /*
            {
                "keyWord":"",
                "pageIndex":1,
                "count":10
            }
         */
        Map<String, Object> map = JSONUtil.toMap(ReadReader.read(reader));
        Result result = postService.getPostsByKeyWord(String.valueOf(map.get("keyWord")), (Integer) map.get("pageIndex"), (Integer) map.get("count"));
        writer.print(JSONUtil.toString(result));
        writer.close();
        reader.close();
    }
}
