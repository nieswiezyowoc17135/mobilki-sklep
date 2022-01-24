package com.example.aplikacjanazaliczenie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDetailsActivity extends AppCompatActivity {

    private Product product;
    private Button btnShoppingCart, btnFavourites;
    private ImageView photo;
    private TextView productName, productPrice, productDescription;
    ProductResponse productResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productName = findViewById(R.id.productName);
        productPrice = findViewById(R.id.productPrice);
        productDescription = findViewById(R.id.productDescription);

        btnShoppingCart = findViewById(R.id.btnShoppingCart);
        btnFavourites = findViewById(R.id.btnFavourites);

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            productResponse = (ProductResponse) intent.getSerializableExtra("data");
            String productNameData = productResponse.getTitle();
            Float productPriceData = productResponse.getPrice();
            String productDescriptionData = productResponse.getDescription();
            String productImageUrl = productResponse.getImage();

            productName.setText(productNameData);
            productPrice.setText(productPriceData.toString());
            productDescription.setText(productDescriptionData);

            product = new Product(productNameData, productPriceData, productDescriptionData, productImageUrl);
        }

        btnFavourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.getInstance().addToFavourites(product);
                Toast.makeText(ProductDetailsActivity.this, "Produkt zostal dodany do ulubionych", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProductDetailsActivity.this, MainActivity.class);
                startActivity(intent);
                Log.e("dziala", Utils.getFavouritesProducts().toString());
            }
        });

        btnShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.getInstance().addToShoppingCart(product);
                Toast.makeText(ProductDetailsActivity.this, "Produkt zostal dodany do koszyka", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProductDetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}