package com.spicytomato.medicalpocket.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.spicytomato.medicalpocket.utils.FragmentCreator;


/**
 * FragmentAdapter 用过在 MainActivity 中设置的 indicator 设置 adapter 来进行对fragment的切换
 *
 * 当indicator 进行切换时 切换的 viewpager 应该显示的fragment为哪一个
 *
 *
 */

public class MainContentAdapter extends FragmentPagerAdapter {
    public MainContentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return FragmentCreator.getFragment(position);
    }

    @Override
    public int getCount() {
        return FragmentCreator.PAGE_COUNT;
    }
}
