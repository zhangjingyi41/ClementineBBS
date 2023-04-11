package cn.org.angry.service.impl;



import cn.org.angry.dao.CommentDao;
import cn.org.angry.dao.SubCommentDao;
import cn.org.angry.dao.UserDao;
import cn.org.angry.dao.impl.CommentDaoImpl;
import cn.org.angry.dao.impl.SubCommentDaoImpl;
import cn.org.angry.dao.impl.UserDaoImpl;
import cn.org.angry.entity.Comment;
import cn.org.angry.entity.SubComment;
import cn.org.angry.service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    private UserDao userDao = new UserDaoImpl();
    private CommentDao commentDao = new CommentDaoImpl();
    private SubCommentDao subCommentDao = new SubCommentDaoImpl();
    @Override
    public boolean addComment(Comment comment) {
        return commentDao.addComment(comment)>0;
    }

    @Override
    public boolean deleteComment(int id) {
        return commentDao.deleteComment(id)>0;
    }

    @Override
    public List<Comment> getCommentsByPid(int pid, int pageSize, int count) {
        List<Comment> comments = commentDao.queryCommentsByPid(pid, pageSize, count);
        for (Comment comment : comments) {
            comment.setUser(userDao.queryUserById(comment.getUid()));
        }
        return comments;
    }

    @Override
    public boolean addSubComment(SubComment subComment) {
        return subCommentDao.addSubComment(subComment) > 0;
    }

    @Override
    public boolean deleteSubComment(int id) {
        return subCommentDao.deleteSubComment(id) > 0;
    }

    @Override
    public List<SubComment> getSubCommentsByCId(int cid, int pageSize, int count) {
        List<SubComment> subComments = subCommentDao.querySubCommentsByCId(cid, pageSize, count);
        for (SubComment subComment : subComments) {
            subComment.setUser(userDao.queryUserById(subComment.getUid()));
        }
        return subComments;
    }
}
