package com.example.tassa.kiribang;

import android.util.Log;
import android.widget.Filter;

import com.example.tassa.kiribang.adapter.CustomAdapter;
import com.example.tassa.kiribang.model.BusModel;

import java.util.ArrayList;

/**
 * Created by Lenovo on 12/01/2017.
 */

public class FilterBus extends Filter {
    ArrayList<BusModel> busModels;
    CustomAdapter adapter;

    public FilterBus(ArrayList<BusModel> busModel, CustomAdapter customAdapter){
        this.busModels = busModel;
        this.adapter = customAdapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        ArrayList<BusModel> FilteredArrayNames = new ArrayList<BusModel>();

        // perform your search here using the searchConstraint String.

        constraint = constraint.toString().toLowerCase();
        for (int i = 0; i < busModels.size(); i++) {
            String dataNames = busModels.get(i).getDesc();
            if (dataNames.toLowerCase().contains(constraint.toString()))  {
                FilteredArrayNames.add(busModels.get(i));
            }
        }

        results.count = FilteredArrayNames.size();
        results.values = FilteredArrayNames;
        Log.e("VALUES", results.values.toString());

        return results;

    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        busModels = (ArrayList<BusModel>) results.values;
        adapter.notifyDataSetChanged();
    }
}