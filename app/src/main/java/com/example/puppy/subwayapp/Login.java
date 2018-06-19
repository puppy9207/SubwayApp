package com.example.puppy.subwayapp;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.puppy.subwayapp.task.TaskLogin;

import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment {
    Button  signIn,unLogin,signUp;
    EditText loginid,loginpw;
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
        MainActivity activity = (MainActivity)getActivity();

        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_login, container, false);
        unLogin = (Button)root.findViewById(R.id.button2);
        signIn  = (Button)root.findViewById(R.id.button);
        signUp  = (Button)root.findViewById(R.id.button6);
        loginid = (EditText) root.findViewById(R.id.loginid);
        loginpw = (EditText) root.findViewById(R.id.loginpw);

        // ----------------------------------------------btn eventListener -----------------------------------------

        unLogin.setOnClickListener(v->
        {
            activity.onFragmentChanged("HomeMenu");
        });

        signIn.setOnClickListener(v ->
        {
           /*DB id, password
           * 1. id있는지 없는지 검증
           * 2. password 검증
           * */
            TaskLogin.DoLogin login = new TaskLogin.DoLogin("/api/login.json",loginid.getText().toString(),loginpw.getText().toString());
            try {
                String loginResult = login.execute().get();
                if(loginResult != null)
                {
                    saveJWT(loginResult);
                    activity.onFragmentChanged("HomeMenu");
                    Toast.makeText(activity, "로그인 성공", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(activity, "로그인 실패", Toast.LENGTH_SHORT).show();
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        signUp.setOnClickListener(v->
        {
            activity.onFragmentChanged("SignUp");

        });

        return root;
    }


    /**
     * 프레그먼트 시작시 로그인 되어있는지 확인
     */
    @Override
    public void onStart() {
        super.onStart();

        if(isLogedin())             // 사용자가 기존에 로그인 했으면 바로 메뉴로
        {
            unLogin.performClick();
        }
    }

    /**
     *  가져온 JWT 토큰을 SharedPreferences 영역에 저장한다.
     */
    private void saveJWT(String JWT)
    {
        Log.i("저장할 JWT",JWT);
        SharedPreferences setting = getActivity().getSharedPreferences("login",0);
        SharedPreferences.Editor editor = setting.edit();;

        editor.putString("JWT",JWT);
        editor.commit();    // commit 은 동기 apply는 비동기 방식
    }

    /**
     * 사용자가 이전에 미리 로그인 했는지 안했는지 확인
     * @return
     */
    private boolean isLogedin()
    {
        SharedPreferences setting = null;
        try{
            setting = getActivity().getSharedPreferences("login",0);
        }catch(NullPointerException e){
            Log.e("로그인 에러", "로그인 안되있음 - 익셉션 핸들링 완료;");
            setting = null;
            return false;
        }

        if(setting.getString("JWT","notExist").equals("notExist"))
        {
            Toast.makeText(getActivity(), "로그인 안되있음", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            Toast.makeText(getActivity(), "로그인 성공", Toast.LENGTH_SHORT).show();
            return true;
        }
    }


}
