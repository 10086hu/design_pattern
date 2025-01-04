package com.tj_JavaEE.decorator;

import java.util.Map;

public class TopPostDecorator extends PostDecorator {
    public TopPostDecorator(PostComponent post) {
        super(post);
    }
    
    @Override
    public String getTitle() {
        return "[置顶] " + super.getTitle();
    }
    
    @Override
    public Map<String, Object> getDisplayInfo() {
        Map<String, Object> info = super.getDisplayInfo();
        info.put("isTop", true);
        info.put("topTime", System.currentTimeMillis());
        return info;
    }
    
    @Override
    public int getPriority() {
        return super.getPriority() + 100; // 置顶帖子优先级+100
    }
} 