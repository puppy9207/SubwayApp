package com.example.puppy.subwayapp.vo;

/**
 * Created by puppy on 2018-06-19.
 */

public class ListVO {
    String title;
    String author;


    public ListVO(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
