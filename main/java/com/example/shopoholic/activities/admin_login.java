package com.example.shopoholic.activities;

import androidx.appcompat.app.AppCompatActivity;
import com.example.shopoholic.R;
import com.google.android.material.textfield.TextInputLayout;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class admin_login extends AppCompatActivity {
    Button login_btn;
    ImageView image;
    TextView logoText, sloganText;
    TextInputLayout email, password;
    Intent nintent;
    dbhelper db;
    String  stemail, stpassword;
    private static final String KEY_ANAME="adminname";
    private static final String KEY_AEMAIL="adminemail";
    private static final String KEY_APASSWORD="adminpassword";
    private static final String KEY_APHONE="adminphonenum";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_admin_login);
        //Hooks
        image = findViewById(R.id.logo_image);
        logoText = findViewById(R.id.logo_name);
        sloganText = findViewById(R.id.slogan_name);
        email = findViewById(R.id.adminemail);
        password = findViewById(R.id.adminpassword);
        login_btn = findViewById(R.id.adminlogin_btn);
        stemail="";
        stpassword="";
        dbhelper db = new dbhelper(admin_login.this);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"Range", "SuspiciousIndentation"})
            @Override
            public void onClick(View v) {
                stemail=email.getEditText().getText().toString();
                stpassword=password.getEditText().getText().toString();
                SharedPreferences shred = getSharedPreferences("admindata",MODE_PRIVATE);
                if (stemail.isEmpty())
                    email.setError("Field cannot be empty");
                if(stpassword.isEmpty())
                    password.setError("Field cannot be empty");
                if (!Patterns.EMAIL_ADDRESS.matcher(stemail).matches())
                    email.setError("Invalid email address");
                else {
                    Cursor result = db.getadmin_pass(stemail);
                    result.moveToFirst();
                    String uemail="",upassword="";
                    String uname = result.getString(result.getColumnIndex("username"));
                    String uphone = result.getString(result.getColumnIndex("phonenum"));
                    uemail=result.getString(result.getColumnIndex("email"));
                    upassword=result.getString(result.getColumnIndex("password"));
                    if(upassword.equals(stpassword)) {
                        Toast.makeText(admin_login.this, "Login successfully", Toast.LENGTH_SHORT).show();
                        nintent = new Intent(admin_login.this, admin.class);
                        startActivity(nintent);
                        SharedPreferences.Editor editor = shred.edit();
                        editor.putString(KEY_ANAME,uname);
                        editor.putString(KEY_APASSWORD,upassword);
                        editor.putString(KEY_AEMAIL,uemail);
                        editor.putString(KEY_APHONE,uphone);
                        editor.apply();
                    }else {
                        Toast.makeText(admin_login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}