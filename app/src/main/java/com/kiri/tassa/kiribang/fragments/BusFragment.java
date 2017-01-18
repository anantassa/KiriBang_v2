package com.kiri.tassa.kiribang.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kiri.tassa.kiribang.BusActivity;
import com.kiri.tassa.kiribang.R;

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
