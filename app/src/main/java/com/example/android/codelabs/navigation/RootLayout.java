package com.example.android.codelabs.navigation;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class RootLayout extends LinearLayout {
    public RootLayout(Context context) {
        super(context);
    }

    public RootLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


    }

    public RootLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RootLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
