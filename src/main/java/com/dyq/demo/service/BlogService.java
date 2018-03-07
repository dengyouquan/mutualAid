package com.dyq.demo.service;

import com.dyq.demo.domain.Blog;

public interface BlogService extends BaseModelService<Blog> {
    /**
     * 阅读量递增
     * @param id
     */
    void readingIncrease(Long id);

    /**
     * 发表评论
     * @param blogId
     * @param commentContent
     * @return
     */
    Blog createComment(Long blogId, String commentContent);

    /**
     * 删除评论
     * @param blogId
     * @param commentId
     * @return
     */
    void removeComment(Long blogId, Long commentId);

    //点赞
    Blog createVote(Long blogId);

    //取消点赞
    void removeVote(Long blogId, Long voteId);
}
