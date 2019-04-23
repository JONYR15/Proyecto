package com.example.proyecto.model;

public class Products {

    private String key;
    private Integer id;
    private String description;
    private String image;
    private Integer quantity;
    private Double cost;
    private Double sale;

    public Products() {
    }

    public Products(Integer id, String description, String image, Integer quantity, Double cost, Double sale) {
        this.id = id;
        this.description = description;
        this.image = image;
        this.quantity = quantity;
        this.cost = cost;
        this.sale = sale;
    }

    public Products(String key, Integer id, String description, String image, Integer quantity, Double cost, Double sale) {
        this.key = key;
        this.id = id;
        this.description = description;
        this.image = image;
        this.quantity = quantity;
        this.cost = cost;
        this.sale = sale;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getSale() {
        return sale;
    }

    public void setSale(Double sale) {
        this.sale = sale;
    }

}
