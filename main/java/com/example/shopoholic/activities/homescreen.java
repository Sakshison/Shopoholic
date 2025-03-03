package com.example.shopoholic.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Carousel;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.shopoholic.databinding.ActivityHomescreenBinding;
import com.google.android.material.navigation.NavigationView;
import android.content.Intent;
import android.annotation.SuppressLint;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.widget.Toast;
import android.content.SharedPreferences;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.Menu;
import android.os.Bundle;

import com.example.shopoholic.R;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;

public class homescreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
  ActivityHomescreenBinding binding;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    EditText etemail;
    String stemail;
    Menu menu;
    TextView textView;
    ViewPager2 viewpager2;
    private static final String KEY_EMAIL="email";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityHomescreenBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_homescreen);
        viewpager2=findViewById(R.id.viewPager);
       drawerLayout=findViewById(R.id.drawer_layout);
       navigationView=findViewById(R.id.nav_view);
        //textView=findViewById(R.id.textView);
        toolbar=findViewById(R.id.toolbar);

        List<SlideIten> sliderItem=new ArrayList<>();
        sliderItem.add(new SlideIten(R.drawable.slide1));
        sliderItem.add(new SlideIten(R.drawable.slide2));
        sliderItem.add(new SlideIten(R.drawable.slide3));
        viewpager2.setAdapter(new SlideAdapter(sliderItem, viewpager2));

        setSupportActionBar(toolbar);
navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
    drawerLayout.addDrawerListener(toggle);
    toggle.syncState();
    navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                break;
            case R.id.nav_settings:
                stemail="";
               // dbhelper mydb = new dbhelper(homescreen.this);
                SharedPreferences getShared = getSharedPreferences("userdata", MODE_PRIVATE);
                String value1 = getShared.getString(KEY_EMAIL, null);
                Toast.makeText(this, "email"+value1, Toast.LENGTH_SHORT).show();
                etemail.setText(value1);

                break;
            case R.id.nav_profile:
                Intent intent = new Intent(homescreen.this, user_profile.class);
                startActivity(intent);
                break;
            case R.id.nav_login: menu.findItem(R.id.nav_logout).setVisible(true);
                menu.findItem(R.id.nav_profile).setVisible(true);
                menu.findItem(R.id.nav_login).setVisible(false);
                break;
            case R.id.nav_logout: menu.findItem(R.id.nav_logout).setVisible(false);
                menu.findItem(R.id.nav_profile).setVisible(false);
                menu.findItem(R.id.nav_login).setVisible(true);
                break;
            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show(); break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}