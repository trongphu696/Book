package com.example.trong.sqlitebookmanage.model;

import java.io.Serializable;

/**
 * Created by trong on 3/10/2018.
 */

public class Book implements Serializable {
    private int id;
    private String name;
    private String author;
    private String kindofbook;

    public Book(String name, String author, String kindofbook) {
        this.name = name;
        this.author = author;
        this.kindofbook = kindofbook;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getKindofbook() {
        return kindofbook;
    }

    public void setKindofbook(String kindofbook) {
        this.kindofbook = kindofbook;
    }

    public Book(int id, String name, String author, String kindofbook) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.kindofbook = kindofbook;

    }
}
