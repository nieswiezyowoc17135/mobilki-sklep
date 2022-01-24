package com.example.aplikacjanazaliczenie;

import java.util.ArrayList;

public class Utils {

    private float sum = 0;
    private static Utils instance;

    private static ArrayList<Product> favouritesProducts;
    private static ArrayList<Product> shoppingCartProducts;
    
    private Utils() {
        if (null == favouritesProducts) {
            favouritesProducts = new ArrayList<>();
            //initData();
        }

        if (null == shoppingCartProducts) {
            shoppingCartProducts = new ArrayList<>();
        }
    }

    public static Utils getInstance() {
        if (null != instance) {
            return instance;
        } else {
            instance = new Utils();
            return instance;
        }
    }

    public static ArrayList<Product> getFavouritesProducts() {
        return favouritesProducts;
    }

    public static ArrayList<Product> getShoppingCartProducts() {
        return shoppingCartProducts;
    }

    public void addToFavourites(Product product) {
        this.favouritesProducts.add(product);
    }

    public void addToShoppingCart(Product product) {
        this.shoppingCartProducts.add(product);
    }

    public boolean removeFromFavourites(Product product) {
        return favouritesProducts.remove(product);
    }

    public boolean removeFromShoppingCart(Product product) {
        return  shoppingCartProducts.remove(product);
    }

    public Float sumOfCart() {
        for (Product product : shoppingCartProducts) {
            sum = sum + product.getPrice();
        }
        return sum;
    }


}
