package com.example.z_shenou;

import cn.bmob.v3.BmobObject;

public class Expressage extends BmobObject{

	private String express_company;
	private String size;
	private String pulish_name;
    private String telephone;
    private String content;
    private String site;
    //发布者
    private MyUser pulish_user;
    //接收者
    private MyUser jieshou_user;
    //是否被接
    private Boolean isjie;
    //是否完成
    private Boolean isfinish;
    //价格
    private Integer price;
    

     
	 public String getExpress_company(){
	return express_company;
	}

	public String getSize(){
	return size;
	}

	public String getTelephone(){
	return telephone;	
	}
	
	public String getContent(){
	return content;
	}
	
	public String getpulish_name(){
		return pulish_name;
		}
		
	
	public String getSite(){
	return site;	
	}
	public MyUser getPulish(){
		return this.pulish_user;
	}
	public MyUser getJieshou(){
		return this.jieshou_user;
	}
	public Boolean getIsjie(){
		return this.isjie;
	}
	public Boolean getIsfinish(){
		return this.isfinish;
	}
	public Integer getPrice(){
		return this.price;
	}
	

	
	public void setExpress_company(String initialExpress_company){
	this.express_company=initialExpress_company;
	}

	public void setSize(String initialsize){
	this.size=initialsize;
	}

	public void setTelephone(String initialtelephone){
		this.telephone=initialtelephone;
	}

	public void setContent(String initialcontent){
		this.content=initialcontent;
	}
	
	public void setSite(String initialsite){
		this.site=initialsite;
	}
	public void setPulish(MyUser initialpulish_user){
	this.pulish_user=initialpulish_user;
	}
	public void setJieshou(MyUser initialjieshou_user){
		this.jieshou_user=initialjieshou_user;
	}
	public void setIsjie(Boolean initialisjie){
		this.isjie=initialisjie;
	}
	public void setIsfinish(Boolean initialfinish){
		this.isfinish=initialfinish;
	}
	public void setPrice(Integer initialprice){
		this.price=initialprice;
	}

	public void setpulish_name(String initialpulish_name){
		this.pulish_name=initialpulish_name;
		}
}