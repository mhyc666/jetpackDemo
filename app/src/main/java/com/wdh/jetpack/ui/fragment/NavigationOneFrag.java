package com.wdh.jetpack.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.wdh.jetpack.R;

public class NavigationOneFrag extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mContentView = inflater.inflate(R.layout.fragment_navigation_one, container, false);
        initView(mContentView);
        return mContentView;
    }

    private void initView(View mContentView) {
        ImageView iv1 = mContentView.findViewById(R.id.iv1);
        ImageView iv2 = mContentView.findViewById(R.id.iv2);
        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv1:
                Navigation.findNavController(v).navigate(R.id.action_navigation_one_to_blankFragment);
                break;
            case R.id.iv2:
                Navigation.findNavController(v).navigate(R.id.action_navigation_one_to_navigationTwoFrag);
                break;
            default:
                break;

        }
    }
}
