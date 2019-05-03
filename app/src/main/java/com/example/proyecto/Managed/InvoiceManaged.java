package com.example.proyecto.Managed;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.proyecto.CustomersActivity;
import com.example.proyecto.R;

public class InvoiceManaged extends AppCompatActivity {

    private EditText editTCustomer;
    private EditText editTAuto;
    private EditText editTDate;
    private EditText editTName;
    private Button btnInvoice;
    private ImageButton selectCustomer;

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

        selectCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CustomersActivity.class);
                intent.putExtra("accion",2);
                v.getContext().startActivity(intent);
            }
        });

    }

    public void selectCustomer(){
        Intent intent = new Intent(this, CustomersActivity.class);
        startActivity(intent);
    }



}
