package com.spicytomato.medicalpocket.utils;

import com.spicytomato.medicalpocket.base.BaseFragment;
import com.spicytomato.medicalpocket.fragments.Fragment_1;
import com.spicytomato.medicalpocket.fragments.Fragment_2;
import com.spicytomato.medicalpocket.fragments.Fragment_3;

import java.util.HashMap;
import java.util.Map;

/**
 * FragmentCreator 主要作用是 当 indicator 进行 viewpager 切换时 fragment 会进行来回切换 若反复初始化 fragment 会让内存吃紧 加大内存开支
 * <p>
 * 这样 通过键值对的形式 存储所需显示的 fragment 若已经创建过 则直接返回 若没有创建过 则创建并加入到Map中
 */

public class FragmentCreator {

    public final static int INDEX_TODO_1 = 0;
    public final static int INDEX_TODO_2 = 1;
    public final static int INDEX_TODO_3 = 2;

    public final static int PAGE_COUNT = 3;

    private static Map<Integer, BaseFragment> sCahe = new HashMap<>();

    public static BaseFragment getFragment(int index) {
        BaseFragment baseFragment = sCahe.get(index);
        if (baseFragment != null) {
            return baseFragment;
        }

        switch (index) {
            case INDEX_TODO_3:
                baseFragment = new Fragment_3();
                break;
            case INDEX_TODO_1:
                baseFragment = new Fragment_1();
                break;
            case INDEX_TODO_2:
                baseFragment = new Fragment_2();
                break;
        }

        sCahe.put(index, baseFragment);
        return baseFragment;
    }
}
