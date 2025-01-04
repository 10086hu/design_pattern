package com.tj_JavaEE.decorator;

import java.util.Map;

public abstract class PostDecorator implements PostComponent {
    protected PostComponent post;
    
    public PostDecorator(PostComponent post) {
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
        return post.getDisplayInfo();
    }
    
    @Override
    public int getPriority() {
        return post.getPriority();
    }
    
    @Override
    public boolean isVisible() {
        return post.isVisible();
    }
} 