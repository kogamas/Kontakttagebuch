package com.example.kontakttagebuch;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Person.class, Contact.class}, views = {ContactWithName.class}, version = 2, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract PersonDao personDao();
    public abstract ContactDao contactDao();
    public abstract PersonWithContactDao personWithContactDao();

    public static final String NAME = "main-db";

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    /*
    *) We've defined a singleton, AppDatabase, to prevent having multiple instances
    of the database opened at the same time.

    * *) getDatabase returns the singleton. It'll create the database the first
    time it's accessed, using Room's database builder to create a RoomDatabase
    object in the application context from the WordRoomDatabase class and names
    it "word_database".

    * *) We've created an ExecutorService with a fixed thread pool that you will use to run database operations asynchronously on a background thread.
     */

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "word_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {

                // Populate the database in the background.
                // If you want to start with more words, just add them.
                PersonDao personDao = INSTANCE.personDao();
                personDao.deleteAll();

                Person person = new Person("Max", "Mustermann", "0660112345", "m.muster@test.at", "ein freund");
                personDao.insertAll(person);
                person = new Person("Beate", "Musterfrau", "0660112345", "b.muster@test.at", "eine bekannte");
                personDao.insertAll(person);
            });

        }
    };

}
