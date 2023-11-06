package com.zybooks.projectthreecs360;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainJava extends AppCompatActivity {

    EditText username, password, confirmPassword;
    Button signup, signin, smsbutton;
    //edit texts and button
    Database DB;
    //DB from database class
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.repassword);
        signup = findViewById(R.id.btnsignup);
        signin = findViewById(R.id.btnsignin);
        smsbutton = findViewById(R.id.smsbutton);
    //all necessary ids for buttons and input fields


        DB = new Database(this);
    //get the specific ids
        signup.setOnClickListener(view -> {
            String user = username.getText().toString();
            String pass = password.getText().toString();
            String repass = confirmPassword.getText().toString();
    //convert to string
            if(user.equals("")||pass.equals("")||repass.equals(""))
                Toast.makeText(MainJava.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            //if input field is empty, inform the user to enure they fill them all out
            else{
                if(pass.equals(repass)){
                    Boolean checkuser = DB.checkUsername(user);
                    if(!checkuser){
                        //if check user false
                        Boolean insert = DB.insertData(user, pass);
                        if(insert){
                            Toast.makeText(MainJava.this, "Welcome!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), HomeJava.class);
                            startActivity(intent);
                            //if the log in is successful greet the user
                        }else{
                            Toast.makeText(MainJava.this, "Wrong username / password", Toast.LENGTH_SHORT).show();
                            //if not valid inform the user they have the wrong username and password.
                        }
                    }
                    else{
                        Toast.makeText(MainJava.this, "You already have an account with PlannerVent, please sign in", Toast.LENGTH_SHORT).show();
                    }
                    //if user already has an account with PlannerVent, inform them to go to the sign in page
                }else{
                    Toast.makeText(MainJava.this, "Passwords are not the same!", Toast.LENGTH_SHORT).show();
                }
                //if password and confirm password are not the same, inform the user they are not the same.
            } });

        signin.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), LoginJava.class);
            startActivity(intent);
            //on user clicking the sign in button on the create account screen, direct them over to the sign in page
        });
        //
        smsbutton.setOnClickListener(view -> {
            Toast.makeText(MainJava.this, "SMS Messaging Approved!", Toast.LENGTH_SHORT).show();
        });






    }
}