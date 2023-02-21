package com.galacticglobal.groclxcinc.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.galacticglobal.groclxcinc.R;
import com.galacticglobal.groclxcinc.classes.Constants;
import com.galacticglobal.groclxcinc.classes.HelperClass;
import com.galacticglobal.groclxcinc.model_classes.order_history.Datum;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    Activity activity;
    //  List<Datum> productsModels;
    List<com.galacticglobal.groclxcinc.model_classes.order_history.Datum> productsModels;

    public OrderAdapter(Activity activity, List<com.galacticglobal.groclxcinc.model_classes.order_history.Datum> productsModels) {
        this.activity = activity;
        this.productsModels = productsModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_order_list, parent, false);
        return new OrderAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Datum datum = productsModels.get(position);
        holder.userName.setText(HelperClass.getUserName(activity));
        holder.amount.setText(datum.getSorderAmt());
        holder.orderStatus.setText(datum.getSorderStatus());
        holder.orderPlaced.setText(datum.getSorderCreated());
        holder.userEmail.setText(HelperClass.getEmail(activity));

        //todo on view call api n visible from xml

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return productsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView userName, userEmail, amount, orderStatus,orderPlaced;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.user_name);
            userEmail = itemView.findViewById(R.id.user_email);
            amount = itemView.findViewById(R.id.amount);
            orderStatus = itemView.findViewById(R.id.order_status);
            orderPlaced = itemView.findViewById(R.id.order_placed);


        }
    }
}
