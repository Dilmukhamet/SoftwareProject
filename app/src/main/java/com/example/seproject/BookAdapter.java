package com.example.seproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seproject.Activities.MainActivity;
import com.example.seproject.Books.Book;

import java.util.Vector;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder> {
    private TextView bookTitle1, Author1, Price1;

    private ImageView bookImage;

public Vector<Book> books = new Vector<>();

public Vector<Book> booksAll = new Vector<>();

public OnItemClick onItemClick;

public Button btnLike;
public BookAdapter(Vector<Book> v){
    books = v;
}

public BookAdapter(Vector<Book> v, OnItemClick onItemClick){
books = v;
this.onItemClick = onItemClick;
    }
    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(parent.getContext());
        View v = inflator.inflate(R.layout.booklayout, parent, false);

        return new BookHolder(v, onItemClick);

    }


    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {
            holder.Bind(books.get(position));
    }
    @Override
    public int getItemCount() {
        return books.size();
    }


    public  class BookHolder extends  RecyclerView.ViewHolder {
        public BookHolder(@NonNull View itemView, OnItemClick onItemClick) {
            super(itemView);
            bookTitle1 = itemView.findViewById(R.id.Booktitle);
            Author1 = itemView.findViewById(R.id.Author);
            Price1 = itemView.findViewById(R.id.priceField);
            bookImage = itemView.findViewById(R.id.bookImageF);
            btnLike = itemView.findViewById(R.id.buttonlike);
        }

         void Bind(final Book book){
            bookTitle1.setText(book.getBookName());
            Author1.setText(book.getAuthor());
            Price1.setText(book.getPrice());
            //bookImage.setImageResource(book.getImage());
             if(book.isLikePressed()==true){
                 btnLike.setBackgroundResource(R.drawable.like);

             }else{
                 btnLike.setBackgroundResource(R.drawable.unlike);

             }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick.onBookClick(book);
                }
            });
            btnLike.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     onItemClick.onLikeClicked(book);
                     if(book.isLikePressed() == true){
                         book.setLikePressed(true);
                         btnLike.setBackgroundResource(R.drawable.like);
                     }
                     else{
                         book.setLikePressed(false);
                         btnLike.setBackgroundResource(R.drawable.unlike);
                     }
                }
            });
        }
    }
    public interface OnItemClick{
        void onBookClick(Book book);
        void onLikeClicked(Book book);
    }
}
