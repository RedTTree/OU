package com.example.z_shenou;

import java.util.ArrayList;
import java.util.List;

import com.example.z_shenou.R;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;/*
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;*/
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.z_shenou.Mugen;
import com.example.z_shenou.MugenCallbacks;
import com.example.z_shenou.BaseAttacher;

public class HomeFragment extends Fragment {
	  
      private int i=0;
      private int lastVisibleItem; 
      private BaseAttacher mBaseAttacher;
	  private View homeView;
	  private SwipeRefreshLayout mswipeRefreshLayout;
	  private RecyclerView recyclerView;
	  private LinearLayoutManager layoutManager;
	  private List<Items> List;
	//是否正在加载
	      private boolean isLoading = false;
	     //当前页数
	      private int currentPage = 1;
	  private RecyclerViewAdapter adapter;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//  每个Fragment里都只有一个简单的view用于演示界面
		 homeView=inflater.inflate(R.layout.p0, container, false);
		
		layoutManager=new LinearLayoutManager(getActivity());
		// 拿到RecyclerView
		 recyclerView= (RecyclerView)homeView.findViewById(R.id.recyclerView);
		 //添加数据
		 initPersonData();
		// 初始化自定义的适配器
		 adapter=new RecyclerViewAdapter(List,getActivity());
		// 设置固定大小
		 recyclerView.setHasFixedSize(true);
			// 设置LinearLayoutManager
	     recyclerView.setLayoutManager(layoutManager);
	  // 设置适配器
	     recyclerView.setAdapter(adapter);
	     
	     
	    TopFlash();
	    DownFlash();
			
		 return homeView;
    }

	private void DownFlash() {
		// TODO 自动生成的方法存根
		  mBaseAttacher = Mugen.with(recyclerView, new MugenCallbacks() {
			               @Override
			               public void onLoadMore() {
			                   //加载更多
			            	   isLoading = true;
			            	   List.add(new Items("item10",getString(R.string.news_four_content),R.drawable.bg04));
	                    	   adapter=new RecyclerViewAdapter(List,getActivity());
	                    	   recyclerView.setAdapter(adapter);
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
		 mswipeRefreshLayout = (SwipeRefreshLayout) homeView.findViewById(R.id.swipe_container);
			//设置刷新时动画的颜色，可以设置4个
		 mswipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
			 
			mswipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
				
	            @Override
	            public void onRefresh() {
	            	
	            	 new Handler().postDelayed(new Runnable() {
	            		                     
	            		                      @Override
	            		                      public void run() {
	            		                    	  i++;
	            		                          // TODO Auto-generated method stub
	            		                    	  List.add(0,new Items("item"+i,getString(R.string.news_one_content),R.drawable.bg04));
	            		                    	  adapter=new RecyclerViewAdapter(List,getActivity());
	            		                    	  recyclerView.setAdapter(adapter);
	            		                          mswipeRefreshLayout.setRefreshing(false);
	            		                          
	            		                      }
	            		                  }, 4800);
	            		             }
	           
	       });
	}

	private void initPersonData() {
		// TODO 自动生成的方法存根
		 List =new ArrayList<Items>();
		 List.add(new Items(getString(R.string.news_one_name),getString(R.string.news_one_content),R.drawable.bg04));
	     List.add(new Items(getString(R.string.news_two_name),getString(R.string.news_two_content),R.drawable.bg04));
	     List.add(new Items(getString(R.string.news_three_name),getString(R.string.news_three_content),R.drawable.bg04));
	     List.add(new Items(getString(R.string.news_four_name),getString(R.string.news_four_content),R.drawable.bg04));
	     
	     List.add(new Items("item11",getString(R.string.news_four_content),R.drawable.bg04));
	     List.add(new Items("item12",getString(R.string.news_four_content),R.drawable.bg04));
	     List.add(new Items("item13",getString(R.string.news_four_content),R.drawable.bg04));
	     List.add(new Items("item14",getString(R.string.news_four_content),R.drawable.bg04));
	}
}
