package com.example.kontakttagebuch;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class AppRepository {
    private ContactDao mContactDao;
    private PersonDao mPersonDao;
    private PersonWithContactDao mPersonWithContact;

    private LiveData<List<Person>> mAllPersons;
    private LiveData<List<NameTuple>> mAllPersonNames;

    AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mContactDao = db.contactDao();
        mPersonDao = db.personDao();
        mPersonWithContact = db.personWithContactDao();
        mAllPersons = mPersonDao.getAlphabetizedPersons();
        mAllPersonNames = mPersonDao.getAlphabetizedNames();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Person>> getAllPersons() {
        return mAllPersons;
    }
    //TODO: add method to get all Contacts of a person

    //returns Person names as string
    LiveData<List<NameTuple>> getAllPersonNames() {return  mAllPersonNames;}

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insertPerson(Person person) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mPersonDao.insertAll(person);
        });
    }

    void insertContact(Contact contact) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mContactDao.insertAll(contact);
        });
    }
/*
The main takeaways:

    The DAO is passed into the repository constructor as opposed to the whole database. This is because you only need access to the DAO, since it contains all the read/write methods for the database. There's no need to expose the entire database to the repository.
    The getAllPersons method returns the LiveData list of Persons from Room; we can do this because of how we defined the getAlphabetizedWords method to return LiveData in the "The LiveData class" step. Room executes all queries on a separate thread. Then observed LiveData will notify the observer on the main thread when the data has changed.
    We need to not run the insert on the main thread, so we use the ExecutorService we created in the WordRoomDatabase to perform the insert on a background thread.

 */
}
