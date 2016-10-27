package com.example.z_shenou;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class MenuFragment extends Fragment  {
	
     //广播
	LocalBroadcastManager broadcastManager;  
	IntentFilter intentFilter;  
	BroadcastReceiver mReceiver; 
	//通过本地缓存登录
	private MyUser currentuser;
	//发布按钮
	Expressage EX;
	private Button pulish;
	//各种信息
	private View menuView;
	private EditText text_price;
	private TextView text_name;
	private EditText text_phone;
	private EditText text_content;
	private Spinner spinner_express;
	private Spinner spinner_weihe;
	private Spinner spinner_size;
	String Phone;
	String Content;
	String Size;
	String Express;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		menuView=inflater.inflate(R.layout.menu, container, false);
        //每个Fragment里都只有一个简单的view用于演示界面
		Bundle bundle = getArguments();
		currentuser=(MyUser)bundle.getSerializable("user");//登录的用户
		//广播
		broadcastManager = LocalBroadcastManager.getInstance(getActivity());  
		intentFilter = new IntentFilter();  
		intentFilter.addAction("android.intent.action.CART_BROADCAST");  
		mReceiver = new BroadcastReceiver() {  
		           @Override
					public void onReceive(Context context, Intent intent) {
						// TODO 自动生成的方法存根
		        	   currentuser=(MyUser)intent.getSerializableExtra("logout_user");	    
		        	   if(currentuser==null)
		   			  text_name.setText("name");
					}  
		 };  
		 broadcastManager.registerReceiver(mReceiver, intentFilter);  
		 
		EX=new Expressage();
        initView();
		initAdapter();
		 // 初始化底部按钮事件
        initEvent();
        return menuView;
    }


	private void initEvent() {
		// TODO 自动生成的方法存根
		pulish.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				initMassag();
			}
			
		});
	}

	private void initAdapter() {
		// TODO 自动生成的方法存根
		//把名字写上
		if(currentuser!=null){
			text_name.setText(currentuser.getUsername());
		}

		//列表
	    spinner_express.setOnItemSelectedListener(new OnItemSelectedListener(){
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			// TODO 自动生成的方法存根
			EX.setExpress_company(parent.getItemAtPosition(position).toString());
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO 自动生成的方法存根
			
		}
		
	});
	spinner_size.setOnItemSelectedListener(new OnItemSelectedListener(){

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			// TODO 自动生成的方法存根
			EX.setSize(parent.getItemAtPosition(position).toString());
			if(position==0){
			text_price.setText("￥2.00");
				EX.setPrice(2.00);
			}
			else{
				text_price.setText("￥1.00");
			    EX.setPrice(1.00);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO 自动生成的方法存根
			
		}
	});
	spinner_weihe.setOnItemSelectedListener(new OnItemSelectedListener(){

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			// TODO 自动生成的方法存根
			EX.setSite(parent.getItemAtPosition(position).toString());
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO 自动生成的方法存根
			
		}
	});

	}


	private void initMassag() {
		// TODO 自动生成的方法存根
		if(currentuser==null){
			Toast.makeText(getActivity(), "请先登录",Toast.LENGTH_SHORT).show();
			Intent intent=new Intent(getActivity(),Login.class);
			startActivity(intent);
		}
		else{
			//设置EX
			EX.setpulish_name(currentuser.getUsername());
			EX.setTelephone(text_phone.getText().toString());
			EX.setContent(text_content.getText().toString());
	        EX.setPulish(currentuser);
	        EX.setJieshou(null); 
	        EX.setIsjie(false);
	        EX.setIsfinish(false);
	        if(EX.getTelephone().equals("")){
	        	Toast.makeText(getActivity(), "请填写电话",Toast.LENGTH_SHORT).show();
	        }
	        else{
	        EX.save(new SaveListener<String>() {

	            @Override
	            public void done(String objectId, BmobException e) {
	                if(e==null){
	                	Toast.makeText(getActivity(), "成功",Toast.LENGTH_SHORT).show();
	                }else{
	                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
	                }
	            }

			
	        });
		}
		}
	}

	private void initView() {
		// TODO 自动生成的方法存根	
		pulish=(Button)menuView.findViewById(R.id.btnpulish);
		text_name=(TextView)menuView.findViewById(R.id.publish_name);
		text_phone=(EditText)menuView.findViewById(R.id.publish_telephone);
		text_content=(EditText)menuView.findViewById(R.id.Other);
		text_price=(EditText)menuView.findViewById(R.id.publish_price);
		spinner_express=(Spinner) menuView.findViewById(R.id.spinner_kaudi);
		spinner_weihe=(Spinner) menuView.findViewById(R.id.spinner_weihe);
		spinner_size=(Spinner) menuView.findViewById(R.id.spinner_size);
		
	}
}
