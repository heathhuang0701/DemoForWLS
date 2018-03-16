package com.liqi.android.demo_for_wls.main;

import android.os.Bundle;

import com.liqi.android.demo_for_wls.EmptyFragment;
import com.liqi.android.demo_for_wls.R;
import com.liqi.android.demo_for_wls.main.view.BottomBar;
import com.liqi.android.demo_for_wls.main.view.BottomBarTab;
import com.liqi.android.demo_for_wls.symbols.SymbolListFragment;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by heath on 2018/3/14.
 */

public class MainActivity extends SupportActivity {
    private static String TAG = "MainActivity";
    private MainActivity mainActivity;
    private SupportFragment[] mFragments = new SupportFragment[2];
    private BottomBar mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = this;
        setContentView(R.layout.activity_main);
        mBottomBar = findViewById(R.id.bottom_bar);

        SupportFragment firstFragment = findFragment(SymbolListFragment.class);
        if (firstFragment == null) {
            mFragments[0] = SymbolListFragment.newInstance();
            mFragments[1] = EmptyFragment.newInstance();

            loadMultipleRootFragment(
                    R.id.fragment_container,
                    0,
                    mFragments[0],
                    mFragments[1]);
        } else {
            mFragments[0] = firstFragment;
            mFragments[1] = findFragment(EmptyFragment.class);
        }

        initView();
        setListener();
    }

    private void initView() {
        mBottomBar
            .addItem(new BottomBarTab(mainActivity, android.R.drawable.ic_lock_silent_mode, android.R.drawable.ic_lock_silent_mode, "測試1"))
            .addItem(new BottomBarTab(mainActivity, android.R.drawable.ic_lock_silent_mode, android.R.drawable.ic_lock_silent_mode,  "測試2"));

        mBottomBar.setCurrentItem(0);
    }

    private void setListener() {
        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, final int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {}
        });
    }
}
