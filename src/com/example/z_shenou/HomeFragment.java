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
	//�Ƿ����ڼ���
	      private boolean isLoading = false;
	     //��ǰҳ��
	      private int currentPage = 1;
	  private RecyclerViewAdapter adapter;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//  ÿ��Fragment�ﶼֻ��һ���򵥵�view������ʾ����
		 homeView=inflater.inflate(R.layout.p0, container, false);
		
		layoutManager=new LinearLayoutManager(getActivity());
		// �õ�RecyclerView
		 recyclerView= (RecyclerView)homeView.findViewById(R.id.recyclerView);
		 //�������
		 initPersonData();
		// ��ʼ���Զ����������
		 adapter=new RecyclerViewAdapter(List,getActivity());
		// ���ù̶���С
		 recyclerView.setHasFixedSize(true);
			// ����LinearLayoutManager
	     recyclerView.setLayoutManager(layoutManager);
	  // ����������
	     recyclerView.setAdapter(adapter);
	     
	     
	    TopFlash();
	    DownFlash();
			
		 return homeView;
    }

	private void DownFlash() {
		// TODO �Զ����ɵķ������
		  mBaseAttacher = Mugen.with(recyclerView, new MugenCallbacks() {
			               @Override
			               public void onLoadMore() {
			                   //���ظ���
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
		// TODO �Զ����ɵķ������
		 mswipeRefreshLayout = (SwipeRefreshLayout) homeView.findViewById(R.id.swipe_container);
			//����ˢ��ʱ��������ɫ����������4��
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
		// TODO �Զ����ɵķ������
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
