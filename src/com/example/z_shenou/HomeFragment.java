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
//  ÿ��Fragment�ﶼֻ��һ���򵥵�view������ʾ����
		 homeView=inflater.inflate(R.layout.p0, container, false);

		 LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
		// �õ�RecyclerView
		 recyclerView= (RecyclerView)homeView.findViewById(R.id.recyclerView);
		 //�������
		 initPersonData();
		// ��ʼ���Զ����������
		 adapter=new RecyclerViewAdapter(newsList,getActivity());
		// ���ù̶���С
		 recyclerView.setHasFixedSize(true);
			// ����LinearLayoutManager
	     recyclerView.setLayoutManager(layoutManager);
	  // ����������
	     recyclerView.setAdapter(adapter);
		 return homeView;
    }

	private void initPersonData() {
		// TODO �Զ����ɵķ������
		 newsList =new ArrayList<News>();
		 newsList.add(new News(getString(R.string.news_one_title),getString(R.string.news_one_desc),R.drawable.bg04));
	     newsList.add(new News(getString(R.string.news_two_title),getString(R.string.news_two_desc),R.drawable.bg04));
	     newsList.add(new News(getString(R.string.news_three_title),getString(R.string.news_three_desc),R.drawable.bg04));
	     newsList.add(new News(getString(R.string.news_four_title),getString(R.string.news_four_desc),R.drawable.bg04));
	}
}
