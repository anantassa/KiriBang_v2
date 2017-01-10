package com.example.tassa.kiribang.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tassa.kiribang.AngkotActivity;
import com.example.tassa.kiribang.BusActivity;
import com.example.tassa.kiribang.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AngkotFragment extends Fragment {


    public AngkotFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Intent myIntent = new Intent(getActivity(), AngkotActivity.class);
        getActivity().startActivity(myIntent);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_angkot, container, false);
    }

}
