package com.example.aplikacjanazaliczenie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoodsActivity extends AppCompatActivity {

    private boolean men = false;
    private boolean women = false;
    private boolean electronics = false;
    private boolean jewelery = false;
    private Switch switchMenClothing, switchWomenClothing, switchJewelery, switchElectronics;
    private Button btn_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);

        switchMenClothing = findViewById(R.id.switch_men_clothing);
        switchWomenClothing = findViewById(R.id.switch_women_clothing);
        switchJewelery = findViewById(R.id.switch_jewelery);
        switchElectronics = findViewById(R.id.switch_electronics);

        switchElectronics.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switchElectronics.isChecked()) {
                    Toast.makeText(GoodsActivity.this, "Wybrano elektronike", Toast.LENGTH_SHORT).show();
                    electronics = true;
                } else {
                    electronics = false;
                }
            }
        });

        switchJewelery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switchJewelery.isChecked()){
                    Toast.makeText(GoodsActivity.this, "Wybrano bizuterie", Toast.LENGTH_SHORT).show();
                    jewelery = true;
                }
                else {
                    jewelery = false;
                }
            }
        });

        switchWomenClothing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switchWomenClothing.isChecked()) {
                    Toast.makeText(GoodsActivity.this, "Wybrano odziez damska", Toast.LENGTH_SHORT).show();
                    women = true;
                } else {
                    women = false;
                }
            }
        });

        switchMenClothing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (switchMenClothing.isChecked()) {
                    Toast.makeText(GoodsActivity.this, "Wybrano odziez meska", Toast.LENGTH_SHORT).show();
                    men = true;
                } else {
                    men = false;
                }
            }
        });

        btn_search = findViewById(R.id.button_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoodsActivity.this, FilteredGoodsActivity.class);
                intent.putExtra("jawelery", jewelery);
                intent.putExtra("electronics", electronics);
                intent.putExtra("women", women);
                intent.putExtra("men", men);
                startActivity(intent);
            }
        });
    }
}