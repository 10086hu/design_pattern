package com.tj_JavaEE.interpreter;

import com.tj_JavaEE.entity.Category;

public class CategoryStatusExpression implements CategoryExpression {
    private String status;
    
    public CategoryStatusExpression(String status) {
        this.status = status;
    }
    
    @Override
    public boolean interpret(Category category) {
        return status.equals(category.getStatus());
    }
} 