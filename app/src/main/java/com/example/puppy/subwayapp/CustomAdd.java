package com.example.puppy.subwayapp;


import android.app.AlertDialog;
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
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class CustomAdd extends Fragment {

    int kiPrice;
    int resultPrice;
    String kiName="",bName="",cName="",sName="",vName="",aName="";

    GridView addList;
    TextView addTv;
    Button btnReset1,btnSend;

    public void kShowText(String input, String kPrice){
        kiName = input;
        kiPrice = Integer.parseInt(kPrice);
    }
    public void bShowText(String input){
        bName = input;
    }
    public void cShowText(String input){
        cName = input;
    }
    public void sShowText(String input){
        sName = input;
    }
    public void vShowText(String input){
        vName = input;
    }

    int addImg[] = {R.drawable.notsel,R.drawable.ad1,R.drawable.ad2,R.drawable.ad3,R.drawable.ad4,R.drawable.ad5,R.drawable.ad6,
            R.drawable.ad7,R.drawable.sd1,R.drawable.sd2,R.drawable.sd3,R.drawable.sd4,R.drawable.sd5,R.drawable.sd6,
            R.drawable.sd7,R.drawable.sd8,R.drawable.sd9,R.drawable.sd10
    };
    String addName[] = {null,"더블업","에그마요","오믈렛","아보카도","베이컨","페퍼로니","더블치즈",
            "브로콜리 & 체더치즈 스프","베이컨 포테이토 스프","더블 초코칩 쿠키","초코칩 쿠키","오트밀 레이즌",
            "라즈베리 치즈케익","화이트 초코 마카다미아","칩","탄산음료","커피"
    };
    int addPrice[] = {0,1500,1500,1100,1100,900,800,800,
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
        kiPrice = kiPrice;
        addTv.setText(aName);

        addList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0) {
                    if(aName.equals("없음")||aName.equals("")){
                        addTv.setText("");
                        addTv.setText(" " + addName[position] + addTv.getText()); // 디비에 보낼 상품 이름
                        aPrice = addPrice[position] + aPrice;
                        aName = addTv.getText().toString();
                    }else{
                        addTv.setText(" " + addName[position] + addTv.getText()); // 디비에 보낼 상품 이름
                        aPrice = addPrice[position] + aPrice;
                        aName = addTv.getText().toString();
                    }


                }else{
                    addTv.setText("없음");
                    aName = addTv.getText().toString();
                    aPrice =0;
                }
            }
        });

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
                resultPrice = aPrice+kiPrice;
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                if(!kiName.isEmpty()&&!bName.isEmpty()&&!cName.isEmpty()&&!sName.isEmpty()&&
                        !vName.isEmpty()&&!aName.isEmpty())
                {
                    builder.setMessage("샌드위치 종류는 " + kiName + " 빵은 " + bName + " 치즈는 " + cName + " 소스는 " + sName +
                            " 뺄 야채는 " + vName  + " 추가상품 " + aName + " 그리고 가격은 " + resultPrice)
                            .setPositiveButton("확인", null).setNegativeButton("취소", null).setTitle("등록하시겠습니까?").show();
                }
                else{
                    Toast.makeText(getActivity(),"하나는 무조건 고르셔야 합니다",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }

}
