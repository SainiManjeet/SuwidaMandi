package com.galacticglobal.groclxcinc.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.galacticglobal.groclxcinc.R;
import com.galacticglobal.groclxcinc.adapter.ProductBuyAdapter;
import com.galacticglobal.groclxcinc.classes.ApiServices;
import com.galacticglobal.groclxcinc.classes.BaseActivity;
import com.galacticglobal.groclxcinc.classes.Constants;
import com.galacticglobal.groclxcinc.classes.HelperClass;
import com.galacticglobal.groclxcinc.interfaces.AddMainProductClick;
import com.galacticglobal.groclxcinc.interfaces.ApiInterfaces;
import com.galacticglobal.groclxcinc.interfaces.QtyClickCallback;
import com.galacticglobal.groclxcinc.model_classes.add_to_cart.AddToCartModel;
import com.galacticglobal.groclxcinc.model_classes.sub_cat_products_model.Datum;
import com.galacticglobal.groclxcinc.model_classes.sub_cat_products_model.SubCatProductsModel;
import com.galacticglobal.groclxcinc.model_classes.updateProduct.UpdateProductModel;
import com.galacticglobal.groclxcinc.util.UIUtility;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsDeatils extends BaseActivity implements AddMainProductClick, QtyClickCallback {

    Activity activity;
    RecyclerView all_products;
    List<Datum> productsModels;
    ImageView back;
    TextView title;
    private List<com.galacticglobal.groclxcinc.model_classes.cart_items.Datum> cratItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_deatils);

        init();
    }

    private void init() {

        activity = ProductsDeatils.this;
        all_products = findViewById(R.id.all_products);
        title = findViewById(R.id.title);
        title.setText(HelperClass.getSubCatName(activity));

        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        all_products.setLayoutManager(layoutManager);

        subCatList(HelperClass.getSubClick(activity));

        Log.d("ascascascasc", String.valueOf(HelperClass.getSubClick(activity)));

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void subCatList(int category_id) {
        if (HelperClass.getNetworkInfo(activity)) {
            try {
                ApiInterfaces apiInterfaces = ApiServices.apiService().create(ApiInterfaces.class);
                Call<SubCatProductsModel> call = apiInterfaces.subCateWiseProductDetails(category_id);
                call.enqueue(new Callback<SubCatProductsModel>() {
                    @Override
                    public void onResponse(@NonNull Call<SubCatProductsModel> call, @NonNull Response<SubCatProductsModel> response) {
                        try {
                            assert response.body() != null;
                            if (response.isSuccessful() && response.body().getIsSuccess().equalsIgnoreCase(Constants.TRUE)) {
                                hideProgressDialog();
                                assert response.body() != null;
                                productsModels = response.body().getData();
                                all_products.setAdapter(new ProductBuyAdapter(activity, productsModels, cratItemList, (position, value, cost, attr, progressBar) -> {
                                    onAddMainProductClick(position, value, cost, attr, progressBar);
                                },

                                        (order, qty, progress, txt, attr) -> {
                                            onUpdateListItemClick(order, qty, progress, txt, attr);
                                        }));


                            } else {
                                hideProgressDialog();

                            }
                        } catch (Exception e) {
                            hideProgressDialog();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<SubCatProductsModel> call, @NonNull Throwable t) {
                        hideProgressDialog();
                        Toast.makeText(activity, R.string.try_again, Toast.LENGTH_LONG).show();
                        // Toast.makeText(activity, t.toString(), Toast.LENGTH_LONG).show();
                        Log.d("error productDetail:", "" + t.toString());

                    }
                });
            } catch (Exception e) {
                hideProgressDialog();
                Toast.makeText(activity, R.string.try_again, Toast.LENGTH_LONG).show();

                e.printStackTrace();
            }
        } else {
            hideProgressDialog();
            Toast.makeText(activity, R.string.no_internet, Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onAddMainProductClick(int position, int value, float cost, float attr, ProgressBar progressBar) {
        if (HelperClass.getUserId(activity) != null && !HelperClass.getUserId(activity).isEmpty()) {

            String orderSession = HelperClass.getOrderSession(ProductsDeatils.this);
            if (orderSession != null && !orderSession.isEmpty()) {
                // Do Nothing
            } else {
                HelperClass.setOrderSession(randomNo(10) + "_" + randomString(32), ProductsDeatils.this);

            }

            addCart(Integer.valueOf(HelperClass.getUserId(activity)), productsModels.get(position).getPid(), value, cost,  attr, HelperClass.getOrderSession(activity)
                    , progressBar);
        } else {
            UIUtility.showDialog(activity, "You need to Login first");
        }
    }

    private void addCart(Integer user_id, int product_id, int product_qty, float product_amt, float attr, String order_session, ProgressBar progressBar) {
        Log.d("addCart:","product_amt: "+product_amt);
        if (HelperClass.getNetworkInfo(activity)) {
            progressBar.setVisibility(View.VISIBLE);

            try {
                ApiInterfaces apiInterfaces = ApiServices.apiService().create(ApiInterfaces.class);
                Call<AddToCartModel> call = apiInterfaces.addCart(user_id, product_id, product_qty, product_amt, attr, order_session);
                call.enqueue(new Callback<AddToCartModel>() {
                    @Override
                    public void onResponse(@NonNull Call<AddToCartModel> call, @NonNull Response<AddToCartModel> response) {
                        try {
                            assert response.body() != null;
                            if (response.isSuccessful() && response.body().getIsSuccess().equalsIgnoreCase(Constants.TRUE)) {
                                hideProgressDialog();
                                progressBar.setVisibility(View.GONE);

                            } else {
                                hideProgressDialog();
                                progressBar.setVisibility(View.GONE);

                                Toast.makeText(activity, response.body().getMsg(), Toast.LENGTH_SHORT).show();

                            }
                        } catch (Exception e) {
                            hideProgressDialog();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<AddToCartModel> call, @NonNull Throwable t) {
                        hideProgressDialog();
                        progressBar.setVisibility(View.GONE);

                        // Toast.makeText(activity, R.string.try_again, Toast.LENGTH_SHORT).show();
                        Toast.makeText(activity, "" + t.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
            } catch (Exception e) {
                hideProgressDialog();
                progressBar.setVisibility(View.GONE);

                e.printStackTrace();
            }
        } else {
            hideProgressDialog();
            progressBar.setVisibility(View.GONE);

            Toast.makeText(activity, R.string.no_internet, Toast.LENGTH_SHORT).show();


        }
    }

    @Override
    public void onUpdateListItemClick(int orderId, String qty, ProgressBar progressBar, TextView txt, float attr) {

        updateCartItem(String.valueOf(orderId), String.valueOf(qty), progressBar, txt, attr);

    }


    private void updateCartItem(String order_id, String product_qty, ProgressBar progressBar, TextView txt, float attr) {
        Log.d("updateCartItem  Id" + order_id + "", " product_qty :" + product_qty + " attr: " + attr);
        if (HelperClass.getNetworkInfo(activity)) {
            progressBar.setVisibility(View.VISIBLE);
            txt.setVisibility(View.GONE);

            try {
                ApiInterfaces apiInterfaces = ApiServices.apiService().create(ApiInterfaces.class);
                Call<UpdateProductModel> call = apiInterfaces.updateCartUsingId(order_id, HelperClass.getOrderSession(activity), product_qty, String.valueOf(attr));
                call.enqueue(new Callback<UpdateProductModel>() {
                    @Override
                    public void onResponse(@NonNull Call<UpdateProductModel> call, @NonNull Response<UpdateProductModel> response) {
                        try {
                            assert response.body() != null;
                            if (response.isSuccessful() && response.body().getIsSuccess().equalsIgnoreCase(Constants.TRUE)) {
                                hideProgressDialog();
                                assert response.body() != null;

                                //Toast.makeText(activity, "Cart updated Successfully!", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                txt.setVisibility(View.VISIBLE);


                            } else {
                                hideProgressDialog();
                                Toast.makeText(activity, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                txt.setVisibility(View.VISIBLE);
                            }
                        } catch (Exception e) {
                            hideProgressDialog();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<UpdateProductModel> call, @NonNull Throwable t) {
                        hideProgressDialog();
                        Toast.makeText(activity, R.string.try_again, Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        txt.setVisibility(View.VISIBLE);
                    }
                });
            } catch (Exception e) {
                hideProgressDialog();
                e.printStackTrace();
            }
        } else {
            hideProgressDialog();
            Toast.makeText(activity, R.string.no_internet, Toast.LENGTH_SHORT).show();
        }
    }
}
