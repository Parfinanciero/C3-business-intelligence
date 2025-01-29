package com.BI.dto.ResponseDto;

import com.BI.Utils.Status;

import java.util.List;

public class GoalResponseDto {

    private String userId;
    private String title;
    private Double currentAmount;
    private Double goalAmount;
    private Status status;
    private List<SuggestionsDto> suggestionsUSer;
public GoalResponseDto() {}
    public GoalResponseDto(String userId, String title, Double currentAmount, Double goalAmount, Status status, List<SuggestionsDto> suggetionsUSer) {
        this.userId = userId;
        this.title = title;
        this.currentAmount = currentAmount;
        this.goalAmount = goalAmount;
        this.status = status;
        this.suggestionsUSer = suggetionsUSer;
    }

    public List<SuggestionsDto> getSuggestionsUSer() {
        return suggestionsUSer;
    }

    public void setSuggestionsUSer(List<SuggestionsDto> suggestionsUSer) {
        this.suggestionsUSer = suggestionsUSer;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(Double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public Double getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(Double goalAmount) {
        this.goalAmount = goalAmount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
