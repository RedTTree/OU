package com.example.z_shenou;

import java.io.Serializable;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

public class MyUser extends BmobUser implements Serializable{
	
	private BmobFile icon;
	private String sex;  		// �Ա�
	private String phone; 		// �绰
	private String school;   // ѧԺ
	
	public BmobFile getIcon() {
		return icon;
	}
	public String getSex(){
		return this.sex;
	}
	public String getSchool(){
		return this.sex;
	}
	public String getPhone(){
		return this.sex;
	}
	public void setSex(String sex){
		this.sex=sex;
	}
	public void setSchool(String school){
		this.school=school;
	}
	public void setPhone(String phone){
		this.phone=phone;
	}
	public void setIcon(BmobFile icon) {
		this.icon = icon;
	}
	
}
