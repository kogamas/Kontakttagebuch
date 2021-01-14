package com.example.kontakttagebuch;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;
@Dao
public interface PersonWithContactDao {
    @Transaction
    @Query("SELECT * FROM person")
    List<PersonWithContact> getPersonsWithContacts();
}

