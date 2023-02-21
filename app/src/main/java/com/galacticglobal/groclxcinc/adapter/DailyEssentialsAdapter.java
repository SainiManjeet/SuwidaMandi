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
import com.galacticglobal.groclxcinc.interfaces.DailyItemClickListner;
import com.galacticglobal.groclxcinc.model_classes.home_products_model.DailyEssentialStore;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DailyEssentialsAdapter extends RecyclerView.Adapter<DailyEssentialsAdapter.ViewHolder> {

    private DailyItemClickListner dailyItemClickListner;
    private Activity activity;
    List<DailyEssentialStore> dailyEssentialStores;

    public DailyEssentialsAdapter(Activity activity, List<DailyEssentialStore> homeKitchenStores, DailyItemClickListner kitchenItemClickListner) {
        this.activity = activity;
        this.dailyEssentialStores = homeKitchenStores;
        this.dailyItemClickListner = kitchenItemClickListner;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_layout, parent, false);
        return new DailyEssentialsAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DailyEssentialStore dail = dailyEssentialStores.get(position);
        Picasso.get().load(dail.getImage()).into(holder.productImg);
        holder.productName.setText(dail.getName());
        holder.productContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dailyItemClickListner != null)
                    dailyItemClickListner.onDailyItemClickListner(position,dail.getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return 3;
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
