package com.BI.service;

import com.BI.dto.ResponseDto.BalanceSheetDto;
import com.BI.dto.ResponseDto.MetricResponseDto;


public interface IMetricService {
    MetricResponseDto calculateIncomeAndExpenseRatio(Integer idUser, String month);
     BalanceSheetDto balanceSheet(long id);
}
