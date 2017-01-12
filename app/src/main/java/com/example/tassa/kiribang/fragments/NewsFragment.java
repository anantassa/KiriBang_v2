package com.example.tassa.kiribang.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tassa.kiribang.BusActivity;
import com.example.tassa.kiribang.NewsActivity;
import com.example.tassa.kiribang.R;

/**
 * Created by Lenovo on 13/01/2017.
 */

public class NewsFragment extends Fragment {

    public NewsFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Intent myIntent = new Intent(getActivity(), NewsActivity.class);
        getActivity().startActivity(myIntent);

        return inflater.inflate(R.layout.fragment_news, container, false);
    }


}
