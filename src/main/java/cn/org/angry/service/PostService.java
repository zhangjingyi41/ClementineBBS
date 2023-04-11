package cn.org.angry.service;


import cn.org.angry.entity.Post;
import cn.org.angry.entity.Result;

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
    Result getPosts(int pageIndex, int count);

    /**
     * 通过关键词查询
     * @param keyWord
     * @param pageIndex
     * @param count
     * @return
     */
    Result getPostsByKeyWord(String keyWord, int pageIndex, int count);

    /**
     * 根据id查询文章
     * @param id
     * @return
     */
    Result getPostById(int id);

    Result addPost(Post post);
    Result deletePost(int id);

}
