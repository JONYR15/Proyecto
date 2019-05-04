package com.example.proyecto.model;

public class Details {

    private String key;
    private String description;
    private Integer quantity;
    private Double priceUnitary;
    private Double totalPrice;

    public Details() {
    }

    public Details(String key, String description, Integer quantity, Double priceUnitary, Double totalPrice) {
        this.key = key;
        this.description = description;
        this.quantity = quantity;
        this.priceUnitary = priceUnitary;
        this.totalPrice = totalPrice;
    }

    public Details(String description, Integer quantity, Double priceUnitary, Double totalPrice) {
        this.description = description;
        this.quantity = quantity;
        this.priceUnitary = priceUnitary;
        this.totalPrice = totalPrice;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPriceUnitary() {
        return priceUnitary;
    }

    public void setPriceUnitary(Double priceUnitary) {
        this.priceUnitary = priceUnitary;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
