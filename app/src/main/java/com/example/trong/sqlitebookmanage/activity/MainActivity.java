package com.example.trong.sqlitebookmanage.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.trong.sqlitebookmanage.activity.add_book.AddBookActivity;
import com.example.trong.sqlitebookmanage.activity.edit_book.EditBookActivity;
import com.example.trong.sqlitebookmanage.common.adapter.BookAdapter;
import com.example.trong.sqlitebookmanage.R;
import com.example.trong.sqlitebookmanage.controller.BookDataController;
import com.example.trong.sqlitebookmanage.base.BaseActivity;
import com.example.trong.sqlitebookmanage.model.Book;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    ListView lvBook;
    ImageButton imgBtnAddBook;
    AutoCompleteTextView autoSearch;
    Spinner spinnerFilter;
    ArrayList<Book> listBook;
    BookAdapter adapter;
    BookDataController bookController;
    private Context context;
    private Toolbar toolbar;
    private ImageButton imgSearch;

    public static final int NAME = 0;
    public static final int KINDOFBOOK = 1;
    private int checkSpinner = 0;

    ArrayAdapter<String> adapterSpiner;
    ArrayAdapter<String> adapterAutoFind;
    ArrayList<String> listSpinner;
    ArrayList<String> listAutoCompeleteTextBOOKNAME = new ArrayList<>();
    ArrayList<String> listAutoCompeleteTextKIND = new ArrayList<>();



    @Override
    protected int getLayoutResources() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariables(Bundle saveInstanceState) {
        lvBook = findViewById(R.id.lvBook);
        imgBtnAddBook = findViewById(R.id.imgBtnAddBook);
        toolbar = findViewById(R.id.tool_bar);
        autoSearch = findViewById(R.id.autoSearch);
        spinnerFilter = findViewById(R.id.spinerFilter);
        imgSearch = findViewById(R.id.imgSerch);
    }

    @Override
    protected void initData(Bundle saveInstanceState) {


        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Book Manage");
        }
        bookController = new BookDataController(this);
        checkDataIsEmty();
        listBook = bookController.getAllBook();
        adapter = new BookAdapter(getLayoutInflater(), listBook);
        lvBook.setAdapter(adapter);
        //khoi tao va set data cho spinner
        initSpinner();
        //khoi tao va set data cho autocompletetext
        initAutoCompleteText(listBook, 0);
        //set onclick
        imgBtnAddBook.setOnClickListener(this);
        imgSearch.setOnClickListener(this);
        lvBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, EditBookActivity.class);
                intent.putExtra("EDIT_BOOK", listBook.get(position));
                startActivityForResult(intent, 1);

            }
        });
        lvBook.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                createDialogDeleteBook(listBook.get(position));
                return false;
            }
        });
        spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                initAutoCompleteText(listBook, position);
                checkSpinner = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        autoSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (checkSpinner == 0) {
                    listBook.clear();
                    listBook.addAll(bookController.getBookName(listAutoCompeleteTextBOOKNAME.get(position)));
   //                 Toast.makeText(context, ""+listAutoCompeleteTextBOOKNAME.get(position), Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                } else {
                    listBook.clear();
                    listBook.addAll(bookController.getBookKind(listAutoCompeleteTextKIND.get(position)));
                    adapter.notifyDataSetChanged();
                }

            }
        });
    }


    private void initAutoCompleteText(ArrayList<Book> listBook, int type) {
        switch (type) {
            case NAME: {
                listAutoCompeleteTextBOOKNAME = new ArrayList<>();
                for (Book b : listBook) {
                    listAutoCompeleteTextBOOKNAME.add(b.getName());
                }
                adapterAutoFind = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listAutoCompeleteTextBOOKNAME);
                autoSearch.setAdapter(adapterAutoFind);
                adapterAutoFind.notifyDataSetChanged();

            }
            break;
            case KINDOFBOOK: {
                listAutoCompeleteTextKIND = new ArrayList<>();
                listAutoCompeleteTextKIND.add("Trinh thám");
                listAutoCompeleteTextKIND.add("Phiêu lưu");
                listAutoCompeleteTextKIND.add("Hài hước");
                listAutoCompeleteTextKIND.add("Ngôn tình");
                listAutoCompeleteTextKIND.add("Lịch sử");
                listAutoCompeleteTextKIND.add("Khoa học");
                adapterAutoFind = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listAutoCompeleteTextKIND);
                autoSearch.setAdapter(adapterAutoFind);
                adapterAutoFind.notifyDataSetChanged();
            }
            break;
        }

    }

    private void initSpinner() {
        listSpinner = new ArrayList<>();
        listSpinner.add("Name");
        listSpinner.add("Kind of book");
        adapterSpiner = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listSpinner);
        spinnerFilter.setAdapter(adapterSpiner);
    }

    private void createDialogDeleteBook(final Book book) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Book");
        builder.setMessage("Do you want to delete this book ?");
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int idBook = book.getId();
                boolean checkDelete = bookController.deleteBook(idBook);
                if (checkDelete) {
                    Toast.makeText(MainActivity.this, "Delete successful " + book.getName(), Toast.LENGTH_SHORT).show();
                    // cập nhật lại list
                    listBook.clear();
                    listBook.addAll(bookController.getAllBook());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Delete fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.create().show();
    }


    public void checkDataIsEmty() {
        try {
            bookController.isCreateDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgBtnAddBook: {
                Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
                startActivityForResult(intent, 2);
            }
            break;
            case R.id.imgSerch:{
                listBook.clear();
                listBook.addAll(bookController.getAllBook());
                adapter.notifyDataSetChanged();
            }
            break;
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 1: {
                Book book = (Book) data.getSerializableExtra("ADD_BOOK");
                bookController.insertBook(book);
                listBook.clear();
                listBook.addAll(bookController.getAllBook());
                adapter.notifyDataSetChanged();

            }
            break;
            case 2: {
                Book book = (Book) data.getSerializableExtra("EDIT_BOOK");
                bookController.editBook(book);
                listBook.clear();
                listBook.addAll(bookController.getAllBook());
                adapter.notifyDataSetChanged();

            }
            break;
        }
    }
}
