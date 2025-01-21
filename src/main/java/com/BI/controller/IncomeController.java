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
public class IncomeController {



    @Operation( summary = "Obtner Ingresos por mes",
            description = "Aqui podras obtener los ingresos de un usuario por mes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Expenses successfully retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    }
    )
    @GetMapping("/ingresos")
    public ResponseEntity<String> getIncomeByMonth() {
        String response = "Datos de ingresos";
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
