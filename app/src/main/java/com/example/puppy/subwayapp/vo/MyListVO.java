package com.example.puppy.subwayapp.vo;

/**
 * @author sdm32
 * @since 2018-06-19
 */


public class MyListVO
{
    private String title;
    private String name;
    private int resId;

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