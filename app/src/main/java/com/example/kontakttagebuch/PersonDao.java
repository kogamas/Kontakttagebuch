package com.example.kontakttagebuch;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PersonDao {
    @Query("SELECT * FROM person")
    List<Person> getAll();

    @Query("SELECT * FROM person WHERE pid IN (:personIds)")
    List<Person> loadAllByIds(int[] personIds);

    @Query("SELECT * FROM person WHERE firstName LIKE :first AND " + "lastName LIKE :last LIMIT 1")
    Person findByName(String first, String last);

    @Query("SELECT * FROM person WHERE phone LIKE :phone LIMIT 1")
    Person findByPhone(String phone);

    @Query("SELECT * FROM person WHERE email LIKE :email LIMIT 1")
    Person findByEmail(String email);

    @Query("SELECT * FROM person ORDER BY firstName ASC")
    LiveData<List<Person>> getAlphabetizedPersons();
    //this crates a LiveData object with the list of persons

    @Query("SELECT firstName,lastName FROM person ORDER BY firstName ASC")
    LiveData<List<NameTuple>> getAlphabetizedNames();

    @Query("SELECT * FROM person WHERE pid LIKE :id LIMIT 1")
    Person findById(int id);

    @Query("DELETE FROM person")
    void deleteAll();

    @Insert
    void insertAll(Person... persons);

    @Delete
    void delete(Person person);
}