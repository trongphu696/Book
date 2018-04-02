package com.example.trong.sqlitebookmanage.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by trong on 3/10/2018.
 */

public class SQLiteDataController extends SQLiteOpenHelper {

    public String DB_PATH = "//data//data//%s//databases//";
    private static String DB_NAME = "Books.db";
    public SQLiteDatabase database;
    private final Context context;


    public SQLiteDataController(Context context) {
        super(context, DB_NAME, null, 1);
        this.DB_PATH = String.format(DB_PATH, context.getPackageName());
        this.context = context;
    }


    public boolean isCreateDatabase() throws IOException {
        if(!checkExitsDatabase()){
            this.getReadableDatabase();
            try{
                copyDatabase();
            }catch (Exception e){
                throw new Error("Error copying database");
            }
        }
        return true;
    }
    private boolean checkExitsDatabase() {
        try {
            String myPath = DB_PATH + DB_NAME;
            File fileDB = new File(myPath);
            return fileDB.exists();

        } catch (Exception e) {
            return false;
        }
    }

    private void copyDatabase() throws IOException {
        InputStream inputStream = context.getAssets().open(DB_NAME);
        OutputStream outputStream = new FileOutputStream(DB_PATH + DB_NAME);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    public boolean delDatabase() {
        File file = new File(DB_PATH + DB_NAME);
        return file.delete();
    }

    public void openDatabase() {
        database = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public int delDataFromTable(String tbName) {
        int result = 0;
        try {
            openDatabase();
            database.beginTransaction();
            result = database.delete(tbName, null, null);
            if (result >= 0) {
                database.setTransactionSuccessful();
            }
        } catch (Exception e) {
            database.endTransaction();
            close();
        } finally {
            database.endTransaction();
            close();
        }
        return result;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // TODO
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // TODO
    }
}
