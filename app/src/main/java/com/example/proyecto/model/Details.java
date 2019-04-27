package com.example.proyecto.model;

public class Details {

    private String key;
    private Integer id;
    private String description;
    private Integer quantity;
    private Double priceUnitary;
    private Double totalPrice;

    public Details() {
    }

    public Details(String key, Integer id, String description, Integer quantity, Double priceUnitary, Double totalPrice) {
        this.key = key;
        this.id = id;
        this.description = description;
        this.quantity = quantity;
        this.priceUnitary = priceUnitary;
        this.totalPrice = totalPrice;
    }

    public Details(Integer id, String description, Integer quantity, Double priceUnitary, Double totalPrice) {
        this.id = id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
