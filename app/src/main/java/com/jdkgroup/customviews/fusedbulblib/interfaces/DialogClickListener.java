package com.jdkgroup.customviews.fusedbulblib.interfaces;

import android.app.Activity;
import android.app.Dialog;

public interface DialogClickListener {
    void positiveListener(Activity context, Dialog dialog);
    void negativeListener(Activity context, Dialog dialog);
}
