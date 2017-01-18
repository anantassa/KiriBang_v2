package com.kiri.tassa.kiribang.adapter;

import com.kiri.tassa.kiribang.model.News;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by Lenovo on 13/01/2017.
 */

public class NewsHelper  {
    DatabaseReference db;
    Boolean saved=null;
    ArrayList<News> news=new ArrayList<>();
    NewsAdapter adapter;

    public NewsHelper(DatabaseReference db) {
        this.db = db;
    }

    //WRITE IF NOT NULL
    public Boolean saveNews(News news1)
    {
        if(news1==null)
        {
            saved=false;
        }else
        {
            try
            {
                db.child("News").push().setValue(news1);
                saved=true;

            }catch (DatabaseException e)
            {
                e.printStackTrace();
                saved=false;
            }
        }

        return saved;
    }
    //IMPLEMENT FETCH DATA AND FILL ARRAYLIST
    private void fetchDataAngkot(DataSnapshot dataSnapshot)
    {
        //angkotModels.clear();

        // for (DataSnapshot ds : dataSnapshot.getChildren())
        // {
        String key = dataSnapshot.getKey();
        News news1 =dataSnapshot.getValue(News.class);
        news.add(news1);
//           adapter.notifyDataSetChanged();
        // }
    }
    //READ BY HOOKING ONTO DATABASE OPERATION CALLBACKS
    public ArrayList<News> retrieve() {
        db.child("News").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchDataAngkot(dataSnapshot);


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchDataAngkot(dataSnapshot);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return news;
    }
}








