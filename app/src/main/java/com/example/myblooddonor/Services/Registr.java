package com.example.myblooddonor.Services;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myblooddonor.MainActivity;
import com.example.myblooddonor.R;
import com.example.myblooddonor.Retrofit.RetfrofitClient;
import com.example.myblooddonor.Retrofit.ServiceNode;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Registr extends AppCompatActivity {

    private ServiceNode myapi;


    private EditText name,prenom,age,mail,pass,tel;
    private Spinner type ,ville;
    private ImageView next ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registr);

        name=findViewById(R.id.lastname);
        prenom=findViewById(R.id.firstname);
        age=findViewById(R.id.age);
        mail=findViewById(R.id.mail);
        pass=findViewById(R.id.password);
        tel=findViewById(R.id.tel);
        ville=findViewById(R.id.ville);
        type=findViewById(R.id.type);
        age=findViewById(R.id.age);
        next=findViewById(R.id.next);



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom1 = name.getText().toString().trim();
                String prenom1 = prenom.getText().toString().trim();
                String email1 = mail.getText().toString().trim();
                String password1 = pass.getText().toString().trim();
                int age1=Integer.parseInt(age.getText().toString());
                String tel1= tel.getText().toString();
                String ville1= ville.getSelectedItem().toString();
                String typeb= type.getSelectedItem().toString();
                if(nom1.isEmpty()) {
                    name.setError("Name is required");
                    name.requestFocus();
                    return;
                }
                if(prenom1.isEmpty()) {
                    prenom.setError("Name is required");
                    prenom.requestFocus();
                    return;
                }

                if(email1.isEmpty()){
                    mail.setError("Email is required");
                    mail.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email1).find()){
                    mail.setError("entre valid email");
                    mail.requestFocus();
                    return;
                }
                if(password1.isEmpty()){
                    pass.setError("Password is required");
                    pass.requestFocus();
                    return;
                }
                if(password1.length() <4){
                    pass.setError("Password should be 4 character long");
                    pass.requestFocus();
                    return;
                }
                if(tel1.length()!=8)
                {
                    tel.setError("invalid number");
                    tel.requestFocus();
                    return;
                }
                if(age1 <18)
                    {
                    age.setError("invalid age");
                    age.requestFocus();
                            return;
                }
                Date date = new Date();

                String str = new SimpleDateFormat("yyyy-MM-dd").format(date);
                Retrofit retrofit= RetfrofitClient.getInstance();
                myapi=retrofit.create(ServiceNode.class);
                Call<ResponseBody> call =myapi.registerUser(nom1,prenom1,email1,password1,tel1,ville1,typeb,age1,str);

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        try {
                            String s = response.body().string();
                            Toast.makeText(Registr.this, s, Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        System.out.println("good");
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        Toast.makeText(Registr.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println(t.getMessage());
                    }
                });
                Toast.makeText(Registr.this, "succes!", Toast.LENGTH_SHORT).show();



                Intent intent = new Intent(Registr.this, MainActivity.class);


                startActivity(intent);
            }
        });

    }
}

