package com.galacticglobal.groclxcinc.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.galacticglobal.groclxcinc.R;
import com.galacticglobal.groclxcinc.interfaces.SubCatCallback;
import com.galacticglobal.groclxcinc.model_classes.Datum;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AllCategoryHome extends RecyclerView.Adapter<AllCategoryHome.ViewHolder> {
    SubCatCallback onSubCatItemClick;
    Activity activity;
    List<Datum> categoryList;

    public AllCategoryHome(FragmentActivity activity, List<Datum> categoryList, SubCatCallback onSubCatItemClick) {

        this.activity = activity;
        this.categoryList = categoryList;
        this.onSubCatItemClick = onSubCatItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_all_cat, parent, false);
        return new AllCategoryHome.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Datum data = categoryList.get(position);

        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        holder.recycler_view_inner.setLayoutManager(layoutManager);

        holder.name.setText(data.getCategoryName());

        holder.main_visible_layout.setVisibility(View.GONE);
        holder.drop_down.animate().rotation(0).start();


        if (data.getCatImage() != null && !data.getCatImage().isEmpty()) {
            Picasso.get().load(data.getCatImage()).into(holder.cateImg);
        }

        holder.drop_down_layout.setOnClickListener(v -> {
            if (holder.main_visible_layout.getVisibility() == View.GONE) {
                holder.main_visible_layout.setVisibility(View.VISIBLE);
                holder.drop_down.animate().rotation(180).start();

                if (onSubCatItemClick != null)
                    onSubCatItemClick.onSubCatItemClick(position, holder.recycler_view_inner);
            } else {
                holder.main_visible_layout.setVisibility(View.GONE);
                holder.drop_down.animate().rotation(0).start();
            }
        });


    }


    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView drop_down, cateImg;

        RecyclerView recycler_view_inner;
        ViewGroup drop_down_layout, main_visible_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.status_text);
            cateImg = itemView.findViewById(R.id.cate_img);
            drop_down = itemView.findViewById(R.id.drop_down);
            drop_down_layout = itemView.findViewById(R.id.drop_down_layout);
            recycler_view_inner = itemView.findViewById(R.id.recycler_view_inner);

            main_visible_layout = itemView.findViewById(R.id.main_visible_layout);
            main_visible_layout.setVisibility(View.GONE);
        }
    }
}
