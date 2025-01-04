//todo
package com.tj_JavaEE.service.impl;

import com.tj_JavaEE.dto.AuditPostInfo;
import com.tj_JavaEE.dto.Pst;
import com.tj_JavaEE.mapper.PostMapper;
import com.tj_JavaEE.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private SearchServiceimpl searchServiceimpl;

    @Override
    public List<AuditPostInfo> auditPostInfoList() {
        return postMapper.auditPostInfoList();
    }

    @Override
    public void postStatusChange(Long postId, String status) {
        postMapper.postStatusChange(postId,status);
    }

    @Override
    public Pst getPostById(Long postId) { return postMapper.getPst(postId); }

    @Override
    public void createPost(Pst pst) {
        String userName = searchServiceimpl.searchUserName(pst.getAuthorId());
        pst.setAuthorName(userName);
        pst.setDislikes(0);
        pst.setLikes(0);
        pst.setAuthorAvatar(searchServiceimpl.searchUserAvatar(pst.getAuthorId()));
        postMapper.createPost(pst);


    }

    @Override
    public List<Pst> search(String keyword)
    {
        return searchServiceimpl.search(keyword);
    }

    @Override
    public void likePost(int userId, Long postId){
        int categoryId=postMapper.searchCategory(postId);

        postMapper.likePost(userId);
        postMapper.addlike(userId,categoryId);

    }

    @Override
    public void reportPost(Long postId,int userId){
        String rr="违规";
        Date dt=new Date();
        String date=dt.toString();


        postMapper.reportPost(postId,userId,rr,date);

    }

    @Override
    public void deletePost(Long postId){
        postMapper.deletePost(postId);
    }

    public Map<String, Object> getPostWithDecorators(Pst post) {
        // 创建基础帖子
        PostComponent basePost = new BasePost(post);
        
        // 根据条件添加装饰器
        if (isTopPost(post)) {
            basePost = new TopPostDecorator(basePost);
        }
        
        if (isHotPost(post)) {
            basePost = new HotPostDecorator(basePost, calculateHeatValue(post));
        }
        
        // 返回处理后的帖子信息
        return basePost.getDisplayInfo();
    }
    
    private boolean isTopPost(Pst post) {
        // 判断是否是置顶帖子的逻辑
        return post.getIsTop() != null && post.getIsTop();
    }
    
    private boolean isHotPost(Pst post) {
        // 判断是否是热门帖子的逻辑
        return calculateHeatValue(post) > 100;
    }
    
    private int calculateHeatValue(Pst post) {
        // 计算热度值的逻辑
        int heatValue = 0;
        heatValue += post.getLikeCount() * 2;
        heatValue += post.getCommentCount() * 3;
        heatValue += post.getViewCount();
        return heatValue;
    }
}
