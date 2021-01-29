package com.example.myblooddonor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class Profile extends AppCompatActivity {

    EditText name, email, phone;
    EditText oldpass,newpasse ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name = findViewById(R.id.nameedit);
        email = findViewById(R.id.emailedit);
        phone = findViewById(R.id.phoneedit);
        oldpass = findViewById(R.id.passwordedit);
        newpasse = findViewById(R.id.p_new_pass);
    }


}
