package com.biologi.ugm.reptilamfibijogja;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import static com.biologi.ugm.reptilamfibijogja.LoginActivity.Auth;
import static com.biologi.ugm.reptilamfibijogja.LoginActivity.MyPREFERENCES;
import static com.biologi.ugm.reptilamfibijogja.MainActivity.role;

/**
 * Reptil dan Amifibi Jogja adalah aplikasi yang diusulkan oleh kelompok studi herpetologi Biologi
 * Digunakan untuk mengakses informasi tentang hewan reptil dan amfibi daerah
 * ID : reptilamfibi , PSW : Reptilamfibi
 *
 *
 *
 *
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        //Mengecek Admin
        SharedPreferences prefs = this.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String lanSettings = prefs.getString(Auth, "Member");
        if (lanSettings.equals("Admin")) role = "Admin";

        //Agar splashcreen diam 3 detik
        Thread myThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}
