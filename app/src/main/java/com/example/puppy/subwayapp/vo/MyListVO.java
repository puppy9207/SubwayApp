package com.example.puppy.subwayapp.vo;

/**
 * Created by puppy on 2018-06-19.
 */

public class MyListVO {
    String title;
    String name;
    int resId;

    public MyListVO(String title, String name, int resId) {
        this.title = title;
        this.name = name;
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
