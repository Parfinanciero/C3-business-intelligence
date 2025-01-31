package com.BI.dto.ResponseDto;


public class CashResponseDto {
    private int userId;
    private Double totalCash;
    private String month;

    public CashResponseDto(int userId, Double totalExpenses, String month) {
        this.userId = userId;
        this.totalCash = totalExpenses;
        this.month = month;
    }

    public CashResponseDto() {}

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Double getTotalExpenses() {
        return totalCash;
    }

    public void setTotalExpenses(Double totalExpenses) {
        this.totalCash = totalExpenses;
    }
}
