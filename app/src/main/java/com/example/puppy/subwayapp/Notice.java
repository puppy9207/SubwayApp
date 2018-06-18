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
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.puppy.subwayapp.vo.ListVO;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Notice extends Fragment {

    ListView noticeList;
    NoticeAdapter adapter;

    public Notice() {
        // Required empty public constructor
    }

    public static interface TextSendCall{
        public void noticePrintText(String title,String context,String author);
    }

    public TextSendCall callback;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof TextSendCall){
            callback = (TextSendCall)context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String [] title = {"A","B","C","D"}; // 디비에서 받아올  작성글 제목
        String [] author = {"z","x","y","u"}; // 디비에서 받아올  작성글 작성자
        String [] context = {"가나다라","마바사아","자차카타","파하파하"}; // 디비에서 받아올 작성글 내용
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_notice, container, false);

        noticeList = (ListView) root.findViewById(R.id.noticeList);
        adapter = new NoticeAdapter();

        for(int i=0;i<title.length;i++) {
            adapter.addItem(new ListVO(title[i],author[i]));
        }
        noticeList.setAdapter(adapter);

        noticeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callback.noticePrintText(title[position],context[position],author[position]);
                //여기서 프래그먼트로 전달.
                MainActivity activity = (MainActivity)getActivity();
                activity.onFragmentChanged("NoticeT");
            }
        });
        return root;
    }

    class NoticeAdapter extends BaseAdapter{
        ArrayList<ListVO> items = new ArrayList<ListVO>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(ListVO list){
            items.add(list);
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
            ListItemView view = new ListItemView(getActivity());
            ListVO vo = items.get(position);
            view.setAuthor(vo.getAuthor());
            view.setTitle(vo.getTitle());
            return view;
        }
    }


}
