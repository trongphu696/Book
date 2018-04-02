package com.example.trong.sqlitebookmanage.activity.add_book;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trong.sqlitebookmanage.R;
import com.example.trong.sqlitebookmanage.base.BaseActivity;
import com.example.trong.sqlitebookmanage.model.Book;

public class AddBookActivity extends BaseActivity implements View.OnClickListener {
    Toolbar toolbar;
    EditText edtBookName, edtAuthor;
    TextView edtKindOfBook;


    @Override
    protected int getLayoutResources() {
        return R.layout.activity_add_book;
    }

    @Override
    protected void initVariables(Bundle saveInstanceState) {
        toolbar = findViewById(R.id.tool_bar);
        edtBookName = findViewById(R.id.edt_book_name);
        edtAuthor = findViewById(R.id.edt_author);
        edtKindOfBook = findViewById(R.id.edt_kindofbook);

    }

    @Override
    protected void initData(Bundle saveInstanceState) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.back);
            actionBar.setTitle("Add Book");
        }
        edtKindOfBook.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_submit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_submit: {
                try {
//                    if (edtBookName.getText().length()==0) edtBookName.setError("No input");
//                    if (edtKindOfBook.getText().length()==0) edtKindOfBook.setError("No input");
//                    if (edtAuthor.getText().length()==0) edtAuthor.setError("No input");
                    edtBookName.getText().charAt(0);
                    edtBookName.getText().charAt(0);
                    edtBookName.getText().charAt(0);
                    Book book = new Book(edtBookName.getText().toString(), edtAuthor.getText().toString(), edtKindOfBook.getText().toString());
                    Intent intent = new Intent();
                    intent.putExtra("ADD_BOOK", book);
                    setResult(1, intent);
                    Toast.makeText(this, "Add book successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }catch (Exception e){
                    Toast.makeText(this, "Bạn không được để trống?", Toast.LENGTH_SHORT).show();
                }

            }
            case android.R.id.home: {
                onBackPressed();
                return true;
            }

        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edt_kindofbook: {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Select Kind Of Book");
                final String[] kob = {""};
                final CharSequence[] data = {"Trinh thám", "Phiêu lưu", "Hài hước", "Ngôn tình", "Lịch sử", "Khoa học"};
                builder.setSingleChoiceItems(data, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        kob[0] = data[which].toString();
                    }
                });
                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edtKindOfBook.setText(kob[0]);
                    }
                });
                builder.create().show();
            }
        }
    }
}
