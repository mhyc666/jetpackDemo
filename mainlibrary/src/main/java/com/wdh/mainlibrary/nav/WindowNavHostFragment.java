package com.wdh.mainlibrary.nav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;

public class WindowNavHostFragment extends NavHostFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View frameLayout = new WindowFrameLayout(inflater.getContext());
        frameLayout.setId(getId());
        return frameLayout;
    }
}
