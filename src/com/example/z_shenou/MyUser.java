 package com.example.z_shenou;

import java.io.Serializable;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

public class MyUser extends BmobUser implements Serializable{
	
	private BmobFile icon;
 
    

public BmobFile getIcon() {
		return icon;
	}



public void setIcon(BmobFile icon) {
		this.icon = icon;
	}
	
}
