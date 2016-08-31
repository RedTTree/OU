package com.example.z_shenou;

import java.util.ArrayList;
import java.util.List;

import com.example.z_shenou.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;/*
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;*/
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomeFragment extends Fragment {
	  
	  private View homeView;
	  private RecyclerView recyclerView;
	  private List<News> newsList;
	  private RecyclerViewAdapter adapter;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//  每个Fragment里都只有一个简单的view用于演示界面
		 homeView=inflater.inflate(R.layout.p0, container, false);

		 LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
		// 拿到RecyclerView
		 recyclerView= (RecyclerView)homeView.findViewById(R.id.recyclerView);
		 //添加数据
		 initPersonData();
		// 初始化自定义的适配器
		 adapter=new RecyclerViewAdapter(newsList,getActivity());
		// 设置固定大小
		 recyclerView.setHasFixedSize(true);
			// 设置LinearLayoutManager
	     recyclerView.setLayoutManager(layoutManager);
	  // 设置适配器
	     recyclerView.setAdapter(adapter);
		 return homeView;
    }

	private void initPersonData() {
		// TODO 自动生成的方法存根
		 newsList =new ArrayList<News>();
		 newsList.add(new News(getString(R.string.news_one_title),getString(R.string.news_one_desc),R.drawable.bg04));
	     newsList.add(new News(getString(R.string.news_two_title),getString(R.string.news_two_desc),R.drawable.bg04));
	     newsList.add(new News(getString(R.string.news_three_title),getString(R.string.news_three_desc),R.drawable.bg04));
	     newsList.add(new News(getString(R.string.news_four_title),getString(R.string.news_four_desc),R.drawable.bg04));
	}
}
