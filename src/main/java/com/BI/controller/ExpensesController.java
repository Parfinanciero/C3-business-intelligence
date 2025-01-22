package com.BI.controller;

import com.BI.dto.ResponseDto.CalculateExpensesResponse;
import com.BI.dto.ResponseDto.Transactions;
import com.BI.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/finanzas")
public class ExpensesController {

    private final TransactionService transactionService;

    @Autowired
    public ExpensesController (TransactionService transactionService){
        this.transactionService = transactionService;
    }

    //metodo por
    @Operation( summary = "Obtner gastos por mes",
            description = "Aqui podras obtener los gastos de un usuario por mes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Expenses successfully retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    }
    )
    @GetMapping("/gatos/{id}")
    public ResponseEntity<List<Transactions>> expenses(@PathVariable int id){

        List<Transactions> transactionsUser = this.transactionService.getExpenses(id);
        return ResponseEntity.status(HttpStatus.OK).body(transactionsUser);

    }




    //metodo para obtener los gastos totales de un usuario por su id
    @Operation( summary = "Obtner gastos totales",
            description = "Aqui podras obtener los gastos totalaes de un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Expenses successfully retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    }
    )
    @GetMapping("total/{id}")
    public ResponseEntity<CalculateExpensesResponse> getTotalExpenses(int id){
        CalculateExpensesResponse allAmount = this.transactionService.calculateTotalExpenses(id);
        return  ResponseEntity.status(HttpStatus.OK).body(allAmount);
    }


}
