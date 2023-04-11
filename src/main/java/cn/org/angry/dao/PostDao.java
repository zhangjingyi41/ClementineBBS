package cn.org.angry.dao;

import cn.org.angry.entity.Post;

import java.util.List;

public interface PostDao {
    int addPost(Post post);
    int updatePost(Post post);
    int deletePost(int pid);
    int deletePostByUid(int uid);

    /**
     * 查询一页文章
     * @param pageIndex 页码
     * @param count 每页文章数量
     * @return 文章集合
     */
    List<Post> queryPosts(int pageIndex, int count);

    /**
     * 根据关键词查询文章。（标题和文章内容的关键词）
     * @param keyWord 关键词
     * @param pageIndex 页码
     * @param count 每页文章数量
     * @return 文章集合
     */
    List<Post> queryPostsByKeyWord(String keyWord, int pageIndex, int count);

    /**
     * 根据文章id查询文章
     * @param id 文章id
     * @return 文章对象
     */
    Post queryPostById(int id);

}
