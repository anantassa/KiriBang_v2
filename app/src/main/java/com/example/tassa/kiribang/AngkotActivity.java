package com.example.tassa.kiribang;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tassa.kiribang.adapter.AngkotAdapter;
import com.example.tassa.kiribang.adapter.AngkotFirebaseHelper;

import com.example.tassa.kiribang.model.AngkotModel;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Lenovo on 06/01/2017.
 */

public class AngkotActivity extends AppCompatActivity {

    DatabaseReference db;
    AngkotFirebaseHelper helper;
    AngkotAdapter adapter;
    ListView lvAngkot;
    EditText noAngkotEditTxt, ruteAngkotTxt, descAngkotTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_angkot);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarAngkot);
        setSupportActionBar(toolbar);

        lvAngkot = (ListView) findViewById(R.id.lv_angkot);

        //INITIALIZE FIREBASE DB
        db = FirebaseDatabase.getInstance().getReference();
        helper = new AngkotFirebaseHelper(db);

        //ADAPTER
        adapter = new AngkotAdapter(this, helper.retrieve());
        lvAngkot.setAdapter(adapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_angkot);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayInputDialog();

            }
        });
    }




    //DISPLAY INPUT DIALOG
    private void displayInputDialog()
    {
        Dialog d=new Dialog(this);
        d.setTitle("Save To Firebase");
        d.setContentView(R.layout.input_dialog_angkot);

        noAngkotEditTxt= (EditText) d.findViewById(R.id.noAngkotEditText);
        ruteAngkotTxt= (EditText) d.findViewById(R.id.ruteAngkotEditText);
        descAngkotTxt= (EditText) d.findViewById(R.id.descAngkotEditText);
        Button saveBtn= (Button) d.findViewById(R.id.saveAngkotBtn);

        //SAVE
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //GET DATA
                String noAngkot=noAngkotEditTxt.getText().toString();
                String ruteAngkot=ruteAngkotTxt.getText().toString();
                String descAngkot=descAngkotTxt.getText().toString();

                //SET DATA
                AngkotModel s=new AngkotModel();
                s.setAngkot(noAngkot);
                s.setRouteAngkot(ruteAngkot);
                s.setDescAngkot(descAngkot);


                //SIMPLE VALIDATION
                if(noAngkot != null && noAngkot.length()>0)
                {
                    //THEN SAVE
                    if(helper.saveAngkot(s))
                    {
                        //IF SAVED CLEAR EDITXT
                        noAngkotEditTxt.setText("");
                        ruteAngkotTxt.setText("");
                        descAngkotTxt.setText("");


                        adapter=new AngkotAdapter(AngkotActivity.this,helper.retrieve());
                        lvAngkot.setAdapter(adapter);


                    }
                }else
                {
                    Toast.makeText(AngkotActivity.this, "Name Must Not Be Empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

        d.show();
    }



}
