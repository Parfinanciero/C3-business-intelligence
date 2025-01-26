package com.BI.dto.ResponseDto;

public class ProspectiveResponseDto {

    private String month;
    private Double income;
    private Double expenses;
    private int userId;
    private Double prospectiveIncome;
    private Double prospectiveExpenses;
    private Double goalAttainment;
     private Double savingsProjected;
     private Double incomeExpenseRatio;

    public ProspectiveResponseDto() {
    }

    public ProspectiveResponseDto(String month, Double income, Double expenses, int userId, Double prospectiveIncome, Double prospectiveExpenses, Double goalAttainment,Double savingsProjected, Double incomeExpenseRation) {
        this.month = month;
        this.income = income;
        this.expenses = expenses;
        this.userId = userId;
        this.prospectiveIncome = prospectiveIncome;
        this.prospectiveExpenses = prospectiveExpenses;
        this.goalAttainment = goalAttainment;
        this.savingsProjected = savingsProjected;
        this.incomeExpenseRatio = incomeExpenseRation;

    }

    public Double getSavingsProjected() {
        return savingsProjected;
    }

    public void setSavingsProjected(Double savingsProjected) {
        this.savingsProjected = savingsProjected;
    }

    public Double getIncomeExpenseRatio() {
        return incomeExpenseRatio;
    }

    public void setIncomeExpenseRatio(Double incomeExpenseRatio) {
        this.incomeExpenseRatio = incomeExpenseRatio;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getExpenses() {
        return expenses;
    }

    public void setExpenses(Double expenses) {
        this.expenses = expenses;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Double getProspectiveIncome() {
        return prospectiveIncome;
    }

    public void setProspectiveIncome(Double prospectiveIncome) {
        this.prospectiveIncome = prospectiveIncome;
    }

    public Double getProspectiveExpenses() {
        return prospectiveExpenses;
    }

    public void setProspectiveExpenses(Double prospectiveExpenses) {
        this.prospectiveExpenses = prospectiveExpenses;
    }

    public Double getGoalAttainment() {
        return goalAttainment;
    }

    public void setGoalAttainment(Double goalAttainment) {
        this.goalAttainment = goalAttainment;
    }
}
