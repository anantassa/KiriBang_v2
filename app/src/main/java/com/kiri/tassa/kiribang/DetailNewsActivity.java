package com.kiri.tassa.kiribang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * Created by Lenovo on 13/01/2017.
 */

public class DetailNewsActivity extends AppCompatActivity {

    TextView namaNewsTxt,ruteNewsTxt,descNewsTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        namaNewsTxt = (TextView) findViewById(R.id.namaDetailNews);
        ruteNewsTxt= (TextView) findViewById(R.id.ruteDetailNews);
        descNewsTxt = (TextView) findViewById(R.id.descDetailNews);

        //GET INTENT
        Intent i=this.getIntent();


        //RECEIVE DATA
        String namaNews=i.getExtras().getString("NO_KEY");
        String ruteNews=i.getExtras().getString("ROUTE_KEY");
        String descNews=i.getExtras().getString("DESC_KEY");


        //BIND DATA
        namaNewsTxt.setText(namaNews);
        ruteNewsTxt.setText(ruteNews);
        descNewsTxt.setText(descNews);


    }
}

