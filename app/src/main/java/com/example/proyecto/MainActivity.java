package com.example.proyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.proyecto.Activity.CustomersActivity;
import com.example.proyecto.Activity.InvoiceActivity;
import com.example.proyecto.Activity.ProductActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void customers(View view) {
        Intent intent = new Intent(view.getContext(), CustomersActivity.class);
        intent.putExtra("accion", 1);
        view.getContext().startActivity(intent);
        Sound sound = new Sound();
        sound.Cliente(view);
    }

    public void products(View view) {
        Intent intent = new Intent(view.getContext(), ProductActivity.class);
        view.getContext().startActivity(intent);
        Sound sound = new Sound();
        sound.Moneda(view);
    }

    public void invoices(View view) {
        Intent intent = new Intent(view.getContext(), InvoiceActivity.class);
        view.getContext().startActivity(intent);
        Sound sound = new Sound();
        sound.Producto(view);
    }
}
