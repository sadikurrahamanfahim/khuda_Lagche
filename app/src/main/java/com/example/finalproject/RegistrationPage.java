package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegistrationPage extends AppCompatActivity {

    private Button signupbtn;
    CircleImageView dp;
    EditText email,username,password,rewritepassword;
    FirebaseAuth auth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Uri imageUri;
    FirebaseDatabase database;
    FirebaseStorage storage;
    String imageuri;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        getSupportActionBar().hide();

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("please wait");
        progressDialog.setCancelable(false);

        dp=findViewById(R.id.profileimage);
        signupbtn=findViewById(R.id.signup);
        email=findViewById(R.id.usermail);
        username=findViewById(R.id.userName);
        password=findViewById(R.id.passWord);
        rewritepassword=findViewById(R.id.rpassWord);


        TextView textView=findViewById(R.id.login);
        String text= "Already a user? Login ";

        SpannableString ss= new SpannableString(text);

        ClickableSpan clickableSpan=new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent=new Intent(RegistrationPage.this,LoginPage.class);
                startActivity(intent);
                finish();
            }
        };
        ss.setSpan(clickableSpan, 16, 21, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 10);
            }
        });


        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                String name= username.getText().toString();
                String pass= password.getText().toString();
                String rpass= rewritepassword.getText().toString();
                String mail= email.getText().toString();

                if (TextUtils.isEmpty(mail)||TextUtils.isEmpty(pass)||TextUtils.isEmpty(name)||TextUtils.isEmpty(rpass)){
                    progressDialog.dismiss();
                    Toast.makeText(RegistrationPage.this,"Enter Valid Data",Toast.LENGTH_SHORT).show();
                }
                else if (!mail.matches(emailPattern)){
                    progressDialog.dismiss();
                    email.setText("Enter Valid Email");
                    Toast.makeText(RegistrationPage.this,"InValid Email",Toast.LENGTH_SHORT).show();
                }
                else if (!pass.equals(rpass)|| pass.length()<6){
                    Toast.makeText(RegistrationPage.this,"Password Missmatch",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

                else{
                    auth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                DatabaseReference reference=database.getReference().child("user").child(auth.getUid());
                                StorageReference storageReference=storage.getReference().child("uplod").child(auth.getUid());

                                if (imageUri!=null)
                                {
                                    storageReference.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                            if (task.isSuccessful())
                                            {
                                                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                    @Override
                                                    public void onSuccess(Uri uri) {
                                                        imageuri=uri.toString();
                                                        Users users= new Users(auth.getUid(),name,mail,imageuri);
                                                        reference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful())
                                                                {
                                                                    progressDialog.dismiss();
                                                                    startActivity(new Intent(RegistrationPage.this, Homepage2.class));
                                                                }else
                                                                {
                                                                    progressDialog.dismiss();
                                                                    Toast.makeText(RegistrationPage.this,"Errror in creating user",Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });
                                                    }
                                                });
                                            }else
                                            {
                                                imageuri="https://firebasestorage.googleapis.com/v0/b/final-project-24be5.appspot.com/o/profile.png?alt=media&token=6739ff67-2dfa-4d8a-94da-3a7c5d9c43be";
                                                Users users= new Users(auth.getUid(),name,mail,imageuri);
                                                reference.setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful())
                                                        {
                                                            progressDialog.dismiss();
                                                            startActivity(new Intent(RegistrationPage.this, Homepage2.class));
                                                        }else
                                                        {
                                                            progressDialog.dismiss();
                                                            Toast.makeText(RegistrationPage.this,"Errror in creating user",Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });
                                            }
                                        }
                                    });
                                }
                                Toast.makeText(RegistrationPage.this,"Account created",Toast.LENGTH_SHORT).show();
                            }else{
                                progressDialog.dismiss();
                                Toast.makeText(RegistrationPage.this,"Account can't be created",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==10)
        {
            if (data!=null)
            {
                imageUri=data.getData();
                dp.setImageURI(imageUri);
            }
        }
    }
}