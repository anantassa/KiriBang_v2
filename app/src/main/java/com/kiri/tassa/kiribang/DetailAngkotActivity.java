package com.kiri.tassa.kiribang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * Created by Lenovo on 06/01/2017.
 */

public class DetailAngkotActivity extends AppCompatActivity {


    TextView noAngkotTxt,descAngkotTxt, ruteAngkotTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_angkot);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarAngkot);
        setSupportActionBar(toolbar);
        //  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        noAngkotTxt = (TextView) findViewById(R.id.noAngkotDetailTxt);
        descAngkotTxt= (TextView) findViewById(R.id.descAngkotDetailTxt);
        ruteAngkotTxt = (TextView) findViewById(R.id.ruteAngkotDetailTxt);

        //GET INTENT
        Intent i=this.getIntent();


        //RECEIVE DATA
        String noAngkot=i.getExtras().getString("NO_KEY");
        String descAngkot=i.getExtras().getString("DESC_KEY");
        String ruteAngkot=i.getExtras().getString("ROUTE_KEY");

        //BIND DATA
        noAngkotTxt.setText(noAngkot);
        descAngkotTxt.setText(descAngkot);
        ruteAngkotTxt.setText(ruteAngkot);


    }
}




