package com.jdkgroup.database;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.jdkgroup.database.room.User;

import java.util.Calendar;
import java.util.Date;

public class DatabaseInitializer {

    public static void populateSync(@NonNull final AppDatabase db) {
        populateWithTestData(db);
    }

    private static User addUser(final AppDatabase db, final String id, final String name, final String lastName, final int age) {
        User user = new User();
        user.id = id;
        user.age = age;
        user.name = name;
        user.lastName = lastName;
        db.userModel().insertUser(user);
        return user;
    }

    private static void populateWithTestData(AppDatabase db) {
        db.userModel().deleteAll();

        addUser(db, "1", "Kamlesh", "Lakhani", 0);
        addUser(db, "2", "Kamlesh", "Lakhani", 1);
        addUser(db, "3", "Jayshree", "Amipara", 3);
    }

    private static Date getTodayPlusDays(int daysAgo) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, daysAgo);
        return calendar.getTime();
    }
}
