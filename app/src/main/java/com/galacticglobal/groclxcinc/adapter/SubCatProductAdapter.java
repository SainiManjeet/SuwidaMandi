package com.galacticglobal.groclxcinc.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.galacticglobal.groclxcinc.R;
import com.galacticglobal.groclxcinc.interfaces.SubCatProductsClick;
import com.galacticglobal.groclxcinc.model_classes.sub_cat_products.Datum;

import java.util.List;

public class SubCatProductAdapter extends RecyclerView.Adapter<SubCatProductAdapter.ViewHolder> {

    FragmentActivity activity;
    List<Datum> subCatProducts;
    SubCatProductsClick onSubCatProductsClick;

    public SubCatProductAdapter(FragmentActivity activity, List<Datum> subCatProducts, SubCatProductsClick onSubCatProductsClick) {
        this.activity = activity;
        this.onSubCatProductsClick = onSubCatProductsClick;
        this.subCatProducts = subCatProducts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_all_subcat, parent, false);
        return new SubCatProductAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Datum datum = subCatProducts.get(position);
        holder.list_item.setText(datum.getCategoryName());
        holder.list_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onSubCatProductsClick != null)
                 onSubCatProductsClick.onSubCatProductsClick(position);
            }

        });


    }

    @Override
    public int getItemCount() {
        return subCatProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView list_item;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            list_item = itemView.findViewById(R.id.list_item);

        }
    }
}
