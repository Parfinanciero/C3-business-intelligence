package com.BI.service.Impl;

import com.BI.Exceptions.Custom.CashApiExceptions;
import com.BI.Exceptions.Custom.InvalidRequestException;
import com.BI.dto.ResponseDto.CashResponseDto;
import com.BI.dto.ResponseDto.GetCashResponse;
import com.BI.dto.ResponseDto.GetTransactionResponse;
import com.BI.dto.ResponseDto.Transactions;
import com.BI.service.IncomeService;
import com.BI.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Service
public class IncomeServiceImpl implements IncomeService {

    private final ITransactionService transactionService;
    private final WebClient webClient;

    @Autowired
    public IncomeServiceImpl(ITransactionService transactionService, WebClient webClient){
        this.transactionService = transactionService;
        this.webClient  = webClient;
    }

    /** MEtodo que calcula el total de ingresos de un usuario con datos falsos
     * @param id  Id del usuario
     * @param  month Mes solicitado
     * @return CashResponseDto dto con el total de gastos el id del usuario y el mes
     * */

    @Override
    public CashResponseDto calculateTotalIncome(Integer id, String month) {

        if(id == null){
            throw new InvalidRequestException("el id no puede estar vacio");
        }
        List<Transactions> transactionsUser = transactionService.getTransactionByUserAndMonth(id,month);
        Double totalIncome = transactionsUser.stream()
                .filter(transactions -> "income".equals(transactions.getType()))
                .mapToDouble(Transactions::getAmount)
                .sum();

        return new CashResponseDto(id,totalIncome,month);

    }

    /** MEtodo que calcula el total de ingresos de un usuario haciendo una peticion a una api
     * @param id  Id del usuario
     * @param  month Mes solicitado
     * @return CashResponseDto dto con el total de gastos el id del usuario y el mes
     * */

    @Override
    public Mono<GetTransactionResponse> calculateTotalIncomeApi(Long id, String month) {

        return webClient.get()
                .uri("/income/{id}/{month}", id, month)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        response -> Mono.error(new CashApiExceptions("Datos no encontrados para ID " + id + " y mes " + month)))
                .onStatus(HttpStatusCode::is5xxServerError,
                        response -> {
                            return response.bodyToMono(String.class)
                                    .flatMap(body -> Mono.error(new CashApiExceptions("Error en el servidor externo: " + body)));
                        })
                .bodyToFlux(GetCashResponse.class)
                .map(GetCashResponse::getAmount)
                .filter(Objects::nonNull)
                .reduce(Double::sum)
                .map(total -> new GetTransactionResponse(total, id, month))
                .onErrorResume(WebClientRequestException.class, e -> {
                    String errorMessage = "Hubo un problema al intentar obtener los ingresos. Intenta más tarde.";
                    return Mono.error(new CashApiExceptions(errorMessage));
                });
    }


}
