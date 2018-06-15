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
public class CustomBread extends Fragment {

    GridView breadList;
    TextView breadTv;

    int breadImg[] = {R.drawable.b1,R.drawable.b2,R.drawable.b3,R.drawable.b4,R.drawable.b5,R.drawable.b6};
    String breadName [] ={"허니 오트","하티","위트","파마산 오레가노","화이트","플랫 브레드"};
    public CustomBread() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_custom_bread, container, false);
        breadList = (GridView)root.findViewById(R.id.breadList);
        MyAdapter breadAdapter = new MyAdapter(getContext(),R.layout.row,breadImg);
        breadTv = (TextView)root.findViewById(R.id.breadTv);
        breadList.setAdapter(breadAdapter);
        breadList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                breadTv.setText(breadName[position]);
            }
        });
        return root;
    }

}
