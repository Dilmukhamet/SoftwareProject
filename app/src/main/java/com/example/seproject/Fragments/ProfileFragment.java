package com.example.seproject.Fragments;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.seproject.Activities.BookUpload;
import com.example.seproject.Activities.MainActivity;
import com.example.seproject.R;
import com.example.seproject.Users.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button btnLogout, btnEdit, btnUpload;
    private EditText name, surname, address, telephone;
    private String namef, surnamef, addressf ,telephonef, userID;
    private int phone;
    private ImageView userImage;
    private static int RESULT_LOAD_IMAGE = 1;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        btnLogout = view.findViewById(R.id.logout);
        btnUpload = view.findViewById(R.id.bookUploadbtn);
        name = view.findViewById(R.id.editName);
        surname = view.findViewById(R.id.editSurname);
        telephone = view.findViewById(R.id.editTelephone);
        address = view.findViewById(R.id.editAddress);
        btnEdit = view.findViewById(R.id.Edit);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        userImage = view.findViewById(R.id.imageUser);
        userImage.setImageResource(R.mipmap.person);
        mAuthListener =  new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){

                }else{

                }
            }
        };
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
               // String value = dataSnapshot.getValue(String.class);
               // Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
                Toast.makeText(getContext(), "Picture selected", Toast.LENGTH_LONG);
            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getContext(), BookUpload.class));
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namef = name.getText().toString();
                surnamef = surname.getText().toString();
                addressf = address.getText().toString();
                telephonef = telephone.getText().toString();
                if(!namef.equals("") && !surnamef.equals("") && !addressf.equals("") && !telephonef.equals("")){
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userID  = user.getUid();
                    User userinfo = new User(namef, surnamef, addressf, telephonef);
                    myRef.child("Users").child(userID).setValue(userinfo);

                    Toast.makeText(getContext(), "Data saved", Toast.LENGTH_SHORT).show();
                   /* myRef.child(userID).child("Name").setValue(namef);
                    myRef.child(userID).child("Surname").setValue(surnamef);
                    myRef.child(userID).child("Telephone").setValue(telephonef);
                    myRef.child(userID).child("Address").setValue(addressf);*/
                }else{
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT);
                }

            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            userImage.setImageURI(selectedImage);

        }
    }

    public void logOut(){
        mAuth.signOut();
        Toast.makeText(getContext(), "Signing Out...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }
}
