package com.tj_JavaEE.strategy;

import com.tj_JavaEE.entity.User;
import org.springframework.stereotype.Component;

@Component
public class PostScoreStrategy implements ScoreStrategy {
    
    @Override
    public int calculateScore(User user, String operation) {
        // 发帖积分策略
        switch (operation) {
            case "create":
                return 10; // 发帖奖励10分
            case "delete":
                return -5; // 删帖扣5分
            case "like":
                return 2;  // 获得点赞奖励2分
            default:
                return 0;
        }
    }
} 