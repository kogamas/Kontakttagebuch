package com.example.kontakttagebuch;

import java.util.Date;

public class Contact {
    Date timeOfContact;
    int typeOfContact;

    public Contact(Date timeOfContact, int typeOfContact) {
        this.timeOfContact = timeOfContact;
        this.typeOfContact = typeOfContact;
    }

    public Contact() {
        this.timeOfContact = new Date();
        this.typeOfContact = 0;     //0 = default contact type
    }

    public Contact(int typeOfContact) {
        this.typeOfContact = typeOfContact;
    }

}
