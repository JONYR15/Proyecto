package com.example.proyecto.Managed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto.R;
import com.example.proyecto.model.Details;
import com.example.proyecto.model.Products;

import static com.example.proyecto.Managed.InvoiceManaged.getInvoce;

public class DetailsManaged extends AppCompatActivity {

    private static Integer pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_managed);

        pos= getIntent().getIntExtra("pos",0);
    }

    public void confirmarProducto(View view) {
        getInvoce();
        InvoiceManaged.getInvoce().getDetailsList().get(pos).setQuantity(2);
    }

}
