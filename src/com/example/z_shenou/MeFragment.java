 package com.example.z_shenou;

import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;

import java.util.List;
import java.util.Map;

import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

public class MeFragment extends Fragment implements OnClickListener {
	
	
    static final int REQUEST_PIC=0;
    static final int REQUEST_LOGIN=1;

	
	private View meView;
	//头像
	private BmobFile icon;
	private ImageView iv_icon;
	//用户名
	private TextView login;
	private String account=null;
	//通过本地缓存登录
	private MyUser user;
	//退出登录
	Button Sign_out;

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
	    
	    
		SimpleAdapter ItemAdapter=new SimpleAdapter(
				getActivity(),
				getData(),//数据源
				R.layout.p2item,//内部布局
				new String[]{"img","massage"},
				new int[]{R.id.img,R.id.massage}
				);
		// 初始化控件
        initView(meView);
        // 初始化底部按钮事件
        initEvent();
        return meView;
    }


	private List<? extends Map<String, ?>> getData() {
		// TODO 自动生成的方法存根
		return null;
	}


	public interface OnLoginSelectedListener {
        public void onLoginSelected(int id);
    }
	
	
	private void initView(View root) {
		// TODO 自动生成的方法存根
		login=(TextView)root.findViewById(R.id.unlogin);
		Sign_out=(Button)root.findViewById(R.id.btnSign_out);
		iv_icon=(ImageView)root.findViewById(R.id.imageView1);
	}

	private void initEvent() {
		// TODO 自动生成的方法存根
		if(user!=null){
			account=user.getUsername();
			login.setText(account);}
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
