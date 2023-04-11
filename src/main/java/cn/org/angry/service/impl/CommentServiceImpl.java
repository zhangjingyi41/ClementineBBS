package cn.org.angry.service.impl;



import cn.org.angry.dao.CommentDao;
import cn.org.angry.dao.SubCommentDao;
import cn.org.angry.dao.UserDao;
import cn.org.angry.dao.impl.CommentDaoImpl;
import cn.org.angry.dao.impl.SubCommentDaoImpl;
import cn.org.angry.dao.impl.UserDaoImpl;
import cn.org.angry.entity.Comment;
import cn.org.angry.entity.Result;
import cn.org.angry.entity.SubComment;
import cn.org.angry.service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    private UserDao userDao = new UserDaoImpl();
    private CommentDao commentDao = new CommentDaoImpl();
    private SubCommentDao subCommentDao = new SubCommentDaoImpl();
    @Override
    public Result addComment(Comment comment) {
        Result result = new Result();
        result.setOk(commentDao.addComment(comment)>0);
        return result;
    }

    @Override
    public Result deleteComment(int id) {
        Result result = new Result();
        result.setOk(commentDao.deleteComment(id)>0);
        return result;
    }

    @Override
    public Result getCommentsByPid(int pid, int pageIndex, int count) {
        Result result = new Result();
        int start = (pageIndex - 1) * count;
        List<Comment> comments = commentDao.queryCommentsByPid(pid, start, count);
        for (Comment comment : comments) {
            // 当前评论所属的用户信息
            comment.setUser(userDao.queryUserById(comment.getUid()));
        }
        result.setOk(true);
        result.setData(comments);
        return result;
    }

    @Override
    public Result addSubComment(SubComment subComment) {
        Result result = new Result();
        result.setOk(subCommentDao.addSubComment(subComment) > 0);
        return result;
    }

    @Override
    public Result deleteSubComment(int id) {
        Result result = new Result();
        result.setOk(subCommentDao.deleteSubComment(id) > 0);
        return result;
    }

    @Override
    public Result getSubCommentsByCId(int cid, int pageIndex, int count) {
        Result result = new Result();
        int start = (pageIndex - 1) * count;
        List<SubComment> subComments = subCommentDao.querySubCommentsByCId(cid, start, count);
        for (SubComment subComment : subComments) {
            subComment.setUser(userDao.queryUserById(subComment.getUid()));
        }
        result.setOk(true);
        result.setData(subComments);
        return result;
    }
}
