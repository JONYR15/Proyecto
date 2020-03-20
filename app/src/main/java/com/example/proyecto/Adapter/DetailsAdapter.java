package com.example.proyecto.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyecto.Activity.CustomersActivity;
import com.example.proyecto.Managed.CustomerManaged;
import com.example.proyecto.Managed.DetailsManaged;
import com.example.proyecto.Managed.InvoiceManaged;
import com.example.proyecto.R;
import com.example.proyecto.model.Customers;
import com.example.proyecto.model.Details;

import java.util.List;

public class DetailsAdapter  extends RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder> {

    private List<Details> details;
    private Context context;

    public class DetailsViewHolder extends RecyclerView.ViewHolder{
        private EditText etDetailDescripcion;
        private EditText etDetailCantidad;
        private EditText etDetailPrecio;
        private EditText etDetailTotal;
        private Button btnDetailConfirmar;

        public Integer pos;

        public DetailsViewHolder(View v){
            super(v);
            etDetailDescripcion = (EditText)v.findViewById(R.id.etDetailsDescription);
            etDetailCantidad = (EditText)v.findViewById(R.id.etDetailsCantidad);
            etDetailPrecio = (EditText)v.findViewById(R.id.etDetailsPrecio);
            etDetailTotal = (EditText)v.findViewById(R.id.etDetailsTotal);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailsManaged.class);
                    intent.putExtra("pos", pos);
                    v.getContext().startActivity(intent);
                }
            });
        }

    }

    public DetailsAdapter(List<Details> details) {
        this.details = details;
    }

    @NonNull
    @Override
    public DetailsAdapter.DetailsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.customer_card, viewGroup, false);
        context = viewGroup.getContext();
        return new DetailsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsAdapter.DetailsViewHolder detailsViewHolder, int i) {
        detailsViewHolder.pos = i;
        detailsViewHolder.etDetailDescripcion.setText( details.get(i).getDescription());
        detailsViewHolder.etDetailCantidad.setText(details.get(i).getQuantity());
        detailsViewHolder.etDetailPrecio.setText(details.get(i).getQuantity());
        detailsViewHolder.etDetailTotal.setText(Double.toString(details.get(i).getTotalPrice()));
    }

    @Override
    public int getItemCount() {
        return details.size();
    }


}
