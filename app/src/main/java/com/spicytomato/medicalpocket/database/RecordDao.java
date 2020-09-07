package com.spicytomato.medicalpocket.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RecordDao {
    @Delete
    void delete(Record...records);

    @Insert
    void insert(Record...records);

    @Query("SELECT *FROM record ORDER BY time DESC")
    LiveData<List<Record>> getALlRecords();

    @Query("SELECT * FROM record WHERE this_sick_name LIKE :pattern ORDER BY time DESC")
    LiveData<List<Record>> getPatternRecord(String pattern);

    @Query("DELETE FROM record")
    void deleteALl();

    @Update
    void update(Record...records);
}
