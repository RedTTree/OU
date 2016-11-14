package com.example.z_shenou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class Item_content  extends Activity implements OnClickListener{

	private Intent intent;
	private Bundle bundle;
	private MyUser currentuser;
	private MyUser pulish_user;
	private Expressage item;
	//����
	private TextView item_name;
	private TextView item_phone;
	private TextView item_content;
	private TextView item_express;
	private TextView item_weihe;
	private TextView item_size;
	private Button itembtn;

	
	
 	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.item_content);
		intent=this.getIntent();
		bundle=intent.getExtras();
		item=(Expressage)bundle.getSerializable("express");
		currentuser=(MyUser)bundle.getSerializable("user");//��¼���û�
		pulish_user=(MyUser)item.getPulish();
		initQuery();
		//����
		initView();
		//���
		initContent();
	}

	private void initQuery() {
		// TODO �Զ����ɵķ������
		BmobQuery<MyUser> query = new BmobQuery<MyUser>();
		query.getObject(pulish_user.getObjectId(), new QueryListener<MyUser>() {

		    @Override
		    public void done(MyUser object, BmobException e) {
		        if(e==null){
		        	pulish_user=object;
		        	item_name.setText(pulish_user.getUsername());
		        	Toast.makeText(Item_content.this,pulish_user.getUsername(), Toast.LENGTH_SHORT).show();
		        }else{
		        	Toast.makeText(Item_content.this,"ʧ��"+e.getMessage(), Toast.LENGTH_SHORT).show();
		        }
		    }


		});
	}

	private void initContent() {
		// TODO �Զ����ɵķ������
		Toast.makeText(Item_content.this,item.getpulish_name(), Toast.LENGTH_SHORT).show();
		item_name.setText(item.getpulish_name());
		item_phone.setText(item.getTelephone());
		item_content.setText(item.getContent());
		item_express.setText(item.getExpress_company());
		item_weihe.setText(item.getSite());
		item_size.setText(item.getSize());
		
		itembtn.setText(item.getPrice().toString()+"/��ȡ");
		itembtn.setOnClickListener(this);
	}

	private void initView() {
		// TODO �Զ����ɵķ������
		item_name=(TextView)this.findViewById(R.id.item_name);
		item_phone=(TextView)this.findViewById(R.id.item_telephone);
		item_content=(TextView)this.findViewById(R.id.item_Other);
		item_express=(TextView)this.findViewById(R.id.item_express);
		item_weihe=(TextView)this.findViewById(R.id.item_weihe);
		item_size=(TextView)this.findViewById(R.id.item_size);
		itembtn=(Button)this.findViewById(R.id.btnitem);
	}

	@Override
	public void onClick(View v) {
		
		// TODO �Զ����ɵķ������
		Expressage newitem=new Expressage();
		newitem.setIsjie(true);
		newitem.setIsfinish(false);
		newitem.setJieshou(currentuser);
		newitem.update(item.getObjectId(),new UpdateListener(){

			@Override
			public void done(BmobException e) {
				// TODO �Զ����ɵķ������
				  if(e==null){
						Toast.makeText(Item_content.this,"��ȡ�ɹ�", Toast.LENGTH_SHORT).show();
			        }else{
			        	Toast.makeText(Item_content.this,"��ȡʧ��"+e.getMessage(), Toast.LENGTH_SHORT).show();
			        }
			}
			
		});
	
		Intent itent=new Intent(this,MainActivity.class);
		this.startActivity(itent);
	}
}