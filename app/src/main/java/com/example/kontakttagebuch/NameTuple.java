package com.example.kontakttagebuch;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;

public class NameTuple {
    @ColumnInfo(name = "pid")
    public int pid;

    @ColumnInfo(name = "firstName")
    public String firstName;

    @ColumnInfo(name = "lastName")
    @NonNull
    public String lastName;

    public String toString() {
        return firstName + " " +lastName;
    }

    public int getPid() {return pid;}
}



