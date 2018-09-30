package com.example.tanjinc.myapplication

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.tanjinc.myapplication.fragment.gridpage.GridFragment
import com.example.tanjinc.myapplication.fragment.gridpage.GridPresenter
import com.example.tanjinc.myapplication.fragment.testpage.TestFragment
import com.example.tanjinc.myapplication.fragment.testpage.TestPresenter
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.experimental.async
import java.util.*

data class Customer(val name: String, val email: String)

class MainActivity : AppCompatActivity() {

    private val TAG:String = "MainActivity"

    private lateinit var mTabLayout: TabLayout
    private lateinit var mViewPager: ViewPager

    private val mFragmentList = ArrayList<Fragment>()
    private val mTitleList = ArrayList<String>()

    private lateinit var customer: Customer

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

        mFragmentList.add(gridFragment)
        mTitleList.add("网格布局")
        mFragmentList.add(gridFragment2)
        mTitleList.add("test")


        val pageAdapter = MyViewPageAdapter(supportFragmentManager, mTitleList, mFragmentList)

        mViewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(mTabLayout))
        mViewPager.adapter = pageAdapter

        mTabLayout.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(mViewPager))
        mTabLayout.setupWithViewPager(mViewPager)

        val a = parseInt("123")
        val b = parseInt("456")
        val c = a!! * b!!


        CommonUtil.instance
        customer = Customer("tanjinc", "tanjinc@126.com")
        Log.d("tag", customer.email)


        var map = mapOf(
                "key" to 12,
                "aaa" to 1
                )

        for ((k,v) in map) {
            Log.d("tag","$k -> $v")
        }



        Thread(Runnable {
            run {

            }
        }).start()
        async {
            dosomething()
        }

        var aaa: String = when(a) {
            1 -> "one"
            2 -> "two"
            3 -> "threw"
            else -> "unknown"
        }

        MMKV.defaultMMKV().encode("hello", false)
        var key = MMKV.defaultMMKV().decodeBool("hello", true)
        Log.d("aa", ""+key)


    }

    //单表达式函数
    private fun double(x:Int) = x * 2

    //相等行


    suspend fun dosomething() {

    }

    fun foo(a:Int = 0, b: Int = 1) = a + b

    class CustomThread:Thread() {
        override fun run() {
            super.run()
        }
    }

    private fun getCustomer(): Customer? {
        var customer = Customer("a", "b")
        return customer
    }

    private fun parseInt(str: String = "100"): Int ? {
        return Integer.valueOf(str)
    }

    inner class MyViewPageAdapter(fm: FragmentManager, private val mTitleArray: ArrayList<String>?, private val mFragmentArray: ArrayList<Fragment>?) : FragmentStatePagerAdapter(fm) {

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
