package com.tj_JavaEE.strategy;

import com.tj_JavaEE.entity.User;

public interface ScoreStrategy {
    int calculateScore(User user, String operation);
} 