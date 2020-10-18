package com.example.kontakttagebuch;

import java.util.Date;
import java.util.LinkedList;

public class Person {
    String name = "";
    LinkedList<Contact> contactList;

    //constructor
    public Person(String name) {
        this.name = name;
        this.contactList = new LinkedList<Contact>();
    }

    //setter method for name
    public void changeName(String name) {
        this.name = name;
    }

    public void addContact(Contact newContact) {
        this.contactList.add(newContact);
    }

    public void addContact(int typeOfContact) {
        this.contactList.add(new Contact(typeOfContact));
    }

    public void addContact(Date timeOfContact, int typeOfContact) {
        this.contactList.add(new Contact(timeOfContact, typeOfContact));
    }


}
