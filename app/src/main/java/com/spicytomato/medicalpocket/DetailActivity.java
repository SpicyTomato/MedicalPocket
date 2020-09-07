package com.spicytomato.medicalpocket;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import com.spicytomato.medicalpocket.content.Content;
import com.spicytomato.medicalpocket.database.Record;
import com.spicytomato.medicalpocket.fragments.Fragment_1;

import org.w3c.dom.Text;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private TextView mName;
    private TextView mSex;
    private TextView mAge;
    private TextView mAddress;
    private TextView mTime;
    private TextView mThisSick;
    private TextView mPastSick;
    private TextView mBodyCheck;
    private TextView mSuggestion;
    private TextView mCurePlan;
    private ImageView mBloodCheck;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initView();
    }

    private void initView() {
        MyViewModel myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);

        Record record = (Record) getIntent().getSerializableExtra(Content.DETAIL_PAGE);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplication());


        mName = findViewById(R.id.patient_name);
        mSex = findViewById(R.id.patient_sex);
        mAge = findViewById(R.id.patient_age);
        mAddress = findViewById(R.id.patient_address);
        mTime = findViewById(R.id.patient_time);
        mThisSick = findViewById(R.id.patient_this_sick);
        mPastSick = findViewById(R.id.patient_past_sick);
        mBodyCheck = findViewById(R.id.patient_body_check);
        mSuggestion = findViewById(R.id.patient_suggestion);
        mCurePlan = findViewById(R.id.patient_cureplan);
        mBloodCheck = findViewById(R.id.patient_blood_check);

        int age = sharedPreferences.getInt(Content.INFORMATION_AGE,0);

        mName.setText(sharedPreferences.getString(Content.INFORMATION_NAME, ""));
        mSex.setText(sharedPreferences.getString(Content.INFORMATION_SEX, ""));
        mAge.setText(String.valueOf(age));
        mAddress.setText(sharedPreferences.getString(Content.INFORMATION_ADDRESS, ""));
        mTime.setText(record.getTime());
        mThisSick.setText(record.getThisSickName());
        mPastSick.setText(record.getPastSickName());
        mBodyCheck.setText(record.getBody_check());
        mSuggestion.setText(record.getSuggestion());
        mCurePlan.setText(record.getCurePlan());

        //将byte[]类型转换成Bitmap类型
        byte[] b = record.getBloodCheck();
        Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
        mBloodCheck.setImageBitmap(bitmap);


    }

}
