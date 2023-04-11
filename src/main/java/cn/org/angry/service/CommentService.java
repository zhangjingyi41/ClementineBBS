package cn.org.angry.service;

import cn.org.angry.entity.Comment;
import cn.org.angry.entity.SubComment;

import java.util.List;

/**
 * 评论接口
 */
public interface CommentService {
    // 评论部分
    boolean addComment(Comment comment);
    boolean deleteComment(int id);
    List<Comment> getCommentsByPid(int pid, int pageSize, int count);


    // 子评论部分
    boolean addSubComment(SubComment subComment);
    boolean deleteSubComment(int id);

    /**
     * 子评论数量可能也会到需要分页的程度，因此和评论查询分开做，单独请求
     * @param cid
     * @param pageSize
     * @param count
     * @return
     */
    List<SubComment> getSubCommentsByCId(int cid, int pageSize, int count);
}
