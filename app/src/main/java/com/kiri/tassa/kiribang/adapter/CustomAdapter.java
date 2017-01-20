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

import com.kiri.tassa.kiribang.DetailBusActivity;
import com.kiri.tassa.kiribang.FilterBus;
import com.kiri.tassa.kiribang.R;
import com.kiri.tassa.kiribang.model.BusModel;

import java.util.ArrayList;

/**
 * Created by Lenovo on 05/01/2017.
 */

public class CustomAdapter extends ArrayAdapter<BusModel> implements Filterable {
    private Context mContext;
    ArrayList<BusModel> originbusModels = null;
    ArrayList<BusModel> filterbusModels = null;
    //   ArrayList<BusModel> busOrigin;
// private LayoutInflater mInflater;
//    private ItemFilter mFilter = new ItemFilter();
    LayoutInflater inflater;

    public CustomAdapter(Context context, ArrayList<BusModel> busModels) {
        super(context, 0, busModels);
        mContext = context;
        System.out.println("Creating Adapter");
        originbusModels = busModels;
        filterbusModels = busModels;
        inflater = LayoutInflater.from(context);
    }
    @Nullable
    @Override
    public int getCount() {
        return filterbusModels.size();
        //return busModels.size();
    }

    @Override
    public BusModel getItem(int pos) {
        // return busModels.get(pos);
        return filterbusModels.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        if(convertView==null)
        {
            convertView= LayoutInflater.from(mContext).inflate(R.layout.model,viewGroup,false);
        }

        TextView noTxt= (TextView) convertView.findViewById(R.id.noBus);
        TextView ruteTxt= (TextView) convertView.findViewById(R.id.rutebus);
        TextView descTxt= (TextView) convertView.findViewById(R.id.descTxt);
        ;

        final BusModel s= (BusModel) this.getItem(position);

        noTxt.setText(s.getBus());
        ruteTxt.setText(s.getRoute());
        descTxt.setText(s.getDesc());


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //OPEN DETAIL
                openDetailActivity(s.getBus(),s.getRoute(),s.getDesc());
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
                filterbusModels = (ArrayList<BusModel>) filterResults.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                ArrayList<BusModel> FilteredArrList = new ArrayList<BusModel>();

                if (originbusModels == null) {
                    originbusModels = new ArrayList<BusModel>(filterbusModels); // saves the original data in mOriginalValues
                }

                if (charSequence == null || charSequence.length() == 0) {
                    results.count = originbusModels.size();
                    results.values = originbusModels;
                } else {
                    charSequence = charSequence.toString().toLowerCase();
                    for (int i = 0; i < originbusModels.size(); i++) {
                        String data = originbusModels.get(i).getDesc();
                        if (data.toLowerCase().contains(charSequence.toString())) {
                            System.out.println(data);
                            FilteredArrList.add(originbusModels.get(i));
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
        Intent i=new Intent(mContext, DetailBusActivity.class);
        i.putExtra("NO_KEY",details[0]);
        i.putExtra("ROUTE_KEY",details[1]);
        i.putExtra("DESC_KEY",details[2]);


        mContext.startActivity(i);
    }
}





