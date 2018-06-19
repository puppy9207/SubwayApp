package com.example.puppy.subwayapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MyList extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);
    }
}
