package com.example.myblooddonor.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myblooddonor.DetailsAllRequest;
import com.example.myblooddonor.Entity.Request;
import com.example.myblooddonor.Entity.User;
import com.example.myblooddonor.Fragments.AllRequestFragment;
import com.example.myblooddonor.Fragments.FragmentDonor;
import com.example.myblooddonor.Fragments.FragmentRequest;
import com.example.myblooddonor.R;
import com.example.myblooddonor.Services.DonationServices;
import com.example.myblooddonor.Services.RequestService;
import com.example.myblooddonor.Services.UserServices;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class AllDonorsAdapter  extends RecyclerView.Adapter<AllDonorsAdapter.ViewHolder> {
    private ArrayList<User> users;
    private Context context;
    private FragmentDonor fragment;

    // private ArrayList<Donation> donations;





    public AllDonorsAdapter(ArrayList<User> Dataset, Context context, FragmentDonor fragment) {
        this.context = context;
        this.users = Dataset;
        this.fragment = fragment;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {



        public TextView username;
        public TextView date;
        public TextView description;
        public TextView countd;
        public ImageView call;

        public CircleImageView imageuser;
        public CircleImageView rating1,rating2,rating3,rating4 ;


        ViewHolder(View v) {
            super(v);
            username = v.findViewById(R.id.donation_annonce_username);
            date = v.findViewById(R.id.donation_annonce_date);
            imageuser = v.findViewById(R.id.donation_annonce_image_user);
            rating1=v.findViewById(R.id.rating1);
            rating2=v.findViewById(R.id.rating2);
            rating3=v.findViewById(R.id.rating3);
            rating4=v.findViewById(R.id.rating4);
            call=v.findViewById(R.id.call2);






        }
    }



    @NonNull
    @Override
    public AllDonorsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_all_donors, parent, false);

        return new AllDonorsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AllDonorsAdapter.ViewHolder holder, final int position) {

        UserServices us = new UserServices();
        us.getUser(context, users.get(position).getId(), new UserServices.CallBack() {
            @Override
            public void onSuccess(User user) {
                holder.username.setText(user.getFirstName() + " "+user.getLastName());



            }
        });
        holder.date.setText(users.get(position).getLast_donation());
        //holder.countd.setText(users.get(position).getCount_donation());
//        holder.description.setText(users.get(position).getCount_donation());
        //holder.description.setText(users.get(position).getCount_donation());

        //holder.date.setText(requests.get(position).getDate_request());
        // holder.titre.setText(requests.get(position).getCityRequest());
        if (users.get(position).getCount_donation()>3 && users.get(position).getCount_donation()<15 ){
            holder.rating1.setVisibility(View.VISIBLE);


        }
        if (users.get(position).getCount_donation()>6 && users.get(position).getCount_donation()<15){

            holder.rating2.setVisibility(View.VISIBLE);


        }
        if (users.get(position).getCount_donation()>9 && users.get(position).getCount_donation()<15){

            holder.rating3.setVisibility(View.VISIBLE);


        }
        if (users.get(position).getCount_donation()>15 ){

            holder.rating4.setVisibility(View.VISIBLE);


        }


    holder.call.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+users.get(position).getPhone())));
        }
    });



        /*if (!requests.get(position).getBloodTypeRequest().equalsIgnoreCase(UserServices.userconnecte.getBloodType())){
            holder.btnDonate.setVisibility(View.GONE);


        }*/












        System.out.println("detail :"+ users.get(position).getFirstName() +", "+ users.get(position).getLastName());




    }

    @Override
    public int getItemCount() {
        return users.size();
    }




}
