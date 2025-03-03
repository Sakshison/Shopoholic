package com.example.shopoholic.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.util.Patterns;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.ActivityOptions;
import android.util.Pair;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.View;
import android.widget.Toast;

import com.example.shopoholic.R;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    Button callSignUp, login_btn;
    ImageView image;
    TextView logoText, sloganText;
    TextInputLayout email, password;
    Intent nintent;
    dbhelper db;
    String  stemail, stpassword;
    private static final String KEY_NAME="username";
    private static final String KEY_EMAIL="email";
    private static final String KEY_PASSWORD="password";
    private static final String KEY_PHONE="phonenum";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //Hooks
        callSignUp = findViewById(R.id.register_screen);
        image = findViewById(R.id.logo_image);
        logoText = findViewById(R.id.logo_name);
        sloganText = findViewById(R.id.slogan_name);
        email = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.login_btn);
        stemail="";
        stpassword="";
        dbhelper db = new dbhelper(MainActivity.this);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"Range", "SuspiciousIndentation"})
            @Override
            public void onClick(View v) {
                stemail=email.getEditText().getText().toString();
                stpassword=password.getEditText().getText().toString();
                SharedPreferences shrd = getSharedPreferences("userdata",MODE_PRIVATE);
                if (stemail.isEmpty())
                email.setError("Field cannot be empty");
                if(stpassword.isEmpty())
                    password.setError("Field cannot be empty");
                    if (!Patterns.EMAIL_ADDRESS.matcher(stemail).matches())
                        email.setError("Invalid email address");
                        Boolean checkuser = db.checkid(stemail);
                if(checkuser==false)
                    email.setError("Email does not exist");
                     else {
                    Cursor result = db.getpassword(stemail);
                    result.moveToFirst();
                    String uemail="",upassword="";
                    String uname = result.getString(result.getColumnIndex("username"));
                    String uphone = result.getString(result.getColumnIndex("phonenum"));
                    uemail=result.getString(result.getColumnIndex("email"));
                    upassword=result.getString(result.getColumnIndex("password"));
                    if(upassword.equals(stpassword)) {
                        Toast.makeText(MainActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                        nintent = new Intent(MainActivity.this, homescreen.class);
                        startActivity(nintent);
                        SharedPreferences.Editor editor = shrd.edit();
                        editor.putString(KEY_NAME,uname);
                        editor.putString(KEY_PASSWORD,upassword);
                        editor.putString(KEY_EMAIL,uemail);
                        editor.putString(KEY_PHONE,uphone);
                        editor.apply();
                    }else {
                        Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, register.class);
                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View, String>(image, "logo_image");
                pairs[1] = new Pair<View, String>(logoText, "logo_text");
                pairs[2] = new Pair<View, String>(sloganText, "logo_punch");
                pairs[3] = new Pair<View, String>(email, "logo_user");
                pairs[4] = new Pair<View, String>(password, "logo_pass");
                pairs[5] = new Pair<View, String>(login_btn, "logo_go");
                pairs[6] = new Pair<View, String>(callSignUp, "logo_login");
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
                    startActivity(intent, options.toBundle());
                }
            }
        });

    }
}