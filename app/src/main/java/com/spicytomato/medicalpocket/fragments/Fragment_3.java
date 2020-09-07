package com.spicytomato.medicalpocket.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.spicytomato.medicalpocket.PersonalChange;
import com.spicytomato.medicalpocket.R;
import com.spicytomato.medicalpocket.base.BaseFragment;
import com.spicytomato.medicalpocket.content.Content;

public class Fragment_3 extends BaseFragment {


    private View mRootView;
    private TextView mName;
    private TextView mSex;
    private TextView mAge;
    private TextView mAddress;
    private Button mChange;
    private SharedPreferences mSharedPreferences;

    @Override
    protected View onSubViewLoaded(LayoutInflater inflater, ViewGroup container) {
        mRootView = inflater.inflate(R.layout.fragment_3,container,false);


        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final FragmentActivity activity = requireActivity();

        mName = activity.findViewById(R.id.personal_name);
        mSex = activity.findViewById(R.id.personal_sex);
        mAge = activity.findViewById(R.id.personal_age);
        mAddress = activity.findViewById(R.id.personal_address);
        mChange = activity.findViewById(R.id.personal_change);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity.getApplication());

        String name  = mSharedPreferences.getString(Content.INFORMATION_NAME,"");
        String sex = mSharedPreferences.getString(Content.INFORMATION_SEX,"");
        int age = mSharedPreferences.getInt(Content.INFORMATION_AGE,0);
        String address = mSharedPreferences.getString(Content.INFORMATION_ADDRESS,"");

        mName.setText(name);
        mSex.setText(sex);
        mAge.setText(String.valueOf(age));
        mAddress.setText(address);

        mChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,PersonalChange.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        String name  = mSharedPreferences.getString(Content.INFORMATION_NAME,"");
        String sex = mSharedPreferences.getString(Content.INFORMATION_SEX,"");
        int age = mSharedPreferences.getInt(Content.INFORMATION_AGE,0);
        String address = mSharedPreferences.getString(Content.INFORMATION_ADDRESS,"");

        mName.setText(name);
        mSex.setText(sex);
        mAge.setText(String.valueOf(age));
        mAddress.setText(address);
    }
}
