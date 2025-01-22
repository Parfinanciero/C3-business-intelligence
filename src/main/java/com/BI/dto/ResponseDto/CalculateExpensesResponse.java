package com.BI.dto.ResponseDto;


public class CalculateExpensesResponse {
    private int userId;
    private Double totalExpenses;

    public CalculateExpensesResponse() {}
    public CalculateExpensesResponse(int userId, Double totalExpenses) {
        this.userId = userId;
        this.totalExpenses = totalExpenses;
    }

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
