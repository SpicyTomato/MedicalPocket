package com.spicytomato.medicalpocket;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.spicytomato.medicalpocket.content.Content;

public class Information_Activity extends Activity {

    private EditText mEditText_name;
    private EditText mEditText_age;
    private EditText mEditText_address;
    private EditText mEditText_tel;

    final static String TAG = "Information_Activity";
    final static String IS_FILLED_INFORMATION = "is filled information";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private Button mButton_commit;
    private EditText mEditText_sex;
    private boolean mIsFilledInformation;

    @SuppressLint("CommitPrefEdits")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_personal_msg);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        mEditor = mSharedPreferences.edit();

        mIsFilledInformation = mSharedPreferences.getBoolean(IS_FILLED_INFORMATION,false);


        if (!mIsFilledInformation) {
            initView();
            initEvent();
        }else{
            moveToMain();
        }
    }

    private void initEvent() {
        final Editable name = mEditText_name.getText();
        final Editable sex =  mEditText_sex.getText();
        final Editable age = mEditText_age.getText();
        final Editable address = mEditText_address.getText();
        final Editable tel = mEditText_tel.getText();

        if (mEditText_name.getText() == null) {
            Toast toast = Toast.makeText(this,"请正确输入姓名",Toast.LENGTH_SHORT);
            toast.show();
        }



        mButton_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.length() == 0 || sex.length() == 0 || age.length() == 0 || address.length() == 0 || tel.length() == 0) {
                    Toast toast = Toast.makeText(getApplication(), "请正确填写信息", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    mEditor.putString(Content.INFORMATION_NAME, String.valueOf(name));
                    mEditor.putString(Content.INFORMATION_SEX, String.valueOf(sex));
                    mEditor.putInt(Content.INFORMATION_AGE, Integer.parseInt(String.valueOf(age)));
                    mEditor.putString(Content.INFORMATION_ADDRESS, String.valueOf(address));
                    mEditor.putString(Content.INFORMATION_TEL, String.valueOf(tel));
                    mEditor.commit();
                mEditor.putBoolean(IS_FILLED_INFORMATION,true);
                mEditor.commit();

                    moveToMain();
                    //TODO
                    //验证这些存储的数据是否全局可用
                }
            }
        });
    }

    private void moveToMain() {
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void initView() {
        mEditText_name = findViewById(R.id.editText_name);
        mEditText_sex = findViewById(R.id.editText_sex);
        mEditText_age = findViewById(R.id.editText_age);
        mEditText_address = findViewById(R.id.editText_address);
        mEditText_tel = findViewById(R.id.editText_tel);
        mButton_commit = findViewById(R.id.button_commit);
    }

    @Override
    public boolean onNavigateUp() {
        mEditor.putBoolean(Content.IS_FIRST_ENTER,true);
        mEditor.commit();
        return super.onNavigateUp();
    }
}
