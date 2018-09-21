package com.example.tanjinc.myapplication.bean;

import com.example.tanjinc.myapplication.BaseConstant;

public class BBean extends BaseBean {
    String name;

    public BBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getType() {
        return BaseConstant.TYPE_B;
    }
}
