package com.example.z_shenou;

import cn.bmob.v3.BmobObject;

public class Successexpress extends BmobObject{
	private int EX_ID;//(快递编号)
	private int AC_ID;//(快递代收者编号)
	private int RE_ID;//（快递发布者编号）

	public int getEX_ID(){
	return EX_ID;
	}

	public int getAC_ID(){
	return AC_ID;
	}

	public int getRE_ID(){
	return RE_ID;
	}


	public void setRE_ID(int initialRE_ID){
	RE_ID=initialRE_ID;
	}

	public void setAC_ID(int initialAC_ID){
	AC_ID=initialAC_ID;
	}


	public void setEX_ID(int initialEX_ID){
	EX_ID=initialEX_ID;
	}
	}
