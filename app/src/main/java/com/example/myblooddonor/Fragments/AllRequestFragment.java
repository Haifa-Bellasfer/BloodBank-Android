package com.example.myblooddonor.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myblooddonor.Adapter.AllRequestsAdapter;
import com.example.myblooddonor.Entity.Request;
import com.example.myblooddonor.R;
import com.example.myblooddonor.SendMail;
import com.example.myblooddonor.Services.RequestService;
import com.example.myblooddonor.Services.UserServices;

import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllRequestFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    RecyclerView homeRecycle ;
    SwipeRefreshLayout homeSwipe;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;



    protected View mView;

    public AllRequestFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView =inflater.inflate(R.layout.fragment_all_request, container, false);
        return mView ;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        homeRecycle = mView.findViewById(R.id.home_recyclerView);

        homeSwipe = mView.findViewById(R.id.home_swiperRefresh);


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
        RequestService as=new RequestService();
        as.AfficherRequest(getContext(),new RequestService.CallBack() {
            @Override
            public void onSuccess(ArrayList<Request> requests) {

                System.out.println("All req frag");
                System.out.println(requests);
                System.out.println("fil callback");
                AllRequestsAdapter adapter = new AllRequestsAdapter(requests ,getContext(),AllRequestFragment.this);


                homeRecycle.setAdapter(adapter);
                homeSwipe.setRefreshing(false);
            }

            @Override
            public void onFail(String msg) {
                // Do Stuff
                homeSwipe.setRefreshing(false);
            }
        });


     // ArrayList<Request> requests = new ArrayList<>();
    /*  Request r1= new Request("souha","souha","test","a+","tes",1,"12345678",new Date(), UserServices.userconnecte);
      Request r2= new Request("souha","souha","test","a+","tes",1,"12345678",new Date(), UserServices.userconnecte);
       requests.add(r1);
       requests.add(r2);
        AllRequestsAdapter adapter = new AllRequestsAdapter(requests,getContext(),AllRequestFragment.this);


        homeRecycle.setAdapter(adapter);
        homeSwipe.setRefreshing(false);
        */
    }

    @Override
    public void onResume() {
        super.onResume();
         loadRecyclerViewData();
    }


}
