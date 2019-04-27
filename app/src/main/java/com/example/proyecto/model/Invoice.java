package com.example.proyecto.model;

import java.util.Date;

public class Invoice {

    private String key;
    private Integer idInvoice;
    private Date date;
    private String nameCustomer;
    private Double subTotal;
    private Double iva;
    private Double total;
    private String employee;

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
}
