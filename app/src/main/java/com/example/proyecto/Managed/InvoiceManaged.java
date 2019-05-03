package com.example.proyecto.Managed;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.proyecto.CustomersActivity;
import com.example.proyecto.MainActivity;
import com.example.proyecto.R;
import com.example.proyecto.model.Customers;
import com.example.proyecto.model.Invoice;

import java.util.Calendar;
import java.util.Date;

public class InvoiceManaged extends AppCompatActivity {

    private static EditText editTCustomer;
    private EditText editTAuto;
    private EditText editTDate;
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
        editTDate = (EditText)findViewById(R.id.editTextDate);
        editTName = (EditText)findViewById(R.id.editTextName);
        btnInvoice = (Button)findViewById(R.id.buttonAddInvoice);
        selectCustomer = findViewById(R.id.selectCustomer);

        editTDate.setText("Fecha"+getCurrentDate());

        selectCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CustomersActivity.class);
                intent.putExtra("accion",2);
                v.getContext().startActivity(intent);
            }
        });

    }

    public Calendar getCurrentDate(){
        Calendar calendar = Calendar.getInstance();
        return calendar;
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

        invoce = new Invoice();
        invoce.setKeyCustomer(requetedCustomer.getKey());
    }



}
