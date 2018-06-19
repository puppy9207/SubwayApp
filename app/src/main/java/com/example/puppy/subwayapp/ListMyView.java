package com.example.puppy.subwayapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by puppy on 2018-06-19.
 */

public class ListMyView extends LinearLayout {

    ImageView img;
    TextView tv1;
    TextView tv2;

    public ListMyView(Context context) {
        super(context);
        init(context);
    }

    public ListMyView(Context context, AttributeSet attrs){
        super(context,attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_mymenu,this,true);
        img = (ImageView)findViewById(R.id.img1);
        tv1 = (TextView)findViewById(R.id.myTitle);
        tv2 = (TextView)findViewById(R.id.myValue);
    }

    public void setMyTitle(int myTitle){
        tv1.setText(Integer.toString(myTitle));
    }
    public void setMyValue(String myValue){
        tv2.setText(myValue);
    }
    public void setMyImg(int myImg){
        img.setImageResource(myImg);
    }

}
