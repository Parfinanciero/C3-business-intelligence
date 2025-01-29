package com.BI.service;

import com.BI.dto.ResponseDto.GoalResponseDto;

import java.util.List;

public interface IGoalsService {
    List<GoalResponseDto> getUserGoalsByUserId(String userId);
    GoalResponseDto evaluateAndUpdateGoal(GoalResponseDto goal);
}
