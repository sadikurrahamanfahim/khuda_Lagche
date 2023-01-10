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

import de.hdodenhof.circleimageview.CircleImageView;

public class Pizzaactivity extends AppCompatActivity {

    BottomNavigationView navigator;
    CircleImageView dp;
    TextView firstitem,seconditem,thirditem,fourthitem,fifthitem,sixthitem;
    ImageView firstimg,secondimg,thirdimg,fourthimg,fifthimg,sixthimg;
    StorageReference dp1,storageReference,storageReference1,storageReference2,storageReference3,storageReference4,storageReference5;
    static String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizzaactivity);
        getSupportActionBar().hide();

        firstitem=findViewById(R.id.firtsitem);
        seconditem=findViewById(R.id.seconditem);
        thirditem=findViewById(R.id.thirditem);
        fourthitem=findViewById(R.id.fourthitem);
        fifthitem=findViewById(R.id.fifthitem);
        sixthitem=findViewById(R.id.sixthitem);
        firstimg=findViewById(R.id.firstimg);
        secondimg=findViewById(R.id.secondimg);
        thirdimg=findViewById(R.id.thirdimg);
        fourthimg=findViewById(R.id.fourthimg);
        fifthimg=findViewById(R.id.fifthimg);
        sixthimg=findViewById(R.id.sixthimg);
        dp=findViewById(R.id.dp);


        navigator=findViewById(R.id.bottomNavigationView);

        navigator.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:
                        Intent intent=new Intent(getApplicationContext(), Homepage2.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.profile:
                        Intent intent3=new Intent(getApplicationContext(),Profile.class);
                        startActivity(intent3);
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


        dp1= FirebaseStorage.getInstance().getReference().child("uplod/tumpadp.jpg");
        try {
            final File localFile=File.createTempFile("tumpa","jpg");
            dp1.getFile(localFile)
                    .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                            Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            dp.setImageBitmap(bitmap);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Pizzaactivity.this,"Error in Fetching",Toast.LENGTH_SHORT).show();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (HomePage1.value.equals("drinks"))
        {

            firstitem.setText("CocaCola");
            storageReference= FirebaseStorage.getInstance().getReference().child("images/cocacola.jpg");
            try {
                final File localFile=File.createTempFile("cocacola","jpeg");
                storageReference.getFile(localFile)
                        .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                                Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                firstimg.setImageBitmap(bitmap);

                                firstimg.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        item="cocacola";
                                        Intent intent=new Intent(Pizzaactivity.this,Itempage.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                });

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Pizzaactivity.this,"Error in Fetching",Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }

            seconditem.setText("Pepsi");
            storageReference1= FirebaseStorage.getInstance().getReference().child("images/pepsi.jpg");
            try {
                final File localFile=File.createTempFile("pepsi","jpg");
                storageReference1.getFile(localFile)
                        .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                                Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                secondimg.setImageBitmap(bitmap);
                                secondimg.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        setContentView(R.layout.activity_itempage);
                                    }
                                });

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Pizzaactivity.this,"Error in Fetching",Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }

            thirditem.setText("7Up");
            storageReference2= FirebaseStorage.getInstance().getReference().child("images/7up.jpg");
            try {
                final File localFile=File.createTempFile("7up","jpg");
                storageReference2.getFile(localFile)
                        .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                                Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                thirdimg.setImageBitmap(bitmap);
                                thirdimg.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                    }
                                });

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Pizzaactivity.this,"Error in Fetching",Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }


            fourthitem.setText("RC cola");
            storageReference3= FirebaseStorage.getInstance().getReference().child("images/rccola.jpeg");
            try {
                final File localFile=File.createTempFile("rccola","jpeg");
                storageReference3.getFile(localFile)
                        .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                                Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                fourthimg.setImageBitmap(bitmap);
                                fourthimg.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                    }
                                });

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Pizzaactivity.this,"Error in Fetching",Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }


            fifthitem.setText("MoJo");
            storageReference4= FirebaseStorage.getInstance().getReference().child("images/mojo.jpg");
            try {
                final File localFile=File.createTempFile("mojo","jpg");
                storageReference4.getFile(localFile)
                        .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                                Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                fifthimg.setImageBitmap(bitmap);
                                fifthimg.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                    }
                                });

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Pizzaactivity.this,"Error in Fetching",Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }


            sixthitem.setText("Clemon");
            storageReference5= FirebaseStorage.getInstance().getReference().child("images/clemon.jpg");
            try {
                final File localFile=File.createTempFile("clemon","jpg");
                storageReference5.getFile(localFile)
                        .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                                Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                sixthimg.setImageBitmap(bitmap);
                                sixthimg.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                    }
                                });

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Pizzaactivity.this,"Error in Fetching",Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }


        } else if (HomePage1.value.equals("pizza"))
        {
            firstitem.setText("Chicken Pizza");
            storageReference= FirebaseStorage.getInstance().getReference().child("images/chickenpizza.png");
            try {
                final File localFile=File.createTempFile("chickenpizza","jpg");
                storageReference.getFile(localFile)
                        .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                                Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                firstimg.setImageBitmap(bitmap);
                                firstimg.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        item="chickenpizza";
                                        Intent intent=new Intent(Pizzaactivity.this,Itempage.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                });

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Pizzaactivity.this,"Error in Fetching",Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }

            seconditem.setText("Italian Pizza");
            storageReference1= FirebaseStorage.getInstance().getReference().child("images/italianpizza.png");
            try {
                final File localFile=File.createTempFile("italianpizza","jpg");
                storageReference1.getFile(localFile)
                        .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                                Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                secondimg.setImageBitmap(bitmap);
                                secondimg.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        item="italianpizza";
                                        Intent intent=new Intent(Pizzaactivity.this,Itempage.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                });

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Pizzaactivity.this,"Error in Fetching",Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }

            thirditem.setText("BBQ Beef Pizza");
            storageReference2= FirebaseStorage.getInstance().getReference().child("images/bbqbeefpizza.png");
            try {
                final File localFile=File.createTempFile("bbqbeefpizza","jpg");
                storageReference2.getFile(localFile)
                        .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                                Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                thirdimg.setImageBitmap(bitmap);
                                thirdimg.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                    }
                                });

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Pizzaactivity.this,"Error in Fetching",Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }


            fourthitem.setText("Mixed Pizza");
            storageReference3= FirebaseStorage.getInstance().getReference().child("images/mixedpizza.png");
            try {
                final File localFile=File.createTempFile("mixedpizza","jpg");
                storageReference3.getFile(localFile)
                        .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                                Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                fourthimg.setImageBitmap(bitmap);
                                fourthimg.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                    }
                                });

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Pizzaactivity.this,"Error in Fetching",Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }


            fifthitem.setText("Pepperoni Pizza");
            storageReference4= FirebaseStorage.getInstance().getReference().child("images/pepperoni.png");
            try {
                final File localFile=File.createTempFile("pepperonipizza","jpg");
                storageReference4.getFile(localFile)
                        .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                                Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                fifthimg.setImageBitmap(bitmap);
                                fifthimg.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                    }
                                });

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Pizzaactivity.this,"Error in Fetching",Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }


            sixthitem.setText("Cheesy Pizza");
            storageReference5= FirebaseStorage.getInstance().getReference().child("images/chessypizza.png");
            try {
                final File localFile=File.createTempFile("cheesepizza","jpg");
                storageReference5.getFile(localFile)
                        .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                                Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                sixthimg.setImageBitmap(bitmap);
                                sixthimg.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                    }
                                });

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Pizzaactivity.this,"Error in Fetching",Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (HomePage1.value.equals("burger"))
        {
            firstitem.setText("Chicken Burger");
            storageReference= FirebaseStorage.getInstance().getReference().child("images/chkbgr.jpg");
            try {
                final File localFile=File.createTempFile("cocacola","jpeg");
                storageReference.getFile(localFile)
                        .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                                Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                firstimg.setImageBitmap(bitmap);

                                firstimg.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        item="chickenburger";
                                        Intent intent=new Intent(Pizzaactivity.this,Itempage.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                });

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Pizzaactivity.this,"Error in Fetching",Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }

            seconditem.setText("Regular Burger");
            storageReference1= FirebaseStorage.getInstance().getReference().child("images/rglbgr.jpg");
            try {
                final File localFile=File.createTempFile("pepsi","jpg");
                storageReference1.getFile(localFile)
                        .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                                Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                secondimg.setImageBitmap(bitmap);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Pizzaactivity.this,"Error in Fetching",Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }

            thirditem.setText("Beef Burger");
            storageReference2= FirebaseStorage.getInstance().getReference().child("images/beefbgr.png");
            try {
                final File localFile=File.createTempFile("7up","jpg");
                storageReference2.getFile(localFile)
                        .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                                Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                thirdimg.setImageBitmap(bitmap);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Pizzaactivity.this,"Error in Fetching",Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }


            fourthitem.setText("Mixed Burger");
            storageReference3= FirebaseStorage.getInstance().getReference().child("images/mxdbgr.jpg");
            try {
                final File localFile=File.createTempFile("rccola","jpeg");
                storageReference3.getFile(localFile)
                        .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                                Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                fourthimg.setImageBitmap(bitmap);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Pizzaactivity.this,"Error in Fetching",Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }


            fifthitem.setText("Dosa");
            storageReference4= FirebaseStorage.getInstance().getReference().child("images/dosabgr.jpg");
            try {
                final File localFile=File.createTempFile("mojo","jpg");
                storageReference4.getFile(localFile)
                        .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                                Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                fifthimg.setImageBitmap(bitmap);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Pizzaactivity.this,"Error in Fetching",Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }


            sixthitem.setText("Cheese Burger");
            storageReference5= FirebaseStorage.getInstance().getReference().child("images/chsbgr.jpg");
            try {
                final File localFile=File.createTempFile("clemon","jpg");
                storageReference5.getFile(localFile)
                        .addOnCompleteListener(new OnCompleteListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<FileDownloadTask.TaskSnapshot> task) {
                                Bitmap bitmap= BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                sixthimg.setImageBitmap(bitmap);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Pizzaactivity.this,"Error in Fetching",Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}