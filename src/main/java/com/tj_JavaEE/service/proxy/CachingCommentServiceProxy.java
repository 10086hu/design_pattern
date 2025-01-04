package com.tj_JavaEE.service.proxy;

import com.tj_JavaEE.service.ICommentService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CachingCommentServiceProxy implements ICommentService {
    private final ICommentService commentService;
    
    public CachingCommentServiceProxy(ICommentService commentService) {
        this.commentService = commentService;
    }
    
    @Override
    @Cacheable(value = "comments", key = "#postId")
    public List<Commentcontent> getCommentsByPostId(long postId) {
        return commentService.getCommentsByPostId(postId);
    }
    
    // 其他方法实现...
} 