package com.example.uberfood.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.uberfood.R;
import com.example.uberfood.utils.ConnectivityService;
import com.example.uberfood.utils.CustomToast;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    ProgressDialog progressDialog ;
    LoginButton loginFacebookButon ;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent ( LoginActivity.this , FirstScreenActivity.class);
        startActivity(intent);
        finish();
    }

    ImageView arrowBack;
    LinearLayout signUpText ;
    CallbackManager  mCallbackManager = CallbackManager.Factory.create();;
    LinearLayout loginButton;
    private FirebaseAuth mAuth;
    AppCompatEditText mail , password ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeViews();
        connectToPlatfmWithFacebook();
    }


    private void handleFacebookAccessToken(AccessToken token) {
        Log.e( "handleFacebookAccessToken:" , token.getToken());
        progressDialog.show();

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e( "signInWithCredential:success","ok");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {


                            updateUI(null);
                        }

                        // ...
                    }
                });
    }



    private void updateUI(FirebaseUser currentUser) {



            startActivity(new Intent(LoginActivity.this , MainActivity.class));
            finish();



    }

    private void connectToPlatfmWithFacebook() {


        // *************%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%************* //


                    // https://developers.facebook.com/docs/facebook-login/android

                    // https://firebase.google.com/docs/auth/android/facebook-login?authuser=0


        // *************%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%************* //

        // étape a suivre : génération d'une clé de hachage de développement
        // i ' m working on https://developers.facebook.com/docs/facebook-login/android#


        // %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%




        loginFacebookButon.setReadPermissions("email", "public_profile");

        loginFacebookButon.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                progressDialog.dismiss();
                Log.e( "facebook:onSuccess:" + loginResult,"ok");
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                progressDialog.dismiss();
                Log.e( "facebook:onCancel","ok");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                progressDialog.dismiss();
                Log.e("facebook:onError", error.toString());
                // ...
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);


    }





    private void initializeViews() {




// ...
// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        arrowBack = findViewById(R.id.arrow_back_from_login);
        mail = findViewById(R.id.et_email_sign_in);
        loginFacebookButon = findViewById(R.id.login_facebook_button);
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


        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (LoginActivity.this , SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mCallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
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
