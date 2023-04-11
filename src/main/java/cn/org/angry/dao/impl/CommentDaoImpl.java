package cn.org.angry.dao.impl;

import cn.org.angry.dao.CommentDao;
import cn.org.angry.entity.Comment;
import cn.org.angry.util.DBUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

public class CommentDaoImpl implements CommentDao {
    @Override
    public int addComment(Comment comment) {
        String sql = "insert into comments values (null, ?, ?, ?, ?)";
        return DBUtil.getInstance().update(sql,
                comment.getContent(),comment.getCreateTime(),comment.getUid(),comment.getPid());
    }

    @Override
    public int deleteComment(int cid) {
        String sql = "delete from comments where id=?";
        return DBUtil.getInstance().update(sql,cid);
    }

    @Override
    public List<Comment> queryCommentsByPid(int pid, int start, int count) {
        String sql = "select * from comments where pid=? limit ?,?";
        return DBUtil.getInstance().query(sql, new BeanPropertyRowMapper<>(Comment.class),pid,start,count);
    }

    @Override
    public List<Comment> queryCommentByUid(int uid, int start, int count) {
        String sql = "select * from comments where uid=? limit ?,?";
        return DBUtil.getInstance().query(sql, new BeanPropertyRowMapper<>(Comment.class),uid,start,count);
    }

    @Override
    public int deleteCommentByPId(int pid) {
        String sql = "delete from comments where pid=?";
        return DBUtil.getInstance().update(sql,pid);
    }

    @Override
    public int deleteCommentByUId(int uid) {
        String sql = "delete from comments where uid=?";
        return DBUtil.getInstance().update(sql,uid);
    }
}
