package com.galacticglobal.groclxcinc.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.galacticglobal.groclxcinc.R;
import com.galacticglobal.groclxcinc.interfaces.KitchenItemClickListner;
import com.galacticglobal.groclxcinc.model_classes.home_products_model.HomeKitchenStore;
import com.squareup.picasso.Picasso;

import java.util.List;

public class KitchenAdapter extends RecyclerView.Adapter<KitchenAdapter.ViewHolder> {

    private KitchenItemClickListner kitchenItemClickListner;
    private Activity activity;
    List<HomeKitchenStore> homeKitchenStores;

    public KitchenAdapter(Activity activity, List<HomeKitchenStore> homeKitchenStores, KitchenItemClickListner kitchenItemClickListner) {
        this.activity = activity;
        this.homeKitchenStores = homeKitchenStores;
        this.kitchenItemClickListner = kitchenItemClickListner;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_layout, parent, false);
        return new KitchenAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        HomeKitchenStore homeKitchenStore = homeKitchenStores.get(position);
        Picasso.get().load(homeKitchenStore.getImage()).into(holder.productImg);
        holder.productName.setText(homeKitchenStore.getName());
        holder.productContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kitchenItemClickListner != null)
                    kitchenItemClickListner.onKitchenItemClickListner(position,homeKitchenStore.getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImg;
        private TextView productName;
        private LinearLayout productContainer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImg = itemView.findViewById(R.id.product_img);
            productName = itemView.findViewById(R.id.product_name);
            productContainer = itemView.findViewById(R.id.container);
        }
    }
}
