package com.example.z_shenou;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import android.view.View.OnClickListener;

public class Register  extends Activity implements OnClickListener{

	
	TextView relogin;
	Button btnReg;
    //EditView
	EditText  reg_account;
	EditText  reg_password;
	EditText  reg_email;
	//String
    private String account=null;
	private String password=null;
	//ע���û�
	private MyUser user=new MyUser();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register);
		initView();
		initEvent();
	}
	private void initEvent() {
		// TODO �Զ����ɵķ������
		relogin.setOnClickListener(this);
		btnReg.setOnClickListener(this);
	}
	private void initView() {
		// TODO �Զ����ɵķ������
		relogin=(TextView)findViewById(R.id.regi);
		btnReg=(Button)findViewById(R.id.btnReg);
		reg_account=(EditText)findViewById(R.id.Re1);
		reg_email=(EditText)findViewById(R.id.Re2);
		reg_password=(EditText)findViewById(R.id.Re3);
				
	}
	@Override
	public void onClick(View v) {
		// TODO �Զ����ɵķ������
		Intent intent;
		 switch (v.getId()){
		 case R.id.regi:
			 intent=new Intent(Register.this, Login.class);
			 startActivity(intent);
			 finish();
			 break;
		 case R.id.btnReg:
			 account=reg_account.getText().toString();
			 password=reg_password.getText().toString();
			 if(TextUtils.isEmpty(reg_account.getText())||TextUtils.isEmpty(reg_password.getText())){	
			 Toast.makeText(Register.this, "�˺Ż���������벻��Ϊ��", Toast.LENGTH_SHORT).show();
		 }
		else{
			//����Ϣ�����user
			user.setUsername(reg_account.getText().toString());
			user.setEmail(reg_email.getText().toString());
			user.setPassword(reg_password.getText().toString());
			//��Զ�����ݿ����
			user.signUp(new SaveListener<MyUser>(){
				@Override
				public void done(MyUser arg0, BmobException arg1) {
					// TODO �Զ����ɵķ������
					if(arg1==null){
						Toast.makeText(Register.this, "ע��ɹ�",Toast.LENGTH_SHORT).show();
						//�رն����Loginҳ��
						// Login.instance.finish();
						//��ת
						 Intent intent1;
						 intent1=new Intent();
						 intent1.putExtra("id",reg_account.getText().toString());
						 //startActivity(intent1);
						 setResult(RESULT_OK,intent1);
						 finish();
					}
					else
					Toast.makeText(Register.this, "ע��ʧ��",Toast.LENGTH_SHORT).show();
				}
			});
		 }
			 break;
		 }
		 }
	}


