package com.BI.dto.ResponseDto;


public class BalanceSheetDto {

    private Double income;
    private Double expense;
    private Double balanceSheet;

    public BalanceSheetDto(Double income, Double expense, Double balanceSheet) {
        this.income = income;
        this.expense = expense;
        this.balanceSheet = balanceSheet;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }

    public Double getBalanceSheet() {
        return balanceSheet;
    }

    public void setBalanceSheet(Double balanceSheet) {
        this.balanceSheet = balanceSheet;
    }
}
