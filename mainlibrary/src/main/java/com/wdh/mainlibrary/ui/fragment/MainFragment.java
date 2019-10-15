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

    private MainFragCtrl mainFragCtrl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View mContentView = inflater.inflate(R.layout.fragment_main, container, false);
        FragmentMainBinding binding = FragmentMainBinding.inflate(inflater, container, false);
        mainFragCtrl = new MainFragCtrl(binding, this);
        binding.setViewCtrl(mainFragCtrl);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mainFragCtrl.setViewPage(this);
    }
}
