package com.example.shopoholic.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.shopoholic.R;
import com.google.android.material.textfield.TextInputLayout;

public class user_profile extends AppCompatActivity {
    TextInputLayout name, email, password, phone;
    EditText fullname,etemail;
    Button update;
    Intent nintent;
    String stname, stemail, stpassword,stphone,rname, remail, rpassword,rphone;
    Cursor result;
    private static final String KEY_NAME="username";
    private static final String KEY_EMAIL="email";
    private static final String KEY_PASSWORD="password";
    private static final String KEY_PHONE="phonenum";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        name = findViewById(R.id.full_name_profile);
        email = findViewById(R.id.email_profile);
        password = findViewById(R.id.password_profile);
        fullname=findViewById(R.id.fullname_field);
        etemail=findViewById(R.id.email_field);
        phone = findViewById(R.id.phone_profile);
        update = (Button) findViewById(R.id.update_profile);
        stname="";
        stemail="";
        stpassword="";
        stphone="";
        rname="";
        remail="";
        rpassword="";
        rphone="";
        result=null;
        dbhelper mydb = new dbhelper(user_profile.this);
        SharedPreferences getShared = getSharedPreferences("userdata", MODE_PRIVATE);
        String value = getShared.getString(KEY_NAME, null);
        String value1 = getShared.getString(KEY_EMAIL, null);
        String value2 = getShared.getString(KEY_PASSWORD, null);
        String value3 = getShared.getString(KEY_PHONE, null);

            name.getEditText().setText(value);
            email.getEditText().setText(value1);
            phone.getEditText().setText(value3);
            password.getEditText().setText(value2);
            fullname.setText(value);
            etemail.setText(value1);

    }
}