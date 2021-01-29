package com.example.myblooddonor.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myblooddonor.DetailsAllRequest;
import com.example.myblooddonor.Entity.Donation;
import com.example.myblooddonor.Entity.Request;
import com.example.myblooddonor.Entity.User;
import com.example.myblooddonor.Fragments.AllRequestFragment;
import com.example.myblooddonor.Fragments.historydonnationFragment;
import com.example.myblooddonor.R;
import com.example.myblooddonor.Services.DonationServices;
import com.example.myblooddonor.Services.RequestService;
import com.example.myblooddonor.Services.UserServices;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyDonationAdapter extends RecyclerView.Adapter<MyDonationAdapter.ViewHolder> {
    private ArrayList<Donation> donations;

    private Context context;
    private historydonnationFragment fragment;





    public MyDonationAdapter(ArrayList<Donation> Dataset, Context context, historydonnationFragment fragment) {
        this.context = context;
        this.donations = Dataset;
        this.fragment = fragment;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public TextView username;
        public TextView date;
        public TextView titre;
        public TextView description;
        public ImageView image;

        public CircleImageView imageuser;

        ImageView btnDonate;

        ViewHolder(View v) {
            super(v);

            username = v.findViewById(R.id.request_username);
            date = v.findViewById(R.id.request_date);
            titre = v.findViewById(R.id.request_titre);
            description = v.findViewById(R.id.request_description);
            image = v.findViewById(R.id.request_layout_image);

            btnDonate = v.findViewById(R.id.All_request_btn_donate);

            imageuser = (CircleImageView) v.findViewById(R.id.request_image_user);

        }
    }



    @NonNull
    @Override
    public MyDonationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_history_donation, parent, false);

        return new MyDonationAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyDonationAdapter.ViewHolder holder, final int position) {

        RequestService rs=new RequestService();

        rs.getRequest(context,donations.get(position).getId_request(), new RequestService.CallBack2() {
            @Override
            public void onSuccess(Request request) {
                holder.description.setText(request.getDescription());
                holder.date.setText(request.getDate_request());
               // holder.titre.setText(request.getCityRequest());


                UserServices us = new UserServices();
                us.getUser(context, request.getUser_id(), new UserServices.CallBack() {
                    @Override
                    public void onSuccess(User user) {
                        holder.username.setText(user.getFirstName() + " "+user.getLastName());

                    }
                });

            }

            @Override
            public void onFail(String msg) {

            }
        });
















    }

    @Override
    public int getItemCount() {
        return donations.size();
    }
}
