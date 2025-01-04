package com.tj_JavaEE.strategy;

import com.tj_JavaEE.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CommentScoreStrategy implements ScoreStrategy {
    
    @Override
    public int calculateScore(User user, String operation) {
        // 评论积分策略
        switch (operation) {
            case "create":
                return 5;  // 发表评论奖励5分
            case "delete":
                return -2; // 删除评论扣2分
            case "like":
                return 1;  // 评论获赞奖励1分
            default:
                return 0;
        }
    }
} 