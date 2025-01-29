package com.BI.service;

import com.BI.dto.ResponseDto.CashResponseDto;
import com.BI.dto.ResponseDto.GetTransactionResponse;
import reactor.core.publisher.Mono;

public interface IExpensesService {
    CashResponseDto calculateTotalExpenses(Integer id,String month);

    Mono<GetTransactionResponse> calculateTotalExpensesApi(Long id, String month);
}
