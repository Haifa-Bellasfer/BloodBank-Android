package com.example.myblooddonor.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myblooddonor.Bloodrequest;
import com.example.myblooddonor.R;
import com.example.myblooddonor.Retrofit.ServiceNode;
import com.example.myblooddonor.Services.UserServices;


/**
 * A simple {@link Fragment} subclass.
 */
public class updateprofileFragment extends Fragment {


    View myFrag2;
    EditText email, phone;
    EditText oldpass, newpass;
    Button update, changePassword;
    ServiceNode myapi;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFrag2 = inflater.inflate(R.layout.fragment_updateprofile,container,false);

        //

        email=myFrag2.findViewById(R.id.emailedit);
        phone=myFrag2.findViewById(R.id.phoneedit);
        //    oldpass = myFrag2.findViewById(R.id.passwordedit);
        //  newpass = myFrag2.findViewById(R.id.p_change);
        update = myFrag2.findViewById(R.id.p_update);


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserServices us = new UserServices();
                us.update(getContext(),UserServices.userconnecte.getId(),
                        email.getText().toString(),phone.getText().toString());
                UserServices.userconnecte.setEmail(email.getText().toString());
                UserServices.userconnecte.setPhone(phone.getText().toString());
                Toast.makeText(getContext(),"update successeful",Toast.LENGTH_LONG).show();

                Intent intent=new Intent(getContext(), Bloodrequest.class);
                getContext().startActivity(intent);
            }
        });

        return myFrag2;
    }



}
