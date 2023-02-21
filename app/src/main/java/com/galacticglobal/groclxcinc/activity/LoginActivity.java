package com.galacticglobal.groclxcinc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.galacticglobal.groclxcinc.R;
import com.galacticglobal.groclxcinc.classes.ApiServices;
import com.galacticglobal.groclxcinc.classes.BaseActivity;
import com.galacticglobal.groclxcinc.classes.Constants;
import com.galacticglobal.groclxcinc.classes.HelperClass;
import com.galacticglobal.groclxcinc.interfaces.ApiInterfaces;
import com.galacticglobal.groclxcinc.model_classes.login.LoginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    EditText email, password;
    private TextView forgotPassword,sign_up;
    private LinearLayout mainLayout;
    private Button submit;
    ImageView cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        init();
    }

    private void init() {

        mainLayout=findViewById(R.id.main_layout);
        submit=findViewById(R.id.submit);
        forgotPassword = findViewById(R.id.forgot_pass);
        sign_up = findViewById(R.id.sign_up);
        forgotPassword.setOnClickListener(this);
        submit.setOnClickListener(this);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        cancel=findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(LoginActivity.this,HomeScreenActivity.class);
                startActivity(in);
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Intent in = new Intent(LoginActivity.this, SignupActivity.class);
                                           startActivity(in);


                                       }
                                   }
        );

    }


    @Override
    public void onClick(View v) {
        if (v == forgotPassword) {
            Intent in = new Intent(LoginActivity.this, ForgotPassActivity.class);
            startActivity(in);
        }
        if (v == submit) {

            validate();
//          if (!email.getText().toString().isEmpty()&&!password.getText().toString().isEmpty()){
//              login(email.getText().toString(),password.getText().toString());
//          }
//          else{
//              HelperClass.snackbar(LoginActivity.this, mainLayout,R.string.field_required);
//          }
        }


    }

    private void validate() {
        if (email.getText().toString().isEmpty()){
            showErrorOnEditText(email, getResources().getString(R.string.email_required));

        }else if(password.getText().toString().isEmpty()){
            showErrorOnEditText(password, getResources().getString(R.string.password_fill));

        }else{
            login(email.getText().toString(),password.getText().toString());

        }
    }

    private void login(String username,String password) {
        Log.d("Email is: ", "" + email);
        if (HelperClass.getNetworkInfo(LoginActivity.this)) {
            showProgressDialog();
            try {
                ApiInterfaces apiInterfaces = ApiServices.apiService().create(ApiInterfaces.class);
                Call<LoginModel> call = apiInterfaces.login(username,password);
                call.enqueue(new Callback<LoginModel>() {
                    @Override
                    public void onResponse(@NonNull Call<LoginModel> call, @NonNull Response<LoginModel> response) {
                        try {
                            assert response.body() != null;
                            if (response.isSuccessful() && response.body().getIsSuccess().equalsIgnoreCase(Constants.TRUE)) {
                                hideProgressDialog();
                                assert response.body() != null;




                                HelperClass.setUserName(response.body().getData().get(0).getUserFname(),LoginActivity.this);
                                HelperClass.setUserId(String.valueOf(response.body().getData().get(0).getUserId()),LoginActivity.this);
                                HelperClass.setAddress(String.valueOf(response.body().getData().get(0).getUserAddress()),LoginActivity.this);

                                HelperClass.setFName(String.valueOf(response.body().getData().get(0).getUserFname()),LoginActivity.this);
                                HelperClass.setLName(String.valueOf(response.body().getData().get(0).getUserLname()),LoginActivity.this);
                                HelperClass.setPhone(String.valueOf(response.body().getData().get(0).getUserPhonePrimary()),LoginActivity.this);

                                HelperClass.setEmail(String.valueOf(response.body().getData().get(0).getUserEmail()),LoginActivity.this);



                                Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                Intent in = new Intent(LoginActivity.this,HomeScreenActivity.class);
                                startActivity(in);
                            } else {
                                hideProgressDialog();
                                HelperClass.snackbar(LoginActivity.this, mainLayout, response.body().getMsg());
                            }
                        } catch (Exception e) {
                            hideProgressDialog();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<LoginModel> call, @NonNull Throwable t) {
                        hideProgressDialog();
                        HelperClass.snackbar(LoginActivity.this, mainLayout, R.string.try_again);
                       // HelperClass.snackbar(LoginActivity.this, mainLayout, ""+t.toString());
                    }
                });
            } catch (Exception e) {
                hideProgressDialog();
                e.printStackTrace();
            }
        } else {
            hideProgressDialog();
            HelperClass.snackbar(LoginActivity.this, mainLayout, R.string.no_internet);
        }
    }

}
