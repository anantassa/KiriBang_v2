package com.kiri.tassa.kiribang;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import com.kiri.tassa.kiribang.adapter.AngkotAdapter;
import com.kiri.tassa.kiribang.adapter.AngkotFirebaseHelper;

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
        noAngkotEditTxt = (EditText) findViewById(R.id.noAngkotEditText);
        noAngkotEditTxt.addTextChangedListener(filterTextWatcher);

        lvAngkot = (ListView) findViewById(R.id.lv_angkot);

        //INITIALIZE FIREBASE DB
        db = FirebaseDatabase.getInstance().getReference();
        helper = new AngkotFirebaseHelper(db);

        //ADAPTER
        adapter = new AngkotAdapter(this, helper.retrieve());
        lvAngkot.setAdapter(adapter);
        lvAngkot.setTextFilterEnabled(true);
        //   FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_angkot);
        //  fab.setOnClickListener(new View.OnClickListener() {


    }

    private TextWatcher filterTextWatcher = new TextWatcher() {

        public void afterTextChanged(Editable s) {
        }

        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            FilterAngkot filter = adapter.getFilter();
            filter.filter(s.toString());
        }
    };
}