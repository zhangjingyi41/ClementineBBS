package cn.org.angry.service;


import cn.org.angry.entity.Post;

import java.util.List;

/**
 * 文章接口
 */
public interface PostService {
    /**
     * 根据页码查询
     * @param pageIndex
     * @param count
     * @return
     */
    List<Post> getPosts(int pageIndex, int count);

    /**
     * 通过关键词查询
     * @param keyWord
     * @param pageIndex
     * @param count
     * @return
     */
    List<Post> getPostsByKeyWord(String keyWord, int pageIndex, int count);

    /**
     * 根据id查询文章
     * @param id
     * @return
     */
    Post getPostById(int id);

    boolean addPost(Post post);
    boolean deletePost(int id);

}
