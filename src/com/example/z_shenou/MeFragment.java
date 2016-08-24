package com.example.z_shenou;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MeFragment extends Fragment implements OnClickListener {
	
	private View meView;
	private TextView unlogin;
	private String account=null;
	

	/*public MeFragment( String account) {
		// TODO 自动生成的构造函数存根
		this.account=account;
	}*/


	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//  每个Fragment里都只有一个简单的view用于演示界面
		meView=inflater.inflate(R.layout.p3, container, false);
		// 初始化控件
        initView(meView);
        // 初始化底部按钮事件
        initEvent();
        return meView;
    }

	public interface OnLoginSelectedListener {
        public void onLoginSelected();
    }
	
	
	private void initView(View root) {
		// TODO 自动生成的方法存根
		unlogin=(TextView)root.findViewById(R.id.unlogin);
		if(account!=null){
		unlogin.setText(account);
		}
	}

	private void initEvent() {
		// TODO 自动生成的方法存根
	 unlogin.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		
		/*switch (v.getId()) {
		case R.id.unlogin:
			Intent intent=new Intent(getActivity(),Login.class);
			getActivity().startActivity(intent);
		}*/
		if (getActivity() instanceof OnLoginSelectedListener)
		{
			((OnLoginSelectedListener) getActivity()).onLoginSelected();
		}
	}

	 public void onActivityCreated(Bundle savedInstanceState) {  
	        super.onActivityCreated(savedInstanceState);  
	 }
	
}
