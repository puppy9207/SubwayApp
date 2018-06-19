package com.example.puppy.subwayapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyInfo extends Fragment {

    TextView myInfoId,myInfoName,myInfoAddr,myInfoTel,myInfoPoint,myInfoAge;

    public MyInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_my_info, container, false);
        myInfoId = (TextView)root.findViewById(R.id.myInfoId);
        myInfoName = (TextView)root.findViewById(R.id.myInfoName);
        myInfoAddr= (TextView)root.findViewById(R.id.myInfoAddr);
        myInfoTel = (TextView)root.findViewById(R.id.myInfoTel);
        myInfoPoint = (TextView)root.findViewById(R.id.myInfoPoint);
        myInfoAge = (TextView)root.findViewById(R.id.myInfoAge);

        //setText로 보여주기만 하면됩니다
        return root;
    }

}
