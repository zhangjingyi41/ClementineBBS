package cn.org.angry.dao.impl;


import cn.org.angry.dao.PostDao;
import cn.org.angry.entity.Post;
import cn.org.angry.util.DBUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

public class PostDaoImpl implements PostDao {

    @Override
    public int addPost(Post post) {
        String sql = "insert into posts values (null, ?, ?, ?, ?)";
        return DBUtil.getInstance().update(sql,
                post.getTitle(),post.getContent(),post.getCreateTime(),post.getUid());
    }

    @Override
    public int updatePost(Post post) {
        String sql = "update posts set title=?, content=? where uid=?";
        return DBUtil.getInstance().update(sql,
                post.getTitle(),post.getContent(),post.getUid());
    }

    @Override
    public int deletePost(int pid) {
        String sql = "delete from posts where id=?";
        return DBUtil.getInstance().update(sql,pid);
    }

    @Override
    public int deletePostByUid(int uid) {
        String sql = "delete from posts where uid=?";
        return DBUtil.getInstance().update(sql,uid);
    }

    @Override
    public List<Post> queryPosts(int start, int count) {
        String sql = "select * from posts limit ?,?";
        return DBUtil.getInstance().query(sql, new BeanPropertyRowMapper<>(Post.class),start,count);
    }

    @Override
    public List<Post> queryPostsByKeyWord(String keyWord, int start, int count) {
        keyWord = "%"+keyWord+"%";
        String sql = "select * from posts where title like ? or content like ? limit ?,?";
        return DBUtil.getInstance().query(sql, new BeanPropertyRowMapper<>(Post.class),keyWord,keyWord,start,count);
    }

    @Override
    public Post queryPostById(int id) {
        String sql = "select * from posts where id=?";
        try {
            return DBUtil.getInstance().queryForObject(sql, new BeanPropertyRowMapper<>(Post.class),id);
        } catch (DataAccessException e){
            return null;
        }
    }
}
