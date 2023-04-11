package cn.org.angry.dao.impl;


import cn.org.angry.dao.SubCommentDao;
import cn.org.angry.entity.SubComment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import cn.org.angry.util.DBUtil;

import java.util.List;

public class SubCommentDaoImpl implements SubCommentDao {
    @Override
    public int addSubComment(SubComment subComment) {
        String sql = "insert into subcomments values (null, ?, ?, ?, ?, ?)";
        return DBUtil.getInstance().update(sql,
                subComment.getContent(),subComment.getCreateTime(),subComment.getUid(),subComment.getCid(),subComment.getScid());
    }

    @Override
    public int deleteSubComment(int id) {
        String sql = "delete from subcomments where id=?";
        return DBUtil.getInstance().update(sql,id);
    }

    @Override
    public int deleteSubCommentByCId(int cid) {
        String sql = "delete from subcomments where cid=?";
        return DBUtil.getInstance().update(sql,cid);
    }

    @Override
    public int deleteSubCommentByUId(int uid) {
        String sql = "delete from subComments where uid=?";
        return DBUtil.getInstance().update(sql,uid);
    }

    @Override
    public List<SubComment> querySubCommentsByCId(int cid, int start, int count) {
        String sql = "select * from subcomments where cid=? limit ?,?";
        return DBUtil.getInstance().query(sql, new BeanPropertyRowMapper<>(SubComment.class),cid,start,count);
    }

    @Override
    public SubComment querySubCommentById(int id) {
        String sql = "select * from subcomments where id=?";
        try {
            return DBUtil.getInstance().queryForObject(sql, new BeanPropertyRowMapper<>(SubComment.class),id);
        } catch (DataAccessException e){
            return null;
        }
    }

    @Override
    public List<SubComment> querySubCommentsByUId(int uid, int start, int count) {
        String sql = "select * from subcomments where uid=? limit ?,?";
        return DBUtil.getInstance().query(sql, new BeanPropertyRowMapper<>(SubComment.class),uid,start,count);
    }
}
