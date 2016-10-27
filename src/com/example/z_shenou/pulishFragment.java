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
	  //�б�
	  private RecyclerView recyclerView;
	  private LinearLayoutManager layoutManager;
	  private pf_RecyclerViewAdapter adapter;
	  //����Դ
	  private Expressage list;
	  private List<Expressage> List;
	  //����
	  private Integer lastVisibleItem; 
      private BaseAttacher mBaseAttacher;
	  private MyProgressBar mProgressBar;
	  //�Ƿ����ڼ���
	  private boolean isLoading = false;
	  //��ǰҳ��
	  private Integer currentPage = 0;
	  //��ǰ��һ��������
	  Expressage preitem;
	  //ˢ��
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
		// TODO �Զ����ɵķ������
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
                //���ظ���
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
		// TODO �Զ����ɵķ������
		 this.currentPage = page;

			 BmobQuery<Expressage> query1 = new BmobQuery<Expressage>();
			//��ѯpageΪcurrentPage������
			 query1.addWhereEqualTo("isjie", false);
			 //��������
			 BmobQuery<Expressage> query2 = new BmobQuery<Expressage>();
				//��ѯpageΪcurrentPage������
		     query2.addWhereEqualTo("isfinish", false);
				 //��������
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
					// TODO �Զ����ɵķ������
					 if(e==null){
						    preitem=arg0.get(0);
						    
							if (page == 0) {
								 //���֮ǰ������Ԥ���ظ�����
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
								 Toast.makeText(getActivity(), "û����", Toast.LENGTH_SHORT).show();
								 mProgressBar.setVisibility(View.GONE);
								 mswipeRefreshLayout.setRefreshing(false);
							}
					 }
					 else{
						 isLoading = false;
						 mProgressBar.setVisibility(View.GONE);
				        	Toast.makeText(getActivity(), "ʧ��", Toast.LENGTH_SHORT).show();
				        	 mswipeRefreshLayout.setRefreshing(false);
				        }
				}

				
			 }); 	 
	}
	


	private void initAdapter() {
		// TODO �Զ����ɵķ������
		//�������
		List=new ArrayList<Expressage>();
		// ��ʼ���Զ����������
		adapter=new pf_RecyclerViewAdapter(List,getActivity());
		// ���ù̶���С
		recyclerView.setHasFixedSize(true); 
		// ����������
		recyclerView.setAdapter(adapter);
		//�տ�ʼ����10������
		 isLoading = true;
  	     mProgressBar.setVisibility(View.VISIBLE);
		getData(0, 10);
	}


	private void initView() {
		// TODO �Զ����ɵķ������
				recyclerView= (RecyclerView)pf_View.findViewById(R.id.pf_recyclerView);
				mProgressBar = (MyProgressBar) pf_View.findViewById(R.id.pf_progressbar);
				//�õ�RecyclerView
				mswipeRefreshLayout = (SwipeRefreshLayout) pf_View.findViewById(R.id.swipe_container);
				//����ˢ��ʱ��������ɫ����������4��
				 mswipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);		 
				layoutManager=new LinearLayoutManager(getActivity());
				// ����LinearLayoutManager
				recyclerView.setLayoutManager(layoutManager);
	}
	
	@Override  
	   public void setUserVisibleHint(boolean isVisibleToUser) {  
	       super.setUserVisibleHint(isVisibleToUser);  
	       if (isVisibleToUser) {  
		   		getData(0, 10);
	       } else {  
	           //�൱��Fragment��onPause  
	       }  
	   }  
}
