package com.example.z_shenou;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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
import cn.bmob.v3.listener.QueryListener;

public class gf_RecyclerViewAdapter extends RecyclerView.Adapter<gf_RecyclerViewAdapter.NewsViewHolder> implements OnClickListener{

    private List<Expressage> items;
    private Context context;
    private MyUser pulish_user;
    private int j;
    
    public gf_RecyclerViewAdapter(List<Expressage> items,Context context) {
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
        Button iv_call;


        public NewsViewHolder(final View itemView) {
            super(itemView);
            cardView= (CardView) itemView.findViewById(R.id.card_view);
            iv_userhead= (ImageView) itemView.findViewById(R.id.pulishuserimage);
            iv_kuaidi= (TextView)itemView.findViewById(R.id.ex_kuaidi);
            iv_time= (TextView) itemView.findViewById(R.id.ex_time);
            iv_call=(Button)itemView.findViewById(R.id.ex_call);
            //设置TextView背景为半透明
            //iv_name.setBackgroundColor(Color.argb(20, 0, 0, 0));
        }
    }
    
    
    @Override
    public gf_RecyclerViewAdapter.NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(context).inflate(R.layout.gf_express,viewGroup,false);
        NewsViewHolder nvh=new NewsViewHolder(v);
        return nvh;
    }

    @Override
    public void onBindViewHolder(gf_RecyclerViewAdapter.NewsViewHolder personViewHolder, int i) {
        
        j=i;
        personViewHolder.iv_userhead.setImageResource(R.drawable.unlogin);
        personViewHolder.iv_kuaidi.setText(items.get(i).getExpress_company());
        personViewHolder.iv_time.setText(items.get(i).getCreatedAt().toString());
        personViewHolder.iv_call.setOnClickListener(this);

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
		Intent intent;
		 switch (v.getId()) {
		 case R.id.ex_call:
		 intent = new Intent(Intent.ACTION_DIAL);
		 Uri data = Uri.parse("tel:" + items.get(j).getTelephone());
		 intent.setData(data);
		 context.startActivity(intent);
	}
	}
	
	
}
