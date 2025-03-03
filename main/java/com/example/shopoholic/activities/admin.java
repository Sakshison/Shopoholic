package com.example.shopoholic.activities;

import androidx.appcompat.app.AppCompatActivity;
import com.example.shopoholic.R;
import com.google.android.material.textfield.TextInputLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class admin extends AppCompatActivity {
    TextInputLayout name, email, password, phone;
    EditText fullname,etemail;
    Button update;
    Intent nintent;
    String stname, stemail, stpassword,stphone,rname, remail, rpassword,rphone;
    Cursor result;
    private static final String KEY_ANAME="adminname";
    private static final String KEY_AEMAIL="adminemail";
    private static final String KEY_APASSWORD="adminpassword";
    private static final String KEY_APHONE="adminphonenum";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        name = findViewById(R.id.admin_name_profile);
        email = findViewById(R.id.admin_email);
        password = findViewById(R.id.admin_password);
        fullname=findViewById(R.id.adminname_field);
        etemail=findViewById(R.id.admin_email_field);
        phone = findViewById(R.id.admin_phone);
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
        dbhelper mydb = new dbhelper(admin.this);
        SharedPreferences getShared = getSharedPreferences("admindata", MODE_PRIVATE);
        String value = getShared.getString(KEY_ANAME, null);
        String value1 = getShared.getString(KEY_AEMAIL, null);
        String value2 = getShared.getString(KEY_APASSWORD, null);
        String value3 = getShared.getString(KEY_APHONE, null);

        name.getEditText().setText(value);
        email.getEditText().setText(value1);
        phone.getEditText().setText(value3);
        password.getEditText().setText(value2);
        fullname.setText(value);
        etemail.setText(value1);
    }
}