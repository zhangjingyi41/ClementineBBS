package cn.org.angry.service.impl;


import cn.org.angry.dao.PostDao;
import cn.org.angry.dao.UserDao;
import cn.org.angry.dao.impl.PostDaoImpl;
import cn.org.angry.dao.impl.UserDaoImpl;
import cn.org.angry.entity.Post;
import cn.org.angry.entity.Result;
import cn.org.angry.service.PostService;

import java.util.List;

public class PostServiceImpl implements PostService {
    private PostDao postDao = new PostDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    @Override
    public Result getPosts(int pageIndex, int count) {
        Result result = new Result();
        int start = (pageIndex - 1) * count;
        List<Post> posts = postDao.queryPosts(start, count);
        // 查询每个文章所属的用户信息
        for (Post post : posts) {
            post.setUser(userDao.queryUserById(post.getUid()));
        }
        result.setOk(true);
        result.setData(posts);
        return result;
    }

    @Override
    public Result getPostsByKeyWord(String keyWord, int pageIndex, int count) {
        Result result = new Result();
        int start = (pageIndex - 1) * count;
        List<Post> posts = postDao.queryPostsByKeyWord(keyWord, start, count);
        for (Post post : posts) {
            post.setUser(userDao.queryUserById(post.getUid()));
        }
        result.setOk(true);
        result.setData(posts);
        return result;
    }

    @Override
    public Result getPostById(int id) {
        Post post = postDao.queryPostById(id);
        Result result = new Result();
        if(post!=null){
            post.setUser(userDao.queryUserById(post.getUid()));
            result.setOk(true);
        }else {
            result.setOk(false);
        }
        result.setData(post);
        return result;
    }

    @Override
    public Result addPost(Post post) {
        Result result = new Result();
        result.setOk(postDao.addPost(post) > 0);
        return result;
    }

    @Override
    public Result deletePost(int id) {
        Result result = new Result();
        result.setOk(postDao.deletePost(id) > 0);
        return result;
    }
}
