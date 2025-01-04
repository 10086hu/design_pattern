package com.tj_JavaEE.service;

import com.tj_JavaEE.entity.User;
import com.tj_JavaEE.strategy.ScoreContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {
    @Autowired
    private ScoreContext scoreContext;
    
    @Autowired
    private UserService userService;
    
    public void processScore(String type, long userId, String operation) {
        User user = userService.getUserById(userId);
        if (user != null) {
            int scoreChange = scoreContext.calculateScore(type, user, operation);
            user.addScore(scoreChange);
            userService.updateUser(user);
        }
    }
} 