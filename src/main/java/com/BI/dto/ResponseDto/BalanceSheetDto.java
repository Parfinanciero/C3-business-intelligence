package com.BI.dto.ResponseDto;


public class BalanceSheetDto {

    private String income;
    private String expense;
    private String balanceSheet;

    public BalanceSheetDto(String income, String expense, String balanceSheet) {
        this.income = income;
        this.expense = expense;
        this.balanceSheet = balanceSheet;
    }


    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getExpense() {
        return expense;
    }

    public void setExpense(String expense) {
        this.expense = expense;
    }

    public String getBalanceSheet() {
        return balanceSheet;
    }

    public void setBalanceSheet(String balanceSheet) {
        this.balanceSheet = balanceSheet;
    }
}
