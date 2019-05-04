package com.example.proyecto.Managed;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.proyecto.Activity.CustomersActivity;
import com.example.proyecto.Activity.ProductActivity;
import com.example.proyecto.Adapter.DetailsAdapter;
import com.example.proyecto.Adapter.ProductAdapter;
import com.example.proyecto.R;
import com.example.proyecto.References;
import com.example.proyecto.model.Customers;
import com.example.proyecto.model.Details;
import com.example.proyecto.model.Invoice;
import com.example.proyecto.model.Products;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class InvoiceManaged extends AppCompatActivity {

    private static final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference infoReference;


    private static EditText editTCustomer;
    private EditText editTAuto;
    private TextView viewDate;
    private EditText editTName;
    private Button btnInvoice;
    private ImageButton selectCustomer;
    private Button selectProduct;

    private static Customers requestedCustomer;
    private static Products requestedProduct;
    DatePickerDialog datePickerDialog;
    private static Invoice invoce;
    private RecyclerView recycler;
    private static android.support.v7.widget.RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_managed);

        editTCustomer = (EditText) findViewById(R.id.editTextCustomer);
        editTAuto = (EditText) findViewById(R.id.editTextAuto);
        viewDate = (TextView) findViewById(R.id.tvDate);
        editTName = (EditText) findViewById(R.id.editTextName);
        btnInvoice = (Button) findViewById(R.id.buttonAddInvoice);
        selectCustomer = findViewById(R.id.selectCustomer);
        selectProduct = findViewById(R.id.buttonAddInvoice);
        invoce = new Invoice();

        String date_etDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        viewDate.setText("Fecha: " + date_etDate);

        selectCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CustomersActivity.class);
                intent.putExtra("accion", 2);
                v.getContext().startActivity(intent);
            }
        });

        selectProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProductActivity.class);
                intent.putExtra("accion", 2);
                v.getContext().startActivity(intent);
            }
        });

        infoReference = FirebaseDatabase.getInstance().getReference().child(References.INFO_REFERENCE);

        DatabaseReference consecutive = infoReference.child(References.CONSECUTIVE_INVOICE_REFERENCE).child(References.CONSECUTIVE_INVOICE_REFERENCE);

        consecutive.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Integer consecutive = dataSnapshot.getValue(Integer.TYPE);
                        System.out.println("FuncionaChochinada"+consecutive);
                        editTAuto.setText(Integer.toString(consecutive+1));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        adapter = new DetailsAdapter(invoce.getDetailsList());
        recycler.setAdapter(adapter);

    }

    public static Invoice getInvoce() {
        return invoce;
    }

    public static void setInvoce(Invoice invoce) {
        InvoiceManaged.invoce = invoce;
    }

    public void selectCustomer() {
        Intent intent = new Intent(this, CustomersActivity.class);
        startActivity(intent);
    }

    public static void selectCustomer(String key) {
        for (Customers customer : CustomersActivity.getCustomers()) {
            if (customer.getKey().equals(key)) {
                requestedCustomer = customer;
            }
        }
        editTCustomer.setText(requestedCustomer.getName() + " " + requestedCustomer.getLastName());
        invoce.setKeyCustomer(requestedCustomer.getKey());

    }

    public static void selectProduct(String key){
        for (Products product :ProductActivity.getProducts()){
            if (product.getKey().equals(key)){
                requestedProduct= product;
            }
        }

        Details details = new Details();
        details.setDescription(requestedProduct.getDescription());
        details.setQuantity(1);
        details.setPriceUnitary(requestedProduct.getSale());
        details.setTotalPrice(1*requestedProduct.getSale());

        invoce.getDetailsList().add(details);
        adapter.notifyDataSetChanged();
    }

}
