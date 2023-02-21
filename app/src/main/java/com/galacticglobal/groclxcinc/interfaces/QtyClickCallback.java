package com.galacticglobal.groclxcinc.interfaces;

import android.widget.ProgressBar;
import android.widget.TextView;

public interface QtyClickCallback {


   // void onUpdateListItemClick(int orderId, String qty, ProgressBar progressBar, TextView txt);
    void onUpdateListItemClick(int orderId, String qty, ProgressBar progressBar, TextView txt,float attr);


}
