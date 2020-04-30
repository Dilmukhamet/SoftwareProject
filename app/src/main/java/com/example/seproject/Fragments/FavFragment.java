package com.example.seproject.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.seproject.Activities.FullBookINFO;
import com.example.seproject.BookAdapter;
import com.example.seproject.Books.Book;
import com.example.seproject.R;

import java.util.Vector;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context c ;
    static FragmentManager manager;
    static Fragment currentFragment;
    private RecyclerView favRecyclerView;
    private static Vector<Book> favBooks = new Vector<>();

    public static void putFavoriteBook(Book book){
        if(!isDuplicate(book)){
            favBooks.add(book);
        }
    }
    public static void removeFavoriteBook(Book book){
        favBooks.remove(book);
    }
    public static boolean isDuplicate(Book book) {
        boolean a = false;
        for (Book b : favBooks) {
            if (book.equals(b)) {
                a = true;
            }
        }
        return a;
    }

    public FavFragment() {
        // Required empty public constructor
    }
  /*  public static void reload(){
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.detach(currentFragment);
        fragmentTransaction.attach(currentFragment);
        fragmentTransaction.commit();
    }*/
    public void reloadFragment(){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.detach(this);
        fragmentTransaction.attach(this);
        fragmentTransaction.commit();
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavFragment newInstance(String param1, String param2) {
        FavFragment fragment = new FavFragment();
        Bundle args = new Bundle();

        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        currentFragment = fragment;
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
     View view =     inflater.inflate(R.layout.fragment_fav, container, false);
        favRecyclerView = view.findViewById(R.id.recycler2);
        favRecyclerView.setLayoutManager(new LinearLayoutManager(c));
        BookAdapter favbookAdapter = new BookAdapter(favBooks, new BookAdapter.OnItemClick() {
            @Override
            public void onBookClick(Book book) {
                navigateToBook(c, book);
            }

            @Override
            public void onLikeClicked(Book book) {
                pressLike(book);
            }
        });
        favRecyclerView.setAdapter(favbookAdapter);
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
            reloadFragment();
        }else{
            book.setLikePressed(false);
            FavFragment.removeFavoriteBook(book);
            reloadFragment();
        }
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        c = context;
    }
}
