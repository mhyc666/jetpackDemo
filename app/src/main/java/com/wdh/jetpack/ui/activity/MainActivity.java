package com.wdh.jetpack.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.wdh.jetpack.R;
import com.wdh.jetpack.ui.fragment.HomeFragment;
import com.wdh.jetpack.ui.fragment.message.MessageFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener
        , ViewPager.OnPageChangeListener {
    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        viewPager = findViewById(R.id.viewPager);
        bottomNavigationView = findViewById(R.id.BottomNavigationView);
        viewPager.addOnPageChangeListener(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), 0));
        //bottomNavigationView.setSelectedItemId(R.id.tab_three);//设置默认选中
        //viewPager.setCurrentItem(1);//设置默认选中方法2

        //BottomNavigationView使用Navigation
        //val navGraphIds = listOf(R.navigation.home, R.navigation.list, R.navigation.form) //kt
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
//        NavigationUI.setupWithNavController(bottomNav, navController);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        switch (itemId) {
            case R.id.tab_one:
                viewPager.setCurrentItem(0);
                break;
            case R.id.tab_two:
                viewPager.setCurrentItem(1);
                //Toast.makeText(this, "德玛西亚", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tab_three:
                viewPager.setCurrentItem(2);
                break;
            case R.id.tab_four:
                viewPager.setCurrentItem(3);
                break;
        }
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        MenuItem menuItem = bottomNavigationView.getMenu().getItem(position);
        menuItem.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}


class ViewPagerAdapter extends FragmentPagerAdapter {
    private Fragment[] mFragments = new Fragment[]{new HomeFragment(), new MessageFragment(), new HomeFragment(), new MessageFragment()};

    ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments[position];
    }

    @Override
    public int getCount() {
        return mFragments.length;
    }
}


//注意
//1、BottomNavigationView只适用于3到5个的导航栏；
//2、当tab个数大余3个时，BottomNavigationView不会均分宽度，一般来说我们都是需要均分宽度。
//解决方案：disableShiftMode(bottomNavigationView);
//
//public void disableShiftMode(BottomNavigationView navigationView) {
//
//        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigationView.getChildAt(0);
//        try {
//        Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
//        shiftingMode.setAccessible(true);
//        shiftingMode.setBoolean(menuView, false);
//        shiftingMode.setAccessible(false);
//
//        for (int i = 0; i < menuView.getChildCount(); i++) {
//        BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(i);
//        itemView.setShiftingMode(false);
//        itemView.setChecked(itemView.getItemData().isChecked());
//        }
//
//        } catch (Exception e) {
//        e.printStackTrace();
//        }
//        }

//源码里面的各个属性的设置：
//
//<dimen name="design_bottom_navigation_active_item_max_width">168dp</dimen>//选中时的最大宽度
//<dimen name="design_bottom_navigation_active_text_size">14sp</dimen>//选中时的字体大小
//<dimen name="design_bottom_navigation_elevation">8dp</dimen>//阴影的大小
//<dimen name="design_bottom_navigation_height">56dp</dimen>//高度
//<dimen name="design_bottom_navigation_item_max_width">96dp</dimen>//未选中的最大宽度
//<dimen name="design_bottom_navigation_item_min_width">56dp</dimen>//未选中的最小的宽度
//<dimen name="design_bottom_navigation_margin">8dp</dimen>//icon与文本之间的间距
//<dimen name="design_bottom_navigation_shadow_height">1dp</dimen>//阴影高度
//<dimen name="design_bottom_navigation_text_size">12sp</dimen>//未选中时的字体大小
//        如果要修改这些属性值，在自己项目的dimens定义相同的名字，重新赋值