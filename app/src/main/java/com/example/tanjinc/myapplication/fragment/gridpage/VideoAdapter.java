package com.example.tanjinc.myapplication.fragment.gridpage;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.example.tanjinc.myapplication.BaseConstant;
import com.example.tanjinc.myapplication.R;
import com.example.tanjinc.myapplication.bean.ABean;
import com.example.tanjinc.myapplication.bean.BBean;
import com.example.tanjinc.myapplication.bean.BaseBean;
import com.example.tanjinc.myapplication.bean.SkinBean;

public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<BaseBean> mDataArray = new ArrayList<>();

    public VideoAdapter() {
    }

    public void setData(List<? extends BaseBean> items) {
        mDataArray.clear();
        mDataArray.addAll(items);
        notifyDataSetChanged();
    }

    public void addFootItems(List<? extends BaseBean> items) {
        mDataArray.addAll(items);
        notifyDataSetChanged();
    }

    public void addHeaderItems(List<? extends BaseBean>  items) {
        mDataArray.addAll(0, items);
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case BaseConstant.TYPE_A:
                View viewA = layoutInflater.inflate(R.layout.list_item_a, parent, false);
                return new AViewHolder(viewA);
            case BaseConstant.TYPE_B:
                View viewB = layoutInflater.inflate(R.layout.list_item_b, parent, false);
                return new BViewHolder(viewB);
            case BaseConstant.TYPE_VIDEO:
                View view = layoutInflater.inflate(R.layout.list_item_video, parent, false);
                return new VideoViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder == null) {
            return;
        }
        switch (getItemViewType(position)) {
            case BaseConstant.TYPE_A:
                bindTypeAViewHolder(viewHolder, position);
                return;
            case BaseConstant.TYPE_B:
                bindTypeBViewHolder(viewHolder, position);
                break;
            case BaseConstant.TYPE_VIDEO:
                bindTypeVideoViewHolder(viewHolder, position);
        }
    }

    private void bindTypeAViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {
        if(!(viewHolder instanceof AViewHolder)) {
            return;
        }
        AViewHolder aViewHolder = (AViewHolder) viewHolder;
        ABean aBean = (ABean) mDataArray.get(position);
        aViewHolder.mItemName.setText(aBean.getName());
    }

    private void bindTypeBViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {
        if (!(viewHolder instanceof BViewHolder)) {
            return;
        }
        BViewHolder bViewHolder = (BViewHolder) viewHolder;
        BBean bBean = (BBean) mDataArray.get(position);
        bViewHolder.itemNameTv.setText(bBean.getName());
    }

    private void bindTypeVideoViewHolder(final RecyclerView.ViewHolder viewHolder, int position) {
        if (!(viewHolder instanceof VideoViewHolder)) {
            return;
        }
        VideoViewHolder vViewHolder = (VideoViewHolder) viewHolder;
        SkinBean vBean = (SkinBean) mDataArray.get(position);
        vViewHolder.itemNameTv.setText(vBean.getName());
    }

    @Override
    public int getItemCount() {
        return mDataArray.size();
    }


    @Override
    public int getItemViewType(int position) {
        return mDataArray.get(position).getType();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type) {
                        case BaseConstant.TYPE_A:
                            return 1;
                        case BaseConstant.TYPE_B:
                            return 1;
                        case BaseConstant.TYPE_VIDEO:
                            return 2;
                    }
                    return 3;
                }
            });
        }
        recyclerView.addItemDecoration(new GridItemDecoration());
    }

    private class GridItemDecoration extends RecyclerView.ItemDecoration {

        private Drawable mDivider;

        public GridItemDecoration() {
        }

        @Override
        public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            final GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
            final GridLayoutManager.SpanSizeLookup lookup = layoutManager.getSpanSizeLookup();


            if (mDivider == null || layoutManager.getChildCount() == 0) {
                return;
            }

            int spanCount = layoutManager.getSpanCount();
            if (layoutManager.getOrientation() == GridLayoutManager.VERTICAL) {

            }
            super.onDraw(c, parent, state);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.bottom = 8;
            outRect.left = 10;
            outRect.right = 10;
        }
    }
}
