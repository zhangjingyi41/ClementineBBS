package cn.org.angry.dao;

import cn.org.angry.entity.SubComment;

import java.util.List;

public interface SubCommentDao {
    int addSubComment(SubComment subComment);

    /**
     * 根据id删除子评论
     * @param id
     * @return
     */
    int deleteSubComment(int id);

    /**
     * 根据父评论id删除子评论
     * @param cid 父评论id
     * @return
     */
    int deleteSubCommentByCId(int cid);

    /**
     * 根据用户id删除子评论
     * @param uid 用户id
     * @return
     */
    int deleteSubCommentByUId(int uid);



    // 查

    /**
     * 根据父评论id查询子评论
     * @param cid 父评论id
     * @param start 起始下标
     * @param count 每页的数量
     * @return 子评论集合
     */
    List<SubComment> querySubCommentsByCId(int cid, int start, int count);

    /**
     * 根据子评论id查询
     * @param id 子评论id
     * @return 子评论对象
     */
    SubComment querySubCommentById(int id);

    /**
     * 根据用户id查询子评论
     * @param uid 用户id
     * @param start 起始下标
     * @param count 每页的数量
     * @return 子评论集合
     */
    List<SubComment> querySubCommentsByUId(int uid, int start, int count);
}
