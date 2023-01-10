package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class Itempage extends AppCompatActivity {
    BottomNavigationView navigator;
    TextView plus,minus,txt,prc,text;
    int i=1,price=1,pr=1;
    ShapeableImageView itempic;
    StorageReference storageReference;
    Button buy,addtocart;
    static String name;
    static String pize;
    static String itemname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itempage);
        getSupportActionBar().hide();

        navigator=findViewById(R.id.bottomNavigationView);
        plus=findViewById(R.id.plus);
        minus=findViewById(R.id.minus);
        txt=findViewById(R.id.txt);
        prc=findViewById(R.id.price);
        itempic=findViewById(R.id.itempic);
        text=findViewById(R.id.text);
        buy=findViewById(R.id.buynow);
        addtocart=findViewById(R.id.addtocart);

        if (Pizzaactivity.item.equals("chickenpizza"))
        {
            text.setText("Chicken Pizza");
            prc.setText("320");
            storageReference= FirebaseStorage.getInstance().getReference().child("images/chickenpizza.png");
            try {
                final File localFile=File.createTempFile("chickenpizza","jpg");
                storageReference.getFile(localFile)
                        .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                                Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                itempic.setImageBitmap(bitmap);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Itempage.this,"Error in Fetching",Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (Pizzaactivity.item.equals("italianpizza"))
        {
            text.setText("Italian Pizza");
            prc.setText("360");
            storageReference= FirebaseStorage.getInstance().getReference().child("images/italianpizza.png");
            try {
                final File localFile=File.createTempFile("chickenpizza","jpg");
                storageReference.getFile(localFile)
                        .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                                Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                itempic.setImageBitmap(bitmap);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Itempage.this,"Error in Fetching",Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (Pizzaactivity.item.equals("cocacola"))
        {
            text.setText("Cocacola");
            prc.setText("45");
            storageReference= FirebaseStorage.getInstance().getReference().child("images/cocacola.jpg");
            try {
                final File localFile=File.createTempFile("chickenpizza","jpg");
                storageReference.getFile(localFile)
                        .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                                Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                itempic.setImageBitmap(bitmap);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Itempage.this,"Error in Fetching",Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (Pizzaactivity.item.equals("chickenburger"))
        {
            text.setText("Chicken Burger");
            prc.setText("162");
            storageReference= FirebaseStorage.getInstance().getReference().child("images/chkbgr.jpg");
            try {
                final File localFile=File.createTempFile("chickenpizza","jpg");
                storageReference.getFile(localFile)
                        .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                                Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                itempic.setImageBitmap(bitmap);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Itempage.this,"Error in Fetching",Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"purchase done",Toast.LENGTH_SHORT).show();
            }
        });
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemname=text.getText().toString();
                name= text.getText().toString();
                pize= prc.getText().toString();
                Intent intent=new Intent(Itempage.this,Cart.class);
                startActivity(intent);
                finish();
            }
        });


        pr=Integer.valueOf(prc.getText().toString());

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                price=i*pr;
                prc.setText(String.valueOf(price));
                txt.setText(String.valueOf(i));

            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i<2){
                    Toast.makeText(Itempage.this,"Choose valid number",Toast.LENGTH_SHORT).show();
                }else{
                    i--;
                    price=i*pr;
                    prc.setText(String.valueOf(price));
                    txt.setText(String.valueOf(i));
                }
            }
        });




        navigator.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:
                        Intent intent=new Intent(Itempage.this, Homepage2.class);
                        startActivity(intent);
                        finish();

                    case R.id.profile:
                        Intent intent1=new Intent(Itempage.this,Profile.class);
                        startActivity(intent1);
                        finish();

                    case R.id.chat:
                        Intent intent4=new Intent(Itempage.this,Chat.class);
                        startActivity(intent4);
                        finish();

                    case R.id.cart:
                        Intent intent3=new Intent(Itempage.this,Cart.class);
                        startActivity(intent3);
                        finish();
                }
                return true;
            }
        });


    }
}