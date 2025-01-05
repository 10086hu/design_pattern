package com.tj_JavaEE.controller;

import com.tj_JavaEE.dto.Commentcontent;
import com.tj_JavaEE.entity.Result;
import com.tj_JavaEE.service.ICommentService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tj_JavaEE.util.JwtUtils;
import com.tj_JavaEE.handler.CommentHandler;
import com.tj_JavaEE.handler.SensitiveWordHandler;
import com.tj_JavaEE.handler.ContentLengthHandler;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/comment")
public class CommentController {
    private final ICommentService commentService;
    
    @Autowired
    public CommentController(ICommentService commentService) {
        this.commentService = commentService; // 注入代理对象
    }

    private final CommentHandler handler;

    public CommentController() {
        // 构建责任链
        CommentHandler sensitiveWordHandler = new SensitiveWordHandler();
        CommentHandler contentLengthHandler = new ContentLengthHandler();
        
        // 设置处理顺序：敏感词检查 -> 内容长度检查
        sensitiveWordHandler.setNext(contentLengthHandler);
        this.handler = sensitiveWordHandler; // 保存责任链的起点
    }

    @PostMapping("/{postId}")
    public Result createComment(@PathVariable long postId, @RequestBody Commentcontent comment, 
                              @RequestHeader("Authorization") String token) {
        token = token.substring(7);
        Claims claim = JwtUtils.parseJwt(token);
        int userId = claim.get("userId", Integer.class);
        comment.setCommenterId(userId);
        comment.setPostId(postId);
        
        // 使用代理服务
        commentService.addComment(comment);
        return Result.success();
    }

    @GetMapping("/{postId}")
    public Result getComments(@PathVariable  long postId ) {


        return Result.success(commentService.getCommentsByPostId(postId));

    }

    @PostMapping("/{commentId}/like")
    public Result likeComment(@PathVariable long commentId) {
        commentService.likeComment(commentId);
        return Result.success();
    }

    @PostMapping("/{commentId}/dislike")
    public Result dislikeComment(@PathVariable long commentId) {
        commentService.dislikeComment(commentId);
        return Result.success();
    }

    @DeleteMapping("/{commentId}/")
    public Result deleteComment(@PathVariable long commentId) {
        commentService.deleteComment(commentId);
        return Result.success();
    }

    @PostMapping("/add")
    public Result addComment(@RequestBody Comment comment) {
        if (!handler.handle(comment.getContent())) {
            return Result.error("评论内容不合规");
        }
        
        // 其余处理逻辑...
        return Result.success();
    }
}
