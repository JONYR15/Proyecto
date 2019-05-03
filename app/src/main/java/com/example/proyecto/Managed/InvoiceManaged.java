package com.example.proyecto.Managed;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.proyecto.CustomersActivity;
import com.example.proyecto.R;

public class InvoiceManaged extends AppCompatActivity {

    private EditText editTCustomer;
    private EditText editTAuto;
    private EditText editTDate;
    private EditText editTName;
    private Button btnInvoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_managed);

        editTCustomer = (EditText)findViewById(R.id.editTextCustomer);
        editTAuto = (EditText)findViewById(R.id.editTextAuto);
        editTDate = (EditText)findViewById(R.id.editTextDate);
        editTName = (EditText)findViewById(R.id.editTextName);
        btnInvoice = (Button)findViewById(R.id.buttonAddInvoice);
    }

    public void onEditTextClick(){
        Intent intent = new Intent(this, CustomersActivity.class);
        startActivity(intent);
    }

}
