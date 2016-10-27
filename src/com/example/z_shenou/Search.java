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
	
	 //����
	 private ImageButton back;
	 //�û�
	  private MyUser currentuser;
	  //�б�
	  private RecyclerView recyclerView;
	  private LinearLayoutManager layoutManager;
	  private RecyclerViewAdapter adapter;
	  //����Դ
	  private Expressage list;
	  private List<Expressage> List;
	  //ˢ��
	  private SwipeRefreshLayout mswipeRefreshLayout;
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
	  //intent
	  Intent intent;
	  String  data; 
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO �Զ����ɵķ������
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.search);
		
		intent=this.getIntent();
		data=intent.getStringExtra("search");
		
		// ��ʼ���ؼ�
        initView();
        // ��ʼ���ײ���ť�¼�
        initEvent();
		initAdapter();	
	    TopFlash();
	    DownFlash();
	}
	
	

	private void DownFlash() {
		// TODO �Զ����ɵķ������
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


	private void TopFlash() {
		// TODO �Զ����ɵķ������
		mswipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
           public void onRefresh() {
                getData(0, 10);
            }
    });
	}


	private void initAdapter() {
		// TODO �Զ����ɵķ������
				//�������
				List=new ArrayList<Expressage>();
				// ��ʼ���Զ����������
				adapter=new RecyclerViewAdapter(List,this,currentuser);
				// ���ù̶���С
				recyclerView.setHasFixedSize(true); 
				// ����������
				recyclerView.setAdapter(adapter);
				mswipeRefreshLayout.setRefreshing(true);
				//�տ�ʼ����10������
				 isLoading = true;
		  	     mProgressBar.setVisibility(View.VISIBLE);
				  getData(0, 10);
	}


	@Override
	public void onClick(View v) {
	
	}

	private void initEvent() {
		// TODO �Զ����ɵķ������
		
	}

	private void initView() {
		// TODO �Զ����ɵķ������
		
		back=(ImageButton)findViewById(R.id.back);
		recyclerView= (RecyclerView)findViewById(R.id.recyclerView);
		mProgressBar = (MyProgressBar)findViewById(R.id.progressbar);
		mswipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_container);
		//����ˢ��ʱ��������ɫ����������4��
		 mswipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);		 
		//�õ�RecyclerView
		layoutManager=new LinearLayoutManager(this);
		// ����LinearLayoutManager
		recyclerView.setLayoutManager(layoutManager);
	}
	
	private void getData(final Integer page, Integer num) {
		// TODO �Զ����ɵķ������
		 this.currentPage = page;

		 BmobQuery<Expressage> query = new BmobQuery<Expressage>();
		//��ѯpageΪcurrentPage������
		 query.addWhereEqualTo("express_company", data);
		 //��������
		 
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
							 Toast.makeText(Search.this, "û����", Toast.LENGTH_SHORT).show();
							 mProgressBar.setVisibility(View.GONE);
							 mswipeRefreshLayout.setRefreshing(false);
						}
				 }
				 else{
					 isLoading = false;
					 mProgressBar.setVisibility(View.GONE);
			        	Toast.makeText(Search.this, "ʧ��", Toast.LENGTH_SHORT).show();
			        	 mswipeRefreshLayout.setRefreshing(false);
			        }
			}

			
		 }); 
		
	}
	
	
}
