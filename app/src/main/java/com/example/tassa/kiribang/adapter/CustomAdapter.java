package com.example.tassa.kiribang.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tassa.kiribang.DetailBusActivity;
import com.example.tassa.kiribang.FilterBus;
import com.example.tassa.kiribang.R;
import com.example.tassa.kiribang.model.BusModel;

import java.util.ArrayList;

/**
 * Created by Lenovo on 05/01/2017.
 */

public class CustomAdapter extends BaseAdapter {
    Context c;
    ArrayList<BusModel> busModels = null;
    ArrayList<BusModel> filterbusModels = null;
    //   ArrayList<BusModel> busOrigin;
// private LayoutInflater mInflater;
//    private ItemFilter mFilter = new ItemFilter();

    public CustomAdapter(Context c, ArrayList<BusModel> busModels) {
        this.c = c;
        this.busModels = busModels;
        this.filterbusModels = filterbusModels;

    }

    @Override
    public int getCount() {
        return busModels.size();
    }

    @Override
    public Object getItem(int pos) {
        return busModels.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        if(convertView==null)
        {
            convertView= LayoutInflater.from(c).inflate(R.layout.model,viewGroup,false);
        }

        TextView noTxt= (TextView) convertView.findViewById(R.id.noBus);
        TextView descTxt= (TextView) convertView.findViewById(R.id.descTxt);
        TextView ruteTxt= (TextView) convertView.findViewById(R.id.rutebus);

        final BusModel s= (BusModel) this.getItem(position);

        noTxt.setText(s.getBus());
        descTxt.setText(s.getDesc());
        ruteTxt.setText(s.getRoute());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //OPEN DETAIL
                openDetailActivity(s.getBus(),s.getRoute(),s.getDesc());
            }
        });

        return convertView;
    }


    public FilterBus getFilter() {

        FilterBus filter = new FilterBus(busModels, this);
        return filter;
    }

    //OPEN DETAIL ACTIVITY
    private void openDetailActivity(String...details)
    {
        Intent i=new Intent(c, DetailBusActivity.class);
        i.putExtra("NO_KEY",details[0]);
        i.putExtra("DESC_KEY",details[1]);
        i.putExtra("ROUTE_KEY",details[2]);

        c.startActivity(i);
    }
}





