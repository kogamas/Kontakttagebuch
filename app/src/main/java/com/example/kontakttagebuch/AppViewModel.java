package com.example.kontakttagebuch;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AppViewModel  extends AndroidViewModel {
    private AppRepository mRepository;

    private final LiveData<List<Person>> mAllPersons;
    private final LiveData<List<NameTuple>> mAllPersonNames;
    private final LiveData<List<Person>> mTop5Persons;
    private final LiveData<List<ContactWithName>> mContactsWithName;

    public AppViewModel (Application application) {
        super(application);
        mRepository = new AppRepository(application);
        mAllPersons = mRepository.getAllPersons();
        mAllPersonNames = mRepository.getAllPersonNames();
        mTop5Persons = mRepository.getTop5Persons();
        mContactsWithName = mRepository.getAllContactsWithName();
    }

    LiveData<List<Person>> getAllPersons() { return mAllPersons; }

    LiveData<List<Person>> getTop5Persons() {
        return mTop5Persons;
    }

    LiveData<List<NameTuple>> getAllPersonNames() { return mAllPersonNames; }

    LiveData<List<ContactWithName>> getAllContactsWithName() {return  mContactsWithName;}

    public void insertPerson(Person person) { mRepository.insertPerson(person); }

    public void insertContact(Contact contact) { mRepository.insertContact(contact); }


}