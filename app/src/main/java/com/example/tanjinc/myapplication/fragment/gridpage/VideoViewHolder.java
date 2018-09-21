package com.example.tanjinc.myapplication.fragment.gridpage;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.example.tanjinc.myapplication.R;
import com.example.tanjinc.myapplication.view.BaseViewHolder;

import static com.example.tanjinc.myapplication.BaseConstant.TYPE_VIDEO;

public class VideoViewHolder extends BaseViewHolder {
    public TextView itemNameTv;

    public VideoViewHolder(@NonNull View itemView) {
        super(itemView);
        itemNameTv = (TextView) itemView.findViewById(R.id.item_name);
    }

    @Override
    public int getType() {
        return TYPE_VIDEO;
    }
}
