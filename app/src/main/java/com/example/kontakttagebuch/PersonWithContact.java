package com.example.kontakttagebuch;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class PersonWithContact {
    @Embedded
    public Person person;
    @Relation(
            parentColumn = "pid",
            entityColumn = "personId"
    )
    public List<Contact> contacts;
}
