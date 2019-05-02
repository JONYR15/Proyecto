package com.example.proyecto;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.proyecto.Adapter.ProductAdapter;
import com.example.proyecto.Managed.ProductManaged;
import com.example.proyecto.model.Products;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {

    private static List<Products> products = new ArrayList<>();

    private DatabaseReference infoReference;
    private StorageReference storageReference;

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    private Uri URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Toolbar toolbar = findViewById(R.id.toolbarProduct);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fabProduct);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ProductManaged.class);
                intent.putExtra("accion", 1);
                view.getContext().startActivity(intent);
            }
        });

        infoReference = FirebaseDatabase.getInstance().getReference().child(References.INFO_REFERENCE).child(References.PRODUCTOS_REFERENCE);
        storageReference = FirebaseStorage.getInstance().getReference().child(References.IMAGES_PRODUCTS);

        infoReference.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        products.clear();
                        System.out.println(dataSnapshot.getChildrenCount());
                        Log.w("TodoApp", "getUser:onCancelled " + dataSnapshot.toString());
                        Log.w("TodoApp", "count = " + String.valueOf(dataSnapshot.getChildrenCount()) + " values " + dataSnapshot.getKey());
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            Log.d("FragmentActivity", "Test Product" + data.getKey());
                            Products product = data.getValue(Products.class);
                            product.setKey(data.getKey());
                            if (product.getImageProduct() != null) {
                                storageReference.child(product.getImageProduct()).getDownloadUrl()
                                        .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                URL = uri;
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                URL = null;
                                            }
                                        });
                            } else {
                                URL = null;
                            }
                            product.setImageUri(URL);
                            System.out.println("URLLLLL" + product.getImageUri());
                            products.add(product);
                        }

                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.w("TodoApp", "getUser:onCancelled", databaseError.toException());

                    }
                }
        );

        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        adapter = new ProductAdapter(products);
        recycler.setAdapter(adapter);


    }

    public static List<Products> getProducts() {
        return products;
    }

    public static void setProducts(List<Products> products) {
        ProductActivity.products = products;
    }

}



