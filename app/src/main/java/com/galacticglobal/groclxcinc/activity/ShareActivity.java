package com.galacticglobal.groclxcinc.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.galacticglobal.groclxcinc.R;
import com.galacticglobal.groclxcinc.classes.BaseActivity;

public class ShareActivity extends BaseActivity {
    ImageView back;
    Button copyLink;
    ViewGroup share;
    private ClipboardManager myClipboard;
    private ClipData myClip;
    TextView link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_share);

        init();
    }

    private void init() {
        myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        share=findViewById(R.id.share);
        link=findViewById(R.id.txt_link);
        copyLink=findViewById(R.id.copy_link);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareTextUrl();
            }
        });
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        copyLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myClip = ClipData.newPlainText("text","https://play.google.com/store/apps/details?id=com.galacticglobal.groclxcinc&hl=en");
                myClipboard.setPrimaryClip(myClip);

                Toast.makeText(getApplicationContext(), "Text Copied",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void shareTextUrl() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Hey check out my app at: https://play.google.com/store/apps/details?id=com.galacticglobal.groclxcinc&hl=en");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
}
