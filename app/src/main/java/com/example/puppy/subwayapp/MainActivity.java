package com.example.puppy.subwayapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements Notice.TextSendCall{

    Login    login;
    HomeMenu  home;
    MyMenu   myMenu;
    SignUp signUp;
    Notice notice;
    NoticeContext noticeContext;
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
        notice = new Notice();
        noticeContext = new NoticeContext();

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
                        .replace(R.id.container, myMenu).addToBackStack(null).commit();
                break;
            case "SignUp":
                manager.beginTransaction()
                        .replace(R.id.container, signUp).commit();
                break;
            case "Notice":
                manager.beginTransaction()
                        .replace(R.id.container, notice).addToBackStack(null).commit();
                break;
            case "NoticeT":
                manager.beginTransaction()
                        .replace(R.id.container, noticeContext).addToBackStack(null).commit();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.logout:
                //로그아웃 로직 넣으면됨
                break;

        }
        return true;
    }

    @Override
    public void noticePrintText(String title, String context, String author) {
        noticeContext.showText(title,context,author);
    }
}
