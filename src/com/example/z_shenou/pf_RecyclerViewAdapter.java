package com.example.z_shenou;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class pf_RecyclerViewAdapter extends RecyclerView.Adapter<pf_RecyclerViewAdapter.NewsViewHolder> implements OnClickListener{

    private List<Expressage> items;
    private Context context;
    private int j;
    private MyUser user;
    private MyUser jie_user;

    public pf_RecyclerViewAdapter(List<Expressage> items,Context context,MyUser user) {
        this.items = items;
        this.context=context;
        this.user=user;
    }
    /**
     * 设置新的数据源，提醒adatper更新
     *
     */
    public void refreshData(List<Expressage> ite) {
        this.items = ite;
        this.notifyDataSetChanged();
    }

    //自定义ViewHolder类
    static class NewsViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView iv_userhead;
        TextView iv_kuaidi;
        TextView iv_time;
        Button iv_true;


        public NewsViewHolder(final View itemView) {
            super(itemView);
            cardView= (CardView) itemView.findViewById(R.id.card_view);
            iv_userhead= (ImageView) itemView.findViewById(R.id.pulishuserimage);
            iv_kuaidi= (TextView)itemView.findViewById(R.id.ex_kuaidi);
            iv_time= (TextView) itemView.findViewById(R.id.ex_time);
            iv_true=(Button)itemView.findViewById(R.id.ex_true);
            //设置TextView背景为半透明
            //iv_name.setBackgroundColor(Color.argb(20, 0, 0, 0));
        }
    }
    
    
    @Override
    public pf_RecyclerViewAdapter.NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.pf_express,viewGroup,false);
        NewsViewHolder nvh=new NewsViewHolder(v);
        return nvh;
    }

    @Override
    public void onBindViewHolder(pf_RecyclerViewAdapter.NewsViewHolder personViewHolder, int i) {
        j=i;
        
        personViewHolder.iv_userhead.setImageResource(R.drawable.unlogin);
        personViewHolder.iv_kuaidi.setText(items.get(i).getExpress_company());
        personViewHolder.iv_time.setText(items.get(i).getCreatedAt().toString());
        personViewHolder.iv_true.setOnClickListener(this);
        
        jie_user=(MyUser)items.get(j).getJieshou();
        
        //为btn_share btn_readMore cardView设置点击事件
        personViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Item_content.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
	@Override
	public void onClick(View v) {
		// TODO 自动生成的方法存根
		 switch (v.getId()) {
		 case R.id.ex_true:
			 
			 Expressage newitem=new Expressage();
			 newitem.setIsfinish(true);
			 newitem.setIsjie(true);
			 newitem.update(items.get(j).getObjectId(),new UpdateListener(){

					@Override
					public void done(BmobException e) {
						// TODO 自动生成的方法存根
						  if(e==null){
							  initUpdate();
							  initJie_Update();
								Toast.makeText(context,"成功", Toast.LENGTH_SHORT).show();
					        }else{
					        	Toast.makeText(context,"失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
					        }
					}
					
				});
			 items.get(j).delete();
		 }
		 
	}
	
	private void initJie_Update() {
		// TODO 自动生成的方法存根
		if(user!=null){
			BmobQuery<MyUser> query = new BmobQuery<MyUser>();
			query.getObject(jie_user.getObjectId(), new QueryListener<MyUser>() {

			    @Override
			    public void done(MyUser object, BmobException e) {
			        if(e==null){
			        	jie_user=object;
			        	initQuery();
			        	Toast.makeText(context,"成功加", Toast.LENGTH_SHORT).show();
			        }else{
			        	Toast.makeText(context,"失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
			        }
			    }


			});
			}
	}
	
	private void initQuery() {
		// TODO 自动生成的方法存根
		MyUser newuser=new MyUser();	
		newuser.setOU_number(jie_user.getOU_number()+items.get(j).getPrice());
		newuser.update(jie_user.getObjectId(),new UpdateListener(){

			@Override
			public void done(BmobException e) {
				// TODO 自动生成的方法存根
				 if(e==null){				 
						Toast.makeText(context,"成功加加", Toast.LENGTH_SHORT).show();
			        }else{
			        	Toast.makeText(context,"失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
			        }
			}
			
		});
	}
	
	private void initUpdate() {
		// TODO 自动生成的方法存根
		MyUser newuser=new MyUser();	
		newuser.setOU_number(user.getOU_number()-items.get(j).getPrice());
		newuser.update(user.getObjectId(),new UpdateListener(){

			@Override
			public void done(BmobException e) {
				// TODO 自动生成的方法存根
				 if(e==null){
					 
						Toast.makeText(context,"成功减", Toast.LENGTH_SHORT).show();
			        }else{
			        	Toast.makeText(context,"失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
			        }
			}
			
		});
	}
	

	

}

