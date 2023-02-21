package com.galacticglobal.groclxcinc.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.galacticglobal.groclxcinc.R;
import com.galacticglobal.groclxcinc.adapter.KitchenAdapter;
import com.galacticglobal.groclxcinc.adapter.OrderAdapter;
import com.galacticglobal.groclxcinc.classes.ApiServices;
import com.galacticglobal.groclxcinc.classes.BaseActivity;
import com.galacticglobal.groclxcinc.classes.Constants;
import com.galacticglobal.groclxcinc.classes.HelperClass;
import com.galacticglobal.groclxcinc.interfaces.ApiInterfaces;
import com.galacticglobal.groclxcinc.model_classes.order_history.Datum;
import com.galacticglobal.groclxcinc.model_classes.order_history.OrderHistory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyOrderActivity extends BaseActivity {
    Activity activity;
    List<Datum> orderHistoryList;
    private RecyclerView orderList;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_orders);
        // myOrder("4");
        myOrder(HelperClass.getUserId(MyOrderActivity.this));
        setupViews();

    }

    private void setupViews() {
        activity = MyOrderActivity.this;
        back = findViewById(R.id.back);
        orderList = findViewById(R.id.order_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        orderList.setLayoutManager(layoutManager);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void myOrder(String user_id) {
        if (HelperClass.getNetworkInfo(MyOrderActivity.this)) {
            showProgressDialog();
            try {
                ApiInterfaces apiInterfaces = ApiServices.apiService().create(ApiInterfaces.class);
                Call<OrderHistory> call = apiInterfaces.orderHistory(user_id);
                call.enqueue(new Callback<OrderHistory>() {
                    @Override
                    public void onResponse(@NonNull Call<OrderHistory> call, @NonNull Response<OrderHistory> response) {
                        try {
                            assert response.body() != null;
                            if (response.isSuccessful() && response.body().getIsSuccess().equalsIgnoreCase(Constants.TRUE)) {
                                hideProgressDialog();
                                assert response.body() != null;
                                Toast.makeText(MyOrderActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();

                                orderHistoryList = response.body().getData();

                                orderList.setAdapter(new OrderAdapter(activity,orderHistoryList));


                            } else {
                                hideProgressDialog();
                                Toast.makeText(MyOrderActivity.this, "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();

                            }
                        } catch (Exception e) {
                            hideProgressDialog();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<OrderHistory> call, @NonNull Throwable t) {
                        hideProgressDialog();
                        Toast.makeText(MyOrderActivity.this, "" + R.string.try_again, Toast.LENGTH_SHORT).show();

                    }
                });
            } catch (Exception e) {
                hideProgressDialog();
                e.printStackTrace();
            }
        } else {
            hideProgressDialog();
            Toast.makeText(MyOrderActivity.this, "" + R.string.no_internet, Toast.LENGTH_SHORT).show();
        }
    }

}
