package com.galacticglobal.groclxcinc.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Patterns;

import com.afollestad.materialdialogs.MaterialDialog;
import com.galacticglobal.groclxcinc.activity.LoginActivity;

public class UIUtility {


    public static boolean isNotValidEmail(CharSequence target) {
        return TextUtils.isEmpty(target) || !Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }


    public static void showDialog(Context context, String message) {
        try {
            MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                    .content(message).contentColor(Color.BLACK)
                    .positiveText("Okay")/*.negativeText("Cancel")
                    .cancelable(false).onNegative((dialog, which) -> {
                        dialog.dismiss();
                    })*/
                    .onPositive((dialog, which) -> {
                        Intent in = new Intent(context, LoginActivity.class);
                        context.startActivity(in);
                        dialog.dismiss();

                    });
            builder.show();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
