package com.galacticglobal.groclxcinc.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.galacticglobal.groclxcinc.R;
import com.galacticglobal.groclxcinc.classes.ApiServices;
import com.galacticglobal.groclxcinc.classes.Constants;
import com.galacticglobal.groclxcinc.classes.HelperClass;
import com.galacticglobal.groclxcinc.interfaces.ApiInterfaces;
import com.galacticglobal.groclxcinc.interfaces.QtyClickCallback;
import com.galacticglobal.groclxcinc.interfaces.cartItemClickCallback;
import com.galacticglobal.groclxcinc.model_classes.cart_items.Datum;
import com.galacticglobal.groclxcinc.model_classes.cart_items.removeCartItems;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartItemAdap extends RecyclerView.Adapter<CartItemAdap.ViewHolder> {
    List<com.galacticglobal.groclxcinc.model_classes.cart_items.Datum> cartItemList;
    int _counter = 0;
    String _stringVal;
    int number = 1;
    private
    Activity activity;
    private cartItemClickCallback clickCallback;
    private QtyClickCallback onUpdateListItemClick;

    public CartItemAdap(Activity activity, List<Datum> cartItemList, cartItemClickCallback clickCallback, QtyClickCallback onUpdateListItemClick) {

        this.activity = activity;
        this.cartItemList = cartItemList;
        this.clickCallback = clickCallback;
        this.onUpdateListItemClick = onUpdateListItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        Datum data = cartItemList.get(position);




        holder.name.setText(data.getProductName());
        holder.price.setText(data.getQty() + "× Rs. " + data.getProductAmt());

        holder.quantity.setText(String.valueOf(data.getQty()));

        Picasso.get().load(data.getProductImage()).into(holder.productImg);




        holder.removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (clickCallback != null)
                    clickCallback.onListItemClick(position, data.getPid(), data.getProductAttr(), HelperClass.getOrderSession(activity));
            }

        });
        holder.increment.setOnClickListener(v -> {

            int count = Integer.parseInt(holder.quantity.getText().toString());
            count++;
            holder.quantity.setText("" + count);
            if (onUpdateListItemClick != null) {

                onUpdateListItemClick.onUpdateListItemClick(cartItemList.get(position).getOid(), holder.quantity.getText().toString(),holder.progressBar, holder.quantity,0);
            }


        });

        holder.decriment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int count = Integer.parseInt(holder.quantity.getText().toString());
                if (count == 1) {
                    holder.quantity.setText("1");
                } else {
                    count -= 1;
                    holder.quantity.setText("" + count);
                }

                //
                if (onUpdateListItemClick != null) {

                    Log.d("sdfsdfsdfsdf", holder.quantity.getText().toString());
                    onUpdateListItemClick.onUpdateListItemClick(cartItemList.get(position).getOid(), holder.quantity.getText().toString(),holder.progressBar, holder.quantity,0);
                }

            }
        });

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onUpdateListItemClick != null) {

                    Log.d("sdfsdfsdfsdf", holder.quantity.getText().toString());
                    onUpdateListItemClick.onUpdateListItemClick(cartItemList.get(position).getOid(), holder.quantity.getText().toString(),holder.progressBar, holder.quantity,0);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ViewGroup decriment, increment;
        TextView quantity;
        ImageView productImg;
        LinearLayout qtyLayout;
        Button update;
        private LinearLayout removeItem;
        private TextView name, price;
        private ProgressBar progressBar;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            removeItem = itemView.findViewById(R.id.remove_item);
            name = itemView.findViewById(R.id.description);
            price = itemView.findViewById(R.id.price);

            increment = itemView.findViewById(R.id.increment);
            decriment = itemView.findViewById(R.id.decriment);
            quantity = itemView.findViewById(R.id.quantity);
            productImg = itemView.findViewById(R.id.product_img);
            qtyLayout = itemView.findViewById(R.id.qty_container);

            update = itemView.findViewById(R.id.btn_update);
            progressBar=itemView.findViewById(R.id.progress_bar);


        }
    }
}
