package com.example.myblooddonor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myblooddonor.Retrofit.RetfrofitClient;
import com.example.myblooddonor.Retrofit.ServiceNode;
import com.example.myblooddonor.Services.Registr;
import com.example.myblooddonor.Services.UserServices;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    ServiceNode myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    private EditText login ;
    private EditText password;
    private Button signin ;
    private TextView register ;

    SharedPreferences sharedPref;
    String username123,password123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init API
        Retrofit retrofit = RetfrofitClient.getInstance();
        myAPI = retrofit.create(ServiceNode.class);

        login=findViewById(R.id.login);
        password=findViewById(R.id.password);
        signin=findViewById(R.id.signin);
        register=findViewById(R.id.register);
        register.setTextColor (Color.BLUE);

     //   login.setText("souha@esprit.tn");
     //   password.setText("1234");

        // Permet de mettre le texte en gras
        register.setTypeface (null, Typeface.BOLD);

        // Definit l'action lors du clic
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Registr.class);
                startActivityForResult(intent,2);

            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // loginUser(login.getText().toString(),password.getText().toString());
                sharedPref = getSharedPreferences("bloodStorage", Context.MODE_PRIVATE);

                username123 = sharedPref.getString("email", "");
                password123 = sharedPref.getString("password", "");
                sharedPref = getSharedPreferences("bloodStorage", Context.MODE_PRIVATE);


                SharedPreferences.Editor e = sharedPref.edit();
                e.putString("email", "");
                e.putString("password", "");
                // e.commit();
                e.apply();

                UserServices us = new UserServices();
                us.Login(MainActivity.this,login.getText().toString(),password.getText().toString());


            }
        });

    }

    private void loginUser(String email, String password) {
        compositeDisposable.add(myAPI.loginUser(email,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {


                        if(s.contains("crypted_password")) {
                            Toast.makeText(MainActivity.this, "login succes", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, Bloodrequest.class);

                            startActivity(intent);

                        }
                        else
                            Toast.makeText(MainActivity.this,"login faild"+s,Toast.LENGTH_SHORT).show();
                    }

                })
        );
    }

    public void showFragment (Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.frmlyt1,fragment).commit();
    }
}
