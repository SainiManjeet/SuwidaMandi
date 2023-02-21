package com.galacticglobal.groclxcinc.classes;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.galacticglobal.groclxcinc.R;
import com.galacticglobal.groclxcinc.application.GroceryApplication;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;


public class BaseFragment extends Fragment {
    private static final String TAG = BaseFragment.class.getSimpleName();
    private String[] listItems = {"Moving", "Cleaning"};
    private String[] listItemsType = {"Moving", "Cleaning"};
    private String[] listItemsQuote = {"All", "Call", "Email"};
    private SharedPreferences mSharedPreferenceHelper;
    private Toast toast;
    private ProgressDialog progressDialog;
    private Context context;
    private GroceryApplication application;

    public static void snackbar(Context context, ViewGroup layout, int message) {
        Snackbar.make(layout, message, Snackbar.LENGTH_LONG)
                .setAction("Hide", v -> {
                }).setActionTextColor(context.getResources().getColor(R.color.white)).show();
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @SuppressLint("ShowToast")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        mSharedPreferenceHelper = Objects.requireNonNull(getActivity()).getSharedPreferences("MaidPickerPrefs", Context.MODE_PRIVATE);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@androidx.annotation.NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            application = (GroceryApplication) Objects.requireNonNull(getActivity()).getApplication();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void showToast(String message) {
        toast.setText(message);
        toast.show();
    }

    public void showProgressDialog() {
        progressDialog = ProgressDialog.show(getActivity(), null, "Loading...", true);
        progressDialog.setCancelable(false);
    }

    public void hideProgressDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

}
