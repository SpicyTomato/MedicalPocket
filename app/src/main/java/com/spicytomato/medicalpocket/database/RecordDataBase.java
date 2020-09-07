package com.spicytomato.medicalpocket.database;

import android.content.Context;
import android.service.autofill.UserData;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {Record.class},version = 1,exportSchema = false)
public abstract class RecordDataBase extends RoomDatabase {

    private static RecordDataBase INSTANCE;

    public static RecordDataBase getInstance(Context context){
        if (INSTANCE == null){
            synchronized (RecordDataBase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),RecordDataBase.class,"record_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract RecordDao getRecordDao();
}
