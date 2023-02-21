package com.galacticglobal.groclxcinc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.galacticglobal.groclxcinc.R;
import com.galacticglobal.groclxcinc.classes.ApiServices;
import com.galacticglobal.groclxcinc.classes.BaseActivity;
import com.galacticglobal.groclxcinc.classes.Constants;
import com.galacticglobal.groclxcinc.classes.HelperClass;
import com.galacticglobal.groclxcinc.interfaces.ApiInterfaces;
import com.galacticglobal.groclxcinc.model_classes.cate_wise_product.CateProduct;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryWiseBuyProduct extends BaseActivity implements View.OnClickListener {
    private LinearLayout mainLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_wise_buy_product);

        mainLayout=findViewById(R.id.main_layout);
        Intent mIntent = getIntent();
        String productName=mIntent.getStringExtra("name");
       // Toast.makeText(this, "Position is: "+mValue, Toast.LENGTH_SHORT).show();
        getData(productName);

    }


    private void getData(String name) {
        if (name.equalsIgnoreCase("Fruits & Vegetables")){
            getProduct("6");

        }
        else if (name.equalsIgnoreCase("Foodgrains,Oils & Masalas")){
            getProduct("8");
        }
        else if (name.equalsIgnoreCase("Cleaning & Household")){
            getProduct("20");
        }
        else  if (name.equalsIgnoreCase("Personal Care")){
            getProduct("21");
        }
        else  if (name.equalsIgnoreCase("Snacks & Beverages")){
            getProduct("1");
        }
        else  if (name.equalsIgnoreCase("Bread,Dairy & Eggs")){
            getProduct("5");
        }
        // Staples corner
        else  if (name.equalsIgnoreCase("Namkeens")){
            getProduct("13");
        } else  if (name.equalsIgnoreCase("Atta & Flour")){
            getProduct("8");
        } else  if (name.equalsIgnoreCase("Dals & Pulses")){
            getProduct("24");
        } else  if (name.equalsIgnoreCase("Rice & Rice Products")){
            getProduct("22");
        }
        else{
            getProduct("10");// Cooking
        }
    }

    @Override
    public void onClick(View v) {

    }

    private void getProduct(String cateId) {

        if (HelperClass.getNetworkInfo(CategoryWiseBuyProduct.this)) {
            showProgressDialog();
            try {
                ApiInterfaces apiInterfaces = ApiServices.apiService().create(ApiInterfaces.class);
                Call<CateProduct> call = apiInterfaces.cateWiseProduct(cateId);
                call.enqueue(new Callback<CateProduct>() {
                    @Override
                    public void onResponse(@NonNull Call<CateProduct> call, @NonNull Response<CateProduct> response) {
                        try {
                            assert response.body() != null;
                            if (response.isSuccessful() && response.body().getIsSuccess().equalsIgnoreCase(Constants.TRUE)) {
                                hideProgressDialog();
                                assert response.body() != null;
                                   HelperClass.snackbar(CategoryWiseBuyProduct.this, mainLayout, response.body().getMsg());

                                Toast.makeText(CategoryWiseBuyProduct.this, response.body().getMsg(),Toast.LENGTH_SHORT).show();
                            } else {
                                hideProgressDialog();
                                  HelperClass.snackbar(CategoryWiseBuyProduct.this, mainLayout, response.body().getMsg());
                                Toast.makeText(CategoryWiseBuyProduct.this, response.body().getMsg(),Toast.LENGTH_SHORT).show();

                            }
                        } catch (Exception e) {
                            hideProgressDialog();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<CateProduct> call, @NonNull Throwable t) {
                        hideProgressDialog();
                        Toast.makeText(CategoryWiseBuyProduct.this,  R.string.try_again,Toast.LENGTH_SHORT).show();

                    }
                });
            } catch (Exception e) {
                hideProgressDialog();
                e.printStackTrace();
            }
        } else {
            hideProgressDialog();
            Toast.makeText(CategoryWiseBuyProduct.this,  R.string.no_internet,Toast.LENGTH_SHORT).show();

            // HelperClass.snackbar(LoginActivity.this, mainLayout, R.string.no_internet);
        }
    }

}
