package com.example.kontakttagebuch;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

/*
* This table stores the contacts made by the user
* additionally it stores the time of contact, quality of contact (risk of infection)
* and a text for additional information
* */
@Entity
public class Contact {
        @PrimaryKey(autoGenerate = true)
        public int cid;
        @ForeignKey(entity = Person.class, parentColumns = "pid", childColumns = "personId")
        public int personId;

        public Date timeOfContact;
        public int typeOfContact;
        public String text;



        public Contact(int personId, Date timeOfContact, int typeOfContact, String text) {
                this.personId = personId;
                this.timeOfContact = timeOfContact;
                this.typeOfContact = typeOfContact;
                this.text = text;
        }

        public String toString(){
                return cid +" "+ personId +" "+ typeOfContact  +" "+ timeOfContact.toString()  +" "+ text;
        }

        public int getCid() {
                return cid;
        }

        public int getTypeOfContact() {
                return typeOfContact;
        }

        public Date getTimeOfContact() {
                return timeOfContact;
        }
}
