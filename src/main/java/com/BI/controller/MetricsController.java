package com.BI.controller;

import com.BI.dto.ResponseDto.CashByCategoryResponse;
import com.BI.dto.ResponseDto.MetricResponseDto;
import com.BI.service.IMetricService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/finanzas/metricas")
public class MetricsController {


    private final IMetricService metricService;


    @Autowired
    public MetricsController (IMetricService metricService){
        this.metricService = metricService;
    }

    @GetMapping("balance-general/{id}")
    public ResponseEntity<?> balanceSheet(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(metricService.balanceSheet(id));
    }

    // metodo de income vs expenses
    //recibe id de usuario y el mes que se desea calcular
    @Operation(
            summary = "Calcula la relación de ingresos y gastos",
            description = "Este método calcula el balance entre ingresos y gastos de un usuario, " +
                    "y devuelve el estado financiero basado en la proporción.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Cálculo exitoso de la relación",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MetricResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Solicitud incorrecta debido a un ingreso o gasto cero",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @GetMapping("/balance/{id}/{month}")
    public  ResponseEntity<MetricResponseDto> metricIncomeVsExpenses(
            @PathVariable Integer id,
            @PathVariable String month){
        MetricResponseDto metricUser = this.metricService.calculateIncomeAndExpenseRatio(id,month);
        return  ResponseEntity.status(HttpStatus.OK).body(metricUser);
    }


    @Operation(
            summary = "Calcula porcentaje  gastos por categoria",
            description = "Este método calcula el balance  de gastos por categoria de un usuario, " +
                    "y devuelve el estado financiero basado en la proporción.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Cálculo exitoso de la relación",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MetricResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Solicitud incorrecta debido a  gasto cero",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @GetMapping("/balance/{id}/{month}/gastos")
    public ResponseEntity<List<CashByCategoryResponse>> ExpensesCategory(
            @PathVariable Integer id ,
            @PathVariable String month){
        List<CashByCategoryResponse> categoryExpenses = this.metricService.calculateExpensesByCategory(id,month);
       return ResponseEntity.status(HttpStatus.OK).body(categoryExpenses);
    }
}
