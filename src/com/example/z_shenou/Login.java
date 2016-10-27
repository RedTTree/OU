package com.example.z_shenou;

import com.example.z_shenou.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Window;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Login  extends Activity implements OnClickListener{
	
	private TextView loginre;
	private TextView  reg;
	private EditText  log_account;
	private String    id;
	private EditText  log_password;
	private Button    btnLog;
	static final int REQUEST_CODE=1;
	//为了在注册成功时不两次出现登录
	 public static Login instance;
	
	//user
	MyUser user=new MyUser();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		
		instance=this;
		// 初始化控件
        initView();
        // 初始化底部按钮事件
        initEvent();
	}
	private void initEvent() {
		// TODO 自动生成的方法存根
		loginre.setOnClickListener(this);
		reg.setOnClickListener(this);
		btnLog.setOnClickListener(this);
	}
	private void initView() {
		// TODO 自动生成的方法存根
		loginre=(TextView)findViewById(R.id.loginre);
		reg=(TextView)findViewById(R.id.reg);
		log_account=(EditText)findViewById(R.id.Lo1);
		log_password=(EditText)findViewById(R.id.Lo2);
		btnLog=(Button)findViewById(R.id.btnLog);
	}
	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		Intent intent;
		 switch (v.getId()) {
		 case R.id.loginre:
			 intent=new Intent(Login.this,MainActivity.class);
			 startActivity(intent);
			 finish();
			 break;
		 case R.id.reg:
			 intent=new Intent(Login.this,Register.class);
			 startActivityForResult(intent,REQUEST_CODE);	 
			 break;
		 case R.id.btnLog:
			 if(TextUtils.isEmpty(log_account.getText())||TextUtils.isEmpty(log_password.getText())){	
			Toast.makeText(Login.this, "请输入账号或密码", Toast.LENGTH_SHORT).show();
			 }
			 else{
				//把信息添加入user
				user.setUsername(log_account.getText().toString());
				user.setPassword(log_password.getText().toString());
				//连接远程
				user.login(new SaveListener<MyUser>(){

					 @Override
					    public void done(MyUser User, BmobException e) {
						// TODO 自动生成的方法存根
						if(User==null){
							Intent intent1=new Intent(Login.this,MainActivity.class);
							intent1.putExtra("fragment",3);
							startActivity(intent1);
							 finish();
							Toast.makeText(Login.this, "登录成功", Toast.LENGTH_SHORT).show();
				            //通过MyUser user = MyUser.getCurrentUser()获取登录成功后的本地用户信息
				            //如果是自定义用户对象MyUser，可通过MyUser user = MyUser.getCurrentUser(MyUser.class)获取自定义用户信息
				        }else{
				        	
				        	Toast.makeText(Login.this, e.toString(), Toast.LENGTH_SHORT).show();
				        }
					}

				});
				break;
			 }
		 }
	}
	@Override	
	protected void onActivityResult(int requestCode,int resultCode,Intent data){
		if(requestCode==REQUEST_CODE){
			if(resultCode==RESULT_CANCELED){
				Toast.makeText(Login.this, "失败",Toast.LENGTH_SHORT).show();
			}
			else if(resultCode==RESULT_OK){
				Bundle extras=data.getExtras();
				id=extras.getString("id");
				log_account.setText(id);
			}
		}

	}
	
	
}
