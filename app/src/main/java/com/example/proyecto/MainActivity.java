package com.example.proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void customers(View view){
        Intent intent = new Intent(view.getContext(), CustomersActivity.class);
        intent.putExtra("accion",1);
        view.getContext().startActivity(intent);
    }

    public void products(View view){
        Intent intent = new Intent(view.getContext(), ProductActivity.class);
        view.getContext().startActivity(intent);
    }

    public void invoices(View view){
        Intent intent = new Intent(view.getContext(), InvoiceActivity.class);
        view.getContext().startActivity(intent);
    }
}
