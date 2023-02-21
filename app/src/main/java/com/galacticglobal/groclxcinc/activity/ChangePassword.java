package com.galacticglobal.groclxcinc.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.galacticglobal.groclxcinc.R;
import com.galacticglobal.groclxcinc.classes.ApiServices;
import com.galacticglobal.groclxcinc.classes.BaseActivity;
import com.galacticglobal.groclxcinc.classes.Constants;
import com.galacticglobal.groclxcinc.classes.HelperClass;
import com.galacticglobal.groclxcinc.interfaces.ApiInterfaces;
import com.galacticglobal.groclxcinc.model_classes.change_pass.ChangePassModel;
import com.galacticglobal.groclxcinc.util.UIUtility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends BaseActivity {

    Activity activity;
    EditText old_pass, new_pass, confirm_pass;
    ViewGroup Update, mainLayout;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        init();
    }

    private void init() {

        activity = ChangePassword.this;
        mainLayout = findViewById(R.id.main_layout);
        back = findViewById(R.id.back);
        old_pass = findViewById(R.id.old_pass);
        new_pass = findViewById(R.id.new_pass);
        confirm_pass = findViewById(R.id.confirm_pass);
        Update = findViewById(R.id.submit);

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void validate() {
        if (HelperClass.getUserId(activity) != null && !HelperClass.getUserId(activity).isEmpty()) {

            if (!old_pass.getText().toString().isEmpty() && !confirm_pass.getText().toString().isEmpty() && !
                    new_pass.getText().toString().isEmpty()) {
                if (new_pass.getText().toString().equals(confirm_pass.getText().toString())) {
                    login(HelperClass.getUserId(activity), old_pass.getText().toString(), confirm_pass.getText().toString());
                } else {
                    Toast.makeText(activity, "New Password & Confirm Password must be same!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(activity, "All Fields Required!", Toast.LENGTH_SHORT).show();
            }
        } else {
            UIUtility.showDialog(activity, "You need to Login first");
        }
    }


    private void login(String user_id, String old_password, String new_password) {
        if (HelperClass.getNetworkInfo(ChangePassword.this)) {
            showProgressDialog();
            try {
                ApiInterfaces apiInterfaces = ApiServices.apiService().create(ApiInterfaces.class);
                Call<ChangePassModel> call = apiInterfaces.pass(user_id, old_password, new_password);
                call.enqueue(new Callback<ChangePassModel>() {
                    @Override
                    public void onResponse(@NonNull Call<ChangePassModel> call, @NonNull Response<ChangePassModel> response) {
                        try {
                            assert response.body() != null;
                            if (response.isSuccessful() && response.body().getIsSuccess().equalsIgnoreCase(Constants.TRUE)) {
                                hideProgressDialog();
                                assert response.body() != null;

                                Toast.makeText(activity, "Password has been Changed!", Toast.LENGTH_SHORT).show();
                                Intent in = new Intent(activity, ChangePassword.class);
                                startActivity(in);
                            } else {
                                hideProgressDialog();
                                HelperClass.snackbar(ChangePassword.this, mainLayout, response.body().getMsg());
                            }
                        } catch (Exception e) {
                            hideProgressDialog();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ChangePassModel> call, @NonNull Throwable t) {
                        hideProgressDialog();
                        HelperClass.snackbar(ChangePassword.this, mainLayout, R.string.try_again);
                        // HelperClass.snackbar(LoginActivity.this, mainLayout, ""+t.toString());
                    }
                });
            } catch (Exception e) {
                hideProgressDialog();
                e.printStackTrace();
            }
        } else {
            hideProgressDialog();
            HelperClass.snackbar(ChangePassword.this, mainLayout, R.string.no_internet);
        }
    }
}
