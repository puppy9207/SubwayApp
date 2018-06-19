package com.example.puppy.subwayapp;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.puppy.subwayapp.task.TaskGet;
import com.example.puppy.subwayapp.vo.ClientVO;
import com.example.puppy.subwayapp.vo.CustomVO;
import com.example.puppy.subwayapp.vo.MyListVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyMenu extends Fragment {

    Button btn4;
    ListView myList;
    MyMenuAdapter adapter;
    Activity activity;

    public MyMenu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        String kImgName[] = {
                "베이컨 에그 & 치즈", "블랙 포레스트햄 에그 & 치즈", "비엘티", "치킨 베이컨 랜치","치킨 데리야끼",
                "에그마요","햄","이탈리안 비엠티","미트볼","풀드 포크","로스트 비프","로스트 치킨","로티세리 치킨", "스파이시 이탈리안",
                "스파이시 이탈리안 아보카도", "스테이크 & 치즈", "스테이크 에그 & 치즈", "써브웨이 클럽", "써브웨이 멜트", "참치","터키","터키 베이컨",
                "터키 베이컨 아보카도", "베지", "웨스턴 에그 & 치즈"
        };

        int kindImg[] = {
                R.drawable.k1 ,R.drawable.k2 ,R.drawable.k3 ,R.drawable.k4 ,R.drawable.k5 ,R.drawable.k6 ,
                R.drawable.k7 ,R.drawable.k8 ,R.drawable.k9 ,R.drawable.k10,R.drawable.k11,R.drawable.k12,R.drawable.k13,
                R.drawable.k14,R.drawable.k15,R.drawable.k16,R.drawable.k17,R.drawable.k18,R.drawable.k19,R.drawable.k20,
                R.drawable.k21,R.drawable.k22,R.drawable.k23,R.drawable.k24,R.drawable.k25
        };


        int[] title = {1,2,3,4,5};//DB에서 가져온 customId 이거 디비에서 받아오면 됨
        String[] kind = {"스파이시 이탈리안 아보카도","스파이시 이탈리안 아보카도","써브웨이 클럽","터키","로스트 비프"};//이거 디비에서 받아오고

        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_my_menu, container, false);

        adapter = new MyMenuAdapter();

        myList = (ListView)root.findViewById(R.id.myList);
        btn4   = (Button)root.findViewById(R.id.button4);

        int a[] = new int[title.length];
        for (int i=0;i<title.length;i++){
            for(int j=0;j<kind.length;j++){
                for(int w=0;w<kImgName.length;w++){
                    if (kind[j].equals(kImgName[w])){
                        a[j]=w; //여기에서 문자열 비교해서 포지션값 넘겨주는거임
                    }
                }
            }
            adapter.addItem(new CustomVO(title[i],kind[i]),kindImg[a[i]]);
        }
        myList.setAdapter(adapter);
        myList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //삭제 로직
                return false;
            }
        });
        btn4.setOnClickListener(v -> {
            Intent intent=new Intent(getActivity(),CustomActivity.class);
            startActivity(intent);
        });

        return root;
    }

    public class MyMenuAdapter extends BaseAdapter
    {
        ArrayList<CustomVO> items = new ArrayList<>();
        ArrayList<Integer> rePosition = new ArrayList<>();
        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(CustomVO vo,int resId){
            items.add(vo);
            rePosition.add(resId);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ListMyView view = new ListMyView(getActivity());
            CustomVO vo = items.get(position);
            int a = rePosition.get(position);
            view.setMyTitle(vo.getCustom_id());
            view.setMyValue(vo.getName());
            view.setMyImg(a);

            return view;
        }
    }

    /**
     * 로그인 되있는지 안되있는지를 확인하고 로그인 되어있으면 clientVO를 웹이서 가져오는 함수
     * @return
     */
    private List<CustomVO> getMyMenu()
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
     * 웹으로 JWT를 보내고 Custom 데이터 가져오는 함수
     * @param jwt 내 디바이스에서 가져온 jwt
     * @return  내가 가진 customList들
     */
    private List<CustomVO> sendData(String jwt)
    {
        TaskGet task = new TaskGet("api/selectCustom.json","?jwt="+jwt);
        ObjectMapper mapper = new ObjectMapper();
        TypeFactory typeFactory = mapper.getTypeFactory();

        try{
            String result = task.execute().get();
            mapper.readValue(result,CustomVO.class);

            return null;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
