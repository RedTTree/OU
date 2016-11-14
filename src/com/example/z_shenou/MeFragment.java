 package com.example.z_shenou;

import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.squareup.picasso.Picasso;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

public class MeFragment extends Fragment implements OnClickListener {
	
	
    static final int REQUEST_PIC=0;
    static final int REQUEST_LOGIN=1;
   
	
	private View meView;
	//ͷ��
	private BmobFile icon;
	private ImageView iv_icon;
	//�û���
	private TextView login;
	private TextView OU_number;
	private String account=null;
	//ͨ�����ػ����¼
	private MyUser user;
	//��������
	private Userdetial detail;
	//�˳���¼
	//list
    private ListView meList1;
    private ListView meList2;
    private ListView meList3;
    private Button Sign_out;
    private Boolean isDetail;
    private Boolean isTrue;
    
	/*public MeFragment( String account) {
		// TODO �Զ����ɵĹ��캯�����
		this.account=account;
	}*/


	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//  ÿ��Fragment�ﶼֻ��һ���򵥵�view������ʾ����
		meView=inflater.inflate(R.layout.p3, container, false);
		Bundle bundle = getArguments();
	    user=(MyUser)bundle.getSerializable("user");//��¼���û�
	    isDetail=false;
	    isTrue=false;
	    initQuery();
		// ��ʼ���ؼ�
        initView(meView);
        // ��ʼ���ײ���ť�¼�
        initEvent();
        return meView;
    }




	private void initQuery() {
		// TODO �Զ����ɵķ������
		if(user!=null){
		BmobQuery<Userdetial> query = new BmobQuery<Userdetial>();
		query.addWhereEqualTo("id_user",user.getObjectId());
		//ִ�в�ѯ����
		query.findObjects(new FindListener<Userdetial>() {

			@Override
			public void done(List<Userdetial> arg0, BmobException e) {
				// TODO �Զ����ɵķ������
				 if(e==null){ 
					 isDetail=true;
					 detail=arg0.get(0);
					 isTrue=detail.getIsTrue();
						OU_number.setText("OU�� ��"+detail.getOU_number());
				 }
				 else{
					 Toast.makeText(getActivity(), "ʧ��",Toast.LENGTH_SHORT).show();
			            Log.i("bmob","ʧ�ܣ�"+e.getMessage()+","+e.getErrorCode());
			        }
			}
			
		});
		}
	}




	public interface OnLoginSelectedListener {
        public void onLoginSelected(int id);
    }
	
	
	private void initView(View root) {
		// TODO �Զ����ɵķ������
		login=(TextView)root.findViewById(R.id.unlogin);
		OU_number=(TextView)root.findViewById(R.id.OUnumber);
		Sign_out=(Button)root.findViewById(R.id.btnSign_out);
		iv_icon=(ImageView)root.findViewById(R.id.imageView1);
	    meList1=(ListView)meView.findViewById(R.id.ListView02);
	    meList2=(ListView)meView.findViewById(R.id.ListView03);
	    meList3=(ListView)meView.findViewById(R.id.ListView04);
	    SimpleAdapter itemAdapter1=new SimpleAdapter(
	    		getActivity(),
				getData(1),//����Դ
				R.layout.p3item,//�ڲ�����
				new String[]{"list"},
				new int[]{R.id.list}
				);
	    SimpleAdapter itemAdapter2=new SimpleAdapter(
	    		getActivity(),
				getData(2),//����Դ
				R.layout.p3item,//�ڲ�����
				new String[]{"list"},
				new int[]{R.id.list}
				);
	    SimpleAdapter itemAdapter3=new SimpleAdapter(
	    		getActivity(),
				getData(3),//����Դ
				R.layout.p3item,//�ڲ�����
				new String[]{"list"},
				new int[]{R.id.list}
				);
		//���������
		meList1.setAdapter(itemAdapter1);
		meList2.setAdapter(itemAdapter2);
		meList3.setAdapter(itemAdapter3);
		meList1.setOnItemClickListener(new OnItemClickListener(){
		@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO �Զ����ɵķ������
				if(isDetail){
				Map<String,Object>clkmap=(Map<String,Object>)arg0.getItemAtPosition(arg2);
				Intent itent=new Intent(getActivity(),Persona_Dail.class);
				itent.putExtra("detail", detail);
				itent.putExtra("user",user);
				startActivity(itent);
				}
			}
		});
		meList2.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO �Զ����ɵķ������
				Map<String,Object>clkmap=(Map<String,Object>)arg0.getItemAtPosition(arg2);
				if(detail!=null){
				final AlertDialog.Builder normalDialog = 
			            new AlertDialog.Builder(getActivity());
				if(!detail.getIsTrue()){
				    normalDialog.setTitle("ʵ����֤");
   			        normalDialog.setMessage("δ��֤����֤δͨ��");	        
				}
				else{
					normalDialog.setTitle("ʵ����֤");
	   			    normalDialog.setMessage("��ͨ����֤");	 
				}
				normalDialog.setNegativeButton("ȷ��", 
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
		meList3.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO �Զ����ɵķ������
				Map<String,Object>clkmap=(Map<String,Object>)arg0.getItemAtPosition(arg2);
			}
		});
	}

	
	private List<? extends Map<String, ?>> getData(int i) {
		// TODO �Զ����ɵķ������
		ArrayList<Map<String,Object>>listitem=new ArrayList<Map<String,Object>>();
		Map<String,Object>map1=new HashMap<String,Object>();
		switch(i){
		case 1:
		map1.put("list", "��������");
		listitem.add(map1);
		break;
		case 2:
		map1.put("list", "�鿴��֤");
		listitem.add(map1);
		break;
		case 3:
		map1.put("list", "�����뷴��");
		listitem.add(map1);
		break;
		}
		return listitem;
	}


	private void initEvent() {
		// TODO �Զ����ɵķ������
		if(user!=null){
			account=user.getUsername();
			login.setText(account);	
			}
		/*	icon=user.getIcon();
			String internetUrl =icon.getUrl();
			Picasso
			  .with(getActivity())
			  .load(internetUrl)
			  .resize(100,100)
			  .into(iv_icon);*/
		iv_icon.setOnClickListener(this);
		
        //��¼
	    login.setOnClickListener(this);
		
		//�˳���¼
	   Sign_out.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		// TODO �Զ����ɵķ������
		
		/*switch (v.getId()) {
		case R.id.unlogin:
			Intent intent=new Intent(getActivity(),Login.class);
			getActivity().startActivity(intent);
		}*/
		switch (v.getId()){
		case R.id.unlogin:  
		if(login.getText().toString().equals("������¼")){
		if (getActivity() instanceof OnLoginSelectedListener)
		{
			((OnLoginSelectedListener) getActivity()).onLoginSelected(REQUEST_LOGIN);
		}
			}
		break;
		case R.id.btnSign_out:
			BmobUser.logOut();
			user=BmobUser.getCurrentUser(MyUser.class);
			Toast.makeText(getActivity(),"���˳���¼",Toast.LENGTH_SHORT).show();
			login.setText("������¼");
			this.OU_number.setText("");
			//�㲥
			Intent intent = new Intent("android.intent.action.CART_BROADCAST"); 
			intent.putExtra("logout_user", user);
			LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);  
			break;
		case R.id.imageView1:
			if(user!=null){
			if (getActivity() instanceof OnLoginSelectedListener)
			{
				((OnLoginSelectedListener) getActivity()).onLoginSelected(REQUEST_PIC);
			}
			}
			break;
		}
	}

	 public void onActivityCreated(Bundle savedInstanceState) {  
	        super.onActivityCreated(savedInstanceState);  
	 }
	
}
