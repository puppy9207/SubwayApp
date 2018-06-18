package com.example.puppy.subwayapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.puppy.subwayapp.vo.MyListVO;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyMenu extends Fragment {

    Button btn4;
    ListView myList;
    MyMenuAdapter adapter;


    public MyMenu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String kImgName[] = {"베이컨 에그 & 치즈", "블랙 포레스트햄 에그 & 치즈", "비엘티", "치킨 베이컨 랜치","치킨 데리야끼",
                "에그마요","햄","이탈리안 비엠티","미트볼","풀드 포크","로스트 비프","로스트 치킨","로티세리 치킨", "스파이시 이탈리안",
                "스파이시 이탈리안 아보카도", "스테이크 & 치즈", "스테이크 에그 & 치즈", "써브웨이 클럽", "써브웨이 멜트", "참치","터키","터키 베이컨",
                "터키 베이컨 아보카도", "베지", "웨스턴 에그 & 치즈"
        };
        int kindImg[] = {R.drawable.k1,R.drawable.k2,R.drawable.k3,R.drawable.k4,R.drawable.k5,R.drawable.k6,
                R.drawable.k7,R.drawable.k8,R.drawable.k9,R.drawable.k10,R.drawable.k11,R.drawable.k12,R.drawable.k13,
                R.drawable.k14,R.drawable.k15,R.drawable.k16,R.drawable.k17,R.drawable.k18,R.drawable.k19,R.drawable.k20,
                R.drawable.k21,R.drawable.k22,R.drawable.k23,R.drawable.k24,R.drawable.k25
        };


        String[] title = {"id1","id2","id3","id4","id5"};//DB에서 가져온 customId
        String[] kind = {"스파이시 이탈리안 아보카도","스파이시 이탈리안 아보카도","써브웨이 클럽","터키","로스트 비프"};//Custom\d
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_my_menu, container, false);
        btn4 = root.findViewById(R.id.button4);
        adapter = new MyMenuAdapter();
        myList = (ListView)root.findViewById(R.id.myList);
        int a[] = new int[title.length];
        for (int i=0;i<title.length;i++){
            for(int j=0;j<kind.length;j++){
                for(int w=0;w<kImgName.length;w++){
                    if (kind[j].equals(kImgName[w])){
                        a[j]=w;
                    }
                }
            }
            adapter.addItem(new MyListVO(title[i],kind[i],kindImg[a[i]]));
        }
        myList.setAdapter(adapter);

        btn4.setOnClickListener(v -> {
            Intent intent=new Intent(getActivity(),CustomActivity.class);
            startActivity(intent);
        });

        return root;
    }

    public class MyMenuAdapter extends BaseAdapter{
        ArrayList<MyListVO> items = new ArrayList<MyListVO>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(MyListVO vo){
            items.add(vo);
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
            MyListVO vo = items.get(position);
            view.setMyTitle(vo.getTitle());
            view.setMyValue(vo.getName());
            view.setMyImg(vo.getResId());

            return view;
        }
    }

}
