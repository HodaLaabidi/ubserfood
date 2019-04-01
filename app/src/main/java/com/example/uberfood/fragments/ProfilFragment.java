package com.example.uberfood.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import com.example.uberfood.R;
import com.example.uberfood.activities.FirstScreenActivity;
import com.example.uberfood.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.example.uberfood.utils.Constants.USER_COLLECTION;

public class ProfilFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FirebaseAuth auth ;
    private  String userId ;
    private User user ;
    AppCompatTextView usernameTextView  , emailProfil ;
    LinearLayout disconnectButton ;
    ImageView more ;


    public ProfilFragment() {
        // Required empty public constructor
    }

    public static ProfilFragment newInstance(String param1, String param2) {
        ProfilFragment fragment = new ProfilFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


       View rootView = inflater.inflate(R.layout.fragment_profil, container, false);
        usernameTextView = rootView.findViewById(R.id.username_profil);
        disconnectButton = rootView.findViewById(R.id.disconnect_button);
        more = rootView.findViewById(R.id.more);
        emailProfil = rootView.findViewById(R.id.email_profil);
        auth = FirebaseAuth.getInstance() ;
        final FirebaseUser firebaseUser = auth.getCurrentUser();
        userId = firebaseUser.getUid();
        CollectionReference Users = db.collection(USER_COLLECTION);

        DocumentReference docRef = db.collection(USER_COLLECTION).document(userId);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                        Log.e( "DocumentSnapshot data: " , documentSnapshot.getData().toString());
                         user =  documentSnapshot.toObject(User.class);
                          usernameTextView.setText(user.getUsername());
                          emailProfil.setText(firebaseUser.getEmail());
                          Log.e("user data " , user.toString());


            }});

        disconnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                auth.signOut();
                Intent intent = new Intent (getContext() , FirstScreenActivity.class) ;
                auth.signOut();
                startActivity(intent);
                getActivity().finish();



            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getActivity(), more);
                popup.getMenuInflater().inflate(R.menu.menu_profil, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        if (item.getItemId() == R.id.menu_profil_item_one){

                        } else if (item.getItemId() == R.id.menu_profil_item_two){

                        } else if (item.getItemId() == R.id.menu_profil_item_three) {

                        }

                        return true;
                    }
                });
                popup.show();
            }
        });


        return rootView ;
    }



}
