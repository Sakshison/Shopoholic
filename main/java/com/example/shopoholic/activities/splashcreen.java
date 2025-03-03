package com.example.shopoholic.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.animation.AnimationUtils;
import android.util.Pair;
import android.view.View;
import android.app.ActivityOptions;

import com.example.shopoholic.R;

public class splashcreen extends AppCompatActivity {
    ImageView logo;
    TextView name,punch;
Animation topAnim,bottomAnim;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splashcreen);
        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        logo = findViewById(R.id.logo);
        name = findViewById(R.id.textView);
        punch = findViewById(R.id.textView2);

//Set animation to elements
        logo.setAnimation(topAnim);
        name.setAnimation(bottomAnim);
        punch.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent( splashcreen.this, option_page.class);
                // Attach all the elements those you want to animate in design
                Pair[]pairs=new Pair[2];
                pairs[0]=new Pair<View, String>(logo,"logo_image");
                pairs[1]=new Pair<View, String>(name,"logo_text");
                //wrap the call in API level 21 or higher
                if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.LOLLIPOP)
                {
                    ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(splashcreen.this,pairs);
                    startActivity(intent,options.toBundle());
            }}
        }, 4000);
    }
    }