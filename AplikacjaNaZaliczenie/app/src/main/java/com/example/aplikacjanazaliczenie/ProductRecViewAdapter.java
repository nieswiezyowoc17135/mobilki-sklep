package com.example.aplikacjanazaliczenie;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

import okhttp3.internal.Util;

public class ProductRecViewAdapter extends RecyclerView.Adapter<ProductRecViewAdapter.ViewHolder> {
    private static final String TAG = "ProductRecViewAdapter";

    private ArrayList<Product> products = new ArrayList<>();
    private Context mContext;
    private String parentActivity;

    public ProductRecViewAdapter(Context mContext, String parentActivity) {
        this.mContext = mContext;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_product, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.d(TAG, "onBindViewHolder: Called");
        holder.txtProductName.setText(products.get(position).getTitle());
        Glide.with(mContext)
                .asBitmap()
                .load(products.get(position).getImage())
                .into(holder.imgProduct);
        
        holder.rodzic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, products.get(position).getTitle() + "Selected", Toast.LENGTH_SHORT).show();
            }
        });

        holder.txtDescription.setText(products.get(position).getDescription());

        if (products.get(position).isExpanded()) {
            TransitionManager.beginDelayedTransition(holder.rodzic);
            holder.expandedRelLayout.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);

            if (parentActivity.equals("favouritesProducts")) {
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnAddToShoppingCart.setVisibility(View.VISIBLE);
                holder.btnAddToShoppingCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Utils.getInstance().addToShoppingCart(products.get(position));
                        Toast.makeText(mContext, "Dodano do koszyka", Toast.LENGTH_SHORT).show();
                        Utils.getInstance().removeFromFavourites(products.get(position));
                        notifyDataSetChanged();
                    }
                });

                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Utils.getInstance().removeFromFavourites(products.get(position))) {
                            Toast.makeText(mContext, "Usunieto", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(mContext, "Cos poszlo nie tak", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else if (parentActivity.equals("shoppingCartProducts")) {
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnAddToShoppingCart.setVisibility(View.GONE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Utils.getInstance().removeFromShoppingCart(products.get(position))) {
                            Toast.makeText(mContext, "Usunieto", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(mContext, "Cos poszlo nie tak", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        } else {
            TransitionManager.beginDelayedTransition(holder.rodzic);
            holder.expandedRelLayout.setVisibility(View.GONE);
            holder.downArrow.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CardView rodzic;
        private ImageView imgProduct;
        private TextView txtProductName;

        private ImageView downArrow, upArrow;
        private RelativeLayout expandedRelLayout;
        private TextView txtPrice, txtDescription;

        private TextView btnDelete,btnAddToShoppingCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rodzic = itemView.findViewById(R.id.rodzic);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtProductName = itemView.findViewById(R.id.txtProductName);

            downArrow = itemView.findViewById(R.id.btnDownArrow);
            upArrow = itemView.findViewById(R.id.btnUpArrow);
            expandedRelLayout = itemView.findViewById(R.id.expandedRelLayout);
            txtPrice = itemView.findViewById(R.id.priceText);
            txtDescription = itemView.findViewById(R.id.descriptionText);

            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnAddToShoppingCart = itemView.findViewById(R.id.btnAddToShoppingCart);

            downArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Product product = products.get(getAdapterPosition());
                    product.setExpanded(!product.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            upArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Product product = products.get(getAdapterPosition());
                    product.setExpanded(!product.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }
    }
}
