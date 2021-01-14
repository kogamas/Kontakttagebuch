package com.example.kontakttagebuch;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDao {
    @Query("SELECT * FROM contact")
    List<Contact> getAll();

    @Query("SELECT * FROM contact WHERE cid IN (:contactIds)")
    List<Contact> loadAllByIds(int[] contactIds);

    @Query("SELECT * FROM contact WHERE typeOfContact LIKE :typeOfContact")
    List<Contact> loadAllByType(int typeOfContact);


    /*  //Example Query
    @Query("SELECT * FROM person WHERE firstName LIKE :first AND " + "lastName LIKE :last LIMIT 1")
    Person findByName(String first, String last);
*/



    @Insert
    void insertAll(Contact... contact);

    @Delete
    void delete(Contact contact);
}