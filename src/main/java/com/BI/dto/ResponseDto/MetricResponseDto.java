package com.BI.dto.ResponseDto;

import com.BI.Utils.FinancialStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public class MetricResponseDto {

    @Schema(description = "ID del usuario que está realizando el cálculo")
    private Integer userId;
    @Schema(description = "Mes para el cálculo de la relación de ingresos y gastos")

    private String month;
    @Schema(description = "Total de ingresos")

    private Double totalIncome;
    @Schema(description = "Total de gastos")

    private Double totalExpense;
    @Schema(description = "Proporción calculada entre ingresos y gastos")
    private BigDecimal proportion;
    @Schema(description = "Estado financiero del usuario basado en la proporción")
    private FinancialStatus status;

    public MetricResponseDto(Integer userId, String month, Double totalIncome, Double totalExpense, BigDecimal proportion, FinancialStatus status) {
        this.userId = userId;
        this.month = month;
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.proportion = proportion;
        this.status = status;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public Double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public BigDecimal getProportion() {
        return proportion;
    }

    public void setProportion(BigDecimal proportion) {
        this.proportion = proportion;
    }

    public FinancialStatus getStatus() {
        return status;
    }

    public void setStatus(FinancialStatus status) {
        this.status = status;
    }
}
