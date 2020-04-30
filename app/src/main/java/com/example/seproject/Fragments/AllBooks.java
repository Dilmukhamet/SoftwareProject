package com.example.seproject.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.seproject.Activities.BooksActivity;
import com.example.seproject.Activities.FullBookINFO;
import com.example.seproject.Activities.MainActivity;
import com.example.seproject.BookAdapter;
import com.example.seproject.Books.Book;
import com.example.seproject.R;

import java.util.Vector;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllBooks#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllBooks extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
Context c;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private Button btnLike;
private ListView listView;
    public AllBooks() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AllBooks newInstance(String param1, String param2) {
        AllBooks fragment = new AllBooks();
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

    TextView textView3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_all_books, container, false);
        recyclerView =  view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(c));
        /*listView = view.findViewById(R.id.listViewLay);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, MainActivity.getList());*/
       // textView3 = view.findViewById(R.id.elements);


        textView3.setText(BooksActivity.getBooks().size() + "");
        BookAdapter bookAdapter = new BookAdapter(MainActivity.getBooks(), new BookAdapter.OnItemClick() {
            @Override
            public void onBookClick(Book book) {
                navigateToBook(getContext(), book);
            }
            @Override
            public void onLikeClicked(Book book) {
                pressLike(book);
            }
        });
        recyclerView.setAdapter(bookAdapter);
        return view;
    }
    public void navigateToBook(Context context, Book book){
        Intent intent = new Intent(context , FullBookINFO.class);
        intent.putExtra("ID", book.getBookID());
        intent.putExtra("title", book.getBookName());
        intent.putExtra("fullnews", book.getInfo());
        intent.putExtra("author", book.getAuthor());
        intent.putExtra("likenumber", book.getPrice());
        intent.putExtra("commentnumber", book.getImage());
        startActivity(intent);
    }
    public void pressLike(Book book){
        //int position = Book.returnBooks().indexOf(book);
        if(book.isLikePressed()==false){
            book.setLikePressed(true);
            FavFragment.putFavoriteBook(book);
        }else{
            book.setLikePressed(false);
            FavFragment.removeFavoriteBook(book);
        }
    }
    @Override
    public void onAttach(@NonNull Context context) {
c = context;
        super.onAttach(context);
    }
}
