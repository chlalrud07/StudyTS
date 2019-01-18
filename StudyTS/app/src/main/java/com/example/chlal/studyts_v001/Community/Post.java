package com.example.chlal.studyts_v001.Community;

public class Post {
    private String pid;
    private String title;
    private String detail;

    public Post(String pid, String title, String detail) {
        this.pid = pid;
        this.title = title;
        this.detail = detail;
    }
    public String getTitle() {return title;}
    public String getDetail() {return detail;}
    public String getPid() {return pid;}
    public void setPid(String pid) {this.pid = pid;}
    public void setTitle(String title) {this.title = title;}
    public void setDetail(String detail) {this.detail = detail;}
}
