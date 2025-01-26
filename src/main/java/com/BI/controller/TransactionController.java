package com.BI.controller;
import com.BI.dto.ResponseDto.Transactions;
import com.BI.service.ITransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/financiera")
public class TransactionController {

    private final ITransactionService transactionService;

    public TransactionController(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Operation( summary = "Obtner Ingresos o gastos de  un usuario",
            description = "Obitiene ingresos o gastos de un usuario, con los datos de cada entrada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Expenses successfully retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    }
    )

    @GetMapping("/transacciones/{id}/{month}")
    public ResponseEntity<List<Transactions>> transactions(@PathVariable int id, @PathVariable String month){

        List<Transactions> transactionsUser = this.transactionService.getTransactionByUserAndMonth(id, month);
        return ResponseEntity.status(HttpStatus.OK).body(transactionsUser);

    }


}
