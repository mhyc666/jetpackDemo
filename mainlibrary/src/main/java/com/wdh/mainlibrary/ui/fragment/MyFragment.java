package com.wdh.mainlibrary.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wdh.mainlibrary.R;
import com.wdh.mainlibrary.databinding.FragmentMainItemBinding;
import com.wdh.mainlibrary.databinding.FragmentMyBinding;

public class MyFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View mContentView = inflater.inflate(R.layout.fragment_my, container, false);
        FragmentMyBinding binding=FragmentMyBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
}
