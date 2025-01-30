package com.BI.controller;
import com.BI.dto.ResponseDto.CashResponseDto;
import com.BI.dto.ResponseDto.GetTransactionResponse;
import com.BI.dto.ResponseDto.MetricResponseDto;
import com.BI.service.IExpensesService;
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
public class ExpensesController {

    private final IExpensesService expensesService;

    @Autowired
    public ExpensesController (IExpensesService expensesService){
        this.expensesService = expensesService;
    }

    //metodo para obtener los gastos totales de un usuario por su id
    @Operation( summary = "Obtner gastos totales (Faker)",
            description = "Aqui podras obtener los gastos totalaes de un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Expenses successfully retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    }
    )
    @GetMapping("/gastos/{id}/{month}")
    public ResponseEntity<CashResponseDto> getTotalExpenses(@PathVariable int id , @PathVariable String month){
        CashResponseDto allAmount = this.expensesService.calculateTotalExpenses(id,month);
        return  ResponseEntity.status(HttpStatus.OK).body(allAmount);
    }


    @Operation(
            summary = "Calcula total de gastos apuntando a una api externa",
            description = "Este método calcula total gastos por categoria de un usuario, " +
                    "y devuelve el total.",
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
    //total gastos desde api externa
    @GetMapping("/total/{id}/{month}/gastos")
    public Mono<ResponseEntity<GetTransactionResponse>> totalExpensesUserExternalApi(Long id, String month){
        return this.expensesService.calculateTotalExpensesApi(id,month)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


}
