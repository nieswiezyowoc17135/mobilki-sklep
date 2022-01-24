package com.example.aplikacjanazaliczenie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductAdapterVH> {

    private List<ProductResponse> productResponseList;
    private Context context;
    private ClickedItem clickedItem;

    public ProductsAdapter(ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }

    public void setData(List<ProductResponse> productResponseList) {
        this.productResponseList = productResponseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ProductAdapterVH(LayoutInflater.from(context).inflate(R.layout.row_products,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapterVH holder, int position) {
        ProductResponse productResponse = productResponseList.get(position);

        String productName = productResponse.getTitle();
        String prefix = "o";
        holder.prefix.setText(prefix);
        holder.productName.setText(productName);
        holder.imageMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedItem.ClickedProduct(productResponse);
            }
        });
    }

    public interface ClickedItem {
        public void ClickedProduct(ProductResponse productResponse);
    }

    @Override
    public int getItemCount() {
        return productResponseList.size();
    }

    public class ProductAdapterVH extends RecyclerView.ViewHolder {

        TextView productName, prefix;
        ImageView imageMore;

        public ProductAdapterVH(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.product_name);
            prefix = itemView.findViewById(R.id.prefix);
            imageMore = itemView.findViewById(R.id.imageMore);

        }
    }
}
