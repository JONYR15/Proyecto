package com.example.proyecto.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.Managed.InvoiceManaged;
import com.example.proyecto.R;
import com.example.proyecto.model.Customers;
import com.example.proyecto.model.Invoice;

import java.util.List;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.InvoiceViewHolder> {

    private List<Invoice> items;

    public static class InvoiceViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        private TextView idInvoice;
        private TextView date;
        private TextView nameCustomer;
        private TextView subTotal;
        private TextView iva;
        private TextView total;
        private TextView employee;

        public String key;

        public InvoiceViewHolder(View v) {
            super(v);

            idInvoice = (TextView) v.findViewById(R.id.tvIdInvoice);
            date = (TextView) v.findViewById(R.id.tvDate);
            nameCustomer = (TextView) v.findViewById(R.id.tvNameCustomer);
            employee = (TextView) v.findViewById(R.id.tvEmployee);
            subTotal = (TextView) v.findViewById(R.id.tvSubTotal);
            iva = (TextView) v.findViewById(R.id.tvIva);
            total = (TextView) v.findViewById(R.id.tvTotal);


            ((ImageButton) v.findViewById(R.id.ibEditar)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), InvoiceManaged.class);
                    intent.putExtra("key", key);
                    intent.putExtra("accion", 2);
                    v.getContext().startActivity(intent);
                    Toast.makeText(v.getContext(), "Editando " + idInvoice.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });

            ((ImageButton) v.findViewById(R.id.ibEliminar)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), InvoiceManaged.class);
                    intent.putExtra("key", key);
                    intent.putExtra("accion", 3);
                    v.getContext().startActivity(intent);
                    Toast.makeText(v.getContext(), "Eliminar " + idInvoice.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public InvoiceAdapter(List<Invoice> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public InvoiceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.invoice_card, viewGroup, false);
        return new InvoiceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(InvoiceViewHolder viewHolder, int i) {
        viewHolder.key = items.get(i).getKey();
        viewHolder.idInvoice.setText("ID: " + Integer.toString(items.get(i).getIdInvoice()));
        viewHolder.date.setText("ID: " + items.get(i).getDate().toString());
        viewHolder.nameCustomer.setText("Nombre: " + items.get(i).getNameCustomer());
        viewHolder.subTotal.setText("Apellidos: " + items.get(i).getSubTotal());
        viewHolder.iva.setText("Numero Celular: " + items.get(i).getIva());
        viewHolder.total.setText("Email: " + items.get(i).getTotal());
        viewHolder.employee.setText("Email: " + items.get(i).getEmployee());
    }

}
