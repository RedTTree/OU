package com.example.z_shenou;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class Userdetial extends BmobObject implements Serializable{

	//一卡通
private BmobFile icon_id;
    //电话
private String telephone;
    //院系
private String college;
   //对应用户
private MyUser id_user;
    //性别
private String sex;
    //学号
private String student_number;
    //真实姓名
private String real_name;
//是否通过验证
private Boolean isTrue;
//藕片数
private int OU_number;


public String getReal_name(){
	return real_name;
}

public String getTelephone(){
return telephone;
}

public String getCollege(){
return college;
}

public MyUser getId(){
	return this.id_user;
}

public String getSex(){
return sex;
}

public String getStudent_number(){
return student_number;
}
public BmobFile getIcon_id() {
	return icon_id;
}
public Boolean getIsTrue(){
	return isTrue;
}
public int getOU_number(){
	return this.OU_number;
}


public void setReal_name(String initialReal_name){
	this.real_name=initialReal_name;
}
public void setTelephone(String initialtelephone){
telephone=initialtelephone;
}

public void setCollege(String initialcollege){
college=initialcollege;
}
public void setIsTrue(Boolean initialisTrue){
	this.isTrue=initialisTrue;
}
public void setUser(MyUser initialid_user){
this.id_user=initialid_user;
}

public void setSex(String initialsex){
sex=initialsex;
}

public void setStudent_number(String initialstudent_number){
student_number=initialstudent_number;
}

public void setIcon_id(BmobFile icon) {
	this.icon_id = icon;
}
public void setOU_number(int initialOU_number){
	this.OU_number=initialOU_number;
}

}

