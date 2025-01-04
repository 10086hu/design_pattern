package com.tj_JavaEE.decorator;

import com.tj_JavaEE.dto.Pst;
import java.util.Map;

public interface PostComponent {
    String getTitle();
    String getContent();
    Map<String, Object> getDisplayInfo();
    int getPriority(); // 用于排序
    boolean isVisible(); // 是否可见
} 