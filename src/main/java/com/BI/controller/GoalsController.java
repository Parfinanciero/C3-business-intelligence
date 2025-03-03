package com.BI.controller;

import com.BI.dto.ResponseDto.GoalResponseDto;
import com.BI.service.IGoalsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/finanzas")
public class GoalsController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
     private final IGoalsService goalsService;

     public GoalsController(IGoalsService goalsService) {
         this.goalsService = goalsService;
     }
    //obtener metas de un usuario

    @Operation( summary = "Obtner metas por usuario(Faker)",
            description = "Aqui podras obtener resultados de metas, si han sido cumplidas o no")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Goals successfully retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    }
    )
    @GetMapping("/metas/{id}")
    public ResponseEntity<List<GoalResponseDto>> getGoalsByUser(@PathVariable String id) {
         List<GoalResponseDto> goal = this.goalsService.getUserGoalsByUserId(id);
        return ResponseEntity.status(HttpStatus.OK).body(goal);
    }
}
