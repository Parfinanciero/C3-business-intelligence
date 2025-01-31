package com.BI;

import com.BI.controller.IncomeController;
import com.BI.dto.ResponseDto.CashResponseDto;
import com.BI.dto.ResponseDto.GetTransactionResponse;
import com.BI.service.IncomeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IncomeController.class)
public class incomeTestController {

    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private IncomeService incomeService;

    /**
     * Metodo para testear el metodo para obtener ingresos de un usuario
     * se construyen los datos de prueba
     * se construye la respuesta
     * Simula la llamada http y verifica la respuesta
     *
     * */
    @Test
   public void testGetIncome() throws Exception {
        // Datos de prueba
        int userId = 1;
        String month = "07"; // Julio
        double totalExpenses = 5000.00;

        CashResponseDto ingresosEsperados = new CashResponseDto();
        ingresosEsperados.setUserId(userId);
        ingresosEsperados.setTotalExpenses(totalExpenses);
        ingresosEsperados.setMonth(month);

        when(incomeService.calculateTotalIncome(userId, month)).thenReturn(ingresosEsperados);

        mockMvc.perform(get("/ingresos/{id}/{month}", userId, month))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userId))
                .andExpect(jsonPath("$.totalCash").value(totalExpenses))
                .andExpect(jsonPath("$.month").value(month));
    }

    /**
     * Metodo para testear el metodo para obtener gastos  de un usuario
     * se construyen los datos de prueba
     * se construye la respuesta
     * Simula la llamada http y verifica la respuesta
     *
     * */
    @Test
    public void testGetExpenses() throws Exception {
        int userId = 1;
        String month = "02";
        double totalExpenses = 5000.00;

        CashResponseDto expensesDto = new CashResponseDto();
        expensesDto.setUserId(userId);
        expensesDto.setMonth(month);
        expensesDto.setTotalExpenses(totalExpenses);
        when(incomeService.calculateTotalIncome(userId, month)).thenReturn(expensesDto);


        mockMvc.perform(get("/gastos/{id}/{month}", userId, month))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userId))
                .andExpect(jsonPath("$.totalCash").value(totalExpenses))
                .andExpect(jsonPath("$.month").value(month));
    }

    /**
     * Metodo para testear el metodo para obtener ingresos  de un usuario
     * a través de una api externa
     * se construyen los datos de prueba
     * se construye la respuesta
     * Simula la llamada http y verifica la respuesta
     *
     * */
    @Test
    public void testTotalIncomeExternalApi_NotFound() throws Exception {
        // Datos de prueba
        Long userId = 1L;
        String month = "2023-10";

        // Simula que el servicio devuelve un Mono vacío
        when(incomeService.calculateTotalIncomeApi(userId, month))
                .thenReturn(Mono.empty());

        // Simula la llamada HTTP y verifica que se devuelve un 404 Not Found
        mockMvc.perform(get("/total/{id}/{month}/ingresos", userId, month))
                .andExpect(status().isNotFound()); // Verifica que el status sea 404 Not Found
    }
}
