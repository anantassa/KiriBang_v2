package com.kiri.tassa.kiribang.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kiri.tassa.kiribang.DetailAngkotActivity;

import com.kiri.tassa.kiribang.FilterAngkot;

import com.kiri.tassa.kiribang.R;
import com.kiri.tassa.kiribang.model.AngkotModel;


import java.util.ArrayList;

/**
 * Created by Lenovo on 06/01/2017.
 */

public class AngkotAdapter extends BaseAdapter {
    Context c;
    ArrayList<AngkotModel> angkotModels;

    public AngkotAdapter(Context c, ArrayList<AngkotModel> angkotModels) {
        this.c = c;
        this.angkotModels = angkotModels;
    }

    @Override
    public int getCount() {
        return angkotModels.size();
    }

    @Override
    public Object getItem(int pos) {
        return angkotModels.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(c).inflate(R.layout.model_angkot,viewGroup,false);
        }

        TextView noAngkot= (TextView) convertView.findViewById(R.id.noAngkot);
        TextView ruteAngkot= (TextView) convertView.findViewById(R.id.ruteangkot);
        TextView descAngkotTxt= (TextView) convertView.findViewById(R.id.descAngkotTxt);


        final AngkotModel s= (AngkotModel) this.getItem(position);

        noAngkot.setText(s.getAngkot());
        ruteAngkot.setText(s.getRouteAngkot());
        descAngkotTxt.setText(s.getDescAngkot());


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //OPEN DETAIL
                openDetailActivity(s.getAngkot(),s.getRouteAngkot(),s.getDescAngkot());
            }
        });

        return convertView;
    }

    public FilterAngkot getFilter() {

        FilterAngkot filter = new FilterAngkot(angkotModels, this);
        return filter;
    }

    //OPEN DETAIL ACTIVITY
    private void openDetailActivity(String...details)
    {
        Intent i=new Intent(c, DetailAngkotActivity.class);
        i.putExtra("NO_KEY",details[0]);
        i.putExtra("ROUTE_KEY",details[1]);
        i.putExtra("DESC_KEY",details[2]);


        c.startActivity(i);
    }
}







