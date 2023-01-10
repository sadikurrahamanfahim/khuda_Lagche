package com.example.finalproject;

import androidx.annotation.NonNull;
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {

    private EditText un,pd;
    private Button loginbtn;
    FirebaseAuth auth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        getSupportActionBar().hide();
        auth=FirebaseAuth.getInstance();

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("please wait");
        progressDialog.setCancelable(false);

        un = findViewById(R.id.email);
        pd = findViewById(R.id.passWord);
        loginbtn = findViewById(R.id.login);

        TextView textView=findViewById(R.id.signup);
        String text= "Not a user?SignUP";

        SpannableString ss= new SpannableString(text);

        ClickableSpan clickableSpan=new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent=new Intent(LoginPage.this,RegistrationPage.class);
                startActivity(intent);
                finish();
            }
        };
        ss.setSpan(clickableSpan, 11, 17, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());



        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                String email=un.getText().toString();
                String pass=pd.getText().toString();

                if(email.equals("admin") && pass.equals("123")){
                    Toast.makeText(LoginPage.this,"welcome Admin",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(LoginPage.this,Admin.class);
                    startActivity(intent);
                    finish();
                }else if (TextUtils.isEmpty(email)||TextUtils.isEmpty(pass)){
                    progressDialog.dismiss();
                    un.setError("Error");
                    pd.setError("Error");
                    Toast.makeText(LoginPage.this,"Enter Data",Toast.LENGTH_SHORT).show();
                }
                else if (!email.matches(emailPattern)){
                    progressDialog.dismiss();
                    un.setError("Error");
                    Toast.makeText(LoginPage.this,"Enter Valid Email",Toast.LENGTH_SHORT).show();
                }
                else {
                    auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressDialog.dismiss();
                                startActivity(new Intent(LoginPage.this, Homepage2.class));
                            }else{
                                progressDialog.dismiss();
                                Toast.makeText(LoginPage.this,"Login Error",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

            }
        });
    }

    public void google(View v)
    {
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://google.com"));
        startActivity(intent);
    }

    public void facebook(View v)
    {
        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com"));
        startActivity(intent);
    }

    public void twitter(View v)
    {
        Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://twitter.com"));
        startActivity(intent);
    }
    public void rem(View v)
    {
        Toast.makeText(LoginPage.this,"signup",Toast.LENGTH_LONG).show();
    }

}