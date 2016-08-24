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
		// TODO �Զ����ɵĹ��캯�����
		this.account=account;
	}*/


	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//  ÿ��Fragment�ﶼֻ��һ���򵥵�view������ʾ����
		meView=inflater.inflate(R.layout.p3, container, false);
		// ��ʼ���ؼ�
        initView(meView);
        // ��ʼ���ײ���ť�¼�
        initEvent();
        return meView;
    }

	public interface OnLoginSelectedListener {
        public void onLoginSelected();
    }
	
	
	private void initView(View root) {
		// TODO �Զ����ɵķ������
		unlogin=(TextView)root.findViewById(R.id.unlogin);
		if(account!=null){
		unlogin.setText(account);
		}
	}

	private void initEvent() {
		// TODO �Զ����ɵķ������
	 unlogin.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		// TODO �Զ����ɵķ������
		
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
