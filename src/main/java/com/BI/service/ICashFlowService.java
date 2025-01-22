package com.BI.service;

import com.BI.dto.ResponseDto.UserExpensesResponse;

public interface ICashFlowService {

    UserExpensesResponse getExpenses (Long userId);
}
