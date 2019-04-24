package com.example.proyecto.Managed;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto.CustomersActivity;
import com.example.proyecto.ProductActivity;
import com.example.proyecto.R;
import com.example.proyecto.References;
import com.example.proyecto.model.Customers;
import com.example.proyecto.model.Products;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProductManaged extends AppCompatActivity {

    private static final FirebaseDatabase database = FirebaseDatabase.getInstance();

    private Button crear;
    private Button editar;
    private Button eliminar;

    private String key;
    private EditText idProduct;
    private EditText description;
    private EditText quantity;
    private EditText cost;
    private EditText sale;

    private Products requestedProduct;
    private DatabaseReference infoReference;
    private DatabaseReference infoReferenceProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_managed);

        crear = findViewById(R.id.btCrear);
        editar = findViewById(R.id.btEditar);
        eliminar = findViewById(R.id.btEliminar);

        idProduct = findViewById(R.id.etIdProducto);
        description = findViewById(R.id.etDescription);
        quantity = findViewById(R.id.etQuantity);
        cost = findViewById(R.id.etCost);
        sale = findViewById(R.id.etSale);

        infoReference = database.getReference(References.INFO_REFERENCE);

        switch (getIntent().getIntExtra("accion", 1)) {
            case 1:
                crear.setVisibility(Button.VISIBLE);
                editar.setVisibility(Button.GONE);
                eliminar.setVisibility(Button.GONE);

                break;
            case 2:
                crear.setVisibility(Button.GONE);
                editar.setVisibility(Button.VISIBLE);
                eliminar.setVisibility(Button.GONE);

                for (Products product : ProductActivity.getProducts()) {
                    if (product.getKey().equals(getIntent().getStringExtra("key"))) {
                        requestedProduct = product;

                        idProduct.setText(Integer.toString(product.getId()));
                        description.setText(product.getDescription());
                        quantity.setText(Integer.toString(product.getQuantity()));
                        cost.setText(Double.toString(product.getCost()));
                        sale.setText(Double.toString(product.getSale()));
                        break;
                    }
                }

                break;
            case 3:
                crear.setVisibility(Button.GONE);
                editar.setVisibility(Button.GONE);
                eliminar.setVisibility(Button.VISIBLE);

                for (Products product : ProductActivity.getProducts()) {
                    if (product.getKey().equals(getIntent().getStringExtra("key"))) {
                        requestedProduct = product;

                        idProduct.setText(Integer.toString(product.getId()));
                        description.setText(product.getDescription());
                        quantity.setText(Integer.toString(product.getQuantity()));
                        cost.setText(Double.toString(product.getCost()));
                        sale.setText(Double.toString(product.getSale()));
                        break;
                    }
                }

                idProduct.setEnabled(false);
                description.setEnabled(false);
                quantity.setEnabled(false);
                cost.setEnabled(false);
                sale.setEnabled(false);
                break;
        }
    }


    public void createProduct(View view) {
        if (!existe("create", null)) {
            int idProducto = Integer.parseInt(((EditText) findViewById(R.id.etIdProducto)).getText().toString());
            String description = this.description.getText().toString();
            int quantity  = Integer.parseInt(((EditText)findViewById(R.id.etQuantity)).getText().toString());
            Double cost = Double.parseDouble(((EditText)findViewById(R.id.etCost)).getText().toString());
            Double sale = Double.parseDouble(((EditText)findViewById(R.id.etSale)).getText().toString());
            Products product = new Products(idProducto, description, quantity, cost, sale);
            infoReference.child(References.PRODUCTOS_REFERENCE).push().setValue(product);
            limpiar();
            finish();
        } else {
            Toast.makeText(this, "El producto ya existe.", Toast.LENGTH_SHORT).show();
        }

    }

    public void editProduct(View view) {
        if (!existe("edit", requestedProduct)) {
            int idProducto = Integer.parseInt(((EditText) findViewById(R.id.etIdProducto)).getText().toString());
            String description = this.description.getText().toString();
            int quantity  = Integer.parseInt(((EditText)findViewById(R.id.etQuantity)).getText().toString());
            Double cost = Double.parseDouble(((EditText)findViewById(R.id.etCost)).getText().toString());
            Double sale = Double.parseDouble(((EditText)findViewById(R.id.etSale)).getText().toString());
            Products product = new Products(idProducto, description, quantity, cost, sale);
            infoReference.child(References.PRODUCTOS_REFERENCE).child(requestedProduct.getKey()).setValue(product);
            limpiar();
            finish();
        } else {
            Toast.makeText(this, "El producto ya existe.", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteProduct(View view) {
        infoReference.child(References.PRODUCTOS_REFERENCE).child(requestedProduct.getKey()).removeValue();
        limpiar();
        finish();
    }

    public Boolean existe(String accion, Products requestedProduct) {
        Boolean existe = Boolean.FALSE;
        for (Products product: ProductActivity.getProducts()) {
            switch (accion) {
                case "create":
                    if (product.getId() == Integer.parseInt(idProduct.getText().toString())) {
                        existe = Boolean.TRUE;
                    }
                    break;
                case "edit":
                    if (product.getId() == Integer.parseInt(idProduct.getText().toString()) && !product.getKey().equals(requestedProduct.getKey())) {
                        existe = Boolean.TRUE;
                    }
                    break;
            }
        }
        return existe;
    }

    public void intentProductos(View view) {
        super.onRestart();
        Intent intent = new Intent(view.getContext(), ProductActivity.class);  //your class
        startActivity(intent);
        finish();
    }

    public void limpiar() {
        idProduct.setText("");
        description.setText("");
        quantity.setText("");
        cost.setText("");
        sale.setText("");
    }

}
