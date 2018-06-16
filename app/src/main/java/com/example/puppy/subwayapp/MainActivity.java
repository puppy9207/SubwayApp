package com.example.puppy.subwayapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    Login    login;
    HomeMenu  home;
    MyMenu   myMenu;
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
        signUp = new SignUp();

    }

    public void onFragmentChanged(String command)
    {
        switch (command)
        {
            case "TaskLogin":
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
            case "SignUp":
                manager.beginTransaction()
                        .replace(R.id.container, signUp).commit();
                break;
        }
    }

}
