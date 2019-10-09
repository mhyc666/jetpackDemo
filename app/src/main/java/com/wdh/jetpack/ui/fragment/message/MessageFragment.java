package com.wdh.jetpack.ui.fragment.message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.wdh.jetpack.R;
import com.wdh.jetpack.databinding.FragmentMessageFragmentBinding;

public class MessageFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentMessageFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_message_fragment, container, false);
        MessageCtrl messageCtrl=new MessageCtrl(binding);
        binding.setMessageCtrl(messageCtrl);
        return binding.getRoot();
//        View mContentView = inflater.inflate(R.layout.fragment_message_fragment, container, false);
//        initView();
//        return mContentView;
    }

    /**
     * activity
     * ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
     * User user = new User("Test", "User");
     * binding.setUser(user);
     */

//    private void initView() {
//
//    }
}
