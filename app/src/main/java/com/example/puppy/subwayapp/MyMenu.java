package com.example.puppy.subwayapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyMenu extends Fragment {

    Button btn4;

    public MyMenu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_my_menu, container, false);
        btn4 = root.findViewById(R.id.button4);
        btn4.setOnClickListener(v -> {
            MainActivity activity = (MainActivity)getActivity();
            activity.onFragmentChanged("CustomMenuRegit");
        });

        return root;
    }

}
