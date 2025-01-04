package com.tj_JavaEE.config;

import com.tj_JavaEE.service.ICommentService;
import com.tj_JavaEE.service.proxy.CommentServiceProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ServiceConfig {
    
    @Bean
    @Primary
    public ICommentService commentServiceProxy(ICommentService commentService) {
        return new CommentServiceProxy(commentService);
    }
} 