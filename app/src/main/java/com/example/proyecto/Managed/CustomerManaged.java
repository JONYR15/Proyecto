package com.example.proyecto.Managed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.proyecto.R;
import com.example.proyecto.References;
import com.example.proyecto.model.Customers;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerManaged extends AppCompatActivity {

    private Button crear;
    private Button editar;
    private Button eliminar;

    private EditText id;
    private EditText name;
    private EditText lastName;
    private EditText numberPhone;
    private EditText email;
    private EditText user;
    private EditText pass;

    private DatabaseReference infoReference;

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

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        infoReference = database.getReference(References.INFO_REFERENCE);

        switch (getIntent().getIntExtra("accion", 0)) {
            case 1:
                crear.setVisibility(Button.VISIBLE);
                editar.setVisibility(Button.GONE);
                eliminar.setVisibility(Button.GONE);

            case 2:
                crear.setVisibility(Button.GONE);
                editar.setVisibility(Button.VISIBLE);
                eliminar.setVisibility(Button.GONE);


                break;
            case 3:
                crear.setVisibility(Button.GONE);
                editar.setVisibility(Button.GONE);
                eliminar.setVisibility(Button.VISIBLE);

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


    public void createCustomer(View view) {
        int id = Integer.parseInt(((EditText) findViewById(R.id.etId)).getText().toString());
        String name = this.name.getText().toString();
        String lastName = this.lastName.getText().toString();
        String numberPhone = this.numberPhone.getText().toString();
        String email = this.email.getText().toString();
        String user = this.user.getText().toString();
        String pass = this.pass.getText().toString();
        Customers customer = new Customers(id, name, lastName, numberPhone, email, user, pass);
        infoReference.child(References.CLIENTES_REFERENCE).push().setValue(customer);
    }

}
