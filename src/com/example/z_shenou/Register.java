package com.example.z_shenou;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;

public class Register  extends Activity implements OnClickListener{

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register);
		
	}
	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		
	}

}
