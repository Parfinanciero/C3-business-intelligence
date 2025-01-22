package com.BI.controller;
import com.BI.dto.ResponseDto.CashResponseDto;
import com.BI.service.IExpensesService;
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
public class ExpensesController {

    private final IExpensesService expensesService;

    @Autowired
    public ExpensesController (IExpensesService expensesService){
        this.expensesService = expensesService;
    }

    //metodo para obtener los gastos totales de un usuario por su id
    @Operation( summary = "Obtner gastos totales",
            description = "Aqui podras obtener los gastos totalaes de un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Expenses successfully retrieved"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    }
    )
    @GetMapping("/gastos/{id}")
    public ResponseEntity<CashResponseDto> getTotalExpenses(@PathVariable int id){
        CashResponseDto allAmount = this.expensesService.calculateTotalExpenses(id);
        return  ResponseEntity.status(HttpStatus.OK).body(allAmount);
    }


}
