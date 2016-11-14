package com.example.z_shenou;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import android.widget.Button;
import android.widget.EditText;

public class Persona_Dail extends Activity implements OnClickListener {
	
	private ListView list;
	//��������
	private Userdetial detail;
	//��������
	private Userdetial new_detail;
	private MyUser user;
	//���水ť
	private Button saveUp;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.personal);
		detail=new Userdetial();
		new_detail=new Userdetial();
		Intent intent=this.getIntent();
		detail=(Userdetial) intent.getSerializableExtra("detail");
		user=(MyUser) intent.getSerializableExtra("user");
		// ��ʼ���ؼ�
        initView();
        // ��ʼ���ײ���ť�¼�
	}

	private void initView() {
		// TODO �Զ����ɵķ������
		list=(ListView)findViewById(R.id.ListView_personal);
		saveUp=(Button) findViewById(R.id.save_up);
		saveUp.setOnClickListener(this);
		SimpleAdapter ItemAdapter=new SimpleAdapter(
				Persona_Dail.this,
				getData(),//����Դ
				R.layout.p2item,//�ڲ�����
				new String[]{"img","massage"},
				new int[]{R.id.img,R.id.massage}
				);
		//���������
		list.setAdapter(ItemAdapter);
		
		list.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO �Զ����ɵķ������
				Map<String,Object>clkmap=(Map<String,Object>)arg0.getItemAtPosition(arg2);
				if(arg2<5)
				showDialog_P(arg2,clkmap);
				else if(arg2==5){
					final AlertDialog.Builder normalDialog = 
				            new AlertDialog.Builder(Persona_Dail.this);
				        normalDialog.setTitle("�ϴ���Ƭ");
				        normalDialog.setMessage("��������ͷ���ϴ���Ƭ");
				        normalDialog.setPositiveButton("ȷ��", 
				            new DialogInterface.OnClickListener() {
				            @Override
				            public void onClick(DialogInterface dialog, int which) {
				            	Intent intent1=new Intent(Persona_Dail.this,MainActivity.class);
								intent1.putExtra("fragment",3);
								startActivity(intent1);
								 finish();
				            }
				        });
				        normalDialog.setNegativeButton("�ر�", 
				                new DialogInterface.OnClickListener() {
				                @Override
				                public void onClick(DialogInterface dialog, int which) {
				                    //...To-do
				                }
				            });
				            // ��ʾ
				            normalDialog.show();
				}
			}
		});
	}
	private void showDialog_P(final int arg2,final Map<String,Object> item) {
		// TODO �Զ����ɵķ������
		 final EditText editText = new EditText(Persona_Dail.this);
		 AlertDialog.Builder inputDialog = 
			        new AlertDialog.Builder(Persona_Dail.this);
		 switch(arg2){
		 case 0:
			 inputDialog.setTitle("��������ʵ����").setView(editText);				 
			 break;
		 case 1:
			 inputDialog.setTitle("������ѧ��").setView(editText);
			 break;
		 case 2:
			 inputDialog.setTitle("������Ժϵ").setView(editText);
			 break;
		 case 3:
			 inputDialog.setTitle("������绰").setView(editText);
			 break;
		 case 4:
			 inputDialog.setTitle("�������Ա�").setView(editText);
			 break;
		 }
		 
		inputDialog.setPositiveButton("ȷ��", 
		            new DialogInterface.OnClickListener() {
		            @Override
		            public void onClick(DialogInterface dialog, int which) {
		                if(TextUtils.isEmpty(editText.getText())){
		                	Toast.makeText(Persona_Dail.this, "����������", Toast.LENGTH_SHORT).show();
		                }
		                else{
		                	item.put("massage", editText.getText().toString());
		                	switch(arg2){
		                	case 0:
		           			 new_detail.setReal_name(editText.getText().toString());				 
		           			 break;
		           		 case 1:
		           			 new_detail.setStudent_number(editText.getText().toString());
		           			 break;
		           		 case 2:
		           			 new_detail.setCollege(editText.getText().toString());
		           			 break;
		           		 case 3:
		           			 new_detail.setTelephone(editText.getText().toString());
		           			 break;
		           		 case 4:
		           			 new_detail.setSex(editText.getText().toString());
		           			 break;
		                	}
		                }
		            }
		        });
		        inputDialog.setNegativeButton("�ر�", 
		            new DialogInterface.OnClickListener() {
		            @Override
		            public void onClick(DialogInterface dialog, int which) {
		                //...To-do
		            }
		        });
		        inputDialog.show();
	}

	private List<? extends Map<String, ?>> getData() {
		// TODO �Զ����ɵķ������
		
		ArrayList<Map<String,Object>>listitem=new ArrayList<Map<String,Object>>();
		Map<String,Object>map3=new HashMap<String,Object>();
		Map<String,Object>map1=new HashMap<String,Object>();
		Map<String,Object>map2=new HashMap<String,Object>();
		Map<String,Object>map4=new HashMap<String,Object>();
		Map<String,Object>map5=new HashMap<String,Object>();
		Map<String,Object>map6=new HashMap<String,Object>();
		Map<String,Object>map7=new HashMap<String,Object>();
		if(detail.getStudent_number().equals("0")){
		map1.put("img",R.drawable.bg05);
		map1.put("massage", "��ʵ����");
		listitem.add(map1);
		map2.put("img",R.drawable.bg05);
		map2.put("massage", "ѧ��");
		listitem.add(map2);
		map3.put("img",R.drawable.bg05);
		map3.put("massage", "Ժϵ");
		listitem.add(map3);
		map4.put("img",R.drawable.bg05);
		map4.put("massage", "�绰");
		listitem.add(map4);
		map5.put("img",R.drawable.bg05);
		map5.put("massage", "�Ա�");
		listitem.add(map5);
		map6.put("img",R.drawable.bg05);
		map6.put("massage", "�ϴ���֤��Ƭ ");
		listitem.add(map6);
		map7.put("img",R.drawable.bg05);
		map7.put("massage", user.getEmail());
		listitem.add(map7);
		}
		else
		{
			map1.put("img",R.drawable.bg05);
			map1.put("massage", detail.getReal_name());
			listitem.add(map1);
			map2.put("img",R.drawable.bg05);
			map2.put("massage", detail.getStudent_number());
			listitem.add(map2);
			map3.put("img",R.drawable.bg05);
			map3.put("massage", detail.getCollege());
			listitem.add(map3);
			map4.put("img",R.drawable.bg05);
			map4.put("massage", detail.getTelephone());
			listitem.add(map4);
			map5.put("img",R.drawable.bg05);
			map5.put("massage", detail.getSex());
			listitem.add(map5);
			map6.put("img",R.drawable.bg05);
			map6.put("massage", "�ϴ���֤��Ƭ ");
			listitem.add(map6);
			map7.put("img",R.drawable.bg05);
			map7.put("massage", user.getEmail());
			listitem.add(map7);
		}
		return listitem;
	}

	@Override
	public void onClick(View v) {
		// TODO �Զ����ɵķ������
		new_detail.update(detail.getObjectId(),new UpdateListener(){
			@Override
			public void done(BmobException e) {
				// TODO �Զ����ɵķ������
				  if(e==null){
						Toast.makeText(Persona_Dail.this,"�ɹ�", Toast.LENGTH_SHORT).show();
			        }else{
			        	Toast.makeText(Persona_Dail.this,"ʧ��"+e.getMessage(), Toast.LENGTH_SHORT).show();
			        }
			}
			
		});
	}
}
