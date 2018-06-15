package com.example.puppy.subwayapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Login    login;
    HomeMenu  home;
    MyMenu   myMenu;
    Map map;
    SignUp signUp;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        manager = getSupportFragmentManager();
        login = (Login)manager.findFragmentById(R.id.mainF);
        home = new HomeMenu();
        myMenu = new MyMenu();
        map = new Map();
        signUp = new SignUp();

    }

    public void onFragmentChanged(String command)
    {
        switch (command)
        {
            case "Login":
                manager.beginTransaction()
                        .replace(R.id.container, login).commit();
                break;
            case "HomeMenu":
                manager.beginTransaction()
                        .replace(R.id.container, home).commit();
                break;
            case "MyMenu":
                manager.beginTransaction()
                        .replace(R.id.container, myMenu).commit();
                break;
            case "Map":
                manager.beginTransaction()
                        .replace(R.id.container, map).commit();
                break;
            case "SignUp":
                manager.beginTransaction()
                        .replace(R.id.container, signUp).commit();
                break;
        }
    }

}
