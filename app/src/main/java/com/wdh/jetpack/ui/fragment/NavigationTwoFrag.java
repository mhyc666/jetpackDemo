package com.wdh.jetpack.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.wdh.jetpack.R;

import java.util.Objects;

public class NavigationTwoFrag extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mContentView = inflater.inflate(R.layout.fragment_navigation_two, container, false);
        initView(mContentView);
        return mContentView;
    }

    @SuppressLint("NewApi")
    private void initView(View mContentView) {
        TextView mTv = mContentView.findViewById(R.id.tv);
        mTv.setText(NavigationTwoFragArgs.fromBundle(Objects.requireNonNull(getArguments())).getNavTwoArg());
        //mTv.setText(Objects.requireNonNull(getArguments()).getString("amount"));
//        mTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
        //navigate(int) 获取操作或目标的资源ID
//                Navigation.findNavController(v).navigate(R.id.action_navigation_one_to_blankFragment);
//            }
//        });
        //对于按钮，还可以使用 Navigation类的 createNavigateOnClickListener() 便捷方法导航到目标
        mTv.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_navigation_one_to_blankFragment, null));
    }
}
