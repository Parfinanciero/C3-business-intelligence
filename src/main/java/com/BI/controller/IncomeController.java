package com.BI.controller;

import com.BI.dto.ResponseDto.CashResponseDto;
import com.BI.dto.ResponseDto.GetTransactionResponse;
import com.BI.service.IncomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/finanzas")
public class IncomeController {

    private final IncomeService incomeService;

    @Autowired
    public IncomeController (IncomeService incomeService){
        this.incomeService = incomeService;
    }


//    metodo para obtener los gastos totales de un usuario por su id

    @Operation(
            summary = "Obtiene el total de ingresos de un usuario por mes (Faker)",
            description = "Este método simula una llamada a una API externa y genera aleatoriamente los ingresos " +
                    "de un usuario para un mes específico, usando Java Faker. Devuelve la suma total de ingresos.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Cálculo exitoso de la suma de ingresos para el usuario y mes especificados",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = CashResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Solicitud incorrecta, posiblemente debido a un formato de entrada no válido",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Usuario no encontrado o ingresos no disponibles para el mes solicitado",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor al procesar la solicitud",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )

    @GetMapping("/ingresos/{id}/{month}")
    public ResponseEntity<CashResponseDto> getIncome(@PathVariable  int id, @PathVariable String month){
        CashResponseDto allAmount = this.incomeService.calculateTotalIncome(id,month);
        return  ResponseEntity.status(HttpStatus.OK).body(allAmount);
    }



    @Operation(
            summary = "Obtiene el total de ingresos",
            description = "Este método realiza una petición a la API de ingresos y gastos, " +
                    "suma los valores de ingresos  de un usuario y devuelve el resultado " +
                    "como un balance financiero.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Cálculo exitoso de la suma de ingresos y gastos",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GetTransactionResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Solicitud incorrecta, posiblemente debido a valores de ingresos o gastos nulos",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se encontraron datos de ingresos o gastos para el usuario solicitado",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor al procesar la solicitud",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @GetMapping("/total/{id}/{month}/ingresos")
    public Mono<ResponseEntity<GetTransactionResponse>> totalIncomeExternalApi(@PathVariable Long id, @PathVariable String month){
        return this.incomeService.calculateTotalIncomeApi(id,month)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @Operation(
            summary = "Obtiene el total de ingresos",
            description = "Este método realiza una petición a la API de ingresos y gastos, " +
                    "suma los valores de ingresos  de un usuario y devuelve el resultado " +
                    "como un balance financiero.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Cálculo exitoso de la suma de ingresos y gastos",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GetTransactionResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Solicitud incorrecta, posiblemente debido a valores de ingresos o gastos nulos",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No se encontraron datos de ingresos o gastos para el usuario solicitado",
                            content = @Content(mediaType = "application/json")
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor al procesar la solicitud",
                            content = @Content(mediaType = "application/json")
                    )
            }
    )
    @GetMapping("/total/{id}/{month}/ingresos")
    public Mono<ResponseEntity<GetTransactionResponse>> totalIncomeExternalApi(@PathVariable Long id, @PathVariable String month){
        return this.incomeService.calculateTotalIncomeApi(id,month)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


}
