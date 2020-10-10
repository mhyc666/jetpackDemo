package com.wdh.mainlibrary.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wdh.mainlibrary.control.MainFragCtrl;
import com.wdh.mainlibrary.databinding.FragmentMainBinding;


public class MainFragment extends Fragment {

    private boolean isNavigationViewInit = false;//记录是否已经初始化过一次视图
    private View lastView = null;//记录上次创建的view


    private MainFragCtrl mainFragCtrl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View mContentView = inflater.inflate(R.layout.fragment_main, container, false);
        //如果fragment的view已经创建则不再重新创建
        if (lastView == null) {
            FragmentMainBinding binding = FragmentMainBinding.inflate(inflater, container, false);
            mainFragCtrl = new MainFragCtrl(binding, this);
            binding.setViewCtrl(mainFragCtrl);
            lastView = binding.getRoot();
        }
        return lastView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (!isNavigationViewInit) {//初始化过视图则不再进行view和data初始化
            mainFragCtrl.setViewPage(this);
            isNavigationViewInit = true;
        }
    }
}
