package com.example.z_shenou;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.z_shenou.R;
import com.example.z_shenou.MeFragment.OnLoginSelectedListener;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;/*
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;*/
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

import com.example.z_shenou.Mugen;
import com.example.z_shenou.MugenCallbacks;
import com.example.z_shenou.BaseAttacher;

public class HomeFragment extends Fragment {
	  
  
	  //搜索
	  private ImageButton search;
	
	  private View homeView;
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

	  
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//  每个Fragment里都只有一个简单的view用于演示界面
		 homeView=inflater.inflate(R.layout.p0, container, false);
		 Bundle bundle = getArguments();
		 currentuser=(MyUser)bundle.getSerializable("user");//登录的用户
		 
		initView();
		initAdapter();	
	    TopFlash();
	    DownFlash();
	    initSearch();
	    
		 return homeView;
    }

	private void initSearch() {
		// TODO 自动生成的方法存根
		
	search.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
					Intent intent=new Intent(getActivity(),search_guodu.class);
				    getActivity().startActivity(intent);
				
			}
			
			
		} );
		
		
			
		
		
	}

	private void initView() {
		// TODO 自动生成的方法存根
		search=(ImageButton)homeView.findViewById(R.id.ib_search);
		recyclerView= (RecyclerView)homeView.findViewById(R.id.recyclerView);
		mProgressBar = (MyProgressBar) homeView.findViewById(R.id.progressbar);
		mswipeRefreshLayout = (SwipeRefreshLayout) homeView.findViewById(R.id.swipe_container);
		//设置刷新时动画的颜色，可以设置4个
		 mswipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);		 
		//拿到RecyclerView
		layoutManager=new LinearLayoutManager(getActivity());
		// 设置LinearLayoutManager
		recyclerView.setLayoutManager(layoutManager);
	}

	private void initAdapter() {
		// TODO 自动生成的方法存根
		//添加数据
		List=new ArrayList<Expressage>();
		// 初始化自定义的适配器
		adapter=new RecyclerViewAdapter(List,getActivity(),currentuser);
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

	//上拉加载
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

	//下拉刷新
	private void TopFlash() {
		// TODO 自动生成的方法存根
		
		mswipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			               @Override
			              public void onRefresh() {
			                   getData(0, 10);
			               }
			       });
	}

	private void initPersonData() {
		// TODO 自动生成的方法存根
		
		/* mList.add(new Items(getString(R.string.news_one_name),getString(R.string.news_one_content),R.drawable.bg04));
	     mList.add(new Items(getString(R.string.news_two_name),getString(R.string.news_two_content),R.drawable.bg04));
	     mList.add(new Items(getString(R.string.news_three_name),getString(R.string.news_three_content),R.drawable.bg04));
	     mList.add(new Items(getString(R.string.news_four_name),getString(R.string.news_four_content),R.drawable.bg04));  
		 mList.add(new Items(1,"item"+currentPage,getString(R.string.news_four_content),R.drawable.bg05));
		 mList.add(new Items(1,"item"+currentPage,getString(R.string.news_four_content),R.drawable.bg04));
		 mList.add(new Items(1,"item"+currentPage,getString(R.string.news_four_content),R.drawable.bg04));
		list=new Items();
		list.setname("item"+currentPage);
		list.setuserhead(R.drawable.bg04);
		list.setcontent(getString(R.string.news_four_content));
		list.setnumber(1);
		list.save(new SaveListener<String>() {

			@Override
			public void done(String arg0, BmobException e) {
				// TODO 自动生成的方法存根
				 if(e==null){
					 Toast.makeText(getActivity(), "创建数据成功：" + arg0, Toast.LENGTH_SHORT).show();
			        }else{
			        	Toast.makeText(getActivity(), "失败：" + e.toString(), Toast.LENGTH_SHORT).show();
			}
			}
		});*/
	}


private void getData(final Integer page, Integer num) {
	// TODO 自动生成的方法存根
	 this.currentPage = page;

		 BmobQuery<Expressage> query = new BmobQuery<Expressage>();
		//查询page为currentPage的数据
		 query.addWhereEqualTo("isjie", false);/*
		// BmobQuery<Expressage> query = new BmobQuery<Expressage>();
		 //query.addWhereEqualTo("isjie", true); 
		  * 
		  query.order("-updatedAt");
          query.include("pulish_user");
          query.findObjects(new FindListener<Expressage>() {
          @Override
          public void done(List<Expressage> object,BmobException e) {
          if(e==null){
            Log.i("bmob","成功");
          }else{
            Log.i("bmob","失败："+e.getMessage());
         }
         }});
*/
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
							 adapter=new RecyclerViewAdapter(List,getActivity(),currentuser);
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
							 Toast.makeText(getActivity(), "没有了", Toast.LENGTH_SHORT).show();
							 mProgressBar.setVisibility(View.GONE);
							 mswipeRefreshLayout .setRefreshing(false);
						}
				 }
				 else{
					 isLoading = false;
					 mProgressBar.setVisibility(View.GONE);
			        	Toast.makeText(getActivity(), "失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
			        }
			}
		 }); 	 
}

@Override  
public void setUserVisibleHint(boolean isVisibleToUser) {  
    super.setUserVisibleHint(isVisibleToUser);  
    if (isVisibleToUser) {  
    	   getData(0, 10);
    } else {  
        //相当于Fragment的onPause  
    }  
}  

}