package com.example.tanjinc.myapplication.fragment.gridpage;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.example.tanjinc.myapplication.BaseConstant;
import com.example.tanjinc.myapplication.R;
import com.example.tanjinc.myapplication.view.BaseViewHolder;

public class BViewHolder extends BaseViewHolder {

    public TextView itemNameTv;

    public BViewHolder(@NonNull View itemView) {
        super(itemView);
        itemNameTv = itemView.findViewById(R.id.item_name);
    }

    @Override
    public int getType() {
        return BaseConstant.TYPE_B;
    }
}
