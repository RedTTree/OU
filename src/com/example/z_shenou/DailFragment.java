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
	private List<Fragment>list;//�������Fҳ��
	
	private int tabLineLenght;
	private int currentPage = 0;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//  ÿ��Fragment�ﶼֻ��һ���򵥵�view������ʾ����
		dailView=inflater.inflate(R.layout.p1, container, false);
		//��ʼ��������
		initTabLine();
		//��ʼ������
		initView();
		
        return dailView;
    }
	private void initView() {
		// TODO �Զ����ɵķ������
		viewpager=(ViewPager) dailView.findViewById(R.id.viewpager);
		tv1=(TextView) dailView.findViewById(R.id.dan1);
		tv2=(TextView) dailView.findViewById(R.id.dan2);
		tv3=(TextView) dailView.findViewById(R.id.dan3);
		
		//����Fragment
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
				// TODO �Զ����ɵķ������
				return list.get(arg0);
			}

			@Override
			public int getCount() {
				// TODO �Զ����ɵķ������
				 return list.size();
			}
			
		};
		
		// ��������
		 viewpager.setAdapter(adapter);
		 
		 //����
		 viewpager.setOnPageChangeListener(new OnPageChangeListener(){

			@Override
			public void onPageScrollStateChanged(int position) {
				// TODO �Զ����ɵķ������
			}
           /*
	             * position :��ǰҳ�棬������������ҳ�� offset:��ǰҳ��ƫ�Ƶİٷֱ� 
	             * offsetPixels:��ǰҳ��ƫ�Ƶ�����λ�� 
	             */ 
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO �Զ����ɵķ������
				Log.i("tuzi", arg0 + "," + arg1 + "," + arg2);
				
				  LinearLayout.LayoutParams ll = (android.widget.LinearLayout.LayoutParams) tabline .getLayoutParams();
			
				  if (currentPage == 0 && arg0 == 0) { // 0->1�ƶ�(��һҳ���ڶ�ҳ)
					               ll.leftMargin = (int) (currentPage * tabLineLenght + arg1 * tabLineLenght);
					              } else if (currentPage == 1 && arg0 == 1) { // 1->2�ƶ����ڶ�ҳ������ҳ��
					                   ll.leftMargin = (int) (currentPage * tabLineLenght + arg1
					                          * tabLineLenght);
					                  } else if (currentPage == 1 && arg0 == 0) { // 1->0�ƶ����ڶ�ҳ����һҳ��
					                     ll.leftMargin = (int) (currentPage * tabLineLenght - ((1 - arg1) * tabLineLenght));
					                  } else if (currentPage == 2 && arg0 == 1) { // 2->1�ƶ�������ҳ���ڶ�ҳ��
					                   ll.leftMargin = (int) (currentPage * tabLineLenght - (1 - arg1)
					                               * tabLineLenght);
					                  }
					  
					                   tabline.setLayoutParams(ll);
			}

			@Override
			public void onPageSelected(int position) {
				// TODO �Զ����ɵķ������
				 // ��ҳ�汻ѡ��ʱ���Ƚ�3��textview��������ɫ��ʼ���ɺ�
				 tv1.setTextColor(Color.BLACK);
				 tv2.setTextColor(Color.BLACK);
				 tv3.setTextColor(Color.BLACK);
				 
				   // �ٸı䵱ǰѡ��ҳ��position����Ӧ��textview��ɫ
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
		// TODO �Զ����ɵķ������
		//��Ļ��Ϣ
		Display display=getActivity().getWindow().getWindowManager().getDefaultDisplay();
		//���
		DisplayMetrics metrics=new DisplayMetrics();
		display.getMetrics(metrics);
		tabLineLenght=metrics.widthPixels/3;
		//�ؼ�ʵ��
		tabline =(ImageView)dailView.findViewById(R.id.tabline);
		//��ز���
		LayoutParams lp=tabline.getLayoutParams();
		lp.width=tabLineLenght;
		tabline.setLayoutParams(lp);
	}
	}

