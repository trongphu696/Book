package com.example.trong.sqlitebookmanage.common.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.trong.sqlitebookmanage.R;
import com.example.trong.sqlitebookmanage.model.Book;

import java.util.ArrayList;

/**
 * Created by trong on 3/12/2018.
 */

public class BookAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Book> list;

    public BookAdapter(LayoutInflater inflater, ArrayList<Book> list) {
        this.inflater = inflater;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Book getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private TextView txtName,txtAuthor,txtKindOfBook;
    @Override
    public View getView(int position, View v, ViewGroup parent){
        Book book = list.get(position);
        if(v == null) {
            v = inflater.inflate(R.layout.item_book, null);
        }
          txtName = v.findViewById(R.id.txtBookName);
          txtAuthor = v.findViewById(R.id.txtAuthor);
          txtKindOfBook = v.findViewById(R.id.txtKindOfBook);
          txtKindOfBook.setText(book.getKindofbook());
          txtAuthor.setText(book.getAuthor());
          txtName.setText(book.getName());
        return v;
    }
}
