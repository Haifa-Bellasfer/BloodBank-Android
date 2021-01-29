package com.example.myblooddonor.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.myblooddonor.Adapter.SectionPageAdapter;
import com.example.myblooddonor.R;
import com.example.myblooddonor.Services.addrequest;
import com.google.android.material.tabs.TabLayout;

public class FragmentRequest extends Fragment {
    View myFrag;
    ViewPager viewPager;
    TabLayout tabLayout;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFrag = inflater.inflate(R.layout.fragrequest,container,false);
        //return super.onCreateView(inflater, container, savedInstanceState);
        viewPager=myFrag.findViewById(R.id.viewp);
        tabLayout=myFrag.findViewById(R.id.tab);
        return myFrag;


    }
    @Override
    public void onActivityCreated(@Nullable  Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        setUpViewPger(viewPager);
        tabLayout.setupWithViewPager(viewPager);
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
        adapter.addFragment(new MyrequestsFragment(),"myRequest");
        adapter.addFragment(new addrequest(),"addRequest");

        viewPger.setAdapter(adapter);
    }
}
