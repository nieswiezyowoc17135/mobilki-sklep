package com.example.aplikacjanazaliczenie;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnTowary, btnUlubione, btnKoszyk, btnONas, btnUninstall;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTowary = findViewById(R.id.btn_towary);
        btnKoszyk = findViewById(R.id.btn_koszyk);
        btnUlubione = findViewById(R.id.btn_ulubione);
        btnONas = findViewById(R.id.btn_o_nas);
        btnUninstall = findViewById(R.id.btnUninstal);


        btnTowary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GoodsActivity.class);
                startActivity(intent);
            }
        });

        btnUlubione.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.getFavouritesProducts() == null) {
                    Toast.makeText(MainActivity.this, "Zbior pusty", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, FavouritesActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnKoszyk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.getShoppingCartProducts() == null) {
                    Toast.makeText(MainActivity.this, "Zbior pusty", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, ShopingCartActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnONas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(getString(R.string.app_name));
                builder.setMessage("Designed by me :D");
                builder.setNegativeButton("Dissmis", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("Sprawdz", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO:odwiedz strone
                        Intent intent = new Intent(MainActivity.this, WebsiteActivity.class);
                        startActivity(intent);
                    }
                });
                builder.create().show();
            }
        });

        btnUninstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DELETE);
                intent.setData(Uri.parse("package:com.example.aplikacjanazaliczenie"));
                startActivity(intent);
            }
        });

    }
}