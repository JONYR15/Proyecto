package com.example.proyecto.model;

import java.util.Date;
import java.util.List;

public class Invoice {

    private String key;
    private Integer idInvoice;
    private Date date;
    private String nameCustomer;
    private Double subTotal;
    private Double iva;
    private Double total;
    private String employee;

    private List<Details> detailsList;

    public Invoice() {
    }

    public Invoice(String key, Integer idInvoice, Date date, String nameCustomer, Double subTotal, Double iva, Double total, String employee) {
        this.key = key;
        this.idInvoice = idInvoice;
        this.date = date;
        this.nameCustomer = nameCustomer;
        this.subTotal = subTotal;
        this.iva = iva;
        this.total = total;
        this.employee = employee;
    }

    public Invoice(Integer idInvoice, Date date, String nameCustomer, Double subTotal, Double iva, Double total, String employee) {
        this.idInvoice = idInvoice;
        this.date = date;
        this.nameCustomer = nameCustomer;
        this.subTotal = subTotal;
        this.iva = iva;
        this.total = total;
        this.employee = employee;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(Integer idInvoice) {
        this.idInvoice = idInvoice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public List<Details> getDetailsList() {
        return detailsList;
    }

    public void setDetailsList(List<Details> detailsList) {
        this.detailsList = detailsList;
    }
}
