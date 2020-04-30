package com.example.seproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.seproject.Books.Book;
import com.example.seproject.Fragments.FavFragment;
import com.example.seproject.R;

public class FullBookINFO extends AppCompatActivity {
    private Button likebutton;
    private TextView bookNameview;
    private TextView authorview;
    private TextView categoryview;
    private TextView bookinfoview;
    private TextView priceview;
    private ImageView bookImage;

    public void setComponents(){
    bookImage = findViewById(R.id.BookImage);
    likebutton = findViewById(R.id.Likebutton);
    bookNameview = findViewById(R.id.BookName);
    authorview =findViewById(R.id.Author);
    categoryview = findViewById(R.id.Categories);
    bookinfoview = findViewById(R.id.BookInfo);
    priceview = findViewById(R.id.Price);
    }
    public void getToolbar(){
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.customBar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.mipmap.logo3);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_book_i_n_f_o);
        Intent intent = getIntent();

        setComponents();
        int position = intent.getIntExtra("ID", 0);
        final Book b = Book.getBookByIndex(position);
        bookNameview.setText(b.getBookName()); // BOOK NAME
        authorview.setText(b.getAuthor()); // AUTHOR NAME
        categoryview.setText(b.getGenres().toString()); // CATEGORY
        bookinfoview.setText(""); // BOOK INFO
        priceview.setText(""); // BOOK PRICE

        bookImage.setImageResource(b.getImage()); // BOOK IMAGE*/
        if(b.isLikePressed() == true){
            likebutton.setBackgroundResource(R.drawable.like);
        }else{
            likebutton.setBackgroundResource(R.drawable.unlike);
        }
        likebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(b.isLikePressed()==false){
                    //News.putToFavorite(n);
                        FavFragment.putFavoriteBook(b);
                    b.setLikePressed(true) ;
                    likebutton.setBackgroundResource(R.drawable.like);
                }else{
                    b.setLikePressed(false);
                    //News.removeFromFavorites(n);
                    FavFragment.removeFavoriteBook(b);
                    likebutton.setBackgroundResource(R.drawable.unlike);
                }
            }
        });
    }

}
