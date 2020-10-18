package com.example.kontakttagebuch.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PersonDao {
    @Query("SELECT * FROM person")
    List<Person> getAll();

    @Query("SELECT * FROM person WHERE uid IN (:userIds)")
    List<Person> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM person WHERE firstName LIKE :first AND " + "lastName LIKE :last LIMIT 1")
    Person findByName(String first, String last);

    @Insert
    void insertAll(Person... persons);

    @Delete
    void delete(Person person);
}