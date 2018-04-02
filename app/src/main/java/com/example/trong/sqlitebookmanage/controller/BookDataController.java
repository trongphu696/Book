package com.example.trong.sqlitebookmanage.controller;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.trong.sqlitebookmanage.model.Book;

import java.util.ArrayList;

/**
 * Created by trong on 3/10/2018.
 */

public class BookDataController extends SQLiteDataController {
    public BookDataController(Context context) {
        super(context);
    }

    public ArrayList<Book> getAllBook() {
        ArrayList<Book> list = new ArrayList<>();
        try {
            openDatabase();
            //     String[] s = new String[]{"id","name","author","kindofbook"};
            String query = "SELECT * FROM book";
            @SuppressLint("Recycle")
            Cursor cur = database.rawQuery(query, null);
            while (cur.moveToNext()) {
                list.add(new Book(cur.getInt(0), cur.getString(1), cur.getString(2), cur.getString(3)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }
    public ArrayList<Book> getBookName(String s){
        ArrayList<Book> list = new ArrayList<>();
        try {
            openDatabase();
            //     String[] s = new String[]{"id","name","author","kindofbook"};
            String query = "SELECT * FROM book WHERE name ='"+s+"'";
            Log.e("hihi",s);
            @SuppressLint("Recycle")
            Cursor cur = database.rawQuery(query, null);
            while (cur.moveToNext()) {
                list.add(new Book(cur.getInt(0), cur.getString(1), cur.getString(2), cur.getString(3)));
            }
            Log.e("hihi",list.get(0).getName());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }
    public ArrayList<Book> getBookKind(String s){
        ArrayList<Book> list = new ArrayList<>();
        try {
            openDatabase();
            //     String[] s = new String[]{"id","name","author","kindofbook"};
            String query = "SELECT * FROM book WHERE kindofbook ='"+s+"'";
            @SuppressLint("Recycle")
            Cursor cur = database.rawQuery(query, null);
            while (cur.moveToNext()) {
                list.add(new Book(cur.getInt(0), cur.getString(1), cur.getString(2), cur.getString(3)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return list;
    }


    public boolean insertBook(Book book) {
        try {
            openDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", book.getName());
            contentValues.put("author", book.getAuthor());
            contentValues.put("kindofbook", book.getKindofbook());

            long id = database.insert("book", null, contentValues);
            return id > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return false;
    }

    public boolean editBook(Book book) {
        try {
            openDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", book.getId());
            contentValues.put("name", book.getName());
            contentValues.put("author", book.getAuthor());
            contentValues.put("kindofbook", book.getKindofbook());
            long id = database.update("book", contentValues, "id = " + book.getId(), null);
            return id > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return false;
    }

    public boolean deleteBook(int idBook) {
        try {
            openDatabase();
            long sl = database.delete("book", "id = " + idBook, null);
            return sl > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return false;
    }



}
