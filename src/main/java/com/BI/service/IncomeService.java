package com.BI.service;

import com.BI.dto.ResponseDto.CashResponseDto;
import com.BI.dto.ResponseDto.GetTransactionResponse;
import reactor.core.publisher.Mono;

public interface IncomeService {
 CashResponseDto calculateTotalIncome (Integer id, String month );
 Mono<GetTransactionResponse> calculateTotalIncomeApi(Long id, String month);



}
