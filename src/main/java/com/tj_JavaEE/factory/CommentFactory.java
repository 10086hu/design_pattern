package com.tj_JavaEE.factory;

import com.tj_JavaEE.dto.Commentcontent;
import com.tj_JavaEE.entity.Comment;

public interface CommentFactory {
    Comment createComment(Commentcontent commentContent);
} 