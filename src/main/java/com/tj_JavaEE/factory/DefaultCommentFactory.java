package com.tj_JavaEE.factory;

import com.tj_JavaEE.dto.Commentcontent;
import com.tj_JavaEE.entity.Comment;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DefaultCommentFactory implements CommentFactory {
    
    @Override
    public Comment createComment(Commentcontent commentContent) {
        Comment comment = new Comment();
        
        // 设置基本属性
        comment.setContent(commentContent.getContent());
        comment.setCommenterId((int) commentContent.getCommenterId());
        comment.setPostId(commentContent.getPostId());
        
        // 设置时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.parse(commentContent.getCommentTime(), formatter);
        comment.setCommentTime(time);
        
        // 设置默认值
        comment.setLikeCount(0);
        comment.setDislikeCount(0);
        
        return comment;
    }
} 