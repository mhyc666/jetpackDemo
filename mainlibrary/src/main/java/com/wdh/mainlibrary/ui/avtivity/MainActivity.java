package com.wdh.mainlibrary.ui.avtivity;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.wdh.mainlibrary.R;
import com.wdh.mainlibrary.databinding.ActivityNavMainBinding;
import com.wdh.mainlibrary.nav.FixFragmentNavigator;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private ActivityNavMainBinding binding;
    //private AppBarConfiguration appBarConfiguration;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_nav_main);

        //this.navController = Navigation.findNavController(this, R.id.main_nav_fragment);
        //this.appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();

        //设置bottomNavigationView 1
//        FragmentManager fragmentManager = getSupportFragmentManager();//这个方法也可以设置bottomNavigationView
//        NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentById(R.id.main_nav_fragment);
//        this.navController = Objects.requireNonNull(navHostFragment).getNavController();

        //设置bottomNavigationView 2
//        this.navController = Navigation.findNavController(this, R.id.main_nav_fragment);
//         关联将BottomNavigationView
//        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);


        Fragment fragmentById = getSupportFragmentManager().findFragmentById(R.id.main_nav_fragment);
        //fragment的重复加载问题和NavController有关
        navController = NavHostFragment.findNavController(Objects.requireNonNull(fragmentById));
//        navController = Navigation.findNavController(this, R.id.main_nav_fragment);
        NavigatorProvider provider = navController.getNavigatorProvider();
        //设置自定义的navigator
        FixFragmentNavigator fixFragmentNavictor = new FixFragmentNavigator(this, getSupportFragmentManager(), fragmentById.getId());
        provider.addNavigator(fixFragmentNavictor);
        navController.setGraph(R.navigation.main_nav_graph);
        //将BottomNavigationView和NaviGraph关联起来--->自定义navigator后这样设置会有一些奇葩的问题,谁用谁知道(╥╯^╰╥)....
        //NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);
        //正确的关联方式o(￣▽￣)ｄ
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            navController.navigate(item.getItemId());
            return true;
        });

    }

//将目的地绑定到菜单项
//Next, override onSupportNavigateUp() to handle Up navigation:
//    @Override
//    public boolean onSupportNavigateUp() {
//        return NavigationUI.navigateUp(navController, appBarConfiguration)
//                || super.onSupportNavigateUp();
//    }

    //将目的地绑定到菜单项 meun
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
    }


//    在每个片段的XML中，如果需要该片段，则会放入一个工具栏。
//    在你的标签式Fragment的XML中，你将使用TabLayout，如果你愿意，
//    还可以使用CoordinatorLayout和AppBarLayout。从每个带有工具栏的片段中，
//    将工具栏设置为ActionBar；
//    Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
//    AppCompatActivity activity = (AppCompatActivity) getActivity();
//    setSupportActionBar(toolbar);
//    NavigationUI.setupActionBarWithNavController(this, this.navController, this.appBarConfiguration);


    @Override
    public void onBackPressed() {
//        如果调用 super.onBackPressed()，navigation会操作回退栈,切换到之前显示的页面，导致 页面叠加错乱
//        super.onBackPressed();
        int id = Objects.requireNonNull(navController.getCurrentDestination()).getId();
        //int homeNavi = navController.getGraph().getId();
        int homeNavi = R.id.mainFragment;
        if (id != homeNavi) {
            binding.bottomNavigationView.setSelectedItemId(R.id.mainFragment);
        } else {
            finish();
        }
    }

}
