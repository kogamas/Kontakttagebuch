package com.example.kontakttagebuch.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Person {
        @PrimaryKey
        public int uid;

        public String firstName;
        public String lastName;
}
