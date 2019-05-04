package com.example.proyecto.model;

import android.net.Uri;

public class Products extends Details {

    private String key;
    private Integer id;
    private String imageProduct;
    private String imageUrl;
    private String description;
    private Integer quantity;
    private Double cost;
    private Double sale;
    private int action;

    public Products() {
    }

    public Products(Integer id, String imageProduct, String description, Integer quantity, Double cost, Double sale) {
        this.id = id;
        this.imageProduct = imageProduct;
        this.description = description;
        this.quantity = quantity;
        this.cost = cost;
        this.sale = sale;
    }

    public Products(Integer id, String description, Integer quantity, Double cost, Double sale) {
        this.id = id;
        this.description = description;
        this.quantity = quantity;
        this.cost = cost;
        this.sale = sale;
    }

    public Products(String key, Integer id, String imageProduct, String imageUrl, String description, Integer quantity, Double cost, Double sale) {
        this.key = key;
        this.id = id;
        this.imageProduct = imageProduct;
        this.imageUrl = imageUrl;
        this.description = description;
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

    public String getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }
}
