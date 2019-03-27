package com.example.uberfood.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.uberfood.R;
import com.example.uberfood.models.User;
import com.example.uberfood.utils.ConnectivityService;
import com.example.uberfood.utils.Constants;
import com.example.uberfood.utils.CustomToast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.example.uberfood.utils.Constants.USER_COLLECTION;
import static com.example.uberfood.utils.Utils.exists;
import static com.example.uberfood.utils.Utils.isNew;

public class SignUpActivity extends AppCompatActivity {

    ImageView arrowBack ;
    AppCompatEditText username  , mail , password , confirmPassword , phoneNumber , location , postalCode;
    LinearLayout buttonSignup ;
    FirebaseAuth auth ;
    DatabaseReference reference ;
    String userId ;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static final String TAG = "SignUpActivity";
    ProgressDialog progressDialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initializeViews();
        Signup();
    }

    private void Signup() {

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG , "signup clicked");
                inscriptionProcess();
            }
        });
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



                    // do that if it 's not saved
                    createUserIntoFirebasePlatform();


                


            }
            }

            }

    private void createUserIntoFirebasePlatform() {


                progressDialog.show();

        auth = FirebaseAuth.getInstance() ;
        String mailString =mail.getText()+"";
        String passwordString = password.getText()+"";

        auth.createUserWithEmailAndPassword(mailString, passwordString).addOnCompleteListener( this ,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    boolean isNew = task.getResult().getAdditionalUserInfo().isNewUser();
                    if (isNew) {

                        FirebaseUser firebaseUser = auth.getCurrentUser();
                        userId = firebaseUser.getUid();
                        reference = FirebaseDatabase.getInstance().getReference(USER_COLLECTION).child(userId);

                        Map<String, String> userObject = new HashMap<>();
                        userObject.put("id", userId);
                        userObject.put("username", username.getText() + "");
                        userObject.put("location", location.getText() + "");
                        userObject.put("postal_code", postalCode.getText() + "");
                        userObject.put("phone_number", phoneNumber.getText() + "");


                        db.collection(USER_COLLECTION)
                                .document(userId)
                                .set(userObject)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            progressDialog.dismiss();
                                            startActivity(new Intent(getBaseContext(), LoginActivity.class));
                                            finish();
                                        }  else {
                                            progressDialog.dismiss();
                                            new CustomToast(SignUpActivity.this, getResources().getString(R.string.error), getResources().getString(R.string.technical_error), R.drawable.ic_erreur, CustomToast.ERROR).show();


                                        }
                                    }
                                });
                    }

                } else {
                    progressDialog.dismiss();
                    Log.e(TAG , "error"+ task.getException()+"!");
                    new CustomToast(SignUpActivity.this, getResources().getString(R.string.error), getResources().getString(R.string.technical_error), R.drawable.ic_erreur, CustomToast.ERROR).show();

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
        progressDialog = new ProgressDialog(SignUpActivity.this , R.style.MyAlertDialogStyle);
        progressDialog.setMessage("Storing data ...");


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
