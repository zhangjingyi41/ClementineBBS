package cn.org.angry.service.impl;


import cn.org.angry.dao.PostDao;
import cn.org.angry.dao.UserDao;
import cn.org.angry.dao.impl.PostDaoImpl;
import cn.org.angry.dao.impl.UserDaoImpl;
import cn.org.angry.entity.Post;
import cn.org.angry.service.PostService;

import java.util.List;

public class PostServiceImpl implements PostService {
    private PostDao postDao = new PostDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    @Override
    public List<Post> getPosts(int pageIndex, int count) {
        List<Post> posts = postDao.queryPosts(pageIndex, count);
        // 查询每个文章所属的用户信息
        for (Post post : posts) {
            post.setUser(userDao.queryUserById(post.getUid()));
        }
        return posts;
    }

    @Override
    public List<Post> getPostsByKeyWord(String keyWord, int pageIndex, int count) {
        List<Post> posts = postDao.queryPostsByKeyWord(keyWord, pageIndex, count);
        for (Post post : posts) {
            post.setUser(userDao.queryUserById(post.getUid()));
        }
        return posts;
    }

    @Override
    public Post getPostById(int id) {
        Post post = postDao.queryPostById(id);
        post.setUser(userDao.queryUserById(post.getUid()));
        return post;
    }

    @Override
    public boolean addPost(Post post) {
        return postDao.addPost(post) > 0;
    }

    @Override
    public boolean deletePost(int id) {
        return postDao.deletePost(id) > 0;
    }
}
