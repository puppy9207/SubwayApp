package com.example.puppy.subwayapp.vo;

import com.example.puppy.subwayapp.Notice;

/**
 * Created by Sight on 2018-06-19.
 *
 * 공지사항의 정보를 가지고 있는 VO
 */

public class BbsVO
{
    private int 	bbs_id;				// 게시판 글 번호, pk
    private String  title;				// 제목
    private String  content;			// 내용
    private String  user_id;			// 글쓴이

    // ----------------------- constructor --------------------------------

    public BbsVO() { }

    public BbsVO(String title, String user_id)
    {
        this.title = title;
        this.user_id = user_id;
    }

    public BbsVO(String title, String content, String user_id)
    {
        this.title = title;
        this.content = content;
        this.user_id = user_id;
    }

    // ------------------------- getters ---------------------------
    public int getBbs_id() {
        return bbs_id;
    }
    public void setBbs_id(int bbs_id) {
        this.bbs_id = bbs_id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
