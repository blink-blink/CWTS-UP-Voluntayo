package com.example.upvoluntaryo;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.upvoluntaryo.objects.Users;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button btnSignIn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.usernameR);
        password = (EditText) findViewById(R.id.passwordR);
        btnSignIn = (Button) findViewById(R.id.loginButton);
        DB = new DBHelper(this);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pw = password.getText().toString();

                if (user.equals("") || pw.equals(""))
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Users logInUser = DB.checkUsernamePassword(user,pw);
                    if (logInUser != null){

                        //session manager
                        SessionManager sessionManager = new SessionManager(LoginActivity.this);
                        sessionManager.createLoginSession(logInUser.getUserId(),
                                logInUser.getFullName(),
                                logInUser.getUsername(),
                                logInUser.getPassword(),
                                logInUser.getPronoun(),
                                logInUser.getBirthday(),
                                logInUser.getAbout());

                        Toast.makeText(LoginActivity.this, "Sign in sucessful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                        //tobechanged
                        finishAffinity();

                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Invalid Login Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
