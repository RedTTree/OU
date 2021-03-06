package com.example.z_shenou;

import java.util.List;

import com.example.z_shenou.MeFragment.OnLoginSelectedListener;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.NewsViewHolder>{

    private List<Expressage> items;
    private Context context;
    private MyUser currentuser;
    
    public RecyclerViewAdapter(List<Expressage> items,Context context,MyUser currentuser) {
        this.items = items;
        this.context=context;
        this.currentuser= currentuser;
    }
    public RecyclerViewAdapter(List<Expressage> items,Context context) {
        this.items = items;
        this.context=context;
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
        Button iv_price;


        public NewsViewHolder(final View itemView) {
            super(itemView);
            cardView= (CardView) itemView.findViewById(R.id.card_view);
            iv_userhead= (ImageView) itemView.findViewById(R.id.pulishuserimage);
            iv_kuaidi= (TextView)itemView.findViewById(R.id.ex_kuaidi);
            iv_time= (TextView) itemView.findViewById(R.id.ex_time);
            iv_price=(Button)itemView.findViewById(R.id.ex_price);
            //设置TextView背景为半透明
            //iv_name.setBackgroundColor(Color.argb(20, 0, 0, 0));
        }
    }
    
    
    @Override
    public RecyclerViewAdapter.NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.activity_express,viewGroup,false);
        NewsViewHolder nvh=new NewsViewHolder(v);
        return nvh;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.NewsViewHolder personViewHolder, int i) {
        final int j=i;

        personViewHolder.iv_userhead.setImageResource(R.drawable.unlogin);
        personViewHolder.iv_kuaidi.setText(items.get(i).getExpress_company());
        personViewHolder.iv_time.setText(items.get(i).getCreatedAt().toString());
        personViewHolder.iv_price.setText("￥"+items.get(i).getPrice());

        //为btn_share btn_readMore cardView设置点击事件
        personViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
        	
            public void onClick(View v) {       
            		// TODO 自动生成的方法存根
            		Intent intent=new Intent(context,Item_content.class);
            	    Bundle bundle = new Bundle();
            	    bundle.putSerializable("express",  items.get(j));
            	    bundle.putSerializable("user",currentuser);
            	    intent.putExtras(bundle);
            	    context.startActivity(intent);
            	
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


	
}
