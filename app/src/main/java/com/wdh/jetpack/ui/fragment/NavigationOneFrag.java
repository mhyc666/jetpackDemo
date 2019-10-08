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

import java.util.Objects;

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
                //https://developer.android.google.cn/guide/navigation/navigation-pass-data?hl=en#java
                //安全的方法需要使用safeargs插件 这是使用导航时传递数据的首选方式，因为它可以确保类型安全。
                String amount = "山印斜阳天接水";
                NavigationOneFragDirections.ActionNavigationOneToNavigationTwoFrag
                        action = NavigationOneFragDirections.actionNavigationOneToNavigationTwoFrag();
                action.setNavTwoArg(amount);


//                EditText amountTv = (EditText) getView().findViewById(R.id.editTextAmount);
//                int amount = Integer.parseInt(amountTv.getText().toString());
//                ConfirmationAction action =
//                        SpecifyAmountFragmentDirections.confirmationAction()
//                action.setAmount(amount)
//                Navigation.findNavController(view).navigate(action);
                //可以使用捆绑包直接传递数据
//                Bundle bundle = new Bundle();
//                bundle.putString("amount", amount);
//                mTv.setText(Objects.requireNonNull(getArguments()).getString("amount"));
                Navigation.findNavController(v).navigate(action);//R.id.action_navigation_one_to_navigationTwoFrag,bundle

                break;
            default:
                break;

        }
    }
}


