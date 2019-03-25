package com.example.uberfood.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    ImageView arrowBack ;
    AppCompatEditText username  , mail , password , confirmPassword , phoneNumber , location , postalCode;
    LinearLayout buttonSignup ;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    DatabaseReference reference ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initializeViews();
        inscriptionProcess();
    }

    private void inscriptionProcess() {

        if ((username.getText() + "").equalsIgnoreCase("")
                || (mail.getText() + "").equalsIgnoreCase("") || ((phoneNumber.getText() + "").equalsIgnoreCase(""))
                || ((location.getText() + "").equalsIgnoreCase("")) || ((password.getText() + "").equalsIgnoreCase(""))
                || ((confirmPassword.getText() + "").equalsIgnoreCase(""))) {

            new CustomToast(SignUpActivity.this, getResources().getString(R.string.error), getResources().getString(R.string.verify_info_login), R.drawable.ic_erreur, CustomToast.ERROR).show();
            return;

        } else if (!((mail.getText() + "").matches("^[A-Za-z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9]{2,6}$"))) {
            new CustomToast(SignUpActivity.this, getResources().getString(R.string.error), getResources().getString(R.string.verify_form_email), R.drawable.ic_erreur, CustomToast.ERROR).show();
            return;


        } else if (!((phoneNumber.getText() + "").matches("\\d{8}"))) {
            new CustomToast(SignUpActivity.this, getResources().getString(R.string.error), getResources().getString(R.string.verify_phone_number), R.drawable.ic_erreur, CustomToast.ERROR).show();
            return;

        }
        else if (!((postalCode.getText() + "").matches("\\d{4}")) ||
                ((Integer.parseInt(postalCode.getText() + "")) > 9999) ||
                ((Integer.parseInt(postalCode.getText() + "")) < 1000)) {
            new CustomToast(SignUpActivity.this, getResources().getString(R.string.error), getResources().getString(R.string.verify_postal_code), R.drawable.ic_erreur, CustomToast.ERROR).show();
            return;


        }
        else if (!(password.getText() + "").matches("[\\W\\w]{6,}")) {
            new CustomToast(SignUpActivity.this, getResources().getString(R.string.error), getResources().getString(R.string.verify_length_password), R.drawable.ic_erreur, CustomToast.ERROR).show();


        } else if (!((password.getText() + "").matches(confirmPassword.getText() + ""))) {
            new CustomToast(SignUpActivity.this, getResources().getString(R.string.error), getResources().getString(R.string.verify_confirmed_password), R.drawable.ic_erreur, CustomToast.ERROR).show();
            return;

        }  else {
            if (! (ConnectivityService.isOnline(SignUpActivity.this))) {

                new CustomToast(SignUpActivity.this, getResources().getString(R.string.warning), getResources().getString(R.string.verify_internet), R.drawable.warning_icon, CustomToast.WARNING).show();


            } else {
                
                createUserIntoFirebasePlatform();

            }
            }

            }

    private void createUserIntoFirebasePlatform() {

        auth.createUserWithEmailAndPassword(mail.getText()+"", password.getText()+"").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    boolean isNew = task.getResult().getAdditionalUserInfo().isNewUser();
                    if (isNew){

                        FirebaseUser firebaseUser = auth.getCurrentUser();
                        String userId = firebaseUser.getUid();
                        reference = FirebaseDatabase.getInstance().getReference("Users").child(userId);
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("id", userId);
                        hashMap.put("username", username.getText()+"");
                        hashMap.put("location", location.getText()+"");
                        hashMap.put("postal_code", postalCode.getText()+"");
                        hashMap.put("phone_number", phoneNumber.getText()+"");


                        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    // get the token for the first connexion , here i will use a webservice to send token to DB

                                            /*Intent intent = new Intent(ConnexionActivity.this, HomeActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            finish();*/
                                    Log.e("successful connexion to" , "firebase");

                                }
                            }
                        });


                    }
                }

            }
        });
    }

    private void initializeViews() {


        username = findViewById(R.id.et_username_sign_up);

        mail = findViewById(R.id.et_email_sign_up);
        password = findViewById(R.id.et_password_sign_up);
        confirmPassword = findViewById(R.id.et_confirm_password_sign_up);
        phoneNumber = findViewById(R.id.et_phone_number);
        location = findViewById(R.id.et_location);
        postalCode = findViewById(R.id.et_postal_code);

        arrowBack = findViewById(R.id.arrow_back_from_signup);
        buttonSignup = findViewById(R.id.button_signup);

        arrowBack.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(SignUpActivity.this , LoginActivity.class);
            startActivity(intent);
            finish();
        }
    });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(SignUpActivity.this , LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
