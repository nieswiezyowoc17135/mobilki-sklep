package com.example.aplikacjanazaliczenie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilteredGoodsActivity extends AppCompatActivity implements ProductsAdapter.ClickedItem{

    public boolean men;
    public boolean women;
    public boolean electronic;
    public boolean jawelery;
    Toolbar toolbar;
    RecyclerView recyclerView;
    ProductsAdapter productsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtered_goods);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            jawelery = bundle.getBoolean("jawelery");
            electronic = bundle.getBoolean("electronics");
            men = bundle.getBoolean("men");
            women = bundle.getBoolean("women");
        }

        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        productsAdapter = new ProductsAdapter(this::ClickedProduct);
        if (men == true && women == true && jawelery == true && electronic == true) {
            searchAllGoods();
        }else if (jawelery == true && men == false && women == false && electronic == false) {
            searchAllJewelery();
        } else if (electronic== true && jawelery == false && women == false && men == false) {
            searchAllElectronics();
        } else {

        }

    }
    public void searchAllGoods() {

        Call<List<ProductResponse>> productsList = ApiProduct.getProductService().getAllProducts();

        productsList.enqueue(new Callback<List<ProductResponse>>() {
            @Override
            public void onResponse(Call<List<ProductResponse>> call, Response<List<ProductResponse>> response) {
                if (response.isSuccessful()) {
                    List<ProductResponse> productResponses = response.body();
                    productsAdapter.setData(productResponses);
                    recyclerView.setAdapter(productsAdapter);
                    men=false;
                    women=false;
                    jawelery=false;
                    electronic=false;
                }
            }
            @Override
            public void onFailure(Call<List<ProductResponse>> call, Throwable t) {
            }
        });
    }

    public void searchAllJewelery() {

        Call<List<ProductResponse>> productsList = ApiProduct.getProductService().getAllJewelery();

        productsList.enqueue(new Callback<List<ProductResponse>>() {
            @Override
            public void onResponse(Call<List<ProductResponse>> call, Response<List<ProductResponse>> response) {
                if (response.isSuccessful()) {
                    List<ProductResponse> productResponses = response.body();
                    productsAdapter.setData(productResponses);
                    recyclerView.setAdapter(productsAdapter);
                    jawelery=false;
                }
            }
            @Override
            public void onFailure(Call<List<ProductResponse>> call, Throwable t) {
            }
        });
    }

    public void searchAllElectronics() {

        Call<List<ProductResponse>> productsList = ApiProduct.getProductService().getAllElectronics();

        productsList.enqueue(new Callback<List<ProductResponse>>() {
            @Override
            public void onResponse(Call<List<ProductResponse>> call, Response<List<ProductResponse>> response) {
                if (response.isSuccessful()) {
                    List<ProductResponse> productResponses = response.body();
                    productsAdapter.setData(productResponses);
                    recyclerView.setAdapter(productsAdapter);
                    electronic = false;
                }
            }
            @Override
            public void onFailure(Call<List<ProductResponse>> call, Throwable t) {
            }
        });
    }


    @Override
    public void ClickedProduct(ProductResponse productResponse) {
        startActivity(new Intent(this, ProductDetailsActivity.class).putExtra("data", productResponse));
    }
}