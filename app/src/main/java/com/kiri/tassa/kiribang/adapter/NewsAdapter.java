package com.kiri.tassa.kiribang.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kiri.tassa.kiribang.DetailNewsActivity;
import com.kiri.tassa.kiribang.R;
import com.kiri.tassa.kiribang.model.News;

import java.util.ArrayList;

/**
 * Created by Lenovo on 13/01/2017.
 */

public class NewsAdapter  extends BaseAdapter {
    Context c;
    ArrayList<News> newsModel;

    public NewsAdapter(Context c, ArrayList<News> newsModel) {
        this.c = c;
        this.newsModel = newsModel;
    }

    @Override
    public int getCount() {
        return newsModel.size();
    }

    @Override
    public Object getItem(int pos) {
        return newsModel.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(c).inflate(R.layout.model_news,viewGroup,false);
        }

        TextView namaNews= (TextView) convertView.findViewById(R.id.nama);
        TextView ruteNews= (TextView) convertView.findViewById(R.id.rutenews);
        TextView descNews= (TextView) convertView.findViewById(R.id.descNews);


        final News s= (News) this.getItem(position);

        namaNews.setText(s.getName());
        ruteNews.setText(s.getRouteNews());
        descNews.setText(s.getNews());


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //OPEN DETAIL
                openDetailActivity(s.getName(),s.getRouteNews(),s.getNews());
            }
        });

        return convertView;
    }



    //OPEN DETAIL ACTIVITY
    private void openDetailActivity(String...details)
    {
        Intent i=new Intent(c, DetailNewsActivity.class);
        i.putExtra("NO_KEY",details[0]);
        i.putExtra("ROUTE_KEY",details[1]);
        i.putExtra("DESC_KEY",details[2]);


        c.startActivity(i);
    }
}
