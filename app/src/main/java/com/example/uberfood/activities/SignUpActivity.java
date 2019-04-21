package com.example.uberfood.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
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
import com.kbeanie.multipicker.api.ImagePicker;
import com.kbeanie.multipicker.api.callbacks.ImagePickerCallback;
import com.kbeanie.multipicker.api.entity.ChosenImage;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.example.uberfood.utils.Constants.PERMISSIONS_PHOTO;
import static com.example.uberfood.utils.Constants.USER_COLLECTION;
import static com.example.uberfood.utils.Utils.exists;
import static com.example.uberfood.utils.Utils.hasPermissions;
import static com.example.uberfood.utils.Utils.isNew;

public class SignUpActivity extends AppCompatActivity {

    ImageView arrowBack ;
    AppCompatEditText firstname  , mail , password , confirmPassword , phoneNumber , lastname;
    LinearLayout buttonSignup ;
    FirebaseAuth auth ;
    DatabaseReference reference ;
    String userId ;
    private Uri croppedImage;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static final String TAG = "SignUpActivity";
    ProgressDialog progressDialog ;
    LinearLayout layoutImage ;
    CircularImageView image ;
    ImagePicker imagePicker ;

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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
            croppedImage = resultUri;
            updateImage();
        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
            Log.e("ucrop result " , cropError.toString());
        }

    }


    private void updateImage() {


            /*try {
                imgProfile = new ImageGalerie(0, croppedImage.getPath(), croppedImage.getPath(), true);
                ivProfilPhoto.setImageUriWithoutProgress(imgProfile.getImage(), this);
                ivPhotoProfilIcon.setImageResource(R.drawable.tick_green);
                lblProfile.setTextColor(getResources().getColor(R.color.black));
                try {
                    business.setImage(imgProfile.getImage());
                    businessReady.setImage(imgProfile.getImage());

                }catch (NullPointerException e)
                {

                }

            } catch (NullPointerException e) {
                System.out.println("null");
            }
       */
        Glide.with(getBaseContext())
                .load(croppedImage.getPath())
                .into(image);




    }


    private void inscriptionProcess() {

        if ((firstname.getText() + "").equalsIgnoreCase("")
                || (mail.getText() + "").equalsIgnoreCase("") || ((phoneNumber.getText() + "").equalsIgnoreCase(""))
               || ((password.getText() + "").equalsIgnoreCase(""))
                || ((confirmPassword.getText() + "").equalsIgnoreCase("")) || (lastname.getText().toString().equalsIgnoreCase(""))) {

            new CustomToast(SignUpActivity.this, getResources().getString(R.string.error), getResources().getString(R.string.verify_info_login), R.drawable.ic_erreur, CustomToast.ERROR).show();
            return;

        } else if (!((mail.getText() + "").matches("^[A-Za-z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9]{2,6}$"))) {
            new CustomToast(SignUpActivity.this, getResources().getString(R.string.error), getResources().getString(R.string.verify_form_email), R.drawable.ic_erreur, CustomToast.ERROR).show();
            return;


        } else if (!((phoneNumber.getText() + "").matches("\\d{8}"))) {
            new CustomToast(SignUpActivity.this, getResources().getString(R.string.error), getResources().getString(R.string.verify_phone_number), R.drawable.ic_erreur, CustomToast.ERROR).show();
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
                        userObject.put("name", firstname.getText() + "");
                        userObject.put("last_Name", lastname.getText() + "");
                        userObject.put("phone_Number", phoneNumber.getText() + "");


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


        firstname = findViewById(R.id.et_first_name_sign_up);

        mail = findViewById(R.id.et_email_sign_up);
        password = findViewById(R.id.et_password_sign_up);
        confirmPassword = findViewById(R.id.et_confirm_password_sign_up);
        phoneNumber = findViewById(R.id.et_phone_number);
        lastname = findViewById(R.id.et_last_name_sign_up);
        arrowBack = findViewById(R.id.arrow_back_from_signup);
        buttonSignup = findViewById(R.id.button_signup);
        progressDialog = new ProgressDialog(SignUpActivity.this , R.style.MyAlertDialogStyle);
        progressDialog.setMessage("Storing data ...");
        layoutImage = findViewById(R.id.ll_profil_pic);
        image = findViewById(R.id.profil_image_from_sign_up);


        arrowBack.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(SignUpActivity.this , LoginActivity.class);
            startActivity(intent);
            finish();
        }
    });


        layoutImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hasPermissions(SignUpActivity.this, Constants.MY_PERMISSIONS_REQUEST_STORAGE, PERMISSIONS_PHOTO)) {
                    uploadImageDialog();
                }
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


    private void uploadImageDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                SignUpActivity.this, R.style.AlertDialogCustom);

        builder.setTitle("Ajouter une photo");
        builder.setMessage("Prenez une photo ou bien sélectionnez depuis vos photos");
        builder.setPositiveButton("GALLERIE", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                imageFromGallerie();
                dialog.dismiss();

            }
        });

        builder.setNegativeButton("CAMERA", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //imageFromCamera();
                dialog.dismiss();

            }
        });


        AlertDialog alert = builder.create();
        alert.show();
        alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorOrange));
        alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorOrange));
        alert.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {

            }
        });
    }




    private void imageFromGallerie() {
        imagePicker = new ImagePicker(this);
        imagePicker.shouldGenerateMetadata(true);
        imagePicker.shouldGenerateThumbnails(true);
        imagePicker.setImagePickerCallback(new ImagePickerCallback() {
            @Override
            public void onImagesChosen(List<ChosenImage> list) {

                if ( list != null){
                    UCrop.Options options = new UCrop.Options();
                    options.setToolbarColor(ContextCompat.getColor(SignUpActivity.this , R.color.colorOrange));
                    options.setStatusBarColor(ContextCompat.getColor(SignUpActivity.this , R.color.colorOrange));
                    options.setActiveWidgetColor(ContextCompat.getColor(SignUpActivity.this , R.color.colorOrange));
                    options.setDimmedLayerColor(ContextCompat.getColor(SignUpActivity.this , R.color.colorWhite));

                    UCrop.of(Uri.fromFile(new File((list.get(0).getOriginalPath()))),
                            Uri.fromFile(new File((list.get(0).getOriginalPath()))))
                            .withOptions(options)
                            .withMaxResultSize(1000, 1000)
                            .start(SignUpActivity.this);


                }


            }

            @Override
            public void onError(String s) {

                Log.e("imagepicker callback " , s + " !");

            }
        });
        imagePicker.allowMultiple();
        imagePicker.pickImage();
    }


}
