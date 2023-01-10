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
import android.widget.TextView;
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

public class Cart extends AppCompatActivity {
    BottomNavigationView navigator;
    TextView plus,minus,txt,plus1,minus1,txt1,order,firstitem,firstprice;
    int i=1,j=1;
    StorageReference storageReference;
    ImageView itempic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportActionBar().hide();

        navigator=findViewById(R.id.bottomNavigationView);

        plus=findViewById(R.id.plus);
        minus=findViewById(R.id.minus);
        txt=findViewById(R.id.txt);
        plus1=findViewById(R.id.plus1);
        minus1=findViewById(R.id.minus1);
        txt1=findViewById(R.id.txt1);
        order=findViewById(R.id.order);
        firstitem=findViewById(R.id.firstitm);
        firstprice=findViewById(R.id.firstprice);
        itempic=findViewById(R.id.firstimg);

        if (Itempage.itemname.equals("Italian Pizza"))
        {
            firstitem.setText(Itempage.name);
            firstprice.setText(Itempage.pize);
        }else if (Itempage.itemname.equals("Chicken Burger"))
        {
            firstitem.setText(Itempage.name);
            firstprice.setText(Itempage.pize);
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
                                Toast.makeText(Cart.this,"Error in Fetching",Toast.LENGTH_SHORT).show();
                            }
                        });
        } catch (IOException e) {
                e.printStackTrace();
            }

            plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                txt.setText(String.valueOf(i));
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i<2){
                    Toast.makeText(Cart.this,"Choose valid number",Toast.LENGTH_SHORT).show();
                }else{
                    i--;
                    txt.setText(String.valueOf(i));
                }
            }
        });

        plus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                j++;
                txt1.setText(String.valueOf(j));
            }
        });

        minus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (j<2){
                    Toast.makeText(Cart.this,"Choose valid number",Toast.LENGTH_SHORT).show();
                }else{
                    j--;
                    txt1.setText(String.valueOf(j));
                }
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Cart.this,"Order Complete",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Cart.this, HomePage1.class);
            }
        });


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

                    case R.id.home:
                        Intent intent1=new Intent(getApplicationContext(), Homepage2.class);
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
    }
}
}