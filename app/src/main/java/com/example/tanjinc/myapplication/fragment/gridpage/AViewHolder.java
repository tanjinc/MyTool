package com.example.tanjinc.myapplication.fragment.gridpage;

import android.view.View;
import android.widget.TextView;

import com.example.tanjinc.myapplication.BaseConstant;
import com.example.tanjinc.myapplication.R;
import com.example.tanjinc.myapplication.view.BaseViewHolder;

public class AViewHolder extends BaseViewHolder {

    View mView;
    public TextView mItemName;

    public AViewHolder(View view) {
        super(view);
        mView = view;
        mItemName = (TextView) view.findViewById(R.id.item_name);
    }
    @Override
    public int getType() {
        return BaseConstant.TYPE_A;
    }
}
