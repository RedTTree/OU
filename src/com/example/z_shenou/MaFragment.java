package com.example.z_shenou;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class MaFragment extends Fragment {
	
	private View maView;
	ListView list;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//  每个Fragment里都只有一个简单的view用于演示界面

		maView=inflater.inflate(R.layout.p2, container, false);
		list=(ListView)maView.findViewById(R.id.ListView01);
		//生成适配器的Item和动态数组对应的元素
				SimpleAdapter ItemAdapter=new SimpleAdapter(
						getActivity(),
						getData(),//数据源
						R.layout.p2item,//内部布局
						new String[]{"img","massage"},
						new int[]{R.id.img,R.id.massage}
						);
				//添加适配器
				list.setAdapter(ItemAdapter);
				
				list.setOnItemClickListener(new OnItemClickListener(){

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
						// TODO 自动生成的方法存根
						Map<String,Object>clkmap=(Map<String,Object>)arg0.getItemAtPosition(arg2);
					}
				});

        return maView;
    }
	private List<? extends Map<String, ?>> getData() {
		// TODO 自动生成的方法存根
		ArrayList<Map<String,Object>>listitem=new ArrayList<Map<String,Object>>();
		Map<String,Object>map3=new HashMap<String,Object>();
		Map<String,Object>map1=new HashMap<String,Object>();
		Map<String,Object>map2=new HashMap<String,Object>();
		map1.put("img",R.drawable.f1);
		map1.put("massage", "消息");
		listitem.add(map1);
		map2.put("img",R.drawable.f2);
		map2.put("massage", "好友");
		listitem.add(map2);
		map3.put("img",R.drawable.f3);
		map3.put("massage", "系统信息");
		listitem.add(map3);
		return listitem;
	}
}

