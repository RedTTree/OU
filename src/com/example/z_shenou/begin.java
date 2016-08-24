package com.example.z_shenou;

import java.util.Timer;
import java.util.TimerTask;

import com.example.z_shenou.R;
import com.example.z_shenou.MainActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class begin extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);	
		final Intent it=new Intent(this,MainActivity.class);
		Timer timer = new Timer(); 
		TimerTask task = new TimerTask() {  
		    @Override  
		    public void run() {   
		    startActivity(it); //Ö´ÐÐ  
		    finish();
		     } 
		 };
	timer.schedule(task, 1000 * 3);
	}
}