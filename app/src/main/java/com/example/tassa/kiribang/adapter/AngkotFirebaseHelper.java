package com.example.tassa.kiribang.adapter;

import com.example.tassa.kiribang.model.AngkotModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by Lenovo on 06/01/2017.
 */

public class AngkotFirebaseHelper {

    DatabaseReference db;
    Boolean saved=null;
    ArrayList<AngkotModel> angkotModels=new ArrayList<>();
    AngkotAdapter adapter;

    public AngkotFirebaseHelper(DatabaseReference db) {
        this.db = db;
    }

    //WRITE IF NOT NULL
    public Boolean saveAngkot(AngkotModel angkotModel)
    {
        if(angkotModel==null)
        {
            saved=false;
        }else
        {
            try
            {
                db.child("Angkot Model").push().setValue(angkotModel);
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
            AngkotModel angkotModel =dataSnapshot.getValue(AngkotModel.class);
            angkotModels.add(angkotModel);
//           adapter.notifyDataSetChanged();
       // }
    }
    //READ BY HOOKING ONTO DATABASE OPERATION CALLBACKS
    public ArrayList<AngkotModel> retrieve() {
        db.child("Angkot Model").addChildEventListener(new ChildEventListener() {
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

        return angkotModels;
    }
}











