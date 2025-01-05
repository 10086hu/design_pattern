package com.tj_JavaEE.service.impl;

import com.tj_JavaEE.entity.Category;
import com.tj_JavaEE.interpreter.*;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl {
    
    public boolean validateCategory(Category category) {
        // 创建表达式
        CategoryExpression levelExpr = new CategoryLevelExpression(1, 3);
        CategoryExpression statusExpr = new CategoryStatusExpression("active");
        
        // 组合表达式
        CategoryExpression finalExpr = new AndExpression(levelExpr, statusExpr);
        
        // 解释执行
        return finalExpr.interpret(category);
    }
    
    public String getCategoryDisplayName(Category category) {
        StringBuilder displayName = new StringBuilder();
        
        // 根据级别添加前缀
        if (category.getLevel() == 1) {
            displayName.append("[主分类]");
        } else if (category.getLevel() == 2) {
            displayName.append("[子分类]");
        }
        
        displayName.append(category.getName());
        
        // 根据状态添加后缀
        if ("inactive".equals(category.getStatus())) {
            displayName.append("(已禁用)");
        }
        
        return displayName.toString();
    }
}

