package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class IntroActivity2 extends AppCompatActivity {
    TextView skip, button,arrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro2);
        getSupportActionBar().hide();

        arrow=findViewById(R.id.introarrow);
        skip=findViewById(R.id.skip);
        button=findViewById(R.id.nextbtn);

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IntroActivity2.this, HomePage1.class);
                startActivity(intent);
                finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IntroActivity2.this, HomePage1.class);
                startActivity(intent);
                finish();
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IntroActivity2.this, HomePage1.class);
                startActivity(intent);
                finish();
            }
        });
    }
}