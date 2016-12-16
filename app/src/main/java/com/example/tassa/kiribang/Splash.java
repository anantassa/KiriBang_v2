package com.example.tassa.kiribang;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //setelah 5 detik, maka akan kita arahkan ke halaman utama
                Intent a = new Intent(getApplicationContext(), FrontActivity.class);
                startActivity(a);
                finish();
            }
        }, 5000L);
    }
}
