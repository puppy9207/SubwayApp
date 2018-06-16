package com.example.puppy.subwayapp;


import android.content.Context;
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
public class CustomCheese extends Fragment {

    GridView cheeseList;
    TextView cheeseTv;
    String cName="";
    int cheeseImg[] = {R.drawable.notsel,R.drawable.c1,R.drawable.c2};
    String cheeseName[] = {null,"아메리칸 치즈","슈레드 치즈"};

    public static interface TextSendCall{
        public void cPrintText(String bInput);
    }

    public TextSendCall callback;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof TextSendCall){
            callback = (TextSendCall)context;
        }
    }

    public CustomCheese() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_custom_cheese, container, false);
        cheeseList = (GridView)root.findViewById(R.id.cheeseList);
        MyAdapter gridAdapter = new MyAdapter(getContext(),R.layout.row,cheeseImg);
        cheeseTv = (TextView)root.findViewById(R.id.cheeseTv);
        cheeseList.setAdapter(gridAdapter);
        cheeseTv.setText(cName);
        cheeseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    if(cName.equals("없음")||cName.equals("")){
                        cheeseTv.setText("");
                        cheeseTv.setText(cheeseName[position]);
                        cName = cheeseTv.getText().toString();
                    }else{
                        cheeseTv.setText(cheeseName[position]);
                        cName = cheeseTv.getText().toString();
                    }

                }
                else{
                    cheeseTv.setText("없음");
                    cName = cheeseTv.getText().toString();
                }

                callback.cPrintText(cheeseTv.getText().toString());// 디비에 보낼 상품 이름
            }
        });

        return root;
}

}
