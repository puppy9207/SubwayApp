package com.example.puppy.subwayapp;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CustomKind extends Fragment {

    GridView kindListView;
    Spinner kindSpinner;
    TextView tv;

    int kindImg[] = {R.drawable.k1,R.drawable.k2,R.drawable.k3,R.drawable.k4,R.drawable.k5,R.drawable.k6,
            R.drawable.k7,R.drawable.k8,R.drawable.k9,R.drawable.k10,R.drawable.k11,R.drawable.k12,R.drawable.k13,
            R.drawable.k14,R.drawable.k15,R.drawable.k16,R.drawable.k17,R.drawable.k18,R.drawable.k19,R.drawable.k20,
            R.drawable.k21,R.drawable.k22,R.drawable.k23,R.drawable.k24,R.drawable.k25
    };
    String kImgName[] = {"베이컨 에그 & 치즈", "블랙 포레스트햄 에그 & 치즈", "비엘티", "치킨 베이컨 랜치","치킨 데리야끼",
            "에그마요","햄","이탈리안 비엠티","미트볼","풀드 포크","로스트 비프","로스트 치킨","로티세리 치킨", "스파이시 이탈리안",
            "스파이시 이탈리안 아보카도", "스테이크 & 치즈", "스테이크 에그 & 치즈", "써브웨이 클럽", "써브웨이 멜트", "참치","터키","터키 베이컨",
            "터키 베이컨 아보카도", "베지", "웨스턴 에그 & 치즈"
    };

    public CustomKind() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_custom_kind, container, false);
        kindListView = (GridView)root.findViewById(R.id.kindList);
        MyAdapter gridAdapter = new MyAdapter(getContext(),R.layout.row,kindImg);
        tv = (TextView)root.findViewById(R.id.kindTv);
        kindListView.setAdapter(gridAdapter);
        kindListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv.setText(kImgName[position]); // 디비에 보낼 상품 이름
            }
        });
        return root;
    }
    class MyAdapter extends BaseAdapter {
        Context context;
        int layout;
        int img[];
        LayoutInflater inf;

        public MyAdapter(Context context, int layout, int[] img) {
            this.context = context;
            this.layout = layout;
            this.img = img;
            inf = (LayoutInflater) context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return img.length;
        }

        @Override
        public Object getItem(int position) {
            return img[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView==null)
                convertView = inf.inflate(layout, null);
            ImageView ib = (ImageView) convertView.findViewById(R.id.imgButton);
            ib.setImageResource(img[position]);

            return convertView;
        }
    }

}
