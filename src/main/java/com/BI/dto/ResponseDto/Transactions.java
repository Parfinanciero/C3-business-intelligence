package com.BI.dto.ResponseDto;

public class Transactions {


    private int id;
    private int idUser;
    private String title;
    private String category;
    private String date;
    private String type;
    private Double amount;

    public Transactions(int id, int idUser, String title, String category, String date, String type, Double amount) {
        this.id = id;
        this.idUser = idUser;
        this.title = title;
        this.category = category;
        this.date = date;
        this.type = type;
        this.amount = amount;
    }

    public Transactions() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
