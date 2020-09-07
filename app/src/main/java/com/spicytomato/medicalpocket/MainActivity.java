package com.spicytomato.medicalpocket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.LiveData;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;

import com.spicytomato.medicalpocket.adapters.IndicatorAdapter;
import com.spicytomato.medicalpocket.adapters.MainContentAdapter;
import com.spicytomato.medicalpocket.database.Record;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.TriangularPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.List;

public class MainActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private MagicIndicator mMagicIndicator;
    private MainContentAdapter mMainContentAdapter;
    private IndicatorAdapter mIndicatorAdapter;
//    private SearchView mSearch;
    private OnSearch onSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
    }

    private void initEvent() {
        mIndicatorAdapter.setOnIndicatorTapClickListener(new IndicatorAdapter.OnIndicatorTapClickListener() {
            @Override
            public void onTapClick(int index) {
                mViewPager.setCurrentItem(index);
            }
        });

//        mSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                if (onSearch != null) {
//                    onSearch.onSearch(newText);
//                }
//
//                return true;
//            }
//        });
    }

    private void initView() {
        mMagicIndicator = (MagicIndicator) findViewById(R.id.main_indicator);
        mMagicIndicator.setBackgroundColor(Color.WHITE);

        //创建indicator的适配器
        mIndicatorAdapter = new IndicatorAdapter(this);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdapter(mIndicatorAdapter);
        commonNavigator.setAdjustMode(true);

//        mSearch = findViewById(R.id.search);

        //ViewPager
        mViewPager = findViewById(R.id.view_pager);

        //创建内容适配器
        mMainContentAdapter = new MainContentAdapter(getSupportFragmentManager(), 0);
        mViewPager.setAdapter(mMainContentAdapter);

        //吧viewpager和indicator绑定到一起
        mMagicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);
    }

    public interface OnSearch {
        void onSearch(String newText);
    }

    public void setOnSearch(OnSearch onSearch) {
        this.onSearch = onSearch;
    }
}
