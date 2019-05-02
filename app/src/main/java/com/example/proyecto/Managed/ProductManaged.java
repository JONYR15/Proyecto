package com.example.proyecto.Managed;


import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.proyecto.ProductActivity;
import com.example.proyecto.R;
import com.example.proyecto.References;
import com.example.proyecto.model.Products;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

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

    private EditText editTextName;

    private Products requestedProduct;
    private DatabaseReference infoReference;
    private StorageReference storageReference;

    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageButton ChooseImage;
    private ImageView imageProduct;
    private ProgressBar mprogressBar;

    //uri to store file
    private Uri imageProductUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_managed);

        ChooseImage = findViewById(R.id.ibChooseImage);
        imageProduct = findViewById(R.id.ivProduct);
        mprogressBar = findViewById(R.id.progressBar);

        ChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenFileChooser();
            }
        });

        crear = findViewById(R.id.btCrear);
        editar = findViewById(R.id.btEditar);
        eliminar = findViewById(R.id.btEliminar);

        idProduct = findViewById(R.id.etIdProducto);
        description = findViewById(R.id.etDescription);
        quantity = findViewById(R.id.etQuantity);
        cost = findViewById(R.id.etCost);
        sale = findViewById(R.id.etSale);

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

        infoReference = FirebaseDatabase.getInstance().getReference().child(References.INFO_REFERENCE).child(References.PRODUCTOS_REFERENCE);
        storageReference = FirebaseStorage.getInstance().getReference().child(References.IMAGES_PRODUCTS);

    }


    public void createProduct(View view) {
        if (!existe("create", null)) {
            int idProducto = Integer.parseInt(((EditText) findViewById(R.id.etIdProducto)).getText().toString());
            String description = this.description.getText().toString();
            int quantity = Integer.parseInt(((EditText) findViewById(R.id.etQuantity)).getText().toString());
            Double cost = Double.parseDouble(((EditText) findViewById(R.id.etCost)).getText().toString());
            Double sale = Double.parseDouble(((EditText) findViewById(R.id.etSale)).getText().toString());
            Products product = new Products(idProducto, uploadImage(), description, quantity, cost, sale);
            infoReference.push().setValue(product);
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
            int quantity = Integer.parseInt(((EditText) findViewById(R.id.etQuantity)).getText().toString());
            Double cost = Double.parseDouble(((EditText) findViewById(R.id.etCost)).getText().toString());
            Double sale = Double.parseDouble(((EditText) findViewById(R.id.etSale)).getText().toString());
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
        for (Products product : ProductActivity.getProducts()) {
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

    private void OpenFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && requestCode == RESULT_OK
                && data != null && data.getData() != null) {
            imageProductUri = data.getData();
            Picasso.with(this).load(imageProductUri).into(imageProduct);
        }

        try {
            imageProductUri = data.getData();
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageProductUri);
            imageProduct.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }


    public String uploadImage() {
        String fileName = "";
        if (imageProductUri != null) {
            fileName = System.currentTimeMillis() + "." + getFileExtension(imageProductUri);
            StorageReference fileRefecence = storageReference.child(fileName);
            fileRefecence.putFile(imageProductUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mprogressBar.setProgress(0);
                                }
                            }, 5000);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ProductManaged.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mprogressBar.setProgress((int) progress);
                        }
                    });

        } else {
            Toast.makeText(this, "Archivo no seleccionado", Toast.LENGTH_SHORT).show();
        }

        return fileName;
    }

}
