package com.example.puppy.subwayapp;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeMenu extends Fragment {
    ImageView img1;
    ImageView img6;
    LinearLayout map;
    LinearLayout myMenuLayout;
    LinearLayout noticeLayout;

    public HomeMenu()
    {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home_menu, container, false);

        img1 = (ImageView) root.findViewById(R.id.imageView2);
        myMenuLayout = (LinearLayout)root.findViewById(R.id.myMenuLayout);
        img6 = (ImageView)root.findViewById(R.id.imageView6);
        map = (LinearLayout)root.findViewById(R.id.findLayout);
        noticeLayout = (LinearLayout)root.findViewById(R.id.noticeLayout);


        myMenuLayout.setOnClickListener(v->{
            //고객 정보가 없으면 넘어가지 않고 Toast를 띄움 띄우고 로그인 화면으로 다시 넘어감.
            MainActivity activity = (MainActivity)getActivity();
            activity.onFragmentChanged("MyMenu");

        });

        // test
        map.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),Map.class);
            startActivity(intent);
        });

        noticeLayout.setOnClickListener(v -> {
            MainActivity activity = (MainActivity)getActivity();
            activity.onFragmentChanged("Notice");
        });
        return root;
    }

}
