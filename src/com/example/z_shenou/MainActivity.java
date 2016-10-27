package com.example.z_shenou;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.example.z_shenou.MeFragment.OnLoginSelectedListener;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

public class MainActivity extends FragmentActivity implements OnClickListener,OnLoginSelectedListener {

	
	    //从前一个activity返回的识别码
	    static final int REQUEST_PIC=0;
	    static final int REQUEST_LOGIN=1;
		private static final int PICTURE = 10086; //requestcode
		//通过本地缓存登录
		private MyUser currentuser;
		//传值
		 Bundle bundle;
	    //五个跳转的框架
	    private List<Fragment> fragments;
	    private HomeFragment homeFragment;
	    private DailFragment dailFragment;
	    private MaFragment maFragment;
	    private MeFragment meFragment;
	    private MenuFragment menuFragment;
	    //下面的字
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
		currentuser=BmobUser.getCurrentUser(MyUser.class);//登录的用户
		 bundle = new Bundle(); 
		 bundle.putSerializable("user", currentuser);
		//具体内容
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
		
		homeFragment.setArguments(bundle);
		dailFragment.setArguments(bundle);
		maFragment.setArguments(bundle);
		meFragment.setArguments(bundle);
		menuFragment.setArguments(bundle);
		
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
	public void onLoginSelected(int id) {
		// TODO 自动生成的方法存根
		if(id==REQUEST_LOGIN){
		Intent intent=new Intent(MainActivity.this,Login.class);
		startActivity(intent);
		}
		else if(id==REQUEST_PIC){
			Intent intent = new Intent();
			if (Build.VERSION.SDK_INT < 19) {//因为Android SDK在4.4版本后图片action变化了 所以在这里先判断一下
			      intent.setAction(Intent.ACTION_GET_CONTENT);
			    } else {
			      intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
			    }
			    intent.setType("image/*");
			    intent.addCategory(Intent.CATEGORY_OPENABLE);
			startActivityForResult(intent, PICTURE);
		}
	}
	
	
	
	
	@Override
	protected void onActivityResult(int requestCode,int resultCode,Intent data){
		 if (data == null) {
		      this.finish();
		      return;
		    }
		    Uri uri = data.getData();
		    switch (requestCode) {
		      case PICTURE:
		       String image = FileUtils.getUriPath(this, uri);
		       Toast.makeText(MainActivity.this,image,Toast.LENGTH_SHORT).show();
		       updatePIC(image);//（因为4.4以后图片uri发生了变化）通过文件工具类 对uri进行解析得到图片路径
		        break;
		      default:
		        break;
		    }
		    this.finish();
	}

	private void updatePIC(String imaged) {
		// TODO 自动生成的方法存根

		
		final BmobFile bmobFile = new BmobFile(new File(imaged));
		 Toast.makeText(MainActivity.this,"完成",Toast.LENGTH_SHORT).show();
		bmobFile.uploadblock(new UploadFileListener() {

		    @Override
		    public void done(BmobException e) {
		        if(e==null){
		            //bmobFile.getFileUrl()--返回的上传文件的完整地址
		        	Toast.makeText(MainActivity.this,"上传文件成功:" + bmobFile.getFileUrl(),Toast.LENGTH_SHORT).show();
		        	MyUser newUser = new MyUser();
		        	newUser.setIcon(bmobFile);
		        	newUser.update(currentuser.getObjectId(), new UpdateListener(){

						@Override
						public void done(BmobException e) {
							// TODO 自动生成的方法存根
							if(e==null){
								Toast.makeText(MainActivity.this,"成功",Toast.LENGTH_SHORT).show();
							}
							else{
								Toast.makeText(MainActivity.this,"失败:" + e.getMessage(),Toast.LENGTH_SHORT).show();
							}
						}
		        		
		        	});
		        	
		        }else{
		        	Toast.makeText(MainActivity.this,"上传文件失败:" + e.getMessage(),Toast.LENGTH_SHORT).show(); 
		        }

		    }

		    @Override
		    public void onProgress(Integer value) {
		        // 返回的上传进度（百分比）
		    }

		});
	}
}
