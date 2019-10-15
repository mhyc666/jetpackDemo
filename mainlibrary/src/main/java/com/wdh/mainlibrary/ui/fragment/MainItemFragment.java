package com.wdh.mainlibrary.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.wdh.mainlibrary.databinding.FragmentMainItemBinding;

public class MainItemFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View mContentView = inflater.inflate(R.layout.fragment_main_item, container, false);
        FragmentMainItemBinding binding=FragmentMainItemBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
}
