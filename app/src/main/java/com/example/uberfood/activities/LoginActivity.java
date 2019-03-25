package com.example.uberfood.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.uberfood.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent ( LoginActivity.this , FirstScreenActivity.class);
        startActivity(intent);
        finish();
    }

    ImageView arrowBack;
    LinearLayout signUpText ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeViews();
    }

    private void initializeViews() {

        arrowBack = findViewById(R.id.arrow_back_from_login);
        signUpText = findViewById(R.id.sign_up);
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent ( LoginActivity.this , FirstScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (LoginActivity.this , SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
