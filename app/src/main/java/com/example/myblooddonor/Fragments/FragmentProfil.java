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
import com.google.android.material.tabs.TabLayout;

public class FragmentProfil extends Fragment {
    View myFrag2;
    ViewPager viewPager2;
    TabLayout tabLayout2;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myFrag2 = inflater.inflate(R.layout.fragprofile,container,false);
        //return super.onCreateView(inflater, container, savedInstanceState);
        viewPager2=myFrag2.findViewById(R.id.viewp2);
        tabLayout2=myFrag2.findViewById(R.id.tab2);
        return myFrag2;


    }
    @Override
    public void onActivityCreated(@Nullable  Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        setUpViewPger(viewPager2);
        tabLayout2.setupWithViewPager(viewPager2);
        tabLayout2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
    private void setUpViewPger (ViewPager viewPger2)
    {
        SectionPageAdapter adapter= new SectionPageAdapter(getChildFragmentManager());
        adapter.addFragment(new NotifFragment(),"notif");
        adapter.addFragment(new MsgFragment(),"msg");
        viewPger2.setAdapter(adapter);
    }
}
