package com.tj_JavaEE.service;

import com.tj_JavaEE.dto.AuditPostInfo;
import com.tj_JavaEE.dto.Pst;

import java.util.List;

public interface PostService {
    List<AuditPostInfo> auditPostInfoList();

    void postStatusChange(Long postId, String status);

    Pst getPostById(Long postId);

    void createPost(Pst pst);

    void likePost(int userId, Long postId);

    void reportPost(Long postId,int userId);

    void deletePost(Long postId);


    List<Pst> search(String keyword);


}
