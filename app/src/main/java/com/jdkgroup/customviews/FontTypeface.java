package com.jdkgroup.customviews;

import android.content.Context;
import android.graphics.Typeface;

import com.jdkgroup.constant.AppConstant;

public class FontTypeface {
    private Context context;

    public FontTypeface(Context context) {
        this.context = context;
    }

    public Typeface getTypefaceAndroid() {
        Typeface typeFace = Typeface.DEFAULT;
        String strFont = "Assets/" + AppConstant.FONT_AILERON_REGULAR;
        typeFace = Typeface.createFromAsset(context.getAssets(), strFont.replace("Assets/", ""));
        return typeFace;
    }
}