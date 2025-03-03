package com.example.shopoholic.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.shopoholic.R;
import com.google.android.material.textfield.TextInputLayout;

public class option_page extends AppCompatActivity {
    Button admin,user;
    dbhelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_page);
        admin = (Button) findViewById(R.id.admin_option);
        user = (Button) findViewById(R.id.user_option);
        db = new dbhelper(option_page.this);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.insertadmin("Sakshi Soni","sonisakshi00011@gmail.com","9118514729","sakshi20@");
                Intent nintent = new Intent(option_page.this, admin_login.class);
                startActivity(nintent);
                 }
        });
           user.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent nintent = new Intent(option_page.this, MainActivity.class);
                   startActivity(nintent);
               }
           });
    }
}
