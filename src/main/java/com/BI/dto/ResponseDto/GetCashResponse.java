package com.BI.dto.ResponseDto;

import java.time.LocalDate;

public class GetCashResponse {

    private String title;
    private double amount;
    private Long userId;
    private Long categoryId;
    private LocalDate date;

    public GetCashResponse(String title, double amount, Long userId, Long categoryId, LocalDate date) {
        this.title = title;
        this.amount = amount;
        this.userId = userId;
        this.categoryId = categoryId;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
