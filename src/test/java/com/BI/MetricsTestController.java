package com.BI;

import com.BI.Utils.FinancialStatus;
import com.BI.dto.ResponseDto.BalanceSheetDto;
import com.BI.dto.ResponseDto.CashByCategoryResponse;
import com.BI.dto.ResponseDto.MetricResponseDto;
import com.BI.service.IMetricService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MetricsTestController.class)
public class MetricsTestController {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private IMetricService metricService;

    String income = "500";
     String expense = "200";
      String balanceSheet = "7000";

      BalanceSheetDto balanceResponse = new BalanceSheetDto(income,expense,balanceSheet);

    @Test
    public void  testGetGeneralMetrics() throws Exception{
        when(metricService.balanceSheet(anyInt())).thenReturn(balanceResponse);
        mockMvc.perform(get("/balance-general/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("500"))
                .andExpect(jsonPath("$").value("200"))
                .andExpect(jsonPath("$").value("700"));

    }

    @Test
    public  void testIncomeVsExpenses() throws Exception{

        Integer userId = 1;
       String month = "01";
       Double totalIncome = 12.000;
       Double totalExpense = 15.000;
       BigDecimal proportion = BigDecimal.valueOf(120.005000);
       FinancialStatus status = FinancialStatus.OVERSPENDING;

       when(metricService.calculateIncomeAndExpenseRatio(userId,month));
        mockMvc.perform(get("/balance/{id}/{month}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",userId).value(1))
                .andExpect(jsonPath("$",month).value("02"))
                .andExpect(jsonPath("$",totalIncome).value(12.000))
                .andExpect(jsonPath("$",totalExpense).value(10.000))
                .andExpect(jsonPath("$",proportion).value(12.000))
                .andExpect(jsonPath("$",status).value(FinancialStatus.BALANCED));


    }

    @Test
    public void testExpensesByCategory() throws Exception{
        Integer id = 1;
        String month = "02";
        String category = "Hi";
        Double percentage = 12.333;

        CashByCategoryResponse cashResponse = new CashByCategoryResponse(category,percentage);
        when(metricService.calculateExpensesByCategory(id,month));
        mockMvc.perform(get("/gastos-categoria/{id}/{month}",id,month))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",id).value(1))
                .andExpect(jsonPath("$",month).value("02"))
                .andExpect(jsonPath("$",month).value("02"));


    }
}
