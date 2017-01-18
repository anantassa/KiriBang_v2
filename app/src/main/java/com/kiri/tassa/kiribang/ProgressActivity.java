package com.kiri.tassa.kiribang;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

public class ProgressActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Loading...");
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

  //  public String getUid() {
       // return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }


