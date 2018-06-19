package com.example.puppy.subwayapp;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.puppy.subwayapp.task.TaskGet;
import com.example.puppy.subwayapp.vo.ClientVO;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Path;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyInfo extends Fragment {

    TextView myInfoId,myInfoName,myInfoAddr,myInfoTel,myInfoPoint,myInfoAge;
    Activity activity;
    public MyInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_my_info, container, false);
        activity = getActivity();
        ClientVO vo = getClientInfo();

        myInfoId = (TextView)root.findViewById(R.id.myInfoId);
        myInfoName = (TextView)root.findViewById(R.id.myInfoName);
        myInfoAddr= (TextView)root.findViewById(R.id.myInfoAddr);
        myInfoTel = (TextView)root.findViewById(R.id.myInfoTel);
        myInfoPoint = (TextView)root.findViewById(R.id.myInfoPoint);
        myInfoAge = (TextView)root.findViewById(R.id.myInfoAge);

        //setText로 보여주기만 하면됩니다

        if(getClientInfo() != null)
        {
            myInfoId.setText(vo.getId());
            myInfoName.setText(vo.getName());
            myInfoAddr.setText(vo.getAddress());
            myInfoTel.setText(vo.getTel());
            myInfoPoint.setText(""+vo.getPoint());
            myInfoAge.setText(""+vo.getAge());
        }

        return root;
    }


    /**
     * 로그인 되있는지 안되있는지를 확인하고 로그인 되어있으면 clientVO를 웹이서 가져오는 함수
     * @return
     */
    private ClientVO getClientInfo()
    {
        SharedPreferences setting;
        String jwt;
        if(isLogedin())
        {
            setting = activity.getSharedPreferences("login",0);
            jwt = setting.getString("JWT","");
            return sendData(jwt);
        }else{
            return null;
        }
    }


    /**
     * 사용자가  로그인 했는지 안했는지 확인
     * @return   로그인 되있으면 true 없으면 false;
     */
    private boolean isLogedin()
    {
        SharedPreferences setting = null;
        try{
            setting = activity.getSharedPreferences("login",0);
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
            return true;
        }
    }

    /**
     * 웹으로 JWT를 보내고 Client 데이터 가져오는 함수
     * @param jwt 내 디바이스에서 가져온 jwt
     */
    private ClientVO sendData(String jwt)
    {
        TaskGet task = new TaskGet("api/getClientInfo.json","?jwt="+jwt);
        ObjectMapper mapper = new ObjectMapper();
        try{
            String result = task.execute().get();

           return  mapper.readValue(result,ClientVO.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
