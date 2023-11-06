package com.zybooks.projectthreecs360;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginJava extends AppCompatActivity {
    EditText username, password;
    //username and password as edit texts
    Button loginButton, backbutton;
    //loginButton as button
    Database DB;
    //DB from database java class





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username1);
        password = findViewById(R.id.password1);
        loginButton = findViewById(R.id.btnsignin1);
        DB = new Database(this);
        backbutton = findViewById(R.id.backbutton);

        backbutton.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainJava.class);
            startActivity(intent);
            //set on click listener for back button, sends the user back to the create account screen

        });



        //get all necessary ids for user, pass, login

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();
                //get username and password, set them to strings
                if (user.equals("") || pass.equals(""))
                    Toast.makeText(LoginJava.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                    //tell user to fill out all the input fields
                else {
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if (checkuserpass) {
                        Toast.makeText(LoginJava.this, "Welcome!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeJava.class);
                        startActivity(intent);
                        //if the user name and password have been validated, greet the user and display the HomeJava class
                        //start the new activity
                    } else {
                        Toast.makeText(LoginJava.this, "Wrong username or password", Toast.LENGTH_SHORT).show();
                    }
                    //wrong user or pass, inform user that they have inputted the wrong username or password
                }
            }

        });
    }

}