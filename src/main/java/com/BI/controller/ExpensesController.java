package com.BI.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/finanzas")
public class ExpensesController {


    //obtener Gastos por mes
    @Operation( summary = "Obtner gastos por mes",
            description = "Aqui podras obtener los gastos de un usuario por mes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Expenses successfully retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    }
    )
    @GetMapping("/gastos")
    public ResponseEntity<String> getIExpensesByMonth() {

        String response = "Datos de tus gastos";
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
