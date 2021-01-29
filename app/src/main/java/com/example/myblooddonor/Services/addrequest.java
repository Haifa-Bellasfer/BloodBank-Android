package com.example.myblooddonor.Services;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myblooddonor.AddLocationActivity;

import com.example.myblooddonor.Fragments.FragmentRequest;
import com.example.myblooddonor.R;
import com.example.myblooddonor.Retrofit.RetfrofitClient;
import com.example.myblooddonor.Retrofit.ServiceNode;

import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class addrequest extends Fragment {

    String lat  ="";
    String lon ="";

    private ServiceNode myapi;
    View fragRequest;
    private EditText firstnamerequest,lastnamerequest,phone,description,cityrequest;
    private Spinner typerequest ;
    private Button add ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragRequest = inflater.inflate(R.layout.fragment_addrequest,container,false);
        //
        firstnamerequest=fragRequest.findViewById(R.id.firstnamerequest);
        lastnamerequest=fragRequest.findViewById(R.id.lastnamerequest);
        cityrequest=fragRequest.findViewById(R.id.add_request_lieu);
        typerequest=fragRequest.findViewById(R.id.bloodtyperequest);
        description=fragRequest.findViewById(R.id.descriptionrequest);
        phone=fragRequest.findViewById(R.id.phonerequest);
        add=fragRequest.findViewById(R.id.addrequest);

        cityrequest.setFocusable(false);
        cityrequest.setClickable(true);

        cityrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getContext(),AddLocationActivity.class);
                startActivityForResult(i1,8);

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prenom1 = firstnamerequest.getText().toString().trim();
                String nom1 = lastnamerequest.getText().toString().trim();

                String type1 = typerequest.getSelectedItem().toString().trim();
                String description1= description.getText().toString().trim();
                int user_id = UserServices.userconnecte.getId();
                String tel1= phone.getText().toString().trim();
                Date date = new Date();

                String str = new SimpleDateFormat("yyyy-MM-dd").format(date);
                if(prenom1.isEmpty()) {
                    firstnamerequest.setError("Name is required");
                    firstnamerequest.requestFocus();
                    return;
                }
                if(nom1.isEmpty()) {
                    lastnamerequest.setError("Name is required");
                    lastnamerequest.requestFocus();
                    return;
                }


                if(description1.isEmpty()){
                    description.setError("Email is required");
                    description.requestFocus();
                    return;
                }

                if(tel1.isEmpty()){
                    phone.setError("Password is required");
                    phone.requestFocus();
                    return;
                }

                if(lat.equalsIgnoreCase("")){
                    cityrequest.setError("Please choose location !");
                    cityrequest.requestFocus();
                    return;
                }

                Retrofit retrofit= RetfrofitClient.getInstance();
                myapi=retrofit.create(ServiceNode.class);
                Call<ResponseBody> call =myapi.addrequest(prenom1,nom1,lat,lon,type1,description1,user_id,tel1,str,0);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                      /*  try {
                            String s = response.body().string();
                            Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    */
                       // Bloodrequest b = new Bloodrequest();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frmlyt1,new FragmentRequest()).commit();

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println(t.getMessage());
                    }
                });
                Toast.makeText(getActivity(), "succes!", Toast.LENGTH_SHORT).show();


            }
        });
        return fragRequest;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 8 && resultCode == Activity.RESULT_OK && data != null) {

            Toast.makeText(getContext(),"location selected ",Toast.LENGTH_LONG).show();
            cityrequest.setText("location selected ");
           lat = AddLocationActivity.lat;
           lon = AddLocationActivity.lng;
        }
    }
}



    /*
    View fragRequest;
    private EditText firstnamerequest,lastnamerequest,phone,description;
    private Spinner typerequest ,cityrequest;
    private Button add ;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragRequest = inflater.inflate(R.layout.fragment_addrequest,container,false);
        //return super.onCreateView(inflater, container, savedInstanceState);
        firstnamerequest=fragRequest.findViewById(R.id.firstnamerequest);
        lastnamerequest=fragRequest.findViewById(R.id.lastnamerequest);
        cityrequest=fragRequest.findViewById(R.id.cityrequest);
        typerequest=fragRequest.findViewById(R.id.bloodtyperequest);
        description=fragRequest.findViewById(R.id.descriptionrequest);
        phone=fragRequest.findViewById(R.id.phonerequest);
        add=fragRequest.findViewById(R.id.addrequest);

       // return fragRequest;

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prenom1 = firstnamerequest.getText().toString().trim();
                String nom1 = lastnamerequest.getText().toString().trim();
                String city1 = cityrequest.getSelectedItem().toString().trim();
                String type1 = typerequest.getSelectedItem().toString().trim();
                String description1= description.getText().toString().trim();
                String tel1= phone.getText().toString().trim();

                if(prenom1.isEmpty()) {
                    firstnamerequest.setError("Name is required");
                    firstnamerequest.requestFocus();
                    return;
                }
                if(nom1.isEmpty()) {
                    lastnamerequest.setError("Name is required");
                    lastnamerequest.requestFocus();
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
                if(age1 <18)
                {
                    age.setError("invalible age");
                    age.requestFocus();
                    return;
                }


                Retrofit retrofit= RetfrofitClient.getInstance();
                myapi=retrofit.create(ServiceNode.class);
                Call<ResponseBody> call =myapi.addrequest(prenom1,nom1,city1,type1,tel1,description1,tel1);

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

                        Toast.makeText(addrequest.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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
*/