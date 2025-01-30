package com.BI.service;

import com.BI.dto.ResponseDto.CashByCategoryResponse;
import com.BI.dto.ResponseDto.CashResponseDto;
import com.BI.dto.ResponseDto.GetTransactionResponse;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IExpensesService {
    CashResponseDto calculateTotalExpenses(Integer id,String month);
    Mono<GetTransactionResponse> calculateTotalExpensesApi(Long id, String month);
}
