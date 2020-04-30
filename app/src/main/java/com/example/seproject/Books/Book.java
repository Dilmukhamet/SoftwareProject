package com.example.seproject.Books;

import android.widget.ImageView;



import com.example.seproject.Activities.MainActivity;
import com.example.seproject.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;


public class Book {

    private float bookID;
    private GENRES genres;
    private int price;
    private String bookName,author, info;
    private int image;
    private boolean likePressed = false;
    {



    }


    public static Vector<Book> mainbooks = new Vector<>();
    public String url;

    public static Book getBookByIndex(int i){
         return mainbooks.get(i);
    }
    public static Vector<Book> returnBooks(){return mainbooks; }

    public static void putNewBook(Book book) {
    if(isDuplicate(book)==false){mainbooks.add(book); }
    }

    public static  void deleteBook(Book book){
        mainbooks.remove(book);
    }

    public static boolean isDuplicate(Book book){
        boolean a = false;
        for(Book book2 : mainbooks){
            if(book2.equals(book)){
                a = true;
            }
        }
        return a;
    }
    public Book(String n, String a, int p, String i){
 bookName = n;
 author = a;
 price = p;
 info=i;
    }

    public Book(GENRES genres, String bookName,  String author, int price, String info, int image) {

        this.genres = genres;
        this.bookName = bookName;
        this.author = author;
        this.price = price;
        this.info = info;
        this.image = image;
        addBook();
        bookID = mainbooks.indexOf(this);
    }
    public Book(GENRES genres, String bookName,  String author, int price, String info) {

        this.genres = genres;
        this.bookName = bookName;
        this.author = author;
        this.price = price;
        this.info = info;
        //this.image = image;
        addBook();
        bookID = mainbooks.indexOf(this);
    }
    public void addBook(){
mainbooks.add(this);
    }
    public void addBooks(){
        Book b1 = new Book( GENRES.PSYCHOLOGY, "ТОНКОЕ ИСКУССТВО ПОФИГИЗМА: ПАРАДОКСАЛЬНЫЙ СПОСОБ ЖИТЬ СЧАСТЛИВО", "Марк Мэнсон", 3700, "Современное общество пропагандирует культ успеха: будь умнее, богаче, продуктивнее — будь лучше всех. Соцсети изобилуют историями на тему, как какой-то малец придумал приложение и заработал кучу денег, статьями в духе «Тысяча и один способ быть счастливым», а фото во френдленте создают впечатление, что окружающие живут лучше и интереснее, чем мы");//, R.drawable.tonkoipophigism);
        Book b2 = new Book( GENRES.LITERATURE, "Волокамское шоссе", "Александр Бек", 3100, "info");//, R.drawable.volokamskoeshose);
        Book b3 = new Book( GENRES.PSYCHOLOGY, "Хачу и Буду", "Михатл Лобковский", 3700, "12");//, R.drawable.hachu);
        Book b4 = new Book( GENRES.LITERATURE, "Шантарам", "Грегори Дэвид Робертс", 4000, "13");//, R.drawable.shantaram);
        Book b5 = new Book( GENRES.LITERATURE, "МОНАХ, КОТОРЫЙ ПРОДАЛ СВОЙ ФЕРРАРИ. ПРИТЧА ОБ ИСПОЛНЕНИИ ЖЕЛАНИЙ И ПОИСКЕ СВОЕГО ПРЕДНАЗНАЧЕНИЯ", "Робин Шарма", 3000, "23");//, R.drawable.monah);
        Book b6 = new Book( GENRES.PSYCHOLOGY, "Моя история", "Мишель Обама", 5400, "1");//, R.drawable.moyaistoria );
        Book b7 = new Book( GENRES.PSYCHOLOGY, "ТОНКОЕ ИСКУССТВО ПОФИГИЗМА: ПАРАДОКСАЛЬНЫЙ СПОСОБ ЖИТЬ СЧАСТЛИВО", "Марк Мэнсон", 4200, "Современное общество пропагандирует культ успеха: будь умнее, богаче, продуктивнее — будь лучше всех. Соцсети изобилуют историями на тему, как какой-то малец придумал приложение и заработал кучу денег, статьями в духе «Тысяча и один способ быть счастливым», а фото во френдленте создают впечатление, что окружающие живут лучше и интереснее, чем мы");//, R.drawable.tonkoipophigism1);
        Book b8 = new Book( GENRES.LITERATURE, "Волокамское шоссе", "Александр Бек", 3100, "info");//, R.drawable.volokamskoeshose1);
    }
    public List<Book> boks = new ArrayList<>();
    public static final Comparator<Book> compareByP = new Comparator<Book>() {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getPrice() - o2.getPrice();
        }
    };

    public void sort1(){
        Collections.sort(boks, compareByP);
    }

    public class CompareByPrice implements Comparator<Book> {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.getPrice() - o2.getPrice();
        }
    }

    public static final Comparator<Book> compareByPrice = new Comparator<Book>() {
        @Override
        public int compare(Book o1, Book o2) {
            return o1.price- o2.price;
        }
    };
    public static Vector<Book> BooksByGenres(Vector<Book> books, GENRES genre){
        Vector <Book> v1 = new Vector<>();

        for(Book b: books){
            if(b.getGenres() == genre){
                v1.add(b);
            }
        }
        return v1;
    }
    public boolean isLikePressed() {
        return likePressed;
    }

    public void setLikePressed(boolean likePressed) {
        this.likePressed = likePressed;
    }
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getBookID() {
        return bookID;
    }

    public void setBookID(float bookID) {
        this.bookID = bookID;
    }

    public GENRES getGenres() {
        return genres;
    }

    public void setGenres(GENRES genres) {
        this.genres = genres;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
