package com.example.seproject.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.seproject.R;
import com.example.seproject.Users.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Registration extends AppCompatActivity {
    private Button addUser;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private FirebaseDatabase mFirebaseDatabase;
    private EditText emailF, passwordF, nameF, surnameF, telephoneF, addressF;
    private String email, password, name, surname, telephone, address;
    private String userID;

    public void getToolbar(){

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.customBar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.mipmap.logo3);
    }
    public void setComponents(){
        addUser = findViewById(R.id.CreateUser);
        emailF = findViewById(R.id.emailField);
        passwordF = findViewById(R.id.passwordField);
        mAuth = FirebaseAuth.getInstance();
        nameF = findViewById(R.id.nameField);
        surnameF = findViewById(R.id.SurNameField);
        telephoneF = findViewById(R.id.telephoneField);
        addressF = findViewById(R.id.addressField);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getToolbar();
        setComponents();
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){

                }else{

                }
            }
        };
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
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = emailF.getText().toString();
                password = passwordF.getText().toString();
                name = nameF.getText().toString();
                surname = surnameF.getText().toString();
                telephone = telephoneF.getText().toString();
                address = addressF.getText().toString();
                if(!email.equals("")&&!password.equals("") && !name.equals("") && !surname.equals("") && !telephone.equals("") && !address.equals("")  ){
                    registration(email, password);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    /*signIn(email, password);
                    FirebaseUser user = mAuth.getCurrentUser();
                    userID = user.getUid();
                    User newuser = new User(name, surname, telephone, address);
                    myRef.child("Users").child(userID).setValue(newuser);
                    //*/
                }
                else{
                    Toast.makeText(getApplicationContext(), "Not all fields entered" + email, Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    public void registration(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "User created", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Not all fields entered", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void signIn(final String email1, String password1){
        mAuth.signInWithEmailAndPassword(email1, password1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String a = email1;
                    Toast.makeText(getApplicationContext(), "Signed in successful as:" + a, Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(getApplicationContext(), BooksActivity.class);
                    startActivity(intent1);
                    emailF.setText("");
                    nameF.setText("");
                    surnameF.setText("");
                    telephoneF.setText("");
                    addressF.setText("");
                    passwordF.setText("");
                }else{
                    Toast.makeText(getApplicationContext(), "Signing in failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
