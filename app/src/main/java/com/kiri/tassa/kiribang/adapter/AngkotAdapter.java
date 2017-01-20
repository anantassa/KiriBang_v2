package com.kiri.tassa.kiribang.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.kiri.tassa.kiribang.DetailAngkotActivity;

import com.kiri.tassa.kiribang.FilterAngkot;

import com.kiri.tassa.kiribang.R;
import com.kiri.tassa.kiribang.model.AngkotModel;
import com.kiri.tassa.kiribang.model.BusModel;


import java.util.ArrayList;

/**
 * Created by Lenovo on 06/01/2017.
 */

public class AngkotAdapter extends ArrayAdapter<AngkotModel> implements Filterable {
    private Context mContext;
    ArrayList<AngkotModel> originangkotModels = null;
    ArrayList<AngkotModel> filterangkotModels = null;
    //   ArrayList<BusModel> busOrigin;
// private LayoutInflater mInflater;
//    private ItemFilter mFilter = new ItemFilter();
    LayoutInflater inflater;

    public AngkotAdapter(Context context, ArrayList<AngkotModel> angkotModels) {
        super(context, 0, angkotModels);
        mContext = context;
        System.out.println("Creating Adapter");
        originangkotModels = angkotModels;
        filterangkotModels = angkotModels;
        inflater = LayoutInflater.from(context);
    }

    @Nullable
    @Override
    public int getCount() {
        return filterangkotModels.size();
    }

    @Override
    public AngkotModel getItem(int pos) {
        // return busModels.get(pos);
        return filterangkotModels.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(mContext).inflate(R.layout.model_angkot,viewGroup,false);
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

    @Override
    public Filter getFilter() {
        //return super.getFilter();
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filterangkotModels = (ArrayList<AngkotModel>) filterResults.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<AngkotModel> FilteredArrList = new ArrayList<AngkotModel>();

                if (originangkotModels == null) {
                    originangkotModels = new ArrayList<AngkotModel>(filterangkotModels); // saves the original data in mOriginalValues
                }

                if (charSequence == null || charSequence.length() == 0) {
                    results.count = originangkotModels.size();
                    results.values = originangkotModels;
                } else {
                    charSequence = charSequence.toString().toLowerCase();
                    for (int i = 0; i < originangkotModels.size(); i++) {
                        String data = originangkotModels.get(i).getDescAngkot();
                        if (data.toLowerCase().contains(charSequence.toString())) {
                            System.out.println(data);
                            FilteredArrList.add(originangkotModels.get(i));
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;

                }
                return results;
            }

        };
        return filter;
    }

    //OPEN DETAIL ACTIVITY
    private void openDetailActivity(String...details)
    {
        Intent i=new Intent(mContext, DetailAngkotActivity.class);
        i.putExtra("NO_KEY",details[0]);
        i.putExtra("ROUTE_KEY",details[1]);
        i.putExtra("DESC_KEY",details[2]);


        mContext.startActivity(i);
    }
}







