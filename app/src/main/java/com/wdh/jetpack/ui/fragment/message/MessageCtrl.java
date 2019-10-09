package com.wdh.jetpack.ui.fragment.message;

import android.util.SparseArray;
import android.view.View;

import com.wdh.jetpack.R;
import com.wdh.jetpack.databinding.FragmentMessageFragmentBinding;


public class MessageCtrl {
    private FragmentMessageFragmentBinding binding;
    private final MessageModel model;

    MessageCtrl(FragmentMessageFragmentBinding binding) {
        this.binding = binding;
        model = new MessageModel("以前我叫怂怂");
        model.bottomTxt.set("迷迷蒙蒙你给的梦");
        binding.setMessageModel(model);
        SparseArray<String> sparseArray = new SparseArray<>();
        sparseArray.put(1, "哈哈哈");
        sparseArray.put(2, "嘿嘿嘿");
        binding.setSparse(sparseArray);
        binding.setIndex(2);
    }

    public void onClickOne(View view) {
        if (view.getId() == R.id.tv1) {
            model.bottomTxt.set("唧唧复唧唧");
            if (binding.getMessageModel().getIsAdult().get()) {
                binding.getMessageModel().getIsAdult().set(false);
            } else {
                binding.getMessageModel().getIsAdult().set(true);
            }
        } else if (view.getId() == R.id.tv3) {
            model.isVisibility.set(true);
        }
    }

}
