package com.example.puppy.subwayapp;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.puppy.subwayapp.vo.CustomVO;


/**
 * A simple {@link Fragment} subclass.
 */
public class CustomView extends Fragment {

    String name,bread,cheese,sauce,excluedVegit,addition;
    int price, imgSrc;

    public CustomView() {
        // Required empty public constructor
    }

    public void setInfo(CustomVO vo, int position){
        this.name = vo.getName();
        this.bread = vo.getBread();
        this.cheese = vo.getCheese();
        this.sauce = vo.getSauce();
        this.excluedVegit = vo.getExcludeVegit();
        this.addition = vo.getAddition();
        this.price = vo.getPrice();
        this.imgSrc = position;
    }
    TextView order,orderPrice;
    ImageView imgView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int kindImg[] = {R.drawable.k1,R.drawable.k2,R.drawable.k3,R.drawable.k4,R.drawable.k5,R.drawable.k6,
                R.drawable.k7,R.drawable.k8,R.drawable.k9,R.drawable.k10,R.drawable.k11,R.drawable.k12,R.drawable.k13,
                R.drawable.k14,R.drawable.k15,R.drawable.k16,R.drawable.k17,R.drawable.k18,R.drawable.k19,R.drawable.k20,
                R.drawable.k21,R.drawable.k22,R.drawable.k23,R.drawable.k24,R.drawable.k25
        };

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_custom_view, container, false);
        imgView = (ImageView)root.findViewById(R.id.imgCustom);
        imgView.setImageResource(kindImg[imgSrc]);
        order = (TextView)root.findViewById(R.id.order);
        orderPrice = (TextView)root.findViewById(R.id.orderPrice);
        orderPrice.setText("커스텀 총 가격 : "+price+"원");
        order.setText("샌드위치 종류는 "+name+"로 해주시고요\n빵은 "+bread+" 해주시고");
        if (!cheese.equals("없음")){
            order.setText(order.getText()+" 치즈는 "+cheese+" 로 해주세요\n");
        }else {
            order.setText(order.getText()+" 치즈는 안 넣을게요\n");
        }
        if(!excluedVegit.equals("없음")){
            order.setText(order.getText()+"뺄 야채는 "+excluedVegit+" 에요\n");
        }else{
            order.setText(order.getText()+"그리고 야채는 다 넣어주세요\n");
        }
        order.setText(order.getText()+"소스는 "+sauce+" 로 해주세요\n");
        if (!addition.equals("없음")){
            order.setText(order.getText()+"추가로 "+addition+" (해)주세요 ");
        }
        return root;
    }
}
