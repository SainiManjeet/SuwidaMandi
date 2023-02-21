package com.galacticglobal.groclxcinc.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.galacticglobal.groclxcinc.R;
import com.galacticglobal.groclxcinc.interfaces.ListItemCallback;

public class UperRecycler extends RecyclerView.Adapter<UperRecycler.ViewHolder> {


    private ListItemCallback listItemClickCallback;
    private Activity activity;
    Integer[] uperInt;
    String[] uperString;

    public UperRecycler(FragmentActivity activity, Integer[] uperInt, String[] uperString, ListItemCallback listItemClickCallback) {
        this.uperInt= uperInt;
        this.uperString = uperString;
        this.activity = activity;
        this.listItemClickCallback= listItemClickCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_layouts, parent, false);
        return new UperRecycler.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.productImg.setImageResource(uperInt[position]);
        holder.productName.setText(uperString[position]);
        holder.productContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listItemClickCallback != null)
                    listItemClickCallback.onListItemClick(position,uperString[position]);
            }
        });

    }

    @Override
    public int getItemCount() {
        return uperInt.length;
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
