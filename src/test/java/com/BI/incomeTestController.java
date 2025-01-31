package com.BI;

import com.BI.controller.IncomeController;
import com.BI.dto.ResponseDto.CashResponseDto;
import com.BI.service.IncomeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

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

        Mockito.when(incomeService.calculateTotalIncome(userId, month)).thenReturn(ingresosEsperados);

        mockMvc.perform(get("/ingresos/{id}/{month}", userId, month))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userId))
                .andExpect(jsonPath("$.totalExpenses").value(totalExpenses))
                .andExpect(jsonPath("$.month").value(month));
    }

    @Test
    public void testGetExpenses() throws Exception {
        int userId = 1;
        String month = "02";
        double totalExpenses = 5000.00;

        CashResponseDto expensesDto = new CashResponseDto();
        expensesDto.setUserId(userId);
        expensesDto.setMonth(month);
        expensesDto.setTotalExpenses(totalExpenses);
        Mockito.when(incomeService.calculateTotalIncome(userId, month)).thenReturn(expensesDto);


        mockMvc.perform(get("/gastos/{id}/{month}", userId, month))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userId))
                .andExpect(jsonPath("$.totalExpenses").value(totalExpenses))
                .andExpect(jsonPath("$.month").value(month));
    }
}
