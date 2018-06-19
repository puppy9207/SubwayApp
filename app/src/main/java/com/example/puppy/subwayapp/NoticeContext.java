package com.example.puppy.subwayapp;


import android.os.Bundle;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NoticeContext extends Fragment {

    TextView noticeTitle,noticeContext,noticeAuthor;
    String title="",contents="", author="";

    public NoticeContext() {
        // Required empty public constructor
    }

    public void showText(String gTitle, String gContext, String gAuthor){
        title = gTitle;
        contents = gContext;
        author = gAuthor;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_notice_context, container, false);
        noticeContext = (TextView)root.findViewById(R.id.noticeContext);
        noticeTitle = (TextView)root.findViewById(R.id.noticeTitle);
        noticeAuthor = (TextView)root.findViewById(R.id.noticeAuthor);

        noticeTitle.setText(title);
        noticeContext.setText(contents);
        noticeAuthor.setText(author);

        return root;
    }

}
