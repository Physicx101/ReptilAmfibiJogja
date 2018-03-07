package com.example.fauziw97.reptilamfibijogja;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.fauziw97.reptilamfibijogja.MainActivity.role;


public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.email)
    AutoCompleteTextView email;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.email_sign_in_button)
    Button signin;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Auth = "authKey";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });


        signin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });


    }


    private void attemptLogin() {

        // Reset errors.
        email.setError(null);
        password.setError(null);

        // Store values at the time of the login attempt.
        String emailAdmin = email.getText().toString();
        String passwordAdmin = password.getText().toString();


        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(passwordAdmin) && !isPasswordValid(passwordAdmin)) {
            password.setError(getString(R.string.error_invalid_password));

        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(emailAdmin)) {
            email.setError("Email tidak boleh kosong");

        } else if (!isEmailValid(emailAdmin)) {
            email.setError("Email tidak valid");


        }

        if (emailAdmin.equalsIgnoreCase("admin@amfirep.com") && passwordAdmin.equalsIgnoreCase("amfirep")) {

            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(Auth, "Admin");
            editor.commit();
            role = "Admin";
            startActivity(new Intent(LoginActivity.this, MainActivity.class));

        } else if (emailAdmin.equalsIgnoreCase("admin@amfirep.com")) {
            password.setError("Password Salah");

        } else email.setError("Email Salah");

    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }


}
