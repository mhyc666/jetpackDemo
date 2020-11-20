package com.wdh.mainlibrary.nav;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowInsets;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@SuppressLint("NewApi")
public class WindowFrameLayout extends FrameLayout {

    public WindowFrameLayout(@NonNull Context context) {
        super(context);
    }

    public WindowFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public WindowFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    public void addView(View child) {
        super.addView(child);
        requestApplyInsets();
    }

    @Override
    public WindowInsets dispatchApplyWindowInsets(WindowInsets windowInsets) {
        WindowInsets insets = dispatchApplyWindowInsets(windowInsets);
        if (!insets.isConsumed()) {
            int count = getChildCount();
            for (int i = 0; i < count; i++) {
                windowInsets = getChildAt(i).dispatchApplyWindowInsets(insets);
            }
        }
        return windowInsets;
    }
}
