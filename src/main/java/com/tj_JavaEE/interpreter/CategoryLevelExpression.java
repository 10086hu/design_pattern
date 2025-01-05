package com.tj_JavaEE.interpreter;

import com.tj_JavaEE.entity.Category;

public class CategoryLevelExpression implements CategoryExpression {
    private int minLevel;
    private int maxLevel;
    
    public CategoryLevelExpression(int minLevel, int maxLevel) {
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
    }
    
    @Override
    public boolean interpret(Category category) {
        int level = category.getLevel();
        return level >= minLevel && level <= maxLevel;
    }
} 