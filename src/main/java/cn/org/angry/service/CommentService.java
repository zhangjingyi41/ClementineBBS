package cn.org.angry.service;

import cn.org.angry.entity.Comment;
import cn.org.angry.entity.Result;
import cn.org.angry.entity.SubComment;

import java.util.List;

/**
 * 评论接口
 */
public interface CommentService {
    // 评论部分
    Result addComment(Comment comment);
    Result deleteComment(int id);
    Result getCommentsByPid(int pid, int pageIndex, int count);


    // 子评论部分
    Result addSubComment(SubComment subComment);
    Result deleteSubComment(int id);

    /**
     * 子评论数量可能也会到需要分页的程度，因此和评论查询分开做，单独请求
     * @param cid
     * @param pageIndex
     * @param count
     * @return
     */
    Result getSubCommentsByCId(int cid, int pageIndex, int count);
}
