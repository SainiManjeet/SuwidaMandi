package com.galacticglobal.groclxcinc.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.galacticglobal.groclxcinc.R;
import com.galacticglobal.groclxcinc.interfaces.ItemClick;
import com.galacticglobal.groclxcinc.model_classes.home_products_model.HomeScreenProduct;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Buyproducts extends RecyclerView.Adapter<Buyproducts.ViewHolder> {
    ItemClick itemClick;
    FragmentActivity activity;
    List<HomeScreenProduct> products;
    List<com.galacticglobal.groclxcinc.model_classes.home_products_model.PAttr> PAttr;

    public Buyproducts(FragmentActivity activity, List<HomeScreenProduct> products, ItemClick itemClick) {
        this.products = products;
        this.activity = activity;
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        HomeScreenProduct data = products.get(position);


        Picasso.get().load(data.getProductImage()).into(holder.product_img);

        ArrayAdapter<String> adapter;
        List<String> spinnerArray = new ArrayList<String>();
        if (data.getPAttr() != null && data.getPAttr().size() > 0) {
            holder.spinner.setVisibility(View.VISIBLE);

            for (int j = 0; j < data.getPAttr().size(); j++) {
                spinnerArray.add(data.getPAttr().get(j).getPSize());
            }

        } else {
            holder.spinner.setVisibility(View.GONE);
        }

        adapter = new ArrayAdapter<String>(
                activity, android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinner.setAdapter(adapter);

        holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                holder.price.setText( data.getPAttr().get(position).getPAmt());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        if (data.getProductCost() != null && !data.getProductCost().isEmpty()) {
            holder.price.setText(data.getProductCost());
        }
        if (data.getProductName() != null && !data.getProductName().isEmpty()) {
            holder.description.setText(data.getProductName());
        }

        holder.add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClick != null) {

                    if (holder.spinner.getVisibility() == View.VISIBLE) {
                        int pos=holder.spinner.getSelectedItemPosition();
                        itemClick.onItemClick(position, 1, Float.parseFloat(holder.price.getText().toString()), data.getPAttr().get(pos).getId());

                    } else {
                        itemClick.onItemClick(position, 1, Float.parseFloat(data.getProductCost()),0);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Spinner spinner;
        ImageView product_img;
        TextView description, price/*add_cart*/;
        Button add_cart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            spinner = itemView.findViewById(R.id.spinner);
            product_img = itemView.findViewById(R.id.product_img);
            description = itemView.findViewById(R.id.description);
            price = itemView.findViewById(R.id.price);
            add_cart = itemView.findViewById(R.id.add_cart);

        }
    }
}


