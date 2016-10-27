package com.example.z_shenou;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class Userdetial extends BmobObject{
private BmobFile icon;
private int telephone;
private String college;
private int grade;
private String sex;
private int student_number;


public int getTelephone(){
return telephone;
}

public String getCollege(){
return college;
}

public int getGrade(){
return grade;
}

public String getSex(){
return sex;
}

public int getStudent_number(){
return student_number;
}
public BmobFile getIcon() {
	return icon;
}



public void setTelephone(int initialtelephone){
telephone=initialtelephone;
}

public void setCollege(String initialcollege){
college=initialcollege;
}

public void setGrade(int initialgrade){
grade=initialgrade;
}

public void setSex(String initialsex){
sex=initialsex;
}

public void setStudent_number(int initialstudent_number){
student_number=initialstudent_number;
}

public void setIcon(BmobFile icon) {
	this.icon = icon;
}
}

