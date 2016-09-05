package com.example.z_shenou;

import java.io.Serializable;

public class Items implements Serializable{
    //新闻标题，内容，图片
    private String name;
    private String content;
    private int userhead;

    /**
     * Constructs a new instance of {@code Object}.
     */
    public Items(String name, String content, int userhead) {
        this.name=name;
        this.content=content;
        this.userhead=userhead;
    }

    public void setcontent(String content) {
        this.content = content;
    }

    public void setname(String name) {
        this.name = name;
    }

    public void setuserhead(int userhead) {
        this.userhead = userhead;
    }

    public String getcontent() {
        return content;
    }

    public int getuserhead() {
        return userhead;
    }

    public String getname() {
        return name;
    }
}
