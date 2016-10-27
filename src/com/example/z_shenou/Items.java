package com.example.z_shenou;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

public class Items extends BmobObject{
    //标题，内容，图片
	private Integer number;
    private String name;
    private String content;
    private Integer userhead;

    /**
     * Constructs a new instance of {@code Object}.
     */
    public Items(Integer number,String name, String content, int userhead) {
        this.name=name;
        this.content=content;
        this.userhead=userhead;
        this.number=number;
    }
    public Items() {
		// TODO 自动生成的构造函数存根
	}
	public void setnumber(Integer number){
    	this.number=number;
    }
    public void setcontent(String content) {
        this.content = content;
    }

    public void setname(String name) {
        this.name = name;
    }

    public void setuserhead(Integer userhead) {
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
