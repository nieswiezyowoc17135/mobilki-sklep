package com.example.aplikacjanazaliczenie;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface ProductService {

    @GET("products/")
    Call<List<ProductResponse>> getAllProducts();

    @GET("products/category/jewelery/")
    Call<List<ProductResponse>> getAllJewelery();

    @GET("products/category/electronics/")
    Call<List<ProductResponse>> getAllElectronics();


}



