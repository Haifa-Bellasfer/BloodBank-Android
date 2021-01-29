package com.example.myblooddonor.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myblooddonor.Adapter.SectionPageAdapter;
import com.example.myblooddonor.Bloodrequest;
import com.example.myblooddonor.MainActivity;
import com.example.myblooddonor.R;
import com.example.myblooddonor.Services.UserServices;
import com.google.android.material.tabs.TabLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class defaultProfileFragment extends Fragment {


    View myFrag;
    ViewPager viewPager;
    TabLayout tabLayout;
    TextView userN;
    ImageView logOut;
    SharedPreferences sharedPref;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFrag = inflater.inflate(R.layout.fragment_default_profile,container,false);
        //return super.onCreateView(inflater, container, savedInstanceState);
        viewPager=myFrag.findViewById(R.id.viewprofile);
        tabLayout=myFrag.findViewById(R.id.tabprofile);
        userN=myFrag.findViewById(R.id.usernameD);
        logOut = myFrag.findViewById(R.id.btnLogOut);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPref = getContext().getSharedPreferences("bloodStorage", Context.MODE_PRIVATE);


                SharedPreferences.Editor e = sharedPref.edit();
                e.putString("email", "");
                e.putString("password", "");
                // e.commit();
                e.apply();
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        return myFrag;


    }
    @Override
    public void onActivityCreated(@Nullable  Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        setUpViewPger(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        userN.setText(UserServices.userconnecte.getFirstName() +"  "+UserServices.userconnecte.getLastName());
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    private void setUpViewPger (ViewPager viewPger)
    {
        SectionPageAdapter adapter= new SectionPageAdapter(getChildFragmentManager());
        adapter.addFragment(new updateprofileFragment(),"Update Profile");
        adapter.addFragment(new historydonnationFragment(),"My History donations");
        viewPger.setAdapter(adapter);
    }

}
