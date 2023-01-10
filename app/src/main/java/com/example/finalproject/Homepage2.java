package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class Homepage2 extends AppCompatActivity {
    BottomNavigationView navigator;
    LinearLayout piz,drinks,burger;
    CircleImageView dp;
    StorageReference storageReference;
    ImageView secondimg,thirdimg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        getSupportActionBar().hide();

        navigator=findViewById(R.id.bottomNavigationView);
        piz=findViewById(R.id.pizza);
        drinks=findViewById(R.id.drinks);
        burger=findViewById(R.id.burger);
        secondimg=findViewById(R.id.secondimg);
        thirdimg=findViewById(R.id.thirdimg);
        dp=findViewById(R.id.dp);

        storageReference= FirebaseStorage.getInstance().getReference().child("uplod/tumpadp.jpg");
        try {
            final File localFile=File.createTempFile("tumpa","jpg");
            storageReference.getFile(localFile)
                    .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                            Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            dp.setImageBitmap(bitmap);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Homepage2.this,"Error in Fetching",Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

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
                    case R.id.chat:
                        Intent intent2=new Intent(getApplicationContext(),Chat.class);
                        startActivity(intent2);
                        finish();
                        break;
                }
                return true;
            }
        });



        piz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomePage1.value="pizza";
                Intent intent=new Intent(Homepage2.this,Pizzaactivity.class);
                startActivity(intent);
                finish();
            }
        });

        drinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomePage1.value="drinks";
                Intent intent=new Intent(Homepage2.this,Pizzaactivity.class);
                startActivity(intent);
                finish();
            }
        });

        burger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomePage1.value="burger";
                Intent intent=new Intent(Homepage2.this,Pizzaactivity.class);
                startActivity(intent);
                finish();
            }
        });

        secondimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pizzaactivity.item="italianpizza";
                Intent intent=new Intent(Homepage2.this,Itempage.class);
                startActivity(intent);
                finish();
            }
        });
        thirdimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pizzaactivity.item="chickenburger";
                Intent intent=new Intent(Homepage2.this,Itempage.class);
                startActivity(intent);
                finish();
            }
        });

    }
}