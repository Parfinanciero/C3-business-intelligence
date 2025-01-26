package com.BI.controller;

import com.BI.dto.ResponseDto.CashResponseDto;
import com.BI.service.IncomeService;
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

@RestController
@RequestMapping("/api/finanzas")
public class IncomeController {

    private final IncomeService incomeService;

    @Autowired
    public IncomeController (IncomeService incomeService){
        this.incomeService = incomeService;
    }


//    metodo para obtener los gastos totales de un usuario por su id

    @Operation( summary = "Obtner Ingresos de un usuario",
            description = "Aqui podras obtener los ingresos de un usuario por mes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Expenses successfully retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    }
    )

    @GetMapping("/ingresos/{id}/{month}")
    public ResponseEntity<CashResponseDto> getIncome(@PathVariable  int id, @PathVariable String month){
        CashResponseDto allAmount = this.incomeService.calculateTotalIncome(id,month);
        return  ResponseEntity.status(HttpStatus.OK).body(allAmount);
    }



}
