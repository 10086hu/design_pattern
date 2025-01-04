package com.tj_JavaEE.decorator;

import java.util.Map;

public class HotPostDecorator extends PostDecorator {
    private int heatValue;
    
    public HotPostDecorator(PostComponent post, int heatValue) {
        super(post);
        this.heatValue = heatValue;
    }
    
    @Override
    public String getTitle() {
        return "[热门] " + super.getTitle();
    }
    
    @Override
    public Map<String, Object> getDisplayInfo() {
        Map<String, Object> info = super.getDisplayInfo();
        info.put("isHot", true);
        info.put("heatValue", heatValue);
        return info;
    }
    
    @Override
    public int getPriority() {
        return super.getPriority() + 50; // 热门帖子优先级+50
    }
} 