package com.example.proyecto.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.Managed.CustomerManaged;
import com.example.proyecto.R;
import com.example.proyecto.model.Customers;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {

private List<Customers> items;

public static class CustomerViewHolder extends RecyclerView.ViewHolder {
    // Campos respectivos de un item
    private TextView idCustomer;
    private TextView name;
    private TextView lastName;
    private TextView numberPhone;
    private TextView email;

    public String key;

    public CustomerViewHolder(View v) {
        super(v);

        idCustomer = (TextView) v.findViewById(R.id.tvId);
        name = (TextView) v.findViewById(R.id.tvName);
        lastName = (TextView) v.findViewById(R.id.tvLastName);
        numberPhone = (TextView) v.findViewById(R.id.tvNumberPhone);
        email = (TextView) v.findViewById(R.id.tvEmail);


        ((ImageButton) v.findViewById(R.id.ibEditar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CustomerManaged.class);
                intent.putExtra("key", key);
                intent.putExtra("accion", 2);
                v.getContext().startActivity(intent);
                Toast.makeText(v.getContext(), "Editando " + name.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        ((ImageButton) v.findViewById(R.id.ibEliminar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CustomerManaged.class);
                intent.putExtra("key", key);
                intent.putExtra("accion", 3);
                v.getContext().startActivity(intent);
                Toast.makeText(v.getContext(), "Eliminar " + name.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

    public CustomerAdapter(List<Customers> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public CustomerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.customer_card, viewGroup, false);
        return new CustomerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CustomerViewHolder viewHolder, int i) {
        viewHolder.key = items.get(i).getKey();
        viewHolder.idCustomer.setText("ID: " + Integer.toString(items.get(i).getId()));
        viewHolder.name.setText("Nombre: " + items.get(i).getName());
        viewHolder.lastName.setText("Apellidos: " + items.get(i).getLastName());
        viewHolder.numberPhone.setText("Numero Celular: " + items.get(i).getNumberPhone());
        viewHolder.email.setText("Email: " + items.get(i).getEmail());
    }
}
