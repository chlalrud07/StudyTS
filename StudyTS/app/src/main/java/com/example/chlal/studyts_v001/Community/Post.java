package com.example.chlal.studyts_v001.Community;

public class Post {
    private String title;
    private String detail;

    public Post(String title, String detail) {
        this.title = title;
        this.detail = detail;
    }
    public String getTitle() {return title;}
    public String getDetail() {return detail;}
    public void setTitle(String title) {this.title = title;}
    public void setDetail(String detail) {this.detail = detail;}
}
