package com.spicytomato.medicalpocket;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.spicytomato.medicalpocket.database.Record;
import com.spicytomato.medicalpocket.database.RecordRepository;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    RecordRepository mRecordRepository;

    public MyViewModel(@NonNull Application application) {
        super(application);
        mRecordRepository = new RecordRepository(application);
    }

    public LiveData<List<Record>> getAllRecords(){
        return mRecordRepository.getAllRecords();
    }

    public LiveData<List<Record>> getPatternRecord(String pattern){
        return mRecordRepository.getPatternRecord(pattern);
    }

    public void insert(Record...records){
        mRecordRepository.insert(records);
    }

    public void delete(Record...records){
        mRecordRepository.delete(records);
    }

    public void update(Record...records){
        mRecordRepository.update(records);
    }

    public void deleteAll(){
        mRecordRepository.deleteAll();
    }
}
