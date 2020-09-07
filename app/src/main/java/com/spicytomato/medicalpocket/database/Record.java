package com.spicytomato.medicalpocket.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "record")
public class Record implements Serializable
{
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "time")
    private String time;

    @ColumnInfo(name = "doctor_name")
    private String doctorName;

    @ColumnInfo(name = "this_sick_name")
    private String thisSickName;

    @ColumnInfo(name = "past_sick_name")
    private String pastSickName;

    @ColumnInfo(name = "body_check")
    private String body_check;

    @ColumnInfo(name = "blood_check", typeAffinity = ColumnInfo.BLOB)
    private byte[] bloodCheck;

    @ColumnInfo(name = "suggestion")
    private String suggestion;

    @ColumnInfo(name = "cure_plan")
    private String curePlan;

    public Record(String doctorName, String thisSickName, String pastSickName, String body_check, byte[] bloodCheck, String suggestion, String curePlan) {
        this.doctorName = doctorName;
        this.thisSickName = thisSickName;
        this.pastSickName = pastSickName;
        this.body_check = body_check;
        this.bloodCheck = bloodCheck;
        this.suggestion = suggestion;
        this.curePlan = curePlan;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setThisSickName(String thisSickName) {
        this.thisSickName = thisSickName;
    }

    public void setPastSickName(String pastSickName) {
        this.pastSickName = pastSickName;
    }

    public void setBody_check(String body_check) {
        this.body_check = body_check;
    }

    public void setBloodCheck(byte[] bloodCheck) {
        this.bloodCheck = bloodCheck;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public void setCurePlan(String curePlan) {
        this.curePlan = curePlan;
    }

    public int getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getThisSickName() {
        return thisSickName;
    }

    public String getPastSickName() {
        return pastSickName;
    }

    public String getBody_check() {
        return body_check;
    }

    public byte[] getBloodCheck() {
        return bloodCheck;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public String getCurePlan() {
        return curePlan;
    }
}
