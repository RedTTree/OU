package com.example.z_shenou;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;
import java.util.List;

import com.example.z_shenou.MeFragment.OnLoginSelectedListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnClickListener,OnLoginSelectedListener {

	

	private List<Fragment> fragments;
	static final int REQUEST_CODE=1;
	    private HomeFragment homeFragment;
	    private DailFragment dailFragment;
	    private MaFragment maFragment;
	    private MeFragment meFragment;
	    private MenuFragment menuFragment;
	    private List<TextView> views;
	    private TextView home;
	    private TextView dail;
	    private TextView ma;
	    private TextView me;
	    private String account=null;
//	    底部中间凸起view
	    private ImageView menuIv;
//	    当前选中的views的下标
	    private int currentIndex = 0;
//	    旧的views下标
	    private int oldIndex = 0;
	    private boolean isMenuSelect = false;	
	
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		 if (savedInstanceState == null) {
	          
	                 // 初始化控件
	                 initView();
	                 // 初始化底部按钮事件
	                 initEvent();
	                 // 初始化并设置当前Fragment
	                 initFragments();
	        }
	}

	private void initFragments() {
		// TODO 自动生成
		homeFragment=new HomeFragment();
		dailFragment=new DailFragment();
		maFragment=new MaFragment();
		meFragment=new MeFragment();
		menuFragment=new MenuFragment();
		
		fragments=new ArrayList<Fragment>();
		fragments.add(homeFragment);
		fragments.add(dailFragment);
		fragments.add(maFragment);
	    fragments.add(meFragment);
	    fragments.add(menuFragment);
	    
	    FragmentManager fragmentManager = getSupportFragmentManager();
	    FragmentTransaction transaction = fragmentManager.beginTransaction();
	    hideFragment(transaction);
	    transaction.add(R.id.content, homeFragment);
	    transaction.add(R.id.content, dailFragment);
	    transaction.add(R.id.content, menuFragment);
	    transaction.hide(dailFragment);
	    transaction.hide(menuFragment);
	    transaction.show(homeFragment);
	 // 提交事务
	    transaction.commit();
	}

	private void hideFragment(FragmentTransaction transaction) {
		// TODO 自动生成的方法存根
		 if (homeFragment != null) {
			 transaction.hide(homeFragment);
			 }
		 if (menuFragment != null) {
			 transaction.hide(menuFragment);
			 }
			 if (dailFragment != null) {
			 transaction.hide(dailFragment);
			 }
			 if (maFragment != null) {
			 transaction.hide(maFragment);
			 }
			if (meFragment != null) {
			 transaction.hide(meFragment);
        }
	}

	private void initEvent() {
		// TODO 自动生成的方法存根
		 home.setOnClickListener(this);
		 dail.setOnClickListener(this);
		 ma.setOnClickListener(this);
		 me.setOnClickListener(this);
	     home.setSelected(true);
	}

	private void initView() {
		// TODO 自动生成的方法存根
		home=(TextView)findViewById(R.id.home);
		dail=(TextView)findViewById(R.id.dail);
		ma=(TextView) findViewById(R.id.ma);
		me=(TextView) findViewById(R.id.me);
		
		views=new ArrayList<TextView>();
		views.add(home);
		views.add(dail);
		views.add(ma);
		views.add(me);
		menuIv=(ImageView) findViewById(R.id.menu_iv);
		menuIv.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
			currentIndex = 4;
		    v.setSelected(isMenuSelect=(isMenuSelect==true?false:true));
		    views.get(oldIndex).setSelected(false);
		    showCurrentFragment(currentIndex);
			}
		});
	}

	
	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		 switch (v.getId()) {
         case R.id.home:
             currentIndex = 0;
             break;
         case R.id.dail:
             currentIndex = 1;
             break;
         case R.id.ma:
             currentIndex = 2;
             break;
         case R.id.me:
             currentIndex = 3;
             break;
     }
//       规避策略将凸起的view还原
		 menuIv.setSelected(false);
	     isMenuSelect = false;
     showCurrentFragment(currentIndex);
	}

	private void showCurrentFragment(int currentIndex) {
		// TODO 自动生成的方法存根
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		 ft.hide(fragments.get(4));
		 if (currentIndex != oldIndex&&currentIndex!=4){
			  views.get(oldIndex).setSelected(false);
	          views.get(currentIndex).setSelected(true);          
	          ft.hide(fragments.get(oldIndex));
	          if (!fragments.get(currentIndex).isAdded()){
	                ft.add(R.id.content,fragments.get(currentIndex));
	            }
	          ft.show(fragments.get(currentIndex)).commit();
	          oldIndex = currentIndex;
		 }
		 else if(currentIndex != oldIndex&&currentIndex==4){
			 ft.hide(fragments.get(oldIndex));
			 ft.show(fragments.get(currentIndex)).commit();
		 }
	}

	@Override
	public void onLoginSelected() {
		// TODO 自动生成的方法存根
		Intent intent=new Intent(MainActivity.this,Login.class);
		startActivityForResult(intent,REQUEST_CODE);
	}
	
	
	
	
	@Override
	protected void onActivityResult(int requestCode,int resultCode,Intent data){
		Bundle extras=data.getExtras(); 
		currentIndex=extras.getInt("loginflag");
		account=extras.getString("account");
		menuIv.setSelected(false);
	    isMenuSelect = false;
	    TextView logined=(TextView)findViewById(R.id.unlogin);
	    logined.setText(account);
		showCurrentFragment(currentIndex);
	}
}
