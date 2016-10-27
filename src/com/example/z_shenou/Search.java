package com.example.z_shenou;

import java.util.ArrayList;
import java.util.List;

import com.example.z_shenou.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Window;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class Search extends Activity implements OnClickListener{
	
	 //返回
	 private ImageButton back;
	 //用户
	  private MyUser currentuser;
	  //列表
	  private RecyclerView recyclerView;
	  private LinearLayoutManager layoutManager;
	  private RecyclerViewAdapter adapter;
	  //数据源
	  private Expressage list;
	  private List<Expressage> List;
	  //刷新
	  private SwipeRefreshLayout mswipeRefreshLayout;
	  //加载
	  private Integer lastVisibleItem; 
    private BaseAttacher mBaseAttacher;
	  private MyProgressBar mProgressBar;
	  //是否正在加载
	  private boolean isLoading = false;
	  //当前页数
	  private Integer currentPage = 0;
	  //最前面一条的数据
	  Expressage preitem;
	  //intent
	  Intent intent;
	  String  data; 
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.search);
		
		intent=this.getIntent();
		data=intent.getStringExtra("search");
		
		// 初始化控件
        initView();
        // 初始化底部按钮事件
        initEvent();
		initAdapter();	
	    TopFlash();
	    DownFlash();
	}
	
	

	private void DownFlash() {
		// TODO 自动生成的方法存根
		  mBaseAttacher = Mugen.with(recyclerView, new MugenCallbacks() {
              @Override
              public void onLoadMore() {
                  //加载更多
           	   isLoading = true;
           	   mProgressBar.setVisibility(View.VISIBLE);
           	   getData((currentPage + 1), 10);
           	 /*  List.add(new Items("item10",getString(R.string.news_four_content),R.drawable.bg04));
           	   isLoading = false;
           	   adapter=new RecyclerViewAdapter(List,getActivity());
           	   recyclerView.setAdapter(adapter);*/
              }
			@Override
              public boolean isLoading() {
                  return isLoading;
              }
  
              @Override
              public boolean hasLoadedAllItems() {
                  return false;
             }
          }).start();
	}


	private void TopFlash() {
		// TODO 自动生成的方法存根
		mswipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
           public void onRefresh() {
                getData(0, 10);
            }
    });
	}


	private void initAdapter() {
		// TODO 自动生成的方法存根
				//添加数据
				List=new ArrayList<Expressage>();
				// 初始化自定义的适配器
				adapter=new RecyclerViewAdapter(List,this,currentuser);
				// 设置固定大小
				recyclerView.setHasFixedSize(true); 
				// 设置适配器
				recyclerView.setAdapter(adapter);
				mswipeRefreshLayout.setRefreshing(true);
				//刚开始加载10条数据
				 isLoading = true;
		  	     mProgressBar.setVisibility(View.VISIBLE);
				  getData(0, 10);
	}


	@Override
	public void onClick(View v) {
	
	}

	private void initEvent() {
		// TODO 自动生成的方法存根
		
	}

	private void initView() {
		// TODO 自动生成的方法存根
		
		back=(ImageButton)findViewById(R.id.back);
		recyclerView= (RecyclerView)findViewById(R.id.recyclerView);
		mProgressBar = (MyProgressBar)findViewById(R.id.progressbar);
		mswipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_container);
		//设置刷新时动画的颜色，可以设置4个
		 mswipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);		 
		//拿到RecyclerView
		layoutManager=new LinearLayoutManager(this);
		// 设置LinearLayoutManager
		recyclerView.setLayoutManager(layoutManager);
	}
	
	private void getData(final Integer page, Integer num) {
		// TODO 自动生成的方法存根
		 this.currentPage = page;

		 BmobQuery<Expressage> query = new BmobQuery<Expressage>();
		//查询page为currentPage的数据
		 query.addWhereEqualTo("express_company", data);
		 //返回数据
		 
		 query.setLimit(num);
		 query.order("-updatedAt");
		 query.setSkip(currentPage*10);
		 query.findObjects(new FindListener<Expressage>() {

			@Override
			public void done(java.util.List<Expressage> arg0, BmobException e) {
				// TODO 自动生成的方法存根
				 if(e==null){
					    preitem=arg0.get(0);
					    
						if (page == 0) {
							 //清空之前的数据预防重复加载
							 List.clear();
						 }
						if(arg0.size()!=0){
						 for(Expressage iem:arg0){
							 List.add(iem);
						 }
						 if(adapter==null){
							 adapter=new RecyclerViewAdapter(List,Search.this);
							 recyclerView.setAdapter(adapter);
						 }
						 else{
							 adapter.refreshData(List);
							 
						 }
						 isLoading = false;
						 mProgressBar.setVisibility(View.GONE);
						 mswipeRefreshLayout.setRefreshing(false);
			        }
						else{
							 Toast.makeText(Search.this, "没有了", Toast.LENGTH_SHORT).show();
							 mProgressBar.setVisibility(View.GONE);
							 mswipeRefreshLayout.setRefreshing(false);
						}
				 }
				 else{
					 isLoading = false;
					 mProgressBar.setVisibility(View.GONE);
			        	Toast.makeText(Search.this, "失败", Toast.LENGTH_SHORT).show();
			        	 mswipeRefreshLayout.setRefreshing(false);
			        }
			}

			
		 }); 
		
	}
	
	
}
