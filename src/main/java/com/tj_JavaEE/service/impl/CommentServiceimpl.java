package com.tj_JavaEE.service.impl;

import com.tj_JavaEE.dto.Commentcontent;
import com.tj_JavaEE.entity.Comment;
import com.tj_JavaEE.factory.CommentFactory;
import com.tj_JavaEE.mapper.CommentMapper;
import com.tj_JavaEE.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {
    
    @Autowired
    private CommentMapper commentMapper;
    
    @Autowired
    private CommentFactory commentFactory;
    
    @Override
    public void addComment(Commentcontent commentContent) {
        // 使用工厂创建评论对象
        Comment comment = commentFactory.createComment(commentContent);
        commentMapper.addComment(comment);
    }
    
    @Override
    public List<Commentcontent> getCommentsByPostId(long postId) {
        return commentMapper.getCommentsByPostId(postId);
    }
    
    @Override
    public void likeComment(long commentId) {
        commentMapper.likeComment(commentId);
    }
    
    @Override
    public void dislikeComment(long commentId) {
        commentMapper.dislikeComment(commentId);
    }
    
    @Override
    public void deleteComment(long commentId) {
        commentMapper.deleteComment(commentId);
    }
}
