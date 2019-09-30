package com.wdh.jetpack.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.wdh.jetpack.R;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        bottomNavigationView=findViewById(R.id.BottomNavigationView);
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