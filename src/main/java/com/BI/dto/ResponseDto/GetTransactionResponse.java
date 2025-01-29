package com.BI.dto.ResponseDto;



public class GetTransactionResponse {
    private Double total;
    private Long idUser;
    private String month;

    public GetTransactionResponse() {

    }

    public GetTransactionResponse(Double total, Long idUser, String month) {
        this.total = total;
        this.idUser = idUser;
        this.month = month;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
