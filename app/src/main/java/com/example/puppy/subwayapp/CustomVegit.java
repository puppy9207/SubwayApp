package com.example.puppy.subwayapp;


import android.content.Context;
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
    String vName;

    int vegitImg[] = {R.drawable.notsel,R.drawable.v1,R.drawable.v2,R.drawable.v3,R.drawable.v4,R.drawable.v5,R.drawable.v6,R.drawable.v7,R.drawable.v8,R.drawable.v9};
    String vegitName [] = {null,"양상추","토마토","오이","피망","양파","피클","올리브","할라피뇨","아보카도"};

    public static interface TextSendCall{
        public void vPrintText(String bInput);
    }

    public TextSendCall callback;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof TextSendCall){
            callback = (TextSendCall)context;
        }
    }

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
        vegitTv.setText(vName);
        vegitList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0) {
                    vegitTv.setText(" " + vegitName[position] + vegitTv.getText());
                    vName = vegitTv.getText().toString();
                }else{
                    vegitTv.setText("없음");
                    vName = vegitTv.getText().toString();
                }
                callback.vPrintText(vegitTv.getText().toString());
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
