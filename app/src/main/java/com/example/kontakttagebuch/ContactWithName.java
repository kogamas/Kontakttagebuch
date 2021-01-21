package com.example.kontakttagebuch;

import androidx.room.DatabaseView;

import java.util.Date;

@DatabaseView("SELECT person.firstName, person.lastName, contact.timeOfContact, contact.typeOfContact FROM contact INNER JOIN person ON person.pid=contact.personId ORDER BY contact.timeOfContact DESC")

public class ContactWithName {
    public String firstName;
    public String lastName;
    public Date timeOfContact;
    public int typeOfContact;

    public String getName() {
        return firstName +" "+ lastName;
    }

    public int getTypeOfContact() {
        return typeOfContact;
    }

    public Date getTimeOfContact() {
        return timeOfContact;
    }


}