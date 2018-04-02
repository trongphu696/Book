package com.example.trong.sqlitebookmanage.activity.edit_book;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trong.sqlitebookmanage.R;
import com.example.trong.sqlitebookmanage.base.BaseActivity;
import com.example.trong.sqlitebookmanage.model.Book;

public class EditBookActivity extends BaseActivity implements View.OnClickListener {

    Toolbar toolbar;
    EditText edtBookName, edtAuthor;
    TextView edtKindOfBook;
    private Book book;


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
            actionBar.setTitle("Edit Book");
        }
        createData();
        edtKindOfBook.setOnClickListener(this);
    }

    private void createData() {
        if (getIntent() != null) {
            book = (Book) getIntent().getSerializableExtra("EDIT_BOOK");
            edtBookName.setText(book.getName());
            edtAuthor.setText(book.getAuthor());
            edtKindOfBook.setText(book.getKindofbook());
        }

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
                if (edtBookName.getText() == null) edtBookName.setError("No input");
                else book.setName(edtBookName.getText().toString());
                if (edtBookName.getText() == null) edtKindOfBook.setError("No input");
                else book.setAuthor(edtAuthor.getText().toString());
                if (edtBookName.getText() == null) edtAuthor.setError("No input");
                else book.setKindofbook(edtKindOfBook.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("EDIT_BOOK", book);
                setResult(2, intent);
                Toast.makeText(this, "Edit book successfully", Toast.LENGTH_SHORT).show();
                finish();
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
