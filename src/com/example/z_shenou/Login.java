package com.example.z_shenou;

import com.example.z_shenou.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login  extends Activity implements OnClickListener{
	
	TextView loginre;
	TextView  reg;
	EditText  account;
	EditText  password;
	Button    btnLog;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		
		
		// ��ʼ���ؼ�
        initView();
        // ��ʼ���ײ���ť�¼�
        initEvent();
	}
	private void initEvent() {
		// TODO �Զ����ɵķ������
		loginre.setOnClickListener(this);
		reg.setOnClickListener(this);
		btnLog.setOnClickListener(this);
	}
	private void initView() {
		// TODO �Զ����ɵķ������
		loginre=(TextView)findViewById(R.id.loginre);
		reg=(TextView)findViewById(R.id.reg);
		account=(EditText)findViewById(R.id.Lo1);
		password=(EditText)findViewById(R.id.Lo2);
		btnLog=(Button)findViewById(R.id.btnLog);
	}
	@Override
	public void onClick(View v) {
		// TODO �Զ����ɵķ������
		Intent intent;
		 switch (v.getId()) {
		 case R.id.loginre:
			 intent=new Intent(Login.this,MainActivity.class);
			 startActivity(intent);
			 finish();
			 break;
		 case R.id.reg:
			 intent=new Intent(Login.this,Register.class);
			 startActivity(intent);
			 finish();
			 break;
		 case R.id.btnLog:
			 if(account.getText().toString()==null&&password.getText().toString()==null){			 
			 }
			 else{
				 intent=new Intent(Login.this,MainActivity.class);
				Bundle bundle=new Bundle();
				bundle.putString("account", account.getText().toString());
				 bundle.putInt("loginflag", 3);
				 intent.putExtras(bundle);
				 setResult(RESULT_OK,intent);
				 finish();
				 break;
			 }
		 }
	}
	
	
	
}
