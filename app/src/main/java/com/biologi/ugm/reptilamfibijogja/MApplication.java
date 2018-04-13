package com.biologi.ugm.reptilamfibijogja;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by tommywahyu44 on 3/7/2018.
 */
public class MApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}