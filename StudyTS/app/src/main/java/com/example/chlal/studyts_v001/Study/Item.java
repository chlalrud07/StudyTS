package com.example.chlal.studyts_v001.Study;

public class Item {
    private String iid;
    private String title;
    private String detail;

    public Item(String pid, String title, String detail) {
        this.iid = pid;
        this.title = title;
        this.detail = detail;
    }
    public String getTitle() {return title;}
    public String getDetail() {return detail;}
    public String getPid() {return iid;}
    public void setPid(String iid) {this.iid = iid;}
    public void setTitle(String title) {this.title = title;}
    public void setDetail(String detail) {this.detail = detail;}
}
