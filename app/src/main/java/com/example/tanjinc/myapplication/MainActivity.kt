package com.example.tanjinc.myapplication

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.tanjinc.myapplication.fragment.BaseFragment
import com.example.tanjinc.myapplication.fragment.apkpage.AppFragment
import com.example.tanjinc.myapplication.fragment.gridpage.GridFragment
import com.example.tanjinc.myapplication.fragment.gridpage.GridPresenter
import com.example.tanjinc.myapplication.fragment.testpage.TestFragment
import com.example.tanjinc.myapplication.fragment.testpage.TestPresenter
import com.tencent.mmkv.MMKV
import java.util.*

class MainActivity : AppCompatActivity() {
    private val TAG:String = "MainActivity"
    private lateinit var mTabLayout: TabLayout
    private lateinit var mViewPager: ViewPager

    private val mFragmentList = ArrayList<BaseFragment>()
    private val mTitleList = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTabLayout = findViewById(R.id.tabLayout)
        mViewPager = findViewById(R.id.main_viewpager)


        val gridFragment = GridFragment()
        val gridPresenter = GridPresenter(gridFragment)
        gridFragment.setPresenter(gridPresenter)

        val gridFragment2 = TestFragment()
        val testPresenter = TestPresenter(gridFragment2)
        gridFragment2.setPresenter(testPresenter)

        val appFragment = AppFragment()


        mFragmentList.add(appFragment)
        mFragmentList.add(gridFragment)
        mFragmentList.add(gridFragment2)

        for (item in mFragmentList) {
            mTitleList.add(item.getName())
        }


        val pageAdapter = MyViewPageAdapter(supportFragmentManager, mTitleList, mFragmentList)

        mViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(mTabLayout))
        mViewPager.adapter = pageAdapter

        mTabLayout.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(mViewPager))
        mTabLayout.setupWithViewPager(mViewPager)

        MMKV.defaultMMKV().encode("hello", false)
        var key = MMKV.defaultMMKV().decodeBool("hello", true)
        Log.d("aa", ""+key)
    }

    inner class MyViewPageAdapter(fm: FragmentManager, private val mTitleArray: ArrayList<String>?, private val mFragmentArray: ArrayList<BaseFragment>?) : FragmentStatePagerAdapter(fm) {

        override fun getItem(i: Int): Fragment? {
            return if (mFragmentArray != null) mFragmentArray[i] else null
        }

        override fun getCount(): Int {
            return mFragmentArray?.size ?: 0
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return if (mTitleArray != null) mTitleArray[position] else super.getPageTitle(position)
        }
    }
}
