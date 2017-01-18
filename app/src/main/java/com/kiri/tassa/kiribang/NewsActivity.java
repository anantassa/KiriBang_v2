package com.kiri.tassa.kiribang;

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

import com.kiri.tassa.kiribang.adapter.NewsAdapter;
import com.kiri.tassa.kiribang.adapter.NewsHelper;
import com.kiri.tassa.kiribang.model.News;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Lenovo on 13/01/2017.
 */

public class NewsActivity extends AppCompatActivity {

    DatabaseReference db;
    NewsHelper helper;
    NewsAdapter adapter;
    ListView lv;
    EditText noEditTxt, ruteTxt, descTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_news);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv = (ListView) findViewById(R.id.lv_news);

        //INITIALIZE FIREBASE DB
        db = FirebaseDatabase.getInstance().getReference();
        helper = new NewsHelper(db);

        //ADAPTER
        adapter = new NewsAdapter(this, helper.retrieve());
        lv.setAdapter(adapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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
        d.setContentView(R.layout.input_dialog_news);

        noEditTxt= (EditText) d.findViewById(R.id.namadialog);
        ruteTxt= (EditText) d.findViewById(R.id.rutedialog);
        descTxt= (EditText) d.findViewById(R.id.descdialog);
        Button saveBtn= (Button) d.findViewById(R.id.saveBtn);

        //SAVE
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //GET DATA
                String no=noEditTxt.getText().toString();
                String rute=ruteTxt.getText().toString();
                String desc=descTxt.getText().toString();

                //SET DATA
                News s=new News();
                s.setName(no);
                s.setRouteNews(rute);
                s.setNews(desc);


                //SIMPLE VALIDATION
                if(no != null && no.length()>0)
                {
                    //THEN SAVE
                    if(helper.saveNews(s))
                    {
                        //IF SAVED CLEAR EDITXT
                        noEditTxt.setText("");
                        ruteTxt.setText("");
                        descTxt.setText("");


                        adapter=new NewsAdapter(NewsActivity.this,helper.retrieve());
                        lv.setAdapter(adapter);


                    }
                }else
                {
                    Toast.makeText(NewsActivity.this, "Name Must Not Be Empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

        d.show();
    }



}
