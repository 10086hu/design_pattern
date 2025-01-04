package com.tj_JavaEE.service;

import com.tj_JavaEE.dto.Commentcontent;
import java.util.List;

public interface ICommentService {
    void addComment(Commentcontent comment);
    List<Commentcontent> getCommentsByPostId(long postId);
    void likeComment(long commentId);
    void dislikeComment(long commentId);
    void deleteComment(long commentId);
} 