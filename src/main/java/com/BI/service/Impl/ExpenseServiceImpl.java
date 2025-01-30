package com.BI.service.Impl;

import com.BI.Exceptions.Custom.CashApiExceptions;
import com.BI.Exceptions.Custom.InvalidRequestException;
import com.BI.dto.ResponseDto.*;
import com.BI.service.IExpensesService;
import com.BI.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl  implements IExpensesService {


    private final ITransactionService transactionService;
    private final WebClient webClient;

    @Autowired
    public ExpenseServiceImpl (ITransactionService transactionService, WebClient webClient){
        this.transactionService = transactionService;
        this.webClient = webClient;
    }

    /** MEtodo para calcular el total de gastos generados por un usuario apartir de datos falsos
     * @param id Id del usuario
     * @param month mes solicitado
     * @return CashResponseDto devuelve un dto con id usuario, total y el mes
     * */
    public CashResponseDto calculateTotalExpenses(Integer id,String month) {

        if(id == null){
            throw new InvalidRequestException("el id no puede estar vacio");
        }

        List<Transactions> transactionsUser = this.transactionService.getTransactionByUserAndMonth(id, month);
        Double totalExpenses = transactionsUser.stream()
                .filter(transaction -> "expenses".equals(transaction.getType()))
                .mapToDouble(Transactions::getAmount)
                .sum();

        return  new CashResponseDto(id,totalExpenses,month);

    }


    /** MEtodo para calcular el total de gastos generados por un usuario apartir de datos de una api externa
     * @param id Id del usuario
     * @param month mes solicitado
     * @return GetTransactionResponse devuelve un dto con id usuario, total y el mes
     * */
    @Override
    public Mono<GetTransactionResponse> calculateTotalExpensesApi(Long id, String month) {
        return  webClient.get()
                .uri("/expenses/{id}/{month}",id,month)
                .retrieve()
                .bodyToFlux(GetCashResponse.class)
                .map(GetCashResponse::getAmount)
                .filter(Objects::nonNull)
                .map(Math::abs) // el valor absoluto sin contar con el signo menos en este caso
                .reduce(Double::sum)// hace la operacion de la suma de los datos
                 .switchIfEmpty(Mono.just(0.0))
                .map(total-> new GetTransactionResponse(total,id,month))// devolveremos un Dto con las respuesta
                .onErrorMap(e -> new CashApiExceptions("Error al calcular los gastos")); // creamos una clase de excepcion personalizada para devolver en json no tan largo

    }

}
