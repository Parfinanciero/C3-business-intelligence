package com.BI.service;

import com.BI.dto.ResponseDto.BalanceSheetDto;
import com.BI.dto.ResponseDto.CashByCategoryResponse;
import com.BI.dto.ResponseDto.MetricResponseDto;

import java.util.List;


public interface IMetricService {
    MetricResponseDto calculateIncomeAndExpenseRatio(Integer idUser, String month);

    BalanceSheetDto balanceSheet(long id);

    List<CashByCategoryResponse> calculateExpensesByCategory(Integer id, String month);
}
