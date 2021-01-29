package com.example.myblooddonor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.myblooddonor.Services.UserServices;

public class Welcome extends AppCompatActivity {
    /*private int SPLASH_TIME = 3000;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_welcome);

            Thread timer = new Thread() {
                public void run() {
                    try {
                        sleep(SPLASH_TIME);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        Intent intent = new Intent(getApplicationContext(),
                                MainActivity.class);
                        startActivity(intent);

                    }
                }
            };
            timer.start();
        }
    }*/
    SharedPreferences sharedPref;
    String username123,password123;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        sharedPref = getSharedPreferences("bloodStorage", Context.MODE_PRIVATE);

        username123 = sharedPref.getString("email", "");
        password123 = sharedPref.getString("password", "");
        sharedPref = getSharedPreferences("bloodStorage", Context.MODE_PRIVATE);



        image = (ImageView) findViewById(R.id.abo);
        final Animation animRotate = AnimationUtils.loadAnimation(this,R.anim.rotate);
        animRotate.setDuration(4000);

        Thread thread = new Thread(){
            @Override
            public void run() {
                try {

                    sleep(4000);



                    if (username123.equalsIgnoreCase("") || password123.equalsIgnoreCase("")){



                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);

                        startActivity(intent);

                    }
                    else{

                        UserServices us = new UserServices();
                        us.Login(getApplicationContext(),username123,password123);

                    }


                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        image.startAnimation(animRotate);
        thread.start();
    }
}
