package com.example.myblooddonor.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myblooddonor.Adapter.AllDonorsAdapter;
import com.example.myblooddonor.Entity.Request;
import com.example.myblooddonor.Entity.User;
import com.example.myblooddonor.R;
import com.example.myblooddonor.Services.RequestService;
import com.example.myblooddonor.Services.UserServices;

import java.util.ArrayList;

public class FragmentDonor extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


        RecyclerView homeRecycle ;
        SwipeRefreshLayout homeSwipe;
        RecyclerView.Adapter mAdapter;
        RecyclerView.LayoutManager layoutManager;



protected View mView;

public FragmentDonor() {
        // Required empty public constructor
        }

@Nullable
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView =inflater.inflate(R.layout.fragdonor, container, false);
        return mView ;
        }

@Override
public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        homeRecycle = mView.findViewById(R.id.donor_recyclerView);

        homeSwipe = mView.findViewById(R.id.donor_swiperRefresh);


        homeSwipe.setOnRefreshListener(this);
        homeSwipe.post(new Runnable() {

@Override
public void run() {



        loadRecyclerViewData();
        }
        });

        layoutManager = new LinearLayoutManager(getContext());
        homeRecycle.setLayoutManager(layoutManager);
        loadRecyclerViewData();
        }

@Override
public void onRefresh() {
        loadRecyclerViewData();
        }

private void loadRecyclerViewData(){

        homeSwipe.setRefreshing(true);
        UserServices us=new UserServices();

        us.AfficherDonors(getContext(),new UserServices.CallBack2() {



            @Override
            public void onSuccess(ArrayList<User> users) {
                System.out.println("All req frag");
                System.out.println(users);
                System.out.println("fil callback");
                AllDonorsAdapter adapter = new AllDonorsAdapter(users ,getContext(),FragmentDonor.this);


                homeRecycle.setAdapter(adapter);
                homeSwipe.setRefreshing(false);

            }



        });


        }

@Override
public void onResume() {
        super.onResume();
        loadRecyclerViewData();
        }
        }

