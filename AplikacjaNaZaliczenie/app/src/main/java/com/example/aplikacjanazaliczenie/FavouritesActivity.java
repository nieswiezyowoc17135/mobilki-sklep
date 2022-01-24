package com.example.aplikacjanazaliczenie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.PowerManager;

import java.util.ArrayList;

public class FavouritesActivity extends AppCompatActivity {

    private RecyclerView productsRecyclerView;
    private ProductRecViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        productsRecyclerView = findViewById(R.id.productsRecView);

        adapter = new ProductRecViewAdapter(this,"favouritesProducts");
        productsRecyclerView = findViewById(R.id.productsRecView);

        productsRecyclerView.setAdapter(adapter);
        productsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Product> products = Utils.getFavouritesProducts();
        adapter.setProducts(products);
    }

}