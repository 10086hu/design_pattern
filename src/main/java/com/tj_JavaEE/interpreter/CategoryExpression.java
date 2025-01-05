package com.tj_JavaEE.interpreter;

import com.tj_JavaEE.entity.Category;

public interface CategoryExpression {
    boolean interpret(Category category);
} 