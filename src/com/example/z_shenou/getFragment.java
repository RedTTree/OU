package com.example.z_shenou;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class getFragment extends Fragment{
	  private View gf_View;
	  //�б�
	  private RecyclerView recyclerView;
	  private LinearLayoutManager layoutManager;
	  private gf_RecyclerViewAdapter adapter;
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
	//ͨ�����ػ����¼
			private MyUser user;
	
	public View onCreateView(LayoutInflater inflater,
          @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		gf_View=  inflater.inflate(R.layout.getfragment, container, false); 
		Bundle bundle = getArguments();
	    user=(MyUser)bundle.getSerializable("user");//��¼���û�
	    
		initView();
		initAdapter();	
	    DownFlash();
      return gf_View;   
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
			 query1.addWhereEqualTo("isjie", true);
			 //��������
			 BmobQuery<Expressage> query2 = new BmobQuery<Expressage>();
				//��ѯpageΪcurrentPage������
		     query2.addWhereEqualTo("isfinish", false);
				 //��������
		     BmobQuery<Expressage> query3 = new BmobQuery<Expressage>();
				//��ѯpageΪcurrentPage������
		     query3.addWhereEqualTo("jieshou_user", user);
				 //��������
			 List<BmobQuery<Expressage>> andQuerys = new ArrayList<BmobQuery<Expressage>>();
			 andQuerys.add(query1);
			 andQuerys.add(query2);
			 andQuerys.add(query3);
			 
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
								 adapter=new gf_RecyclerViewAdapter(List,getActivity());
								 recyclerView.setAdapter(adapter);
							 }
							 else{
								 adapter.refreshData(List);
							 }
							 isLoading = false;
							 mProgressBar.setVisibility(View.GONE);
				
				        }
							else{
								 Toast.makeText(getActivity(), "û����", Toast.LENGTH_SHORT).show();
								 mProgressBar.setVisibility(View.GONE);
								 
							}
					 }
					 else{
						 isLoading = false;
						 mProgressBar.setVisibility(View.GONE);
				        	Toast.makeText(getActivity(), "ʧ��", Toast.LENGTH_SHORT).show();
				        }
				}

				
			 }); 	 
	}
	


	private void initAdapter() {
		// TODO �Զ����ɵķ������
		//�������
		List=new ArrayList<Expressage>();
		// ��ʼ���Զ����������
		adapter=new gf_RecyclerViewAdapter(List,getActivity());
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
				recyclerView= (RecyclerView)gf_View.findViewById(R.id.gf_recyclerView);
				mProgressBar = (MyProgressBar) gf_View.findViewById(R.id.gf_progressbar);
				//�õ�RecyclerView
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