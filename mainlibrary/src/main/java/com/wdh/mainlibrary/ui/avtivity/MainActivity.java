package com.wdh.mainlibrary.ui.avtivity;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.wdh.mainlibrary.R;
import com.wdh.mainlibrary.databinding.ActivityNavMainBinding;



public class MainActivity extends AppCompatActivity {

    private NavController navController;
    //private AppBarConfiguration appBarConfiguration;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNavMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_nav_main);

        //this.navController = Navigation.findNavController(this, R.id.main_nav_fragment);
        //this.appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();

//        FragmentManager fragmentManager = getSupportFragmentManager();//这个方法也可以设置bottomNavigationView
//        NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentById(R.id.main_nav_fragment);
//        this.navController = Objects.requireNonNull(navHostFragment).getNavController();

        //设置bottomNavigationView 2
        this.navController = Navigation.findNavController(this, R.id.main_nav_fragment);

        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);
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
}
