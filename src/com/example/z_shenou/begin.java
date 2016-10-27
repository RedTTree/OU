package com.example.z_shenou;

import java.util.Timer;
import java.util.TimerTask;

import com.example.z_shenou.R;
import com.example.z_shenou.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import cn.bmob.v3.Bmob;


public class begin extends Activity {

	 //OU应用密钥
    private String Bmob_AppId = "9bdf475e44f7c755ed65784f9b84739f";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);	
		final Intent it=new Intent(this,MainActivity.class);
		Timer timer = new Timer(); 
	
		// 初始化 Bmob SDK
				Bmob.initialize(this, Bmob_AppId);
		TimerTask task = new TimerTask() {  
		    @Override  
		    public void run() {   
		    startActivity(it); //执行  
		    finish();
		     } 
		 };
	timer.schedule(task, 1000 * 3);
	}
}