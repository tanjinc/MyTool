package com.example.tanjinc.myapplication;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tanjinc.myapplication.fragment.gridpage.GridFragment;
import com.example.tanjinc.myapplication.fragment.gridpage.GridPresenter;
import com.example.tanjinc.myapplication.fragment.testpage.TestFragment;
import com.example.tanjinc.myapplication.fragment.testpage.TestPresenter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private ArrayList<Fragment> mFragmentList = new ArrayList<>();
    private ArrayList<String> mTitleList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = findViewById(R.id.main_tablayout);
        mViewPager = findViewById(R.id.main_viewpager);

        GridFragment gridFragment = new GridFragment();
        GridPresenter gridPresenter = new GridPresenter(gridFragment);
        gridFragment.setPresenter(gridPresenter);

        TestFragment gridFragment2 = new TestFragment();
        TestPresenter testPresenter = new TestPresenter(gridFragment2);
        gridFragment2.setPresenter(testPresenter);

        mFragmentList.add(gridFragment);
        mTitleList.add("网格布局");
        mFragmentList.add(gridFragment2);
        mTitleList.add("test");


        MyViewPageAdapter pageAdapter = new MyViewPageAdapter(getSupportFragmentManager(), mTitleList,mFragmentList);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mViewPager.setAdapter(pageAdapter);

        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public class MyViewPageAdapter extends FragmentStatePagerAdapter {

        private ArrayList<String> mTitleArray;
        private ArrayList<Fragment> mFragmentArray;

        public MyViewPageAdapter(FragmentManager fm, ArrayList<String> titles, ArrayList<Fragment> fragments) {
            super(fm);
            mTitleArray = titles;
            mFragmentArray = fragments;
        }

        @Override
        public Fragment getItem(int i) {
            return mFragmentArray != null ? mFragmentArray.get(i) : null;
        }

        @Override
        public int getCount() {
            return mFragmentArray != null ? mFragmentArray.size() : 0;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleArray != null ? mTitleArray.get(position) : super.getPageTitle(position);
        }
    }
}
