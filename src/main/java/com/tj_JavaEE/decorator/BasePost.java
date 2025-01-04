package com.tj_JavaEE.decorator;

import com.tj_JavaEE.dto.Pst;
import java.util.HashMap;
import java.util.Map;

public class BasePost implements PostComponent {
    protected Pst post;
    
    public BasePost(Pst post) {
        this.post = post;
    }
    
    @Override
    public String getTitle() {
        return post.getTitle();
    }
    
    @Override
    public String getContent() {
        return post.getContent();
    }
    
    @Override
    public Map<String, Object> getDisplayInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("title", post.getTitle());
        info.put("content", post.getContent());
        info.put("authorId", post.getAuthorId());
        info.put("createTime", post.getCreateTime());
        return info;
    }
    
    @Override
    public int getPriority() {
        return 0; // 基础优先级
    }
    
    @Override
    public boolean isVisible() {
        return true; // 基础帖子默认可见
    }
} 