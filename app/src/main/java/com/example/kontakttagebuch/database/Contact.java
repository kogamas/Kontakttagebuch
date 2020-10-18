package com.example.kontakttagebuch.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;

/*
* This table stores the contacts made by the user
* additionally it stores the time of contact, quality of contact (risk of infection)
* and a text for additional information
* */
@Entity
public class Contact {
        @PrimaryKey
        public int cid;

        //personID
        public int personId;

        public Timestamp timestamp;
        public String text;
}
