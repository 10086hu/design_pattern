package com.tj_JavaEE.interpreter;

import com.tj_JavaEE.entity.Category;

public class AndExpression implements CategoryExpression {
    private CategoryExpression expr1;
    private CategoryExpression expr2;
    
    public AndExpression(CategoryExpression expr1, CategoryExpression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }
    
    @Override
    public boolean interpret(Category category) {
        return expr1.interpret(category) && expr2.interpret(category);
    }
} 