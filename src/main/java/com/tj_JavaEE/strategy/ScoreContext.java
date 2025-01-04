package com.tj_JavaEE.strategy;

import com.tj_JavaEE.entity.User;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.HashMap;

@Component
public class ScoreContext {
    private final Map<String, ScoreStrategy> strategies;
    
    public ScoreContext(PostScoreStrategy postStrategy, 
                       CommentScoreStrategy commentStrategy) {
        strategies = new HashMap<>();
        strategies.put("post", postStrategy);
        strategies.put("comment", commentStrategy);
    }
    
    public int calculateScore(String type, User user, String operation) {
        ScoreStrategy strategy = strategies.get(type);
        if (strategy == null) {
            throw new IllegalArgumentException("Unknown score type: " + type);
        }
        return strategy.calculateScore(user, operation);
    }
} 