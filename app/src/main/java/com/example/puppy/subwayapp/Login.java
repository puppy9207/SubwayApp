package com.example.puppy.subwayapp;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment {
    Button  signIn,unLogin,signUp;

    public Login() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_login, container, false);
        unLogin = (Button)root.findViewById(R.id.button2);
        signIn = (Button)root.findViewById(R.id.button);
        signUp = (Button)root.findViewById(R.id.button6);
        unLogin.setOnClickListener(v->{

            MainActivity activity = (MainActivity)getActivity();
            activity.onFragmentChanged("HomeMenu");

        });
        signIn.setOnClickListener(v -> {
           /*DB id, password
           * 1. id있는지 없는지 검증
           * 2. password 검증
           * */

        });
        signUp.setOnClickListener(v->{

            MainActivity activity = (MainActivity)getActivity();
            activity.onFragmentChanged("SignUp");

        });

        return root;
    }

}
