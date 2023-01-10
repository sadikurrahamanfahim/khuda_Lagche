package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Chat extends AppCompatActivity {
    BottomNavigationView navigator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        getSupportActionBar().hide();

        navigator=findViewById(R.id.bottomNavigationView);

        navigator.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.profile:
                        Intent intent=new Intent(getApplicationContext(),Profile.class);
                        startActivity(intent);
                        finish();
                        break;

                    case R.id.cart:
                        Intent intent1=new Intent(getApplicationContext(),Cart.class);
                        startActivity(intent1);
                        finish();
                        break;
                    case R.id.home:
                        Intent intent2=new Intent(getApplicationContext(), Homepage2.class);
                        startActivity(intent2);
                        finish();
                        break;
                }
                return true;
            }
        });
    }
}