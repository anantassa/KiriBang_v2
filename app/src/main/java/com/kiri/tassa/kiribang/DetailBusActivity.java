package com.kiri.tassa.kiribang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * Created by Lenovo on 05/01/2017.
 */

public class DetailBusActivity extends AppCompatActivity {


    TextView noTxt,descTxt, ruteTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bus);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      //  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        noTxt = (TextView) findViewById(R.id.noDetailTxt);
        ruteTxt = (TextView) findViewById(R.id.ruteDetailTxt);
        descTxt= (TextView) findViewById(R.id.descDetailTxt);


        //GET INTENT
        Intent i=this.getIntent();


        //RECEIVE DATA
        String no=i.getExtras().getString("NO_KEY");
        String rute=i.getExtras().getString("ROUTE_KEY");
        String desc=i.getExtras().getString("DESC_KEY");


        //BIND DATA
        noTxt.setText(no);
        descTxt.setText(desc);
        ruteTxt.setText(rute);


        }
    }

