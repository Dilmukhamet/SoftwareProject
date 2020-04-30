package com.example.seproject.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.seproject.Books.Book;
import com.example.seproject.Books.GENRES;
import com.example.seproject.Fragments.AllBooks;
import com.example.seproject.Fragments.FavFragment;
import com.example.seproject.Fragments.ProfileFragment;
import com.example.seproject.Fragments.SearchFragment;
import com.example.seproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Vector;

public class BooksActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    private FirebaseAuth mAuth;
    private static Vector<Book> books = new Vector<>();
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
    public void getToolBar() {
       bottomNavigation = findViewById(R.id.bottombuttons);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.customBar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.mipmap.logo3);

    }
TextView textView5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        //Intent intent = getIntent();
        textView5 = findViewById(R.id.elementsw);
        textView5.setText(MainActivity.getBooks().size()+ "");
        getToolBar();
       addBooks();
        //int userPosition = intent.getIntExtra("position", 0);
    }


    public void openFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
           // HomeFragment homeFragment =  HomeFragment.newInstance(state);

            switch (item.getItemId()) {
                case R.id.nav_home:
                    openFragment(AllBooks.newInstance( "", ""));
                    break;
                case R.id.nav_favorites:

                    openFragment(FavFragment.newInstance("", ""));
                    break;
                case R.id.nav_profile:
                    openFragment(ProfileFragment.newInstance("", ""));
                    break;
                case R.id.nav_search:
                    openFragment(SearchFragment.newInstance("", ""));
            }
            return true;
        }
    };

}
