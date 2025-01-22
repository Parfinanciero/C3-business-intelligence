package com.BI.service;

import com.BI.dto.ResponseDto.CalculateExpensesResponse;
import com.BI.dto.ResponseDto.Transactions;

import java.util.List;

public interface TransactionService {

    List<Transactions> getExpenses(int userIs);
    CalculateExpensesResponse calculateTotalExpenses(Integer id);

    CalculateExpensesResponse calculateTotalIncome(Integer id);
}
