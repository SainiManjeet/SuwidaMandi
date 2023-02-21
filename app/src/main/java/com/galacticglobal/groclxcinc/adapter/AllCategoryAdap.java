package com.galacticglobal.groclxcinc.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.galacticglobal.groclxcinc.R;
import com.galacticglobal.groclxcinc.model_classes.Datum;

import java.util.List;

public class AllCategoryAdap extends RecyclerView.Adapter<AllCategoryAdap.ViewHolder> {
    Activity activity;
    List<Datum> driver_report;
    public AllCategoryAdap(Activity activity, List<Datum> driver_report) {
        this.activity = activity;
        this.driver_report = driver_report;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cate, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Datum data = driver_report.get(position);

        holder.name.setText(data.getCategoryName());


    }

    @Override
    public int getItemCount() {
        return driver_report.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

             private TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.category_name);


        }
    }
}
