package com.spicytomato.medicalpocket.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.spicytomato.medicalpocket.MainActivity;
import com.spicytomato.medicalpocket.R;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.TriangularPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

/**
 * 创建indicator的适配器
 */

public class IndicatorAdapter extends CommonNavigatorAdapter {


    private final String[] mTitles;
    private OnIndicatorTapClickListener indicatorTapClickListener;

    public IndicatorAdapter(Context context) {
        mTitles = context.getResources().getStringArray(R.array.indicator_name);
    }

    @Override
    public int getCount() {
        if (mTitles != null) {
            return mTitles.length;
        }
        return 0;
    }

    @Override
    public IPagerTitleView getTitleView(Context context, final int index) {
        SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
        simplePagerTitleView.setText(mTitles[index]);
        simplePagerTitleView.setNormalColor(Color.parseColor("#333333"));
        simplePagerTitleView.setSelectedColor(Color.parseColor("#e94220"));
        simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通知viewPager 去改变
                indicatorTapClickListener.onTapClick(index);
            }
        });
        return simplePagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        TriangularPagerIndicator indicator = new TriangularPagerIndicator(context);
        indicator.setLineColor(Color.parseColor("#e94220"));
        return indicator;
    }

    public void setOnIndicatorTapClickListener(OnIndicatorTapClickListener indicatorTapClickListener){
        this.indicatorTapClickListener = indicatorTapClickListener;
    }

    public interface OnIndicatorTapClickListener{
        void onTapClick(int index);
    }
}
