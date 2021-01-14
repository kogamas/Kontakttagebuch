package com.example.kontakttagebuch;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Person {
        @PrimaryKey(autoGenerate = true)
        public int pid;

        public String firstName;
        public String lastName;
        public String phone;
        public String email;
        public String text;



        //constructor
        public Person(String firstName, String lastName, String phone, String email, String text) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.phone = phone;
                this.email = email;
                this.text = text;
        }
        @Ignore
        public Person(String firstName) {
                this.firstName = firstName;
        }
        @Ignore
        public Person(String firstName, String lastName) {
                this.firstName = firstName;
                this.lastName = lastName;
        }
        //getter method for PrimaryKey pid
        public int getPid() {
                return this.pid;
        }

        //setter method for Firstname
        public void changeFirstname(String firstName) {
                this.firstName = firstName;
        }

        //getter method for Firstname
        public String getFirstname() {
                return this.firstName;
        }



}
