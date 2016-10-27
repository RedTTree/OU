package com.example.z_shenou;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class pulishFragment extends Fragment{
	
	  private View pf_View;
	  //列表
	  private RecyclerView recyclerView;
	  private LinearLayoutManager layoutManager;
	  private pf_RecyclerViewAdapter adapter;
	  //数据源
	  private Expressage list;
	  private List<Expressage> List;
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
	  //刷新
	  private SwipeRefreshLayout mswipeRefreshLayout;
	
	
	public View onCreateView(LayoutInflater inflater,
            @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		pf_View=  inflater.inflate(R.layout.pulishfragment, container, false); 
		
		
		initView();
		TopFlash();
		initAdapter();	
	    DownFlash();
        return pf_View;   
}


	private void TopFlash() {
		// TODO 自动生成的方法存根
		mswipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			
            @Override
            public void onRefresh() {
            	
            	new Handler().postDelayed(new Runnable() {
            		   public void run() {
            		 getData(0, 10);
                }
            }, 4800);
       }

});
	}


	private void DownFlash() {
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


	private void getData(final int page, int num) {
		// TODO 自动生成的方法存根
		 this.currentPage = page;

			 BmobQuery<Expressage> query1 = new BmobQuery<Expressage>();
			//查询page为currentPage的数据
			 query1.addWhereEqualTo("isjie", false);
			 //返回数据
			 BmobQuery<Expressage> query2 = new BmobQuery<Expressage>();
				//查询page为currentPage的数据
		     query2.addWhereEqualTo("isfinish", false);
				 //返回数据
			 List<BmobQuery<Expressage>> andQuerys = new ArrayList<BmobQuery<Expressage>>();
			 andQuerys.add(query1);
			 andQuerys.add(query2);
			 
			 BmobQuery<Expressage> query = new BmobQuery<Expressage>();
			 query.and(andQuerys);
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
								 adapter=new pf_RecyclerViewAdapter(List,getActivity());
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
								 mswipeRefreshLayout.setRefreshing(false);
							}
					 }
					 else{
						 isLoading = false;
						 mProgressBar.setVisibility(View.GONE);
				        	Toast.makeText(getActivity(), "失败", Toast.LENGTH_SHORT).show();
				        	 mswipeRefreshLayout.setRefreshing(false);
				        }
				}

				
			 }); 	 
	}
	


	private void initAdapter() {
		// TODO 自动生成的方法存根
		//添加数据
		List=new ArrayList<Expressage>();
		// 初始化自定义的适配器
		adapter=new pf_RecyclerViewAdapter(List,getActivity());
		// 设置固定大小
		recyclerView.setHasFixedSize(true); 
		// 设置适配器
		recyclerView.setAdapter(adapter);
		//刚开始加载10条数据
		 isLoading = true;
  	     mProgressBar.setVisibility(View.VISIBLE);
		getData(0, 10);
	}


	private void initView() {
		// TODO 自动生成的方法存根
				recyclerView= (RecyclerView)pf_View.findViewById(R.id.pf_recyclerView);
				mProgressBar = (MyProgressBar) pf_View.findViewById(R.id.pf_progressbar);
				//拿到RecyclerView
				mswipeRefreshLayout = (SwipeRefreshLayout) pf_View.findViewById(R.id.swipe_container);
				//设置刷新时动画的颜色，可以设置4个
				 mswipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);		 
				layoutManager=new LinearLayoutManager(getActivity());
				// 设置LinearLayoutManager
				recyclerView.setLayoutManager(layoutManager);
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
