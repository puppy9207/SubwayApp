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
public class CustomAdd extends Fragment {

    public interface CustomAddValue{
        void addValue(String kind, int price);
    }

    private CustomAddValue mCustomAddValue;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity()!=null && getActivity() instanceof CustomAddValue){
            mCustomAddValue = (CustomAddValue)getActivity();
        }
    }

    GridView addList;
    TextView addTv,resultTV;
    Button btnReset1,btnSend;

    int addImg[] = {R.drawable.ad1,R.drawable.ad2,R.drawable.ad3,R.drawable.ad4,R.drawable.ad5,R.drawable.ad6,
            R.drawable.ad7,R.drawable.sd1,R.drawable.sd2,R.drawable.sd3,R.drawable.sd4,R.drawable.sd5,R.drawable.sd6,
            R.drawable.sd7,R.drawable.sd8,R.drawable.sd9,R.drawable.sd10
    };
    String addName[] = {"더블업","에그마요","오믈렛","아보카도","베이컨","페퍼로니","더블치즈",
            "브로콜리 & 체더치즈 스프","베이컨 포테이토 스프","더블 초코칩 쿠키","초코칩 쿠키","오트밀 레이즌",
            "라즈베리 치즈케익","화이트 초코 마카다미아","칩","탄산음료","커피"
    };
    int addPrice[] = {1500,1500,1100,1100,900,800,800,
            2900,2900,1000,1000,1000,
            1000,1000,1000,1500,1500
    };
    int aPrice=0;

    public CustomAdd() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_custom_add, container, false);
        addList = (GridView)root.findViewById(R.id.addList);
        MyAdapter gridAdapter = new MyAdapter(getContext(),R.layout.row,addImg);
        addTv = (TextView)root.findViewById(R.id.addTv);
        addList.setAdapter(gridAdapter);
        btnReset1 = (Button)root.findViewById(R.id.btnReset1);
        btnSend = (Button)root.findViewById(R.id.btnSend);
        resultTV = (TextView)root.findViewById(R.id.resultTV);
        addList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                addTv.setText(" "+addName[position]+addTv.getText()); // 디비에 보낼 상품 이름
                aPrice = addPrice[position]+aPrice;
            }
        });
        String mAdd = addTv.getText().toString();
        if (mCustomAddValue!=null){
            mCustomAddValue.addValue(mAdd,aPrice);
        }
        btnReset1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTv.setText("");
                aPrice = 0;
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomActivity activity = new CustomActivity();
                activity.finish();
                //디비로 값 다 전송하고 버튼 누르면 꺼지게 할라그러는데 안꺼지네
            }
        });

        return root;
    }

}
