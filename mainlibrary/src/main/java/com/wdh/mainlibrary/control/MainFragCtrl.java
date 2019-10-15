package com.wdh.mainlibrary.control;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.wdh.mainlibrary.databinding.FragmentMainBinding;
import com.wdh.mainlibrary.ui.fragment.OrderFragment;
import com.wdh.mainlibrary.viewmodels.MainFragViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainFragCtrl {
    private final String[] mTitles = {"全部", "未付定金", "待接单", "待施工"};
    private final FragmentMainBinding binding;
    private MainFragViewModel viewModel;

    public MainFragCtrl(FragmentMainBinding binding, Fragment fragment) {
        this.binding = binding;
        viewModel = ViewModelProviders.of(fragment).get(MainFragViewModel.class);
        binding.setLifecycleOwner(fragment);

        binding.setViewModel(viewModel);
        viewModel.getCurrentName().setValue("哈哈哈");
        // Create the observer which updates the UI.
        // Update the UI, in this case, a TextView.
        final Observer<String> cityNameObserver = binding.city::setText;
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.getCurrentName().observe(fragment, cityNameObserver);

    }

    public void setViewPage(Fragment fragment) {
        List<Fragment> mFragments = new ArrayList<>();
        for (int i = 0; i < mTitles.length; i++)
            mFragments.add(new OrderFragment());
        ViewPageAdapter adapter = new ViewPageAdapter(fragment.getChildFragmentManager(), mFragments, mTitles);
        binding.viewpage.setAdapter(adapter);
        //将tab和ViewPager关联起来
        binding.viewpage.setOffscreenPageLimit(0);
        binding.slidingTab.setupWithViewPager(binding.viewpage);
    }

    public void onClick(View view) {
        viewModel.getCurrentName().setValue("是的发生时");
    }

}

class ViewPageAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragments;
    private String[] mTitles;

    ViewPageAdapter(FragmentManager fm, List<Fragment> mFragments, String[] mTitles) {
        super(fm);
        this.mFragments = mFragments;
        this.mTitles = mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles == null ? "" : mTitles[position];
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //super.destroyItem(container, position, object);//不销毁
    }
}
