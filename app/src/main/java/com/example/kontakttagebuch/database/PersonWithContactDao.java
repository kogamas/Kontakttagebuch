package com.example.kontakttagebuch.database;

import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

public interface PersonWithContactDao {
    @Transaction
    @Query("SELECT * FROM person")
    List<PersonWithContact> getPersonsWithContacts();
}

