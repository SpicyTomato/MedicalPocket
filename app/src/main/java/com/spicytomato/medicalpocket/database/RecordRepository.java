package com.spicytomato.medicalpocket.database;

import android.content.Context;
import android.os.AsyncTask;
import android.service.autofill.UserData;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RecordRepository {

    private final RecordDao mRecordDao;
    private final LiveData<List<Record>> mAllRecords;

    public RecordRepository(Context context){
        RecordDataBase userDataBase = RecordDataBase.getInstance(context);
        mRecordDao = userDataBase.getRecordDao();
        mAllRecords = mRecordDao.getALlRecords();
    }

    public LiveData<List<Record>> getAllRecords(){
        return mAllRecords;
    }

    public LiveData<List<Record>> getPatternRecord(String pattern){
        return mRecordDao.getPatternRecord("%" + pattern + "%");
    }

    public void insert(Record...records){
        new Insert(mRecordDao).execute(records);
    }

    public void delete(Record...records){
        new Delete(mRecordDao).execute(records);
    }

    public void update(Record...records){
        new Update(mRecordDao).execute(records);
    }

    public void deleteAll(){
        new DeleteAll(mRecordDao).execute();
    }

    static class Insert extends AsyncTask<Record,Void,Void>{
        RecordDao mRecordDao;

        Insert(RecordDao recordDao){
            this.mRecordDao = recordDao;
        }


        @Override
        protected Void doInBackground(Record... records) {
            mRecordDao.insert(records);
            return null;
        }
    }

    static class Delete extends AsyncTask<Record,Void,Void>{
        RecordDao mRecordDao;

        Delete(RecordDao recordDao){
            this.mRecordDao = recordDao;
        }

        @Override
        protected Void doInBackground(Record... records) {
            mRecordDao.delete(records);
            return null;
        }
    }

    static class Update extends AsyncTask<Record,Void,Void>{
        RecordDao mRecordDao;

        public Update(RecordDao recordDao) {
            this.mRecordDao = recordDao;
        }

        @Override
        protected Void doInBackground(Record... records) {
            mRecordDao.update(records);
            return null;
        }
    }

    static class DeleteAll extends AsyncTask<Void,Void,Void>{
        RecordDao mRecordDao;

        public DeleteAll(RecordDao recordDao) {
            this.mRecordDao = recordDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mRecordDao.deleteALl();
            return null;
        }
    }



}
