package com.wdh.mainlibrary.nav;

import android.content.Intent;
import android.util.SparseArray;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.wdh.mainlibrary.R;

import java.util.List;

/**
 * https://github.com/android/architecture-components-samples/tree/master/NavigationAdvancedSample/app/src/androidTest/java/com/example/android/navigationadvancedsample
 *
 *  <androidx.fragment.app.FragmentContainerView
 *         android:id="@+id/nav_host_container"
 *         android:layout_width="match_parent"
 *         android:layout_height="0dp"
 *         android:layout_weight="1" />
 *
 * onCreate
 * if(savedInstanceState==null){
 * setupBottomNavigation();
 * }
 *
 * @Override
 * protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
 * super.onRestoreInstanceState(savedInstanceState);
 * setupBottomNavigation();
 * }
 * private LiveData<NavController> currentNavController = null;
 * @Override
 * public boolean onNavigateUp() {
 * Objects.requireNonNull(currentNavController.getValue()).navigateUp();
 * return super.onNavigateUp();
 * }
 * <p>
 * private void setupBottomNavigation(){
 * BottomNavigationView bottomNavigationView = binding.bottomNavigationView;
 * <p>
 * List<Integer> navGraphList = new ArrayList<>();
 * navGraphList.add(R.navigation.main_nav_graph);
 *         navGraphList.add(R.navigation.find_navigation);
 *         navGraphList.add(R.navigation.options_navigation);
 * currentNavController = new NavigationExtensions().setupWithNavController(
 * bottomNavigationView
 * , navGraphList
 * , getSupportFragmentManager()
 * , R.id.nav_host_container, getIntent()
 * );
 * }
 * https://stackoverflow.com/questions/59251349/how-to-use-navigationextensions-kt-in-a-java-project
 **/
public class NavigationExtensions {
    private String selectedItemTag;
    private boolean isOnFirstFragment;
    private MutableLiveData<NavController> selectedNavController;
    private int firstFragmentGraphId;
    private SparseArray<String> graphIdToTagMap;

    public LiveData<NavController> setupWithNavController(
            final BottomNavigationView navView,
            List<Integer> navGraphIds,
            final FragmentManager fragmentManager,
            int containerId,
            Intent intent
    ) {
        graphIdToTagMap = new SparseArray<>();
        selectedNavController = new MutableLiveData<>();
        firstFragmentGraphId = 0;

        for (Integer navGraphId : navGraphIds) {
            String fragmentTag = getFragmentTag(navGraphIds.indexOf(navGraphId));
            NavHostFragment navHostFragment = obtainNavHostFragment(
                    fragmentManager,
                    fragmentTag,
                    navGraphId,
                    containerId
            );
            int graphId = navHostFragment.getNavController().getGraph().getId();

            if (navGraphIds.indexOf(navGraphId) == 0) {
                firstFragmentGraphId = graphId;
            }

            graphIdToTagMap.append(graphId, fragmentTag);

            if (navView.getSelectedItemId() == graphId) {
                selectedNavController.postValue(navHostFragment.getNavController());
                attachNavHostFragment(fragmentManager, navHostFragment, navGraphIds.indexOf(navGraphId) == 0);
            } else detachNavHostFragment(fragmentManager, navHostFragment);
        }

        selectedItemTag = graphIdToTagMap.get(navView.getSelectedItemId());
        final String firstFragmentTag = graphIdToTagMap.get(firstFragmentGraphId);
        isOnFirstFragment = selectedItemTag.equals(firstFragmentTag);

        navView.setOnNavigationItemSelectedListener(item -> {
            if (fragmentManager.isStateSaved()) {
                return false;
            } else {
                final String newlySelectedItemTag = graphIdToTagMap.get(item.getItemId());
                if (!selectedItemTag.equals(newlySelectedItemTag)) {
                    // Pop everything above the first fragment (the "fixed start destination")
                    fragmentManager.popBackStack(firstFragmentTag,
                            FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    final NavHostFragment selectedFragment = (NavHostFragment) fragmentManager.findFragmentByTag(newlySelectedItemTag);
                    if (!firstFragmentTag.equals(newlySelectedItemTag)) {
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction()
                                .setCustomAnimations(
                                        R.anim.nav_default_enter_anim,
                                        R.anim.nav_default_exit_anim,
                                        R.anim.nav_default_pop_enter_anim,
                                        R.anim.nav_default_pop_exit_anim)
                                .attach(selectedFragment)
                                .setPrimaryNavigationFragment(selectedFragment);
                        for (int i = 0; i < graphIdToTagMap.size(); i++) {
                            if (!graphIdToTagMap.valueAt(i).equals(newlySelectedItemTag)) {
                                if (fragmentManager.findFragmentByTag(firstFragmentTag) != null)
                                    fragmentTransaction.detach(fragmentManager.findFragmentByTag(firstFragmentTag));
                            }
                        }
                        fragmentTransaction.addToBackStack(firstFragmentTag)
                                .setReorderingAllowed(true)
                                .commit();
                    }
                    selectedItemTag = newlySelectedItemTag;
                    isOnFirstFragment = selectedItemTag.equals(firstFragmentTag);
                    selectedNavController.postValue(selectedFragment.getNavController());
                    return true;
                } else return false;

            }
        });


        fragmentManager.addOnBackStackChangedListener(() -> {
            if (!isOnFirstFragment && !isOnBackStack(fragmentManager, firstFragmentTag)) {
                navView.setSelectedItemId(firstFragmentGraphId);
                NavController controller = selectedNavController.getValue();
                if (controller.getCurrentDestination() == null) {
                    controller.navigate(selectedNavController.getValue().getGraph().getId());
                }
                selectedNavController.postValue(controller);
            }


        });

        return selectedNavController;
    }

    private NavHostFragment obtainNavHostFragment(
            FragmentManager fragmentManager,
            String fragmentTag,
            int navGraphId,
            int containerId
    ) {
        if (fragmentManager.findFragmentByTag(fragmentTag) instanceof NavHostFragment) {
            return (NavHostFragment) fragmentManager.findFragmentByTag(fragmentTag);
        }

        NavHostFragment navHostFragment = NavHostFragment.create(navGraphId);
        fragmentManager.beginTransaction()
                .add(containerId, navHostFragment, fragmentTag)
                .commitNow();
        return navHostFragment;
    }

    private void attachNavHostFragment(
            FragmentManager fragmentManager,
            NavHostFragment navHostFragment,
            boolean isPrimaryNavFragment
    ) {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.attach(navHostFragment);
        if (isPrimaryNavFragment) {
            fragmentTransaction.setPrimaryNavigationFragment(navHostFragment);
        }
        fragmentTransaction.commitNow();
    }

    private void detachNavHostFragment(
            FragmentManager fragmentManager,
            NavHostFragment navHostFragment
    ) {
        fragmentManager.beginTransaction()
                .detach(navHostFragment)
                .commitNow();
    }

    private boolean isOnBackStack(FragmentManager fragmentManager, String backStackName) {
        int backStackCount = fragmentManager.getBackStackEntryCount();
        for (int i = 0; i < backStackCount; i++) {
            if (fragmentManager.getBackStackEntryAt(i).getName().equals(backStackName)) {
                return true;
            }
        }
        return false;
    }

    private String getFragmentTag(int index) {
        return "bottomNavigation#" + index;
    }
}