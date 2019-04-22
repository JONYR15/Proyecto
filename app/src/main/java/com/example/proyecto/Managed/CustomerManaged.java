package com.example.proyecto.Managed;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto.R;
import com.example.proyecto.References;
import com.example.proyecto.model.Customers;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CustomerManaged extends AppCompatActivity {

    private static List<Customers> customers = new ArrayList<>();

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
        if (existe()) {
            int id = Integer.parseInt(((EditText) findViewById(R.id.etId)).getText().toString());
            String name = this.name.getText().toString();
            String lastName = this.lastName.getText().toString();
            String numberPhone = this.numberPhone.getText().toString();
            String email = this.email.getText().toString();
            String user = this.user.getText().toString();
            String pass = this.pass.getText().toString();
            Customers customer = new Customers(id, name, lastName, numberPhone, email, user, pass);
            infoReference.child(References.CLIENTES_REFERENCE).push().setValue(customer);
        }else{
            Toast.makeText(this, "El usuario ya existe.", Toast.LENGTH_SHORT).show();
        }

    }

    public Boolean existe() {
        Boolean existe = Boolean.FALSE;
        infoReference = FirebaseDatabase.getInstance().getReference().child(References.INFO_REFERENCE).child(References.CLIENTES_REFERENCE);
        infoReference.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        System.out.println(dataSnapshot.getChildrenCount());
                        Log.w("TodoApp", "getUser:onCancelled " + dataSnapshot.toString());
                        Log.w("TodoApp", "count = " + String.valueOf(dataSnapshot.getChildrenCount()) + " values " + dataSnapshot.getKey());
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            Log.d("FragmentActivity", "Test Customer" + data.getKey());
                            Customers customer = data.getValue(Customers.class);
                            System.out.println(customer.getId() + customer.getName() + customer.getEmail());
                            customers.add(customer);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.w("TodoApp", "getUser:onCancelled", databaseError.toException());

                    }
                }
        );

        for (Customers customer : customers) {
            if (customer.getId().equals(id.getText().toString())) {
                existe = Boolean.TRUE;
            }
        }

        return existe;
    }

}
