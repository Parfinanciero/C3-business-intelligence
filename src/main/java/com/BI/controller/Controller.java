package com.BI.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/business")
@Slf4j
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);


    //obtener gastos por mes de un usuario
    @Operation( summary = "Obtner ingresos por mes",
            description = "Aqui podras obtener los ingresos de un usuario por mes")
            @ApiResponses(value = {
                    @ApiResponse(responseCode = "201", description = "Income successfully retrieved"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    @GetMapping("/getIncome")
    public ResponseEntity<String> getIncomeByMonth() {
        String response = "Datos de ingresos";
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //obtener gastos por mes
    @Operation( summary = "Obtner gastos por mes",
            description = "Aqui podras obtener los gastos de un usuario por mes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Income successfully retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    }
    )
    @GetMapping("/expenses")
    public ResponseEntity<String> getIExpensesByMonth() {

        String response = "Datos de tus gastos";
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

     //obtener metas de un usuario

    @Operation( summary = "Obtner metas por usuario",
            description = "Aqui podras obtener resultados de metas, si han sido cumplidas o no")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Income successfully retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    }
    )
    @GetMapping("/goals")
    public ResponseEntity<String> getGoalsByUser() {
      logger.info("iniciado metodo");
        String response = "Metas cumplidas o no";
        logger.info("datos obtenidos con respuesta {}",response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
