package com.spicytomato.medicalpocket;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.spicytomato.medicalpocket.content.Content;

public class PersonalChange extends AppCompatActivity {

    private SharedPreferences mSharedPreferences;
    private EditText mName;
    private EditText mSex;
    private EditText mAge;
    private EditText mAddress;
    private Button mCommit;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_change);

        initView();

        initEvent();
    }

    private void initEvent() {
        final Editable name = mName.getText();
        final Editable sex =  mSex.getText();
        final Editable age = mAge.getText();
        final Editable address = mAddress.getText();

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplication());
        mEditor = mSharedPreferences.edit();


        mCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.length() == 0 || sex.length() == 0 || age.length() == 0 || address.length() == 0 ) {
                    Toast toast = Toast.makeText(getApplication(), "请正确填写信息", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    mEditor.putString(Content.INFORMATION_NAME, String.valueOf(name));
                    mEditor.putString(Content.INFORMATION_SEX, String.valueOf(sex));
                    mEditor.putInt(Content.INFORMATION_AGE, Integer.parseInt(String.valueOf(age)));
                    mEditor.putString(Content.INFORMATION_ADDRESS, String.valueOf(address));
                    mEditor.commit();
                }

                finish();

            }
        });
    }


    private void initView() {
        mName = findViewById(R.id.personal_change_name);
        mSex = findViewById(R.id.personal_change_sex);
        mAge = findViewById(R.id.personal_change_age);
        mAddress = findViewById(R.id.personal_change_address);
        mCommit = findViewById(R.id.personal_change_commit);
    }
}
