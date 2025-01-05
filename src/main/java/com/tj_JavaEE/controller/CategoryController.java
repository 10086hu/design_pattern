package com.tj_JavaEE.controller;

import com.tj_JavaEE.entity.Category;
import com.tj_JavaEE.entity.Result;
import com.tj_JavaEE.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    
    @Autowired
    private CategoryServiceImpl categoryService;
    
    @PostMapping("/add")
    public Result addCategory(@RequestBody Category category) {
        // 使用解释器验证分类
        if (!categoryService.validateCategory(category)) {
            return Result.error("分类配置不符合要求");
        }
        
        // 获取显示名称
        String displayName = categoryService.getCategoryDisplayName(category);
        category.setDisplayName(displayName);
        
        // 其他处理逻辑...
        return Result.success();
    }
}
