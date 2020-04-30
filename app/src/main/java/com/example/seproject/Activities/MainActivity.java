package com.example.seproject.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.seproject.Books.Book;
import com.example.seproject.Books.GENRES;
import com.example.seproject.R;
import com.example.seproject.Users.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {
private Button login;
private Button logup;
private EditText editUsername;
private EditText editPassword;
private String email;
private String password;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressBar progressBar;
    private static Vector<Book> books = new Vector<>();
    private static Book[] booksList = {new Book( GENRES.PSYCHOLOGY, "ТОНКОЕ ИСКУССТВО ПОФИГИЗМА: ПАРАДОКСАЛЬНЫЙ СПОСОБ ЖИТЬ СЧАСТЛИВО", "Марк Мэнсон", 3700, "Современное общество пропагандирует культ успеха: будь умнее, богаче, продуктивнее — будь лучше всех. Соцсети изобилуют историями на тему, как какой-то малец придумал приложение и заработал кучу денег, статьями в духе «Тысяча и один способ быть счастливым», а фото во френдленте создают впечатление, что окружающие живут лучше и интереснее, чем мы"),
        new Book( GENRES.LITERATURE, "Волокамское шоссе", "Александр Бек", 3100, "info"), new Book( GENRES.PSYCHOLOGY, "Хачу и Буду", "Михатл Лобковский", 3700, "12"), new Book( GENRES.LITERATURE, "Шантарам", "Грегори Дэвид Робертс", 4000, "13")

    };
    public static Book[] getList(){
        return booksList;
    }
    public void addBooks(){
        books.add(new Book( GENRES.PSYCHOLOGY, "ТОНКОЕ ИСКУССТВО ПОФИГИЗМА: ПАРАДОКСАЛЬНЫЙ СПОСОБ ЖИТЬ СЧАСТЛИВО", "Марк Мэнсон", 3700, "Современное общество пропагандирует культ успеха: будь умнее, богаче, продуктивнее — будь лучше всех. Соцсети изобилуют историями на тему, как какой-то малец придумал приложение и заработал кучу денег, статьями в духе «Тысяча и один способ быть счастливым», а фото во френдленте создают впечатление, что окружающие живут лучше и интереснее, чем мы"));//, R.drawable.tonkoipophigism);
        books.add( new Book( GENRES.LITERATURE, "Волокамское шоссе", "Александр Бек", 3100, "info"));//, R.drawable.volokamskoeshose);
        books.add( new Book( GENRES.PSYCHOLOGY, "Хачу и Буду", "Михатл Лобковский", 3700, "12"));//, R.drawable.hachu);
        books.add( new Book( GENRES.LITERATURE, "Шантарам", "Грегори Дэвид Робертс", 4000, "13"));//, R.drawable.shantaram);
        books.add( new Book( GENRES.LITERATURE, "МОНАХ, КОТОРЫЙ ПРОДАЛ СВОЙ ФЕРРАРИ. ПРИТЧА ОБ ИСПОЛНЕНИИ ЖЕЛАНИЙ И ПОИСКЕ СВОЕГО ПРЕДНАЗНАЧЕНИЯ", "Робин Шарма", 3000, "23"));//, R.drawable.monah);
        books.add( new Book( GENRES.PSYCHOLOGY, "Моя история", "Мишель Обама", 5400, "1"));//, R.drawable.moyaistoria );
        books.add( new Book( GENRES.PSYCHOLOGY, "ТОНКОЕ ИСКУССТВО ПОФИГИЗМА: ПАРАДОКСАЛЬНЫЙ СПОСОБ ЖИТЬ СЧАСТЛИВО", "Марк Мэнсон", 4200, "Современное общество пропагандирует культ успеха: будь умнее, богаче, продуктивнее — будь лучше всех. Соцсети изобилуют историями на тему, как какой-то малец придумал приложение и заработал кучу денег, статьями в духе «Тысяча и один способ быть счастливым», а фото во френдленте создают впечатление, что окружающие живут лучше и интереснее, чем мы"));//, R.drawable.tonkoipophigism1);
        books.add(new Book( GENRES.LITERATURE, "Волокамское шоссе", "Александр Бек", 3100, "info"));//, R.drawable.volokamskoeshose1);
    }
    public static Vector<Book> getBooks(){
        return books;
    }
public void setComponents(){
    mAuth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = mAuth.getCurrentUser();
    login = findViewById(R.id.SignIn);
    logup = findViewById(R.id.SignUp);
    editUsername = findViewById(R.id.logUsername);
    editPassword = findViewById(R.id.logPassword);
   progressBar = (ProgressBar) findViewById(R.id.progressBar);

}

public void getToolbar(){

    androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.customBar1);
    setSupportActionBar(toolbar);
    getSupportActionBar().setIcon(R.mipmap.logo3);
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addBooks();
        getToolbar();
        setComponents();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){

                }else{

                }
            }
        };

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = editUsername.getText().toString();
                password = editPassword.getText().toString();
                if(!email.equals("")&&!password.equals("")){
                    signIn(email, password);
                }else {
                    Toast.makeText(getApplicationContext(), "Wrong email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        logup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Registration.class);
                startActivity(intent);
            }
        });
    }
    public void signIn(final String email1, String password1){
        progressBar.setVisibility(ProgressBar.VISIBLE);
    mAuth.signInWithEmailAndPassword(email1, password1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful()){
                String a = email1;
                Toast.makeText(getApplicationContext(), "Signed in successful as:" + a, Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(getApplicationContext(), BooksActivity.class);
                startActivity(intent1);
            }else{
                Toast.makeText(getApplicationContext(), "Signing in failed", Toast.LENGTH_SHORT).show();
            }

        }
    });
    progressBar.setVisibility(ProgressBar.INVISIBLE);
    }


}
