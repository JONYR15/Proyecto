package com.example.proyecto.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.Activity.CustomersActivity;
import com.example.proyecto.Managed.CustomerManaged;
import com.example.proyecto.Managed.InvoiceManaged;
import com.example.proyecto.R;
import com.example.proyecto.model.Customers;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {

    private List<Customers> items;

    private Context context;

    public static class CustomerViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        private TextView idCustomer;
        private TextView name;
        private TextView lastName;
        private TextView numberPhone;
        private TextView email;

        private ImageButton edit;
        private ImageButton delete;

        private String key;
        private Integer accion;

        public CustomerViewHolder(View v) {
            super(v);

            idCustomer = (TextView) v.findViewById(R.id.tvIdCustomer);
            name = (TextView) v.findViewById(R.id.tvName);
            lastName = (TextView) v.findViewById(R.id.tvLastName);
            numberPhone = (TextView) v.findViewById(R.id.tvNumberPhone);
            email = (TextView) v.findViewById(R.id.tvEmail);

            edit = (ImageButton) v.findViewById(R.id.ibEditar);
            delete =(ImageButton) v.findViewById(R.id.ibEliminar);

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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (accion==2) {
                        InvoiceManaged.selectCustomer(key);
                        ((CustomersActivity) v.getContext()).finish();
                    }
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
        context = viewGroup.getContext();
        return new CustomerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CustomerViewHolder viewHolder, int i) {
        viewHolder.key = items.get(i).getKey();
        viewHolder.idCustomer.setText(context.getString(R.string.cliente_id) + ":" + items.get(i).getId());
        viewHolder.name.setText(context.getString(R.string.nombre_cliente) + ":" + items.get(i).getName());
        viewHolder.lastName.setText(context.getString(R.string.apellido_cliente) + ":" + items.get(i).getLastName());
        viewHolder.numberPhone.setText(context.getString(R.string.telefono_cliente) + ":" + items.get(i).getNumberPhone());
        viewHolder.email.setText(context.getString(R.string.email_cliente) + ":" + items.get(i).getEmail());

        viewHolder.accion = items.get(i).getAccion();
        if (items.get(i).getAccion()==1) {
            viewHolder.edit.setVisibility(Button.VISIBLE);
            viewHolder.delete.setVisibility(Button.VISIBLE);
        }else{
            viewHolder.edit.setVisibility(Button.GONE);
            viewHolder.delete.setVisibility(Button.GONE);
        }
    }
}
