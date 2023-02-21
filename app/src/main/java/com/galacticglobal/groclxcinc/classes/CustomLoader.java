package com.galacticglobal.groclxcinc.classes;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.galacticglobal.groclxcinc.R;

public class CustomLoader extends Dialog {

    Animation animatnRotate;
    private CustomLoader d;
    private ImageView loader;

    public CustomLoader(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_custom_loader);
        loader = findViewById(R.id.loader);
        animatnRotate = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_clockwise);
        loader.startAnimation(animatnRotate);

        d = CustomLoader.this;
        d.setCancelable(false);
    }

    private void rotateLoader(ImageView imageView, int stateDegree, int endDegree) {
        RotateAnimation rotateAnimation = new RotateAnimation(stateDegree, endDegree,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setDuration(1000);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setRepeatCount(RotateAnimation.INFINITE);
        imageView.startAnimation(rotateAnimation);

    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void hide() {
        super.hide();
        loader.clearAnimation();
    }

    @Override
    protected void onStop() {
        super.onStop();
        loader.clearAnimation();
    }

    public void onBackPress() {
        super.onBackPressed();
        dismiss();
    }

}
