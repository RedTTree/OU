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
	//ͷ��
	private BmobFile icon;
	private ImageView iv_icon;
	//�û���
	private TextView login;
	private String account=null;
	//ͨ�����ػ����¼
	private MyUser user;
	//�˳���¼
	Button Sign_out;

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
	    
	    
		SimpleAdapter ItemAdapter=new SimpleAdapter(
				getActivity(),
				getData(),//����Դ
				R.layout.p2item,//�ڲ�����
				new String[]{"img","massage"},
				new int[]{R.id.img,R.id.massage}
				);
		// ��ʼ���ؼ�
        initView(meView);
        // ��ʼ���ײ���ť�¼�
        initEvent();
        return meView;
    }


	private List<? extends Map<String, ?>> getData() {
		// TODO �Զ����ɵķ������
		return null;
	}


	public interface OnLoginSelectedListener {
        public void onLoginSelected(int id);
    }
	
	
	private void initView(View root) {
		// TODO �Զ����ɵķ������
		login=(TextView)root.findViewById(R.id.unlogin);
		Sign_out=(Button)root.findViewById(R.id.btnSign_out);
		iv_icon=(ImageView)root.findViewById(R.id.imageView1);
	}

	private void initEvent() {
		// TODO �Զ����ɵķ������
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
