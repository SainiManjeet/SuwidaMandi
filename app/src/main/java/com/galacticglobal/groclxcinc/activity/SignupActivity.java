package com.galacticglobal.groclxcinc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.galacticglobal.groclxcinc.R;
import com.galacticglobal.groclxcinc.classes.ApiServices;
import com.galacticglobal.groclxcinc.classes.BaseActivity;
import com.galacticglobal.groclxcinc.classes.Constants;
import com.galacticglobal.groclxcinc.classes.HelperClass;
import com.galacticglobal.groclxcinc.interfaces.ApiInterfaces;
import com.galacticglobal.groclxcinc.model_classes.sign_up.SignUpModel;
import com.galacticglobal.groclxcinc.util.UIUtility;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends BaseActivity implements View.OnClickListener {
    private EditText firstName, lastName, email, password, houseNo, streetAddress, phone;
    private LinearLayout submit;
    private LinearLayout mainLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sign_up);

        init();
    }

    private void init() {
        mainLayout = findViewById(R.id.main_layout);
        submit = findViewById(R.id.submit);
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        houseNo = findViewById(R.id.house_no);
        streetAddress = findViewById(R.id.street);
        phone = findViewById(R.id.phone);
        submit.setOnClickListener(this);

    }





    @Override
    public void onClick(View v) {
        if (v == submit) {

            validateData();

        }
    }


    private void validateData() {

        if (firstName.getText().toString().isEmpty()) {
            showErrorOnEditText(firstName, getResources().getString(R.string.name_required));
        } else if (lastName.getText().toString().isEmpty()) {
            showErrorOnEditText(lastName, getResources().getString(R.string.name_required));
        } else if (UIUtility.isNotValidEmail(email.getText().toString())) {
            showErrorOnEditText(email, getResources().getString(R.string.invalid_email));
        } else if (password.getText().toString().isEmpty() || password.getText().length() < 5) {
            showErrorOnEditText(password, getResources().getString(R.string.invalid_pass));
        } else if (houseNo.getText().toString().isEmpty()) {
            showErrorOnEditText(houseNo, getResources().getString(R.string.field_required));
        } else if (streetAddress.getText().toString().isEmpty()) {
            showErrorOnEditText(streetAddress, getResources().getString(R.string.field_required));
        } else if (phone.getText().toString().isEmpty() || phone.getText().length() < 10) {
            showErrorOnEditText(phone, getResources().getString(R.string.field_required_phn));
        } else {
            signUp(firstName.getText().toString(), lastName.getText().toString(), email.getText().toString().trim(), password.getText().toString().trim(),
                    streetAddress.getText().toString().trim(), phone.getText().toString(), houseNo.getText().toString());
        }

    }

    private void signUp(String fname, String lname, String email, String password, String address, String phone, String hno) {
        // Log.d("Param:=", " fName: " + fName + " lName: " + lName + " email " + email + " password: " + password + " address " + address+" phone: "+phone+  " hno: "+hno);
        if (HelperClass.getNetworkInfo(SignupActivity.this)) {
            showProgressDialog();
            try {
                ApiInterfaces apiInterfaces = ApiServices.apiService().create(ApiInterfaces.class);
                Call<SignUpModel> call = apiInterfaces.signUp(fname, lname, email, password, address, phone, hno);
                call.enqueue(new Callback<SignUpModel>() {
                    @Override
                    public void onResponse(@NonNull Call<SignUpModel> call, @NonNull Response<SignUpModel> response) {
                        try {
                            assert response.body() != null;
                            if (response.isSuccessful() && response.body().getIsSuccess().equalsIgnoreCase(Constants.TRUE)) {
                                hideProgressDialog();
                                assert response.body() != null;

                                Toast.makeText(SignupActivity.this, "Sign up Successfully", Toast.LENGTH_SHORT).show();
                                Intent in = new Intent(SignupActivity.this, LoginActivity.class);
                                startActivity(in);


                            } else {
                                hideProgressDialog();
                                HelperClass.snackbar(SignupActivity.this, mainLayout, response.body().getMsg());
                            }
                        } catch (Exception e) {
                            hideProgressDialog();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<SignUpModel> call, @NonNull Throwable t) {
                        hideProgressDialog();
                        HelperClass.snackbar(SignupActivity.this, mainLayout, R.string.try_again);
                        // HelperClass.snackbar(SignupActivity.this, mainLayout, t.getMessage());
                    }
                });
            } catch (Exception e) {
                hideProgressDialog();
                e.printStackTrace();
            }
        } else {
            hideProgressDialog();
            HelperClass.snackbar(SignupActivity.this, mainLayout, R.string.no_internet);
        }
    }


}