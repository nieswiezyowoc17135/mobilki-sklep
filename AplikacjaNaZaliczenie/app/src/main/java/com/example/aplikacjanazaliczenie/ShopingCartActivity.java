package com.example.aplikacjanazaliczenie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ShopingCartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductRecViewAdapter adapter;

    private TextView txtSumOfCart;
    private TextView txtSum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoping_cart);

//        txtSumOfCart = findViewById(R.id.txtSumOfCart);
//        txtSum = findViewById(R.id.txtSum);

        // stuff odnosnie recyclerView
        recyclerView = findViewById(R.id.productsRecyclerView);

        adapter = new ProductRecViewAdapter(this, "shoppingCartProducts");
        recyclerView = findViewById(R.id.productsRecyclerView);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Product> products = Utils.getShoppingCartProducts();
//        txtSum.setText(Utils.getInstance().sumOfCart().toString());
        adapter.setProducts(products);
    }
}