package com.wdh.mainlibrary.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wdh.mainlibrary.R;
import com.wdh.mainlibrary.databinding.FragmentMyBinding;
import com.wdh.mainlibrary.databinding.FragmentOrderBinding;

public class OrderFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentOrderBinding binding=FragmentOrderBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
}
