package com.BI.dto.ResponseDto;


public class CashResponseDto {
    private int userId;
    private Double totalExpenses;

    public CashResponseDto(int userId, Double totalExpenses) {
        this.userId = userId;
        this.totalExpenses = totalExpenses;
    }

    public CashResponseDto() {}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Double getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(Double totalExpenses) {
        this.totalExpenses = totalExpenses;
    }
}
