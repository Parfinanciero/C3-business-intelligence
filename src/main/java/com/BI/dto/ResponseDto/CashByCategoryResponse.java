package com.BI.dto.ResponseDto;

public class CashByCategoryResponse {
    private String category;
    private Double percentage;

    public CashByCategoryResponse(String category, Double percentage) {
        this.category = category;
        this.percentage = percentage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
