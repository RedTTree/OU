package com.example.z_shenou;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.View;
import android.view.View.OnClickListener;

public class Item_content  extends Activity implements OnClickListener{

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.item_content);
		
	}

	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		
	}
}