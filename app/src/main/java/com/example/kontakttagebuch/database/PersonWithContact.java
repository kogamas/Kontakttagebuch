package com.example.kontakttagebuch.database;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

//this class creates a 1->n connection between Person(1) -> Contact(n)
public class PersonWithContact {
    @Embedded public Person person;
        @Relation(
                parentColumn = "pid",
                entityColumn = "personId"
        )
    public List<Contact> contacts;
}