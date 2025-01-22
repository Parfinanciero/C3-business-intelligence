package com.BI.controller;

import com.BI.dto.ResponseDto.CalculateExpensesResponse;
import com.BI.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/finanzas")
public class IncomeController {

    private final TransactionService transactionService;

    @Autowired
    public IncomeController (TransactionService transactionService){
        this.transactionService = transactionService;
    }


    @Operation( summary = "Obtner Ingresos por mes",
            description = "Aqui podras obtener los ingresos de un usuario por mes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Expenses successfully retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    }
    )



    //metodo para obtener los gastos totales de un usuario por su id

    @GetMapping("total/{id}")
    public ResponseEntity<CalculateExpensesResponse> getExpensesByUser(int id){
        CalculateExpensesResponse allAmount = this.transactionService.calculateTotalIncome(id);
        return  ResponseEntity.status(HttpStatus.OK).body(allAmount);
    }

}
