package cn.org.angry.dao;

import cn.org.angry.entity.Comment;

import java.util.List;

/**
 * 评论
 * 支持增删查，不允许修改
 */
public interface CommentDao {
    int addComment(Comment comment);

    /**
     * 删除评论
     * @param cid 评论id
     * @return
     */
    int deleteComment(int cid);
    /**
     * 根据文章id删除评论
     * @param pid 文章id
     * @return 删除的行数
     */
    int deleteCommentByPId(int pid);

    /**
     * 根据用户id删除评论
     * @param uid 用户id
     * @return 删除的行数
     */
    int deleteCommentByUId(int uid);

    /**
     * 根据文章id查询文章下的所有评论
     * @param pid 文章id
     * @param start 起始下标
     * @param count 查询的评论数量
     * @return 评论集合
     */
    List<Comment> queryCommentsByPid(int pid, int start, int count);

    /**
     * 根据用户id查询用户的所有评论
     * @param uid 用户id
     * @param start 起始下标
     * @param count 每页评论的数量
     * @return 评论集合
     */
    List<Comment> queryCommentByUid(int uid, int start, int count);


}
