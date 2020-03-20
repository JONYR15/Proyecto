package com.example.proyecto.Managed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto.Activity.CustomersActivity;
import com.example.proyecto.R;
import com.example.proyecto.References;
import com.example.proyecto.model.Customers;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerManaged extends AppCompatActivity {

    private static final FirebaseDatabase database = FirebaseDatabase.getInstance();

    private Button crear;
    private Button editar;
    private Button eliminar;

    private MenuItem mCrear;
    private MenuItem mEditar;
    private MenuItem mEliminar;

    private String key;

    private EditText id;
    private EditText name;
    private EditText lastName;
    private EditText numberPhone;
    private EditText email;
    private EditText user;
    private EditText pass;

    private Customers requestedCustomer;

    private DatabaseReference infoReference;

    private Integer accion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_managed);

        crear = findViewById(R.id.btCrear);
        editar = findViewById(R.id.btEditar);
        eliminar = findViewById(R.id.btEliminar);

        id = findViewById(R.id.etId);
        name = findViewById(R.id.etName);
        lastName = findViewById(R.id.etLastName);
        numberPhone = findViewById(R.id.etNumberPhone);
        email = findViewById(R.id.etEmail);
        user = findViewById(R.id.etUser);
        pass = findViewById(R.id.etPass);

        infoReference = database.getReference(References.INFO_REFERENCE);

        accion = getIntent().getIntExtra("accion", 1);

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

               // mCrear.setVisibility(Button.GONE);
               // mEditar.setVisibility(Button.VISIBLE);
               // mEliminar.setVisibility(Button.GONE);

                for (Customers customer : CustomersActivity.getCustomers()) {
                    if (customer.getKey().equals(getIntent().getStringExtra("key"))) {
                        requestedCustomer = customer;

                        id.setText(Integer.toString(customer.getId()));
                        name.setText(customer.getName());
                        lastName.setText(customer.getLastName());
                        numberPhone.setText(customer.getNumberPhone());
                        email.setText(customer.getEmail());
                        user.setText(customer.getUser());
                        pass.setText(customer.getPass());
                        break;
                    }
                }

                break;
            case 3:
                crear.setVisibility(Button.GONE);
                editar.setVisibility(Button.GONE);
                eliminar.setVisibility(Button.VISIBLE);

               //  mCrear.setVisibility(Button.GONE);
               // mEditar.setVisibility(Button.GONE);
               // mEliminar.setVisibility(Button.VISIBLE);

                for (Customers customer : CustomersActivity.getCustomers()) {
                    if (customer.getKey().equals(getIntent().getStringExtra("key"))) {
                        requestedCustomer = customer;

                        id.setText(Integer.toString(customer.getId()));
                        name.setText(customer.getName());
                        lastName.setText(customer.getLastName());
                        numberPhone.setText(customer.getNumberPhone());
                        email.setText(customer.getEmail());
                        user.setText(customer.getUser());
                        pass.setText(customer.getPass());
                        break;
                    }
                }

                id.setEnabled(false);
                name.setEnabled(false);
                lastName.setEnabled(false);
                numberPhone.setEnabled(false);
                email.setEnabled(false);
                user.setEnabled(false);
                pass.setEnabled(false);

                break;
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        mCrear = menu.findItem(R.id.miCreate);
        mEditar = menu.findItem(R.id.miEdit);
        mEliminar = menu.findItem(R.id.miDelete);
        switch (getIntent().getIntExtra("accion", 1)) {
            case 1:
                mCrear.setVisible(true);
                mEditar.setVisible(false);
                mEliminar.setVisible(false);
                break;
            case 2:
                mCrear.setVisible(false);
                mEditar.setVisible(true);
                mEliminar.setVisible(false);
                break;
            case 3:
                mCrear.setVisible(false);
                mEditar.setVisible(false);
                mEliminar.setVisible(true);
                break;
        }


        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.action_customer,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        switch (id) {
            case R.id.miCreate:
                item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        createCustomer(item.getActionView());
                        return false;
                    }
                });
                break;
            case R.id.miEdit:
                item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        editCustomer(item.getActionView());
                        return false;
                    }
                });
                break;
            case R.id.miDelete:
                item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        deleteCustomer(item.getActionView());
                        return false;
                    }
                });
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void createCustomer(View view) {
        if (!existe("create", null)) {
            int id = Integer.parseInt(((EditText) findViewById(R.id.etId)).getText().toString());
            String name = this.name.getText().toString();
            String lastName = this.lastName.getText().toString();
            String numberPhone = this.numberPhone.getText().toString();
            String email = this.email.getText().toString();
            String user = this.user.getText().toString();
            String pass = this.pass.getText().toString();
            Customers customer = new Customers(id, name, lastName, numberPhone, email, user, pass);
            infoReference.child(References.CLIENTES_REFERENCE).push().setValue(customer);

            limpiar();
            finish();
        } else {
            Toast.makeText(this, "El usuario ya existe.", Toast.LENGTH_SHORT).show();
        }

    }

    public void editCustomer(View view) {
        if (!existe("edit", requestedCustomer)) {
            int id = Integer.parseInt(((EditText) findViewById(R.id.etId)).getText().toString());
            String name = this.name.getText().toString();
            String lastName = this.lastName.getText().toString();
            String numberPhone = this.numberPhone.getText().toString();
            String email = this.email.getText().toString();
            String user = this.user.getText().toString();
            String pass = this.pass.getText().toString();
            Customers customer = new Customers(id, name, lastName, numberPhone, email, user, pass);
            infoReference.child(References.CLIENTES_REFERENCE).child(requestedCustomer.getKey()).setValue(customer);

            limpiar();
            finish();
        } else {
            Toast.makeText(this, "El usuario ya existe.", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteCustomer(View view) {
        infoReference.child(References.CLIENTES_REFERENCE).child(requestedCustomer.getKey()).removeValue();
        limpiar();
        finish();
    }

    public Boolean existe(String accion, Customers requestedCustomer) {
        Boolean existe = Boolean.FALSE;
        for (Customers customer : CustomersActivity.getCustomers()) {
            switch (accion) {
                case "create":
                    if (customer.getId() == Integer.parseInt(id.getText().toString())) {
                        existe = Boolean.TRUE;
                    }
                    break;
                case "edit":
                    if (customer.getId() == Integer.parseInt(id.getText().toString()) && !customer.getKey().equals(requestedCustomer.getKey())) {
                        existe = Boolean.TRUE;
                    }
                    break;
            }
        }
        return existe;
    }

    public void intentClientes(View view) {
        super.onRestart();
        Intent intent = new Intent(view.getContext(), CustomersActivity.class);  //your class
        startActivity(intent);
        finish();
    }

    public void limpiar() {
        id.setText("");
        name.setText("");
        lastName.setText("");
        numberPhone.setText("");
        email.setText("");
        user.setText("");
        pass.setText("");
    }

}
