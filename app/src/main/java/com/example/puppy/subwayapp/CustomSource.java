package com.example.puppy.subwayapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CustomSource extends Fragment {
    GridView sourceList;
    TextView sourceTv;

    int sourceImg[] = {R.drawable.s1,R.drawable.s2,R.drawable.s3,R.drawable.s4,R.drawable.s5,R.drawable.s6,
            R.drawable.s7,R.drawable.s8,R.drawable.s9,R.drawable.s10,R.drawable.s11,R.drawable.s12,R.drawable.s13,
            R.drawable.s14,R.drawable.s15,R.drawable.s16
    };
    String sourceName[] = {"렌치 드레싱","마요네즈","스위트 어니언","허니 머스터드","스위트 칠리","핫 칠리","사우스 웨스트"
    ,"머스타드","디종홀스래디쉬","사우전 아일랜드","이탈리안 드레싱","올리브 오일","레드와인 식초","소금","후추","스모크 바비큐 소스"};


    public CustomSource() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_custom_source, container, false);
        sourceList = (GridView)root.findViewById(R.id.sourceList);
        MyAdapter breadAdapter = new MyAdapter(getContext(),R.layout.row,sourceImg);
        sourceTv = (TextView)root.findViewById(R.id.sourceTv);
        sourceList.setAdapter(breadAdapter);
        sourceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sourceTv.setText(sourceName[position]);
            }
        });
        return root;
    }

}
