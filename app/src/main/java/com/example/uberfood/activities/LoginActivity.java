package com.example.uberfood.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.uberfood.R;
import com.example.uberfood.utils.ConnectivityService;
import com.example.uberfood.utils.CustomToast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    ProgressDialog progressDialog ;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent ( LoginActivity.this , FirstScreenActivity.class);
        startActivity(intent);
        finish();
    }

    ImageView arrowBack;
    LinearLayout signUpText ;
    LinearLayout loginButton;
    AppCompatEditText mail , password ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeViews();
    }

    private void initializeViews() {






        arrowBack = findViewById(R.id.arrow_back_from_login);
        mail = findViewById(R.id.et_email_sign_in);
        password = findViewById(R.id.et_password_sign_in);
        signUpText = findViewById(R.id.sign_up);
        loginButton = findViewById(R.id.login);
        progressDialog = new ProgressDialog(LoginActivity.this , R.style.MyAlertDialogStyle);
        progressDialog.setMessage("Login ...");
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyFromFireStore();
            }
        });
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent ( LoginActivity.this , FirstScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });


        /*


         */

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (LoginActivity.this , SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void verifyFromFireStore() {


        if ((mail.getText()+"").equalsIgnoreCase("")){


            new CustomToast(LoginActivity.this, getResources().getString(R.string.error), getResources().getString(R.string.no_email), R.drawable.ic_erreur, CustomToast.ERROR).show();




        }else if ((password.getText()+"").equalsIgnoreCase("")) {




            new CustomToast(LoginActivity.this, getResources().getString(R.string.error), getResources().getString(R.string.no_password), R.drawable.ic_erreur, CustomToast.ERROR).show();




        } else {
            if (ConnectivityService.isOnline(getBaseContext())){

                connectToPlatform();


            } else {

                new CustomToast(LoginActivity.this, getResources().getString(R.string.error), getResources().getString(R.string.verify_internet), R.drawable.ic_erreur, CustomToast.ERROR).show();



            }


        }

    }

    // i can the two tests

    private void connectToPlatform() {

        progressDialog.show();

        auth.signInWithEmailAndPassword(mail.getText().toString() , password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(LoginActivity.this , MainActivity.class));
                    progressDialog.dismiss();
                    finish();
                } else {

                    progressDialog.dismiss();
                    new CustomToast(LoginActivity.this, getResources().getString(R.string.error), getResources().getString(R.string.login_error_message), R.drawable.ic_erreur, CustomToast.ERROR).show();


                }

            }

        });

    }
}
