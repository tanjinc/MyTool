package com.example.tanjinc.myapplication.fragment

import android.content.Context
import android.support.v4.app.Fragment
import android.widget.Toast

open abstract class BaseFragment : Fragment() {
    abstract fun getName() : String

    public fun toast(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
    }
}