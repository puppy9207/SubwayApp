package com.example.puppy.subwayapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by puppy on 2018-06-19.
 */

public class ListItemView extends LinearLayout {

    TextView title;
    TextView author;

    public ListItemView(Context context) {
        super(context);
        init(context);
    }

    public ListItemView(Context context, AttributeSet attrs){
        super(context,attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.list_notice,this,true);

        title = (TextView)findViewById(R.id.listTitle);
        author = (TextView)findViewById(R.id.listAuthor);
    }
    public void setTitle(String listTitle){
        title.setText(listTitle);
    }
    public void setAuthor(String listAuthor){
        author.setText(listAuthor);
    }
}
