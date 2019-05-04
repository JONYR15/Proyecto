package com.example.proyecto.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.proyecto.Managed.ProductManaged;
import com.example.proyecto.R;
import com.example.proyecto.model.Products;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Products> items;

    private Context context;

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        private TextView idProduct;
        private ImageView imageProduct;
        private TextView description;
        private TextView quantity;
        private TextView cost;
        private TextView sale;
        private ImageButton ibedit;
        private ImageButton ibdelete;


        public String key;
        public String nameImage;
        private Integer accion;

        public ProductViewHolder(View v) {
            super(v);
            idProduct = (TextView) v.findViewById(R.id.tvIdProduct);
            imageProduct = (ImageView) v.findViewById(R.id.ivProduct);
            description = (TextView) v.findViewById(R.id.tvDescription);
            quantity = (TextView) v.findViewById(R.id.tvQuantity);
            cost = (TextView) v.findViewById(R.id.tvCost);
            sale = (TextView) v.findViewById(R.id.tvSale);
            ibdelete = (ImageButton)v.findViewById(R.id.ibDelete);
            ibedit = (ImageButton)v.findViewById(R.id.ibEdit);


            ((ImageButton) v.findViewById(R.id.ibEdit)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ProductManaged.class);
                    intent.putExtra("key", key);
                    intent.putExtra("accion", 2);
                    v.getContext().startActivity(intent);
                    Toast.makeText(v.getContext(), "Editando " + description.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });

            ((ImageButton) v.findViewById(R.id.ibDelete)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ProductManaged.class);
                    intent.putExtra("key", key);
                    intent.putExtra("accion", 3);
                    v.getContext().startActivity(intent);
                    Toast.makeText(v.getContext(), "Eliminar " + description.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public ProductAdapter(List<Products> items) {
        this.items = items;
    }

    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_card, parent, false);
        context = parent.getContext();
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int i) {
        holder.key = items.get(i).getKey();
        holder.nameImage = items.get(i).getImageProduct();

        if (items.get(i).getImageUrl() != null) {
            Picasso.with(context).load(items.get(i).getImageUrl()).into(holder.imageProduct);
        }

        holder.idProduct.setText("ID: " + items.get(i).getId());
        holder.description.setText("Nombre:" + items.get(i).getDescription());
        holder.quantity.setText("Cantidad:" + items.get(i).getQuantity());
        holder.cost.setText("Costo:" + items.get(i).getCost());
        holder.sale.setText("Venta:" + items.get(i).getSale());

        holder.accion = items.get(i).getAction();
        if (items.get(i).getAction()==1) {
            holder.ibedit.setVisibility(Button.VISIBLE);
            holder.ibdelete.setVisibility(Button.VISIBLE);
        }else{
            holder.ibedit.setVisibility(Button.GONE);
            holder.ibdelete.setVisibility(Button.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
