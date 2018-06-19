package com.example.puppy.subwayapp;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.puppy.subwayapp.task.TaskGet;
import com.example.puppy.subwayapp.vo.BbsVO;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


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
        public void noticePrintText(BbsVO vo);
    }

    public TextSendCall callback;

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if(context instanceof TextSendCall)
        {
            callback = (TextSendCall)context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        List<BbsVO> notices = getNoticeList();  // 여기서 받아옴
        String [] title = {"A","B","C","D"};                            // 디비에서 받아올  작성글 제목
        String [] author = {"z","x","y","u"};                           // 디비에서 받아올  작성글 작성자
        String [] context = {"가나다라","마바사아","자차카타","파하파하"};  // 디비에서 받아올 작성글 내용
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_notice, container, false);
        noticeList = (ListView) root.findViewById(R.id.noticeList);

        adapter = new NoticeAdapter();

        notices.forEach(adapter::addItem);

//        for(int i=0;i<title.length;i++)     // adapter에 삽입
//        {
//            adapter.addItem(new BbsVO(title[i],author[i]));
//        }

        noticeList.setAdapter(adapter);
        noticeList.setOnItemClickListener((parent, view, position, id) ->
        {
            //callback.noticePrintText(title[position],context[position],author[position]);
            callback.noticePrintText(notices.get(position));
            //여기서 프래그먼트로 전달.
            MainActivity activity = (MainActivity)getActivity();
            activity.onFragmentChanged("NoticeT");
        });
        return root;
    }

    class NoticeAdapter extends BaseAdapter
    {
        ArrayList<BbsVO> items = new ArrayList<BbsVO>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(BbsVO list){
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
            BbsVO vo = items.get(position);
            view.setAuthor(vo.getUser_id());
            view.setTitle(vo.getTitle());
            return view;
        }
    }


    /**
     * 웹서버 DB로부터 공지사항 리스트를 가져오는 메서드
     * @return
     */
    private ArrayList<BbsVO> getNoticeList()
    {
        TaskGet task = new TaskGet("api/getNoticeList.json","");
        ArrayList<BbsVO> list;
        ObjectMapper mapper     = new ObjectMapper();
        TypeFactory typeFactory = mapper.getTypeFactory();

        try {
            String result = task.execute().get();
            list =  mapper.readValue(result,typeFactory.constructCollectionType(ArrayList.class, BbsVO.class));
            return list;
        } catch (InterruptedException | ExecutionException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
