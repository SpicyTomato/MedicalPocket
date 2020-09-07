package com.spicytomato.medicalpocket;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.service.autofill.UserData;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.spicytomato.medicalpocket.content.Content;

public class Enter_Activity extends Activity {

    private Button mButton_doctor;
    private Button mButton_patient;
    final static String TAG = "Enter_Activity";

    private boolean mIsFirstEnter;
    private SharedPreferences mSharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_enter);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
        editor = mSharedPreferences.edit();
        mIsFirstEnter = mSharedPreferences.getBoolean(Content.IS_FIRST_ENTER, true);

        if (mIsFirstEnter) {
            initView();
            initEvent();
        } else {
            moveToPersonalMsg();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
        initEvent();

    }

    private void initEvent() {
        mButton_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean(Content.IS_FIRST_ENTER, false);
                editor.commit();
                moveToPersonalMsg();
            }
        });

        mButton_doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean(Content.IS_FIRST_ENTER, false);
                editor.commit();
                //TODO
            }
        });
    }

    private void moveToPersonalMsg() {
        Intent intent = new Intent(this, Information_Activity.class);
        startActivity(intent);
    }

    private void initView() {
        mButton_patient = findViewById(R.id.button_patient);
        mButton_doctor = findViewById(R.id.button_doctor);
    }
}
