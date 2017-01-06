package com.example.tassa.kiribang.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.tassa.kiribang.BusActivity;
import com.example.tassa.kiribang.R;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusFragment extends Fragment {

    public BusFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

           Intent myIntent = new Intent(getActivity(), BusActivity.class);
            getActivity().startActivity(myIntent);

        return inflater.inflate(R.layout.fragment_bus, container, false);
    }


}
