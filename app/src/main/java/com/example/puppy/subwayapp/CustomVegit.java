package com.example.puppy.subwayapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CustomVegit extends Fragment {
    GridView vegitList;
    TextView vegitTv;
    Button btnReset;

    int vegitImg[] = {R.drawable.v1,R.drawable.v2,R.drawable.v3,R.drawable.v4,R.drawable.v5,R.drawable.v6,R.drawable.v7,R.drawable.v8,R.drawable.v9};
    String vegitName [] = {"양상추","토마토","오이","피망","양파","피클","올리브","할라피뇨","아보카도"};
    public CustomVegit() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_custom_vegit, container, false);
        vegitList = (GridView)root.findViewById(R.id.vegitList);
        MyAdapter vegitAdapter = new MyAdapter(getContext(),R.layout.row,vegitImg);
        vegitTv = (TextView)root.findViewById(R.id.vegitTv);
        vegitList.setAdapter(vegitAdapter);
        vegitList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                vegitTv.setText("-"+vegitName[position]+vegitTv.getText());
            }
        });
        btnReset = (Button)root.findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vegitTv.setText("");
            }
        });

        return root;
    }

}
