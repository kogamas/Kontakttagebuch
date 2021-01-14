package com.example.kontakttagebuch;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AppViewModel  extends AndroidViewModel {
    private AppRepository mRepository;

    private final LiveData<List<Person>> mAllPersons;

    public AppViewModel (Application application) {
        super(application);
        mRepository = new AppRepository(application);
        mAllPersons = mRepository.getAllPersons();
    }
    LiveData<List<Person>> getAllPersons() { return mAllPersons; }

    public void insertPerson(Person person) { mRepository.insertPerson(person); }

    public void insertContact(Contact contact) { mRepository.insertContact(contact); }

}