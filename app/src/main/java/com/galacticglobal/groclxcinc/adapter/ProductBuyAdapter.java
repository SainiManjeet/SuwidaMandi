package com.galacticglobal.groclxcinc.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.galacticglobal.groclxcinc.R;
import com.galacticglobal.groclxcinc.classes.HelperClass;
import com.galacticglobal.groclxcinc.interfaces.AddMainProductClick;
import com.galacticglobal.groclxcinc.interfaces.QtyClickCallback;
import com.galacticglobal.groclxcinc.model_classes.sub_cat_products_model.Datum;
import com.galacticglobal.groclxcinc.util.UIUtility;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductBuyAdapter extends RecyclerView.Adapter<ProductBuyAdapter.ViewHolder> {

    Activity activity;
    List<Datum> productsModels;
    AddMainProductClick onAddMainProductClick;
    QtyClickCallback onUpdateListItemClick;
    String quantities;
    String prices = null;

    List<com.galacticglobal.groclxcinc.model_classes.cart_items.Datum> cartItemList;

    public ProductBuyAdapter(Activity activity, List<Datum> productsModels, List<com.galacticglobal.groclxcinc.model_classes.cart_items.Datum> cartItemList,
                             AddMainProductClick onAddMainProductClick, QtyClickCallback onUpdateListItemClick) {

        this.activity = activity;
        this.onAddMainProductClick = onAddMainProductClick;
        this.productsModels = productsModels;
        this.cartItemList = cartItemList;
        this.onUpdateListItemClick = onUpdateListItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_product_lists, parent, false);
        return new ProductBuyAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        Datum datum = productsModels.get(position);

        holder.increment.setOnClickListener(v -> {
            if (HelperClass.getUserId(activity) != null && !HelperClass.getUserId(activity).isEmpty()) {
                int count = Integer.parseInt(holder.quantity.getText().toString());
                count++;
                holder.quantity.setText("" + count);

                if (holder.spinner.getVisibility() == View.VISIBLE) {
                    int pos = holder.spinner.getSelectedItemPosition();
                    onAddMainProductClick.onAddMainProductClick(position, 1, Float.parseFloat(holder.price.getText().toString()), datum.getPAttr().get(pos).getId(), holder.progressBar);
                } else {
                    onAddMainProductClick.onAddMainProductClick(position, 1, Float.parseFloat(datum.getProductCost()), 0, holder.progressBar);
                }
            } else {
                UIUtility.showDialog(activity, "You need to Login first");
            }

        });

        holder.decriment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (HelperClass.getUserId(activity) != null && !HelperClass.getUserId(activity).isEmpty()) {
                    int count = Integer.parseInt(holder.quantity.getText().toString());
                    if (count == 1) {
                        holder.quantity.setText("1");
                    } else {
                        count -= 1;
                        holder.quantity.setText("" + count);
                    }

                    if (holder.spinner.getVisibility() == View.VISIBLE) {

                        int pos = holder.spinner.getSelectedItemPosition();
                        onUpdateListItemClick.onUpdateListItemClick(productsModels.get(position).getPid(), holder.quantity.getText().toString(), holder.progressBar,
                                holder.quantity, datum.getPAttr().get(pos).getId());


                    } else {
                        onUpdateListItemClick.onUpdateListItemClick(productsModels.get(position).getPid(), holder.quantity.getText().toString(), holder.progressBar,
                                holder.quantity, 0);
                    }
                } else {
                    UIUtility.showDialog(activity, "You need to Login first");
                }
            }
        });


        holder.product_name.setText(datum.getProductName());
        holder.price.setText(datum.getProductCost());
        Picasso.get().load(datum.getProductImage()).into(holder.product_img);

        //  holder.add_cart.setVisibility(View.VISIBLE);


        ArrayAdapter<String> adapter;
        List<String> spinnerArray = new ArrayList<String>();
        if (datum.getPAttr() != null && datum.getPAttr().size() > 1) {
            holder.spinner.setVisibility(View.VISIBLE);


            for (int j = 0; j < datum.getPAttr().size(); j++) {
                spinnerArray.add(datum.getPAttr().get(j).getPSize());
            }

        } else {
            holder.spinner.setVisibility(View.GONE);
            holder.weight.setVisibility(View.VISIBLE);
            holder.weight.setText(datum.getProductWeight());

        }

        holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                holder.price.setText(datum.getPAttr().get(position).getPAmt());


                prices = datum.getPAttr().get(position).getPAmt();

                holder.add_cart.setVisibility(View.VISIBLE);
                holder.plus_minus.setVisibility(View.GONE);
                quantities = datum.getPAttr().get(position).getPSize();

                holder.quantity.setText("1");


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here

            }

        });


        adapter = new ArrayAdapter<String>(
                activity, android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinner.setAdapter(adapter);


        // Todo Cross check this
       /* if (datum.getProductStockStatus() == 0 && datum.getRemainingStock() == 0) {
            holder.containerStock.setVisibility(View.VISIBLE);
            holder.add_cart.setVisibility(View.GONE);
        } else {
            holder.containerStock.setVisibility(View.GONE);
            holder.add_cart.setVisibility(View.VISIBLE);
        }*/


        holder.add_cart.setOnClickListener(v -> {
            holder.plus_minus.setVisibility(View.VISIBLE);
            holder.add_cart.setVisibility(View.GONE);

            if (holder.spinner.getVisibility() == View.VISIBLE) {
                int pos = holder.spinner.getSelectedItemPosition();
                onAddMainProductClick.onAddMainProductClick(position, 1, Float.parseFloat(holder.price.getText().toString()), datum.getPAttr().get(pos).getId(), holder.progressBar);
            } else {
                onAddMainProductClick.onAddMainProductClick(position, 1, Float.parseFloat(datum.getProductCost()), 0, holder.progressBar);
            }
        });


    }

    @Override
    public int getItemCount() {
        return productsModels.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button add_cart;
        ImageView product_img;
        TextView quantity, product_name, price, containerStock, weight;
        //ViewGroup decriment, increment, plus_minus, after_add;
        ViewGroup decriment, increment, plus_minus;
        Spinner spinner;
        ProgressBar progressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            add_cart = itemView.findViewById(R.id.add_cart);
            product_img = itemView.findViewById(R.id.product_img);

            product_name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.price);
            decriment = itemView.findViewById(R.id.decriment);
            increment = itemView.findViewById(R.id.increment);
            quantity = itemView.findViewById(R.id.quantity);
            weight = itemView.findViewById(R.id.weight);

            plus_minus = itemView.findViewById(R.id.plus_minus);
            containerStock = itemView.findViewById(R.id.out_stock);
            progressBar = itemView.findViewById(R.id.progress_bar);


            spinner = itemView.findViewById(R.id.spinner);
        }
    }
}

