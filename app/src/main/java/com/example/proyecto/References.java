package com.example.proyecto;

public class References {
    public static final String INFO_REFERENCE="shop";
    public static final String CLIENTES_REFERENCE="customers";
    public static final String PRODUCTOS_REFERENCE="products";
    public static final String INVOICE_REFERENCE="invoice";
    public static final String CONSECUTIVE_INVOICE_REFERENCE="consecutive_invoice";
    public static final String IMAGES_PRODUCTS="products";


    public static String getURl(String fileName) {
        return "https://firebasestorage.googleapis.com/v0/b/lec09-80b6c.appspot.com/o/" + IMAGES_PRODUCTS + "%2F" + fileName + "?alt=media&token=b4b912a3-ed99-45a1-b8d6-8503f5e83cc8";
    }

}
