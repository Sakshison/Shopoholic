package com.example.shopoholic.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.shopoholic.R;
import com.google.android.material.textfield.TextInputLayout;

public class register extends AppCompatActivity {
    TextInputLayout name, email, password, renterpassword,phone;
    Button reg,move_log;
    Intent nintent;
    dbhelper db;
    String stname, stemail, stpassword, strenterpassword,stphone;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = (TextInputLayout) findViewById(R.id.username);
        email = (TextInputLayout) findViewById(R.id.email);
        phone = (TextInputLayout) findViewById(R.id.phoneNo);
        password = (TextInputLayout) findViewById(R.id.password);
        renterpassword = (TextInputLayout) findViewById(R.id.conpassword);
        reg = (Button) findViewById(R.id.register);
        move_log= (Button) findViewById(R.id.login);
        db = new dbhelper(this);
        stname = name.getEditText().getText().toString();
        stemail = email.getEditText().getText().toString();
        stphone = phone.getEditText().getText().toString();
        stpassword = password.getEditText().getText().toString();
        strenterpassword = renterpassword.getEditText().getText().toString();
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validatePassword() | !validateConfirmpass() | !validatePhoneNo() | !validateEmail() | !validateUsername()) {
                    return;
                }
                Boolean regResult = db.insertreg(stname,stemail,stphone,stpassword);
                if(regResult==true) {
                    Toast.makeText(register.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                    Intent nintent = new Intent(register.this, option_page.class);
                    startActivity(nintent);
                }else{
                    Toast.makeText(register.this,"Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }
    });
        move_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nintent = new Intent(register.this, MainActivity.class);
                    startActivity(nintent);
                }

        });

        }
    private Boolean validateUsername() {
        stname = name.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if (stname.isEmpty()) {
            name.setError("Field cannot be empty");
            return false;
        } else if (stname.length() >= 15) {
            name.setError("Username too long");
            return false;
        } else if (!stname.matches(noWhiteSpace)) {
            name.setError("White Spaces are not allowed");
            return false;
        } else {
            name.setError(null);
            name.setEnabled(false);
            return true;
        }
    }
    private Boolean validateEmail() {
        stemail = email.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (stemail.isEmpty()) {
            email.setError("Field cannot be empty");
            return false;
        } else if (!stemail.matches(emailPattern)) {
            email.setError("Invalid email address");
            return false;
        } else {
            email.setError(null);
            email.setEnabled(false);
            return true;
        }
    }
    private Boolean validatePhoneNo() {
        stphone = phone.getEditText().getText().toString();
        if (stphone.isEmpty()) {
            phone.setError("Field cannot be empty");
            return false;
        }else
            if((int)stphone.length()!=10){
                phone.setError("Phone Number is invalid");
                return false;
            }
        else {
            phone.setError(null);
            phone.setEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword() {
        stpassword = password.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if (stpassword.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else if (!stpassword.matches(passwordVal)) {
            password.setError("Password is too weak");
            return false;
        } else {
            password.setError(null);
            password.setEnabled(false);
            return true;
        }
    }
    private Boolean validateConfirmpass() {
         strenterpassword=renterpassword.getEditText().getText().toString();
        if (strenterpassword.isEmpty()) {
            renterpassword.setError("Field cannot be empty");
            return false;
        } else if (!strenterpassword.matches(stpassword)) {
            renterpassword.setError("Password does not match");
            return false;
        } else {
            renterpassword.setError(null);
            renterpassword.setEnabled(false);
            return true;
        }
    }
}