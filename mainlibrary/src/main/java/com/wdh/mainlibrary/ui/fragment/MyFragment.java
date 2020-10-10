package com.wdh.mainlibrary.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wdh.mainlibrary.databinding.FragmentMyBinding;

public class MyFragment extends Fragment {

    private View lastView = null;//记录上次创建的view
    private boolean isNavigationViewInit = false;//记录是否已经初始化过一次视图

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View mContentView = inflater.inflate(R.layout.fragment_my, container, false);
        if (lastView == null) {
            FragmentMyBinding binding = FragmentMyBinding.inflate(inflater, container, false);
            lastView = binding.getRoot();
        }
        return lastView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (!isNavigationViewInit) {//初始化过视图则不再进行view和data初始化
            super.onViewCreated(view, savedInstanceState);
            isNavigationViewInit = true;
        }
    }
}
