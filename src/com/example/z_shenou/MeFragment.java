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
	//头像
	private BmobFile icon;
	private ImageView iv_icon;
	//用户名
	private TextView login;
	private TextView OU_number;
	private String account=null;
	//通过本地缓存登录
	private MyUser user;
	//个人详情
	private Userdetial detail;
	//退出登录
	//list
    private ListView meList1;
    private ListView meList2;
    private ListView meList3;
    private Button Sign_out;
    private Boolean isDetail;
    private Boolean isTrue;
    
	/*public MeFragment( String account) {
		// TODO 自动生成的构造函数存根
		this.account=account;
	}*/


	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//  每个Fragment里都只有一个简单的view用于演示界面
		meView=inflater.inflate(R.layout.p3, container, false);
		Bundle bundle = getArguments();
	    user=(MyUser)bundle.getSerializable("user");//登录的用户
	    isDetail=false;
	    isTrue=false;
	    initQuery();
		// 初始化控件
        initView(meView);
        // 初始化底部按钮事件
        initEvent();
        return meView;
    }




	private void initQuery() {
		// TODO 自动生成的方法存根
		if(user!=null){
		BmobQuery<Userdetial> query = new BmobQuery<Userdetial>();
		query.addWhereEqualTo("id_user",user.getObjectId());
		//执行查询方法
		query.findObjects(new FindListener<Userdetial>() {

			@Override
			public void done(List<Userdetial> arg0, BmobException e) {
				// TODO 自动生成的方法存根
				 if(e==null){ 
					 isDetail=true;
					 detail=arg0.get(0);
					 isTrue=detail.getIsTrue();
						OU_number.setText("OU数 ："+detail.getOU_number());
				 }
				 else{
					 Toast.makeText(getActivity(), "失败",Toast.LENGTH_SHORT).show();
			            Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
			        }
			}
			
		});
		}
	}




	public interface OnLoginSelectedListener {
        public void onLoginSelected(int id);
    }
	
	
	private void initView(View root) {
		// TODO 自动生成的方法存根
		login=(TextView)root.findViewById(R.id.unlogin);
		OU_number=(TextView)root.findViewById(R.id.OUnumber);
		Sign_out=(Button)root.findViewById(R.id.btnSign_out);
		iv_icon=(ImageView)root.findViewById(R.id.imageView1);
	    meList1=(ListView)meView.findViewById(R.id.ListView02);
	    meList2=(ListView)meView.findViewById(R.id.ListView03);
	    meList3=(ListView)meView.findViewById(R.id.ListView04);
	    SimpleAdapter itemAdapter1=new SimpleAdapter(
	    		getActivity(),
				getData(1),//数据源
				R.layout.p3item,//内部布局
				new String[]{"list"},
				new int[]{R.id.list}
				);
	    SimpleAdapter itemAdapter2=new SimpleAdapter(
	    		getActivity(),
				getData(2),//数据源
				R.layout.p3item,//内部布局
				new String[]{"list"},
				new int[]{R.id.list}
				);
	    SimpleAdapter itemAdapter3=new SimpleAdapter(
	    		getActivity(),
				getData(3),//数据源
				R.layout.p3item,//内部布局
				new String[]{"list"},
				new int[]{R.id.list}
				);
		//添加适配器
		meList1.setAdapter(itemAdapter1);
		meList2.setAdapter(itemAdapter2);
		meList3.setAdapter(itemAdapter3);
		meList1.setOnItemClickListener(new OnItemClickListener(){
		@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO 自动生成的方法存根
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
				// TODO 自动生成的方法存根
				Map<String,Object>clkmap=(Map<String,Object>)arg0.getItemAtPosition(arg2);
				if(detail!=null){
				final AlertDialog.Builder normalDialog = 
			            new AlertDialog.Builder(getActivity());
				if(!detail.getIsTrue()){
				    normalDialog.setTitle("实名验证");
   			        normalDialog.setMessage("未验证或验证未通过");	        
				}
				else{
					normalDialog.setTitle("实名验证");
	   			    normalDialog.setMessage("已通过验证");	 
				}
				normalDialog.setNegativeButton("确定", 
		                new DialogInterface.OnClickListener() {
		                @Override
		                public void onClick(DialogInterface dialog, int which) {
		                    //...To-do
		                }
		            });
		            // 显示
		        normalDialog.show();
				}
			}
		});
		meList3.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO 自动生成的方法存根
				Map<String,Object>clkmap=(Map<String,Object>)arg0.getItemAtPosition(arg2);
			}
		});
	}

	
	private List<? extends Map<String, ?>> getData(int i) {
		// TODO 自动生成的方法存根
		ArrayList<Map<String,Object>>listitem=new ArrayList<Map<String,Object>>();
		Map<String,Object>map1=new HashMap<String,Object>();
		switch(i){
		case 1:
		map1.put("list", "个人资料");
		listitem.add(map1);
		break;
		case 2:
		map1.put("list", "查看验证");
		listitem.add(map1);
		break;
		case 3:
		map1.put("list", "帮助与反馈");
		listitem.add(map1);
		break;
		}
		return listitem;
	}


	private void initEvent() {
		// TODO 自动生成的方法存根
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
		
        //登录
	    login.setOnClickListener(this);
		
		//退出登录
	   Sign_out.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		
		/*switch (v.getId()) {
		case R.id.unlogin:
			Intent intent=new Intent(getActivity(),Login.class);
			getActivity().startActivity(intent);
		}*/
		switch (v.getId()){
		case R.id.unlogin:  
		if(login.getText().toString().equals("请点击登录")){
		if (getActivity() instanceof OnLoginSelectedListener)
		{
			((OnLoginSelectedListener) getActivity()).onLoginSelected(REQUEST_LOGIN);
		}
			}
		break;
		case R.id.btnSign_out:
			BmobUser.logOut();
			user=BmobUser.getCurrentUser(MyUser.class);
			Toast.makeText(getActivity(),"已退出登录",Toast.LENGTH_SHORT).show();
			login.setText("请点击登录");
			this.OU_number.setText("");
			//广播
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
