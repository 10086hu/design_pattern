package com.tj_JavaEE.service.proxy;

import com.tj_JavaEE.service.ICommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommentServiceProxy implements ICommentService {
    private final ICommentService commentService;
    
    public CommentServiceProxy(ICommentService commentService) {
        this.commentService = commentService;
    }
    
    @Override
    public void addComment(Commentcontent comment) {
        log.info("开始添加评论: {}", comment);
        try {
            // 前置处理
            validateComment(comment);
            
            // 调用实际服务
            commentService.addComment(comment);
            
            // 后置处理
            log.info("评论添加成功");
        } catch (Exception e) {
            log.error("添加评论失败: {}", e.getMessage());
            throw e;
        }
    }
    
    @Override
    public List<Commentcontent> getCommentsByPostId(long postId) {
        log.info("获取帖子评论, postId: {}", postId);
        try {
            List<Commentcontent> comments = commentService.getCommentsByPostId(postId);
            log.info("成功获取{}条评论", comments.size());
            return comments;
        } catch (Exception e) {
            log.error("获取评论失败: {}", e.getMessage());
            throw e;
        }
    }
    
    @Override
    public void likeComment(long commentId) {
        log.info("点赞评论, commentId: {}", commentId);
        try {
            commentService.likeComment(commentId);
            log.info("点赞成功");
        } catch (Exception e) {
            log.error("点赞失败: {}", e.getMessage());
            throw e;
        }
    }
    
    private void validateComment(Commentcontent comment) {
        if (comment == null || comment.getContent() == null) {
            throw new IllegalArgumentException("评论内容不能为空");
        }
        // 其他验证逻辑...
    }
} 