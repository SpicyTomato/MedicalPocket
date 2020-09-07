package com.spicytomato.medicalpocket.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import com.spicytomato.medicalpocket.MyViewModel;
import com.spicytomato.medicalpocket.R;
import com.spicytomato.medicalpocket.base.BaseFragment;
import com.spicytomato.medicalpocket.database.Record;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Fragment_2 extends BaseFragment {

    private static final int REQUEST_PERMISSION_CODE = 101;

    public static final int PERMISSION_GRANTED = 0;//授权成功
    public static final int PERMISSION_DENIED = -1;//拒绝授权
    private Uri mUri;
    private View mRootView;
    private EditText mDoctorName;
    private EditText mThisSickName;
    private EditText mPastSickName;
    private EditText mBodyCheck;
    private ImageButton mBloodCheck;
    private EditText mSuggestion;
    private EditText mCurePlan;
    private ImageView mImageView;
    private Bitmap mPhoto;
    private MyViewModel mMyViewModel;
    private Button mButton_commit;

    @Override
    protected View onSubViewLoaded(LayoutInflater inflater, ViewGroup container) {
        mRootView = inflater.inflate(R.layout.fragment_2, container, false);
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentActivity activity = requireActivity();

        mMyViewModel = ViewModelProviders.of(activity).get(MyViewModel.class);

        mDoctorName = activity.findViewById(R.id.editText_doctor_name);
        mThisSickName = activity.findViewById(R.id.editText_this_sick);
        mPastSickName = activity.findViewById(R.id.editText_past_sick);
        mBodyCheck = activity.findViewById(R.id.editText_body_check);
        mBloodCheck = activity.findViewById(R.id.blood_check_photo);
        mSuggestion = activity.findViewById(R.id.editText_suggestion);
        mCurePlan = activity.findViewById(R.id.editText_cure_plan);
        mImageView = activity.findViewById(R.id.imageView);
        mButton_commit = activity.findViewById(R.id.button_commit_2);



        mBloodCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//大于Android 6.0
                    if (!checkPermission()) { //没有或没有全部授权
                        requestPermissions(); //请求权限
                    }
                } else {
                    takePhoto();//拍照逻辑
                }
            }
        });


        mButton_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editable doctorName = mDoctorName.getText();
                Editable thisSickName = mThisSickName.getText();
                Editable pastSickName = mPastSickName.getText();
                Editable bodyCheck = mBodyCheck.getText();
                Editable suggestion = mSuggestion.getText();
                Editable curePlan = mCurePlan.getText();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                mPhoto.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] datas = baos.toByteArray();


                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年-MM月dd日");

                String time = dateFormat.format(date);

                Log.d("TAG", "onClick: " + time);

                Record record = new Record(String.valueOf(doctorName),String.valueOf(thisSickName),String.valueOf(pastSickName),String.valueOf(bodyCheck),datas,String.valueOf(suggestion),String.valueOf(curePlan));
                record.setTime(time);
                mMyViewModel.insert(record);
                mDoctorName.setText("");
                mThisSickName.setText("");
                mPastSickName.setText("");
                mBodyCheck.setText("");
                mSuggestion.setText("");
                mCurePlan.setText("");


            }
        });



    }

    //检查权限
    private boolean checkPermission() {
        //是否有权限
        boolean haveCameraPermission = ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;

        boolean haveWritePermission = ContextCompat.checkSelfPermission(requireActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

        return haveCameraPermission && haveWritePermission;

    }

    // 请求所需权限
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestPermissions() {
        requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CODE);
    }

    // 请求权限后会在这里回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSION_CODE:

                boolean allowAllPermission = false;

                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {//被拒绝授权
                        allowAllPermission = false;
                        break;
                    }
                    allowAllPermission = true;
                }

                if (allowAllPermission) {
                    takePhoto();//开始拍照或从相册选取照片
                } else {
                    Toast.makeText(requireActivity(), "该功能需要授权方可使用", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mPhoto = data.getParcelableExtra("data");
        mImageView.setImageBitmap(mPhoto);

    }
}