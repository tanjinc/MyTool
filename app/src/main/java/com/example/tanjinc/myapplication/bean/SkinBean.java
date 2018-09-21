package com.example.tanjinc.myapplication.bean;

import com.example.tanjinc.myapplication.BaseConstant;

public class SkinBean extends BaseBean{
    private String name;

    public SkinBean(String title) {
        this.name = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getType() {
        return BaseConstant.TYPE_VIDEO;
    }
}
