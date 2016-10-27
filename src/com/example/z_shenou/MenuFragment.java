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
	
     //�㲥
	LocalBroadcastManager broadcastManager;  
	IntentFilter intentFilter;  
	BroadcastReceiver mReceiver; 
	//ͨ�����ػ����¼
	private MyUser currentuser;
	//������ť
	Expressage EX;
	private Button pulish;
	//������Ϣ
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
        //ÿ��Fragment�ﶼֻ��һ���򵥵�view������ʾ����
		Bundle bundle = getArguments();
		currentuser=(MyUser)bundle.getSerializable("user");//��¼���û�
		//�㲥
		broadcastManager = LocalBroadcastManager.getInstance(getActivity());  
		intentFilter = new IntentFilter();  
		intentFilter.addAction("android.intent.action.CART_BROADCAST");  
		mReceiver = new BroadcastReceiver() {  
		           @Override
					public void onReceive(Context context, Intent intent) {
						// TODO �Զ����ɵķ������
		        	   currentuser=(MyUser)intent.getSerializableExtra("logout_user");	    
		        	   if(currentuser==null)
		   			  text_name.setText("name");
					}  
		 };  
		 broadcastManager.registerReceiver(mReceiver, intentFilter);  
		 
		EX=new Expressage();
        initView();
		initAdapter();
		 // ��ʼ���ײ���ť�¼�
        initEvent();
        return menuView;
    }


	private void initEvent() {
		// TODO �Զ����ɵķ������
		pulish.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				initMassag();
			}
			
		});
	}

	private void initAdapter() {
		// TODO �Զ����ɵķ������
		//������д��
		if(currentuser!=null){
			text_name.setText(currentuser.getUsername());
		}

		//�б�
	    spinner_express.setOnItemSelectedListener(new OnItemSelectedListener(){
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			// TODO �Զ����ɵķ������
			EX.setExpress_company(parent.getItemAtPosition(position).toString());
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO �Զ����ɵķ������
			
		}
		
	});
	spinner_size.setOnItemSelectedListener(new OnItemSelectedListener(){

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			// TODO �Զ����ɵķ������
			EX.setSize(parent.getItemAtPosition(position).toString());
			if(position==0){
			text_price.setText("��2.00");
				EX.setPrice(2.00);
			}
			else{
				text_price.setText("��1.00");
			    EX.setPrice(1.00);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO �Զ����ɵķ������
			
		}
	});
	spinner_weihe.setOnItemSelectedListener(new OnItemSelectedListener(){

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			// TODO �Զ����ɵķ������
			EX.setSite(parent.getItemAtPosition(position).toString());
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO �Զ����ɵķ������
			
		}
	});

	}


	private void initMassag() {
		// TODO �Զ����ɵķ������
		if(currentuser==null){
			Toast.makeText(getActivity(), "���ȵ�¼",Toast.LENGTH_SHORT).show();
			Intent intent=new Intent(getActivity(),Login.class);
			startActivity(intent);
		}
		else{
			//����EX
			EX.setpulish_name(currentuser.getUsername());
			EX.setTelephone(text_phone.getText().toString());
			EX.setContent(text_content.getText().toString());
	        EX.setPulish(currentuser);
	        EX.setJieshou(null); 
	        EX.setIsjie(false);
	        EX.setIsfinish(false);
	        if(EX.getTelephone().equals("")){
	        	Toast.makeText(getActivity(), "����д�绰",Toast.LENGTH_SHORT).show();
	        }
	        else{
	        EX.save(new SaveListener<String>() {

	            @Override
	            public void done(String objectId, BmobException e) {
	                if(e==null){
	                	Toast.makeText(getActivity(), "�ɹ�",Toast.LENGTH_SHORT).show();
	                }else{
	                    Log.i("bmob","ʧ�ܣ�"+e.getMessage()+","+e.getErrorCode());
	                }
	            }

			
	        });
		}
		}
	}

	private void initView() {
		// TODO �Զ����ɵķ������	
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
