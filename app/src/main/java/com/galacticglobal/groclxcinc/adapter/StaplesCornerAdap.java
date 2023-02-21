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
import com.galacticglobal.groclxcinc.interfaces.PersonalItemClick;
import com.galacticglobal.groclxcinc.model_classes.home_products_model.PersonalCare;
import com.squareup.picasso.Picasso;

import java.util.List;

public class StaplesCornerAdap extends RecyclerView.Adapter<StaplesCornerAdap.ViewHolder> {
    PersonalItemClick onPersonalItemClick;
    private Activity activity;
    List<PersonalCare> personalCares;

    public StaplesCornerAdap(Activity activity, List<PersonalCare> personalCares, PersonalItemClick onPersonalItemClick) {
        this.activity = activity;
        this.personalCares = personalCares;
        this.onPersonalItemClick = onPersonalItemClick;
    }

    @NonNull
    @Override
    public StaplesCornerAdap.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_layout, parent, false);
        return new StaplesCornerAdap.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final StaplesCornerAdap.ViewHolder holder, int position) {

        PersonalCare personalCare = personalCares.get(position);
        Picasso.get().load(personalCare.getImage()).into(holder.productImg);
        holder.productName.setText(personalCare.getName());
        holder.productContainer.setOnClickListener(v -> {
            if (onPersonalItemClick != null)
                onPersonalItemClick.onPersonalItemClick(position, personalCare.getName());
        });
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImg;
        private TextView productName;
        private LinearLayout productContainer;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImg = itemView.findViewById(R.id.product_img);
            productName = itemView.findViewById(R.id.product_name);
            productContainer = itemView.findViewById(R.id.container);

        }
    }
}
