package com.jdkgroup.interviewdemo.activity;

import android.os.Bundle;

import com.jdkgroup.baseclass.BaseActivity;
import com.jdkgroup.database.AppDatabase;
import com.jdkgroup.database.DatabaseInitializer;
import com.jdkgroup.database.room.User;
import com.jdkgroup.interviewdemo.R;

import java.util.List;

public class RoomDBActivity extends BaseActivity {

    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_api);

        mDb = AppDatabase.getInMemoryDatabase(getApplicationContext());

        userInsert();
        fetchData();
    }

    private void userInsert() {
        DatabaseInitializer.populateSync(mDb);
    }

    private void fetchData() {
        StringBuilder sb = new StringBuilder();
        List<User> youngUsers = mDb.userModel().findUsersYoungerThanSolution(3);

        System.out.println("Tag " + youngUsers.size());
    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }
}
