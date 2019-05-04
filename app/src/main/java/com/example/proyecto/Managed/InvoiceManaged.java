package com.example.proyecto.Managed;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.proyecto.Activity.CustomersActivity;
import com.example.proyecto.R;
import com.example.proyecto.model.Customers;
import com.example.proyecto.model.Invoice;
import com.example.proyecto.model.Products;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class InvoiceManaged extends AppCompatActivity {

    private static EditText editTCustomer;
    private EditText editTAuto;
    private TextView viewDate;
    private EditText editTName;
    private Button btnInvoice;
    private ImageButton selectCustomer;

    private static Customers requetedCustomer;
    DatePickerDialog datePickerDialog;
    private static Invoice invoce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_managed);

        editTCustomer = (EditText)findViewById(R.id.editTextCustomer);
        editTAuto = (EditText)findViewById(R.id.editTextAuto);
        viewDate = (TextView) findViewById(R.id.tvDate);
        editTName = (EditText)findViewById(R.id.editTextName);
        btnInvoice = (Button)findViewById(R.id.buttonAddInvoice);
        selectCustomer = findViewById(R.id.selectCustomer);
        invoce = new Invoice();

        String date_etDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        viewDate.setText("Fecha: "+date_etDate);

        selectCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CustomersActivity.class);
                intent.putExtra("accion",2);
                v.getContext().startActivity(intent);
            }
        });

    }

    public static Invoice getInvoce() {
        return invoce;
    }

    public static void setInvoce(Invoice invoce) {
        InvoiceManaged.invoce = invoce;
    }

    public void selectCustomer(){
        Intent intent = new Intent(this, CustomersActivity.class);
        startActivity(intent);
    }

    public static void selectCustomer( String key){
        for (Customers customer:CustomersActivity.getCustomers()){
            if (customer.getKey().equals(key)){
                requetedCustomer= customer;
            }
        }
        editTCustomer.setText(requetedCustomer.getName() +" "+ requetedCustomer.getLastName());
        invoce.setKeyCustomer(requetedCustomer.getKey());

    }
    /*
    public void selectProduct(String key){
        for (Products :CustomersActivity.getCustomers()){
            if (customer.getKey().equals(key)){
                requetedCustomer= customer;
            }
        }
        editTCustomer.setText(requetedCustomer.getName() +" "+ requetedCustomer.getLastName());
        invoce.setKeyCustomer(requetedCustomer.getKey());
    }

    */

}
