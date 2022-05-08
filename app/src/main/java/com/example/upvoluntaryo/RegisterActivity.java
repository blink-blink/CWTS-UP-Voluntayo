package com.example.upvoluntaryo;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    TextView register;
    EditText username, password, rePassword;
    Button btnRegister,btnExistingUser;
    DBHelper DB;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //assign view to vars
        register = (TextView) findViewById(R.id.register);
        username = (EditText) findViewById(R.id.usernameR);
        password = (EditText) findViewById(R.id.passwordR);
        rePassword = (EditText) findViewById(R.id.retypePasswordR);
        btnRegister = (Button) findViewById(R.id.registerButton);
        btnExistingUser = (Button) findViewById(R.id.existingUserButton);

        DB = new DBHelper(this);

        //on click listers
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //do things on register button click
                String user = username.getText().toString();
                String pw = password.getText().toString();
                String rPW = rePassword.getText().toString();

                if (user.equals("") || pw.equals("") || rPW.equals(""))
                    Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else
                if  (pw.equals(rPW)){
                    if (DB.checkUsername(user) == false){
                        if (DB.insertUserData(user, pw)){
                            Toast.makeText(RegisterActivity.this, "Registered Succesfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Password do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnExistingUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                // do things on sign in button click
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}
