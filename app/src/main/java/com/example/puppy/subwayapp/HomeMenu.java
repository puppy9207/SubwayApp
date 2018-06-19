package com.example.puppy.subwayapp;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeMenu extends Fragment {
    ImageView img1;
    ImageView img6;
    LinearLayout map;
    LinearLayout myMenuLayout;
    LinearLayout noticeLayout;
    LinearLayout myInfoLayout;
    LinearLayout basicLayout;

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
        myInfoLayout = (LinearLayout)root.findViewById(R.id.myInfoLayout);
        basicLayout = (LinearLayout)root.findViewById(R.id.defaultMenuLayout);

        myMenuLayout.setOnClickListener(v->{
            //고객 정보가 없으면 넘어가지 않고 Toast를 띄움 띄우고 로그인 화면으로 다시 넘어감.
            if (isLogedin()) {
                MainActivity activity = (MainActivity) getActivity();
                activity.onFragmentChanged("MyMenu");
            }else {
                MainActivity activity = (MainActivity) getActivity();
                activity.onFragmentChanged("TaskLogin");
            }
        });

        basicLayout.setOnClickListener(v -> {
            MainActivity activity = (MainActivity)getActivity();
            activity.onFragmentChanged("BasicMenu");

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

        myInfoLayout.setOnClickListener(v -> {
            if (isLogedin()) {
                MainActivity activity = (MainActivity) getActivity();
                activity.onFragmentChanged("MyInfo");
            }else {
                MainActivity activity = (MainActivity) getActivity();
                activity.onFragmentChanged("TaskLogin");
            }
        });
        return root;
    }

    private boolean isLogedin()
    {
        SharedPreferences setting = null;
        try{
            setting = getActivity().getSharedPreferences("login",0);
        }catch(NullPointerException e){
            e.printStackTrace();
            Log.e("로그인 에러", "로그인 안되있음 - 익셉션 핸들링 완료;");
            setting = null;
            return false;
        }
        if(setting.getString("JWT","notExist").equals("notExist"))
        {
            Toast.makeText(getActivity(), "로그인 안되있음", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
    }
}
