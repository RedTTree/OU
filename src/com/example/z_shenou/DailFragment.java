package com.example.z_shenou;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DailFragment extends Fragment {
	
	private View dailView;
	private ViewPager viewpager;
	private TextView tv1;
	private TextView tv2;
	private TextView tv3;
	private ImageView tabline;
	private List<Fragment>list;//存放三个F页面
	
	private int tabLineLenght;
	private int currentPage = 0;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//  每个Fragment里都只有一个简单的view用于演示界面
		dailView=inflater.inflate(R.layout.p1, container, false);
		//初始化滑动条
		initTabLine();
		//初始化界面
		initView();
		
        return dailView;
    }
	private void initView() {
		// TODO 自动生成的方法存根
		viewpager=(ViewPager) dailView.findViewById(R.id.viewpager);
		tv1=(TextView) dailView.findViewById(R.id.dan1);
		tv2=(TextView) dailView.findViewById(R.id.dan2);
		tv3=(TextView) dailView.findViewById(R.id.dan3);
		
		//设置Fragment
		pulishFragment pf=new pulishFragment();
		getFragment gf=new getFragment();
		finishFragment ff=new finishFragment();
		
		list=new ArrayList<Fragment>();
		
		list.add(pf);
		list.add(gf);
		list.add(ff);
		
		FragmentPagerAdapter adapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager()){

			@Override
			public Fragment getItem(int arg0) {
				// TODO 自动生成的方法存根
				return list.get(arg0);
			}

			@Override
			public int getCount() {
				// TODO 自动生成的方法存根
				 return list.size();
			}
			
		};
		
		// 绑定适配器
		 viewpager.setAdapter(adapter);
		 
		 //监听
		 viewpager.setOnPageChangeListener(new OnPageChangeListener(){

			@Override
			public void onPageScrollStateChanged(int position) {
				// TODO 自动生成的方法存根
			}
           /*
	             * position :当前页面，及你点击滑动的页面 offset:当前页面偏移的百分比 
	             * offsetPixels:当前页面偏移的像素位置 
	             */ 
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO 自动生成的方法存根
				Log.i("tuzi", arg0 + "," + arg1 + "," + arg2);
				
				  LinearLayout.LayoutParams ll = (android.widget.LinearLayout.LayoutParams) tabline .getLayoutParams();
			
				  if (currentPage == 0 && arg0 == 0) { // 0->1移动(第一页到第二页)
					               ll.leftMargin = (int) (currentPage * tabLineLenght + arg1 * tabLineLenght);
					              } else if (currentPage == 1 && arg0 == 1) { // 1->2移动（第二页到第三页）
					                   ll.leftMargin = (int) (currentPage * tabLineLenght + arg1
					                          * tabLineLenght);
					                  } else if (currentPage == 1 && arg0 == 0) { // 1->0移动（第二页到第一页）
					                     ll.leftMargin = (int) (currentPage * tabLineLenght - ((1 - arg1) * tabLineLenght));
					                  } else if (currentPage == 2 && arg0 == 1) { // 2->1移动（第三页到第二页）
					                   ll.leftMargin = (int) (currentPage * tabLineLenght - (1 - arg1)
					                               * tabLineLenght);
					                  }
					  
					                   tabline.setLayoutParams(ll);
			}

			@Override
			public void onPageSelected(int position) {
				// TODO 自动生成的方法存根
				 // 当页面被选择时，先讲3个textview的字体颜色初始化成黑
				 tv1.setTextColor(Color.BLACK);
				 tv2.setTextColor(Color.BLACK);
				 tv3.setTextColor(Color.BLACK);
				 
				   // 再改变当前选择页（position）对应的textview颜色
				                 switch (position) {
				                  case 0:
				                      tv1.setTextColor(Color.rgb(255, 136, 144));
				                      break;
				                  case 1:
				                      tv2.setTextColor(Color.rgb(255, 136, 144));
				                      break;
				                  case 2:
				                      tv3.setTextColor(Color.rgb(255, 136, 144));
				                      break;
				                  }
				  
				                  currentPage = position;
			}
			 
		 });
	}
	

	private void initTabLine() {
		// TODO 自动生成的方法存根
		//屏幕信息
		Display display=getActivity().getWindow().getWindowManager().getDefaultDisplay();
		//宽度
		DisplayMetrics metrics=new DisplayMetrics();
		display.getMetrics(metrics);
		tabLineLenght=metrics.widthPixels/3;
		//控件实例
		tabline =(ImageView)dailView.findViewById(R.id.tabline);
		//相关参数
		LayoutParams lp=tabline.getLayoutParams();
		lp.width=tabLineLenght;
		tabline.setLayoutParams(lp);
	}
	}

