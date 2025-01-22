package com.BI.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/finanzas")
public class GoalsController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    //obtener metas de un usuario

    @Operation( summary = "Obtner metas por usuario",
            description = "Aqui podras obtener resultados de metas, si han sido cumplidas o no")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Goals successfully retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    }
    )
    @GetMapping("/metas")
    public ResponseEntity<String> getGoalsByUser() {
        logger.info("iniciado metodo");
        String response = "Metas cumplidas o no";
        logger.info("datos obtenidos con respuesta {}",response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
