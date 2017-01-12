package com.example.tassa.kiribang;

import android.util.Log;
import android.widget.Filter;

import com.example.tassa.kiribang.adapter.AngkotAdapter;
import com.example.tassa.kiribang.adapter.CustomAdapter;
import com.example.tassa.kiribang.model.AngkotModel;
import com.example.tassa.kiribang.model.BusModel;

import java.util.ArrayList;

/**
 * Created by Lenovo on 13/01/2017.
 */

public class FilterAngkot  extends Filter {
    ArrayList<AngkotModel> angkotModels;
    AngkotAdapter angkotadapt;

    public FilterAngkot(ArrayList<AngkotModel> angkotModel, AngkotAdapter angkotAdapter){
        this.angkotModels = angkotModel;
        this.angkotadapt = angkotAdapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        ArrayList<AngkotModel> FilteredArrayNames = new ArrayList<AngkotModel>();

        // perform your search here using the searchConstraint String.

        constraint = constraint.toString().toLowerCase();
        for (int i = 0; i < angkotModels.size(); i++) {
            String dataNames = angkotModels.get(i).getDescAngkot();
            if (dataNames.toLowerCase().contains(constraint.toString()))  {
                FilteredArrayNames.add(angkotModels.get(i));
            }
        }

        results.count = FilteredArrayNames.size();
        results.values = FilteredArrayNames;
        Log.e("VALUES", results.values.toString());

        return results;

    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        angkotModels = (ArrayList<AngkotModel>) results.values;
        angkotadapt.notifyDataSetChanged();
    }
}

