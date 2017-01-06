package com.example.tassa.kiribang.adapter;

import android.widget.ArrayAdapter;

import com.example.tassa.kiribang.model.BusModel;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by Lenovo on 05/01/2017.
 */

public class FirebaseHelper {

    DatabaseReference db;
    Boolean saved=null;
    ArrayList<BusModel> busModels=new ArrayList<>();
    CustomAdapter adapter;

    public FirebaseHelper(DatabaseReference db) {
        this.db = db;
    }

    //WRITE IF NOT NULL
    public Boolean save(BusModel busModel)
    {
        if(busModel==null)
        {
            saved=false;
        }else
        {
            try
            {
                db.child("Bus Model").push().setValue(busModel);
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
    private void fetchData(DataSnapshot dataSnapshot)
    {
        busModels.clear();

        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            BusModel busModel =ds.getValue(BusModel.class);
            busModels.add(busModel);
//           adapter.notifyDataSetChanged();
        }
    }
    //READ BY HOOKING ONTO DATABASE OPERATION CALLBACKS
    public ArrayList<BusModel> retrieve() {
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               fetchData(dataSnapshot);
              // BusModel bm = dataSnapshot.getValue(BusModel.class);
             //  busModels.add(bm);
            //  adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);

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

        return busModels;
    }
}









