package com.example.z_shenou;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.NewsViewHolder>{

    private List<Items> items;
    private Context context;

    public RecyclerViewAdapter(List<Items> items,Context context) {
        this.items = items;
        this.context=context;
    }


    //自定义ViewHolder类
    static class NewsViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView iv_userhead;
        TextView iv_name;
        TextView iv_content;


        public NewsViewHolder(final View itemView) {
            super(itemView);
            cardView= (CardView) itemView.findViewById(R.id.card_view);
            iv_userhead= (ImageView) itemView.findViewById(R.id.iv_userhead);
            iv_name= (TextView)itemView.findViewById(R.id.iv_name);
            iv_content= (TextView) itemView.findViewById(R.id.iv_content);
            //设置TextView背景为半透明
            //iv_name.setBackgroundColor(Color.argb(20, 0, 0, 0));
        }
    }
    @Override
    public RecyclerViewAdapter.NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.p0item,viewGroup,false);
        NewsViewHolder nvh=new NewsViewHolder(v);
        return nvh;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.NewsViewHolder personViewHolder, int i) {
        final int j=i;

        personViewHolder.iv_userhead.setImageResource(items.get(i).getuserhead());
        personViewHolder.iv_name.setText(items.get(i).getname());
        personViewHolder.iv_content.setText(items.get(i).getcontent());

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


}
