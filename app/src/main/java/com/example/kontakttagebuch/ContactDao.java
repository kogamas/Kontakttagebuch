package com.example.kontakttagebuch;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.Date;
import java.util.List;

@Dao
public interface ContactDao {
    @Query("SELECT * FROM contact")
    List<Contact> getAll();

    @Query("SELECT * FROM contact WHERE cid IN (:contactIds)")
    List<Contact> loadAllByIds(int[] contactIds);

    @Query("SELECT * FROM contact WHERE typeOfContact LIKE :typeOfContact")
    LiveData<List<Contact>> loadAllByType(int typeOfContact);

    @Query("SELECT * FROM contact WHERE timeOfContact BETWEEN :from AND :to")
    LiveData<List<Contact>> loadAllByContactTime(Date from, Date to);

    @Query("SELECT * FROM contact WHERE timeOfContact BETWEEN :from AND :to  AND typeOfContact LIKE :typeOfContact")
    LiveData<List<Contact>> loadAllByContactTimeAndType(Date from, Date to, int typeOfContact);

    @Query("SELECT * FROM ContactWithName")
    LiveData<List<ContactWithName>> loadAllContactWithNames();

    /*  //Example Query
    @Query("SELECT * FROM person WHERE firstName LIKE :first AND " + "lastName LIKE :last LIMIT 1")
    Person findByName(String first, String last);
*/



    @Insert
    void insertAll(Contact... contact);

    @Delete
    void delete(Contact contact);


}