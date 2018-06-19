package com.example.puppy.subwayapp;

import android.content.Context;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CustomActivity extends AppCompatActivity implements CustomKind.TextSendCall,CustomBread.TextSendCall,
        CustomCheese.TextSendCall,CustomSource.TextSendCall,CustomVegit.TextSendCall{

    ViewPager vp;
    Button btn1, btn2, btn3, btn4, btn5, btn6;
    CustomAdd customAdd;
    CustomVegit customVegit;
    CustomSource customSource;
    CustomCheese customCheese;
    CustomBread customBread;
    CustomKind customKind;

    public CustomActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.sublogo);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        vp = (ViewPager)findViewById(R.id.vp);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        btn5 = (Button)findViewById(R.id.btn5);
        btn6 = (Button)findViewById(R.id.btn6);
        customAdd = new CustomAdd();
        customBread = new CustomBread();
        customCheese = new CustomCheese();
        customKind = new CustomKind();
        customSource = new CustomSource();
        customVegit = new CustomVegit();

        vp.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        vp.setCurrentItem(0);

        btn1.setOnClickListener(movePageListener);
        btn1.setTag(0);
        btn2.setOnClickListener(movePageListener);
        btn2.setTag(1);
        btn3.setOnClickListener(movePageListener);
        btn3.setTag(2);
        btn4.setOnClickListener(movePageListener);
        btn4.setTag(3);
        btn5.setOnClickListener(movePageListener);
        btn5.setTag(4);
        btn6.setOnClickListener(movePageListener);
        btn6.setTag(5);


    }
    View.OnClickListener movePageListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            int tag = (int) v.getTag();
            vp.setCurrentItem(tag);
        }
    };

    @Override
    public void bPrintText(String bInput) {
        customAdd.bShowText(bInput);
    }
    @Override
    public void printText(String kInput, String kPrice) {
        customAdd.kShowText(kInput,kPrice);
    }

    @Override
    public void cPrintText(String cInput) {
        customAdd.cShowText(cInput);
    }

    @Override
    public void sPrintText(String sInput) {
        customAdd.sShowText(sInput);
    }

    @Override
    public void vPrintText(String vInput) {
        customAdd.vShowText(vInput);
    }

    private class pagerAdapter extends FragmentStatePagerAdapter
    {
        public pagerAdapter(android.support.v4.app.FragmentManager fm)
        {
            super(fm);
        }
        @Override
        public android.support.v4.app.Fragment getItem(int position)
        {
            switch(position)
            {
                case 0:
                    return customKind;
                case 1:
                    return customBread;
                case 2:
                    return customSource;
                case 3:
                    return customVegit;
                case 4:
                    return customCheese;
                case 5:
                    return customAdd;
                default:
                    return null;
            }
        }
        @Override
        public int getCount()
        {
            return 6;
        }
    }





}
