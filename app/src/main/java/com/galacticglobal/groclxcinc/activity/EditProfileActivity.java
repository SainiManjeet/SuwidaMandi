package com.galacticglobal.groclxcinc.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.galacticglobal.groclxcinc.R;
import com.galacticglobal.groclxcinc.classes.ApiServices;
import com.galacticglobal.groclxcinc.classes.BaseActivity;
import com.galacticglobal.groclxcinc.classes.Constants;
import com.galacticglobal.groclxcinc.classes.HelperClass;
import com.galacticglobal.groclxcinc.interfaces.ApiInterfaces;
import com.galacticglobal.groclxcinc.model_classes.edit_profile_model.Datum;
import com.galacticglobal.groclxcinc.model_classes.edit_profile_model.EditProfileModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends BaseActivity implements View.OnClickListener {

    Activity activity;
    RadioButton same, different;
    EditText user_address;
    TextView same_add_txt;
    ViewGroup same_add_lay;
    private EditText firstName, lastName, email, password, houseNo, streetAddress, phone;
    private LinearLayout submit;
    //List<Datum> productsModels;
    List<Datum> editModel;
    ImageView cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        init();
    }

    private void init() {

        activity = EditProfileActivity.this;
        cancel=findViewById(R.id.img_cross);
        cancel.setOnClickListener(v -> {
            Intent in = new Intent(activity,HomeScreenActivity.class);
            startActivity(in);
        });

        same = findViewById(R.id.same);
        different = findViewById(R.id.different);
        same_add_txt = findViewById(R.id.same_add_txt);
        same_add_lay = findViewById(R.id.same_add_lay);

        submit = findViewById(R.id.submit);
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        streetAddress = findViewById(R.id.street);
        user_address = findViewById(R.id.user_address);
        phone = findViewById(R.id.phone);


        submit.setOnClickListener(this);

        same.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                different.setChecked(false);
                same_add_lay.setVisibility(View.GONE);
                same_add_txt.setVisibility(View.GONE);
            }
        });

        different.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                same.setChecked(false);
                same_add_lay.setVisibility(View.VISIBLE);
                same_add_txt.setVisibility(View.VISIBLE);
            }
        });

        setData();
    }

    private void setData() {
        if (HelperClass.getUserId(activity) != null && !HelperClass.getUserId(activity).isEmpty()) {
            firstName.setText(HelperClass.getFName(activity));
            lastName.setText(HelperClass.getLName(activity));
            streetAddress.setText(HelperClass.getAddress(activity));
            phone.setText(HelperClass.getPhone(activity));
        }
    }

    @Override
    public void onClick(View v) {

        if (v == submit) {
            validate();
        }

    }


    private void validate() {
        if (phone.getText().toString().isEmpty() || phone.getText().length() < 10 ) {
            showErrorOnEditText(phone, getResources().getString(R.string.field_required_phn));

        }
        else if(firstName.getText().toString().trim().isEmpty()){
            showErrorOnEditText(firstName, getResources().getString(R.string.field_required_fname));
        }
        else if(lastName.getText().toString().trim().isEmpty()){
            showErrorOnEditText(lastName, getResources().getString(R.string.field_required_lname));
        }
        else if(streetAddress.getText().toString().trim().isEmpty()){
            showErrorOnEditText(streetAddress, getResources().getString(R.string.field_required_add));
        }


        else{

            if (same.isChecked()) {
                ediProfile(Integer.parseInt(HelperClass.getUserId(activity)), firstName.getText().toString().trim(), lastName.getText().toString().trim(), streetAddress.getText().toString().trim()
                        , phone.getText().toString(), "same", streetAddress.getText().toString().trim());

            } else if (different.isChecked()) {
                ediProfile(Integer.parseInt(HelperClass.getUserId(activity)), firstName.getText().toString().trim(), lastName.getText().toString().trim(), streetAddress.getText().toString().trim()
                        , phone.getText().toString(), "different", user_address.getText().toString().trim());
            }
        }
    }




    private void ediProfile(int user_id, String fName, String lName, String address,
                            String phone_no, String billing_address, String user_bill_address) {

        if (HelperClass.getNetworkInfo(EditProfileActivity.this)) {
            showProgressDialog();
            try {
                ApiInterfaces apiInterfaces = ApiServices.apiService().create(ApiInterfaces.class);
                Call<EditProfileModel> call = apiInterfaces.editProfile(user_id, fName, lName, address, phone_no, billing_address, user_bill_address);
                call.enqueue(new Callback<EditProfileModel>() {
                    @Override
                    public void onResponse(@NonNull Call<EditProfileModel> call, @NonNull Response<EditProfileModel> response) {
                        try {
                            assert response.body() != null;
                            if (response.isSuccessful() && response.body().getIsSuccess().equalsIgnoreCase(Constants.TRUE)) {
                                hideProgressDialog();
                                assert response.body() != null;
                                editModel=response.body().getData();
                                HelperClass.setFName(editModel.get(0).getUserFname(),activity);
                                HelperClass.setLName(editModel.get(0).getUserLname(),activity);
                                HelperClass.setPhone(editModel.get(0).getUserPhonePrimary(),activity);
                                HelperClass.setAddress(editModel.get(0).getUserAddress(),activity);
                                //launchActivity(response.body().getData());


                                Intent in = new Intent(activity, HomeScreenActivity.class);
                                startActivity(in);
                                //  HelperClass.snackbar(EditProfileActivity.this, mainLayout, response.body().getMsg());

                            } else {
                                hideProgressDialog();
                                //    HelperClass.snackbar(EditProfileActivity.this, mainLayout, response.body().getMsg());
                            }
                        } catch (Exception e) {
                            hideProgressDialog();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<EditProfileModel> call, @NonNull Throwable t) {
                        hideProgressDialog();

                        Toast.makeText(activity, R.string.try_again, Toast.LENGTH_LONG).show();
                        //      HelperClass.snackbar(SignupActivity.this, mainLayout, R.string.try_again);
                    }
                });
            } catch (Exception e) {
                hideProgressDialog();
                e.printStackTrace();
            }
        } else {
            hideProgressDialog();
            //  HelperClass.snackbar(SignupActivity.this, mainLayout, R.string.no_internet);
            Toast.makeText(activity, R.string.no_internet, Toast.LENGTH_LONG).show();

        }
    }


}
