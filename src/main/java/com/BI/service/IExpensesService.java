package com.BI.service;

import com.BI.dto.ResponseDto.CashResponseDto;

public interface IExpensesService {
    CashResponseDto calculateTotalExpenses(Integer id);
}
