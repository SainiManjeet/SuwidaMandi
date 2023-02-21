package com.galacticglobal.groclxcinc.classes;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.galacticglobal.groclxcinc.application.GroceryApplication;

import java.security.SecureRandom;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();
    public ImageView mIvFilterJobs;
    ProgressDialog progressDialog;
    private Toast toast;
    private GroceryApplication application;
    static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static final String NoString = "0123456789";
    static SecureRandom rnd = new SecureRandom();

    @SuppressLint("ShowToast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        application = (GroceryApplication) getApplication();
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
       /* progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);*/
    }


    public void showProgressDialog() {
        progressDialog = ProgressDialog.show(this, null, "Loading...", true);
        progressDialog.setCancelable(false);
    }

    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

   public String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    public String randomNo(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(NoString.charAt(rnd.nextInt(NoString.length())));
        return sb.toString();
    }

    public void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    protected void showToast(String message) {
        toast.setText(message);
        toast.show();
    }

    public void showErrorOnEditText(EditText editText, String error) {
        editText.setError(error);
        editText.requestFocus();
    }

    public void showErrorOnText(TextView editText, String error) {
        editText.setError(error);
        editText.requestFocus();
    }

    <T> void startFragment(Fragment tClass, Bundle bundle, Integer id) {
        tClass.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(id, tClass);
        fragmentTransaction.commit();
    }

//    public <T> void startActivity(Class<T> tClass) {
//        Intent intent = new Intent(this, tClass);
//        startActivity(intent);
//        startWithAnim();
//    }
//
//    protected void startWithAnim() {
//        overridePendingTransition(R.anim.anim_left_in, R.anim.anim_left_out);
//    }
//
//    public <T> void finishStartActivity(Class<T> tClass) {
//        Intent intent = new Intent(this, tClass);
//        startActivity(intent);
//        finish();
//        //
//        overridePendingTransition(R.anim.anim_left_in, R.anim.anim_left_out);
//    }

}
