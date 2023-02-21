package com.galacticglobal.groclxcinc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.galacticglobal.groclxcinc.R;
import com.galacticglobal.groclxcinc.classes.ApiServices;
import com.galacticglobal.groclxcinc.classes.BaseActivity;
import com.galacticglobal.groclxcinc.classes.Constants;
import com.galacticglobal.groclxcinc.classes.HelperClass;
import com.galacticglobal.groclxcinc.interfaces.ApiInterfaces;
import com.galacticglobal.groclxcinc.model_classes.ForgotModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassActivity extends BaseActivity {
private EditText email;
private LinearLayout mainLayout,submit;
private ImageView cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_forgot_pass);
        init();
    }

    private void init() {
        cancel=findViewById(R.id.img_cross);
        email=findViewById(R.id.email);
        mainLayout=findViewById(R.id.main_layout);
        submit=findViewById(R.id.submit);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ForgotPassActivity.this,LoginActivity.class);
                startActivity(in);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!email.getText().toString().isEmpty()){
                    forgotPassword(email.getText().toString());
                }
                else{
                    showErrorOnEditText(email, getResources().getString(R.string.email_required));
                }
            }
        });
    }
    private void forgotPassword(String email) {
        Log.d("Email is: ",""+email);
        if (HelperClass.getNetworkInfo(ForgotPassActivity.this)) {
            showProgressDialog();
            try {
                ApiInterfaces apiInterfaces = ApiServices.apiService().create(ApiInterfaces.class);
                Call<ForgotModel> call = apiInterfaces.forgotPass(email);
                call.enqueue(new Callback<ForgotModel>() {
                    @Override
                    public void onResponse(@NonNull Call<ForgotModel> call, @NonNull Response<ForgotModel> response) {
                        try {
                            assert response.body() != null;
                            Log.d("Response is: ",""+response.isSuccessful());
                            if (response.isSuccessful() && response.body().getIsSuccess().equalsIgnoreCase(Constants.TRUE)) {
                                hideProgressDialog();
                                assert response.body() != null;

                                HelperClass.snackbar(ForgotPassActivity.this, mainLayout, response.body().getMsg());

                            } else {
                                hideProgressDialog();
                                HelperClass.snackbar(ForgotPassActivity.this, mainLayout, response.body().getMsg());
                            }
                        } catch (Exception e) {
                            hideProgressDialog();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ForgotModel> call, @NonNull Throwable t) {
                        hideProgressDialog();
                        HelperClass.snackbar(ForgotPassActivity.this, mainLayout, R.string.try_again);
                    }
                });
            } catch (Exception e) {
                hideProgressDialog();
                e.printStackTrace();
            }
        } else {
            hideProgressDialog();
            HelperClass.snackbar(ForgotPassActivity.this, mainLayout, R.string.no_internet);
        }
    }



}
