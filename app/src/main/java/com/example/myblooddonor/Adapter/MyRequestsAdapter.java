package com.example.myblooddonor.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myblooddonor.Entity.Request;
import com.example.myblooddonor.Entity.User;
import com.example.myblooddonor.Fragments.MyrequestsFragment;
import com.example.myblooddonor.R;
import com.example.myblooddonor.Services.DonationServices;
import com.example.myblooddonor.Services.RequestService;
import com.example.myblooddonor.Services.UserServices;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyRequestsAdapter extends RecyclerView.Adapter<MyRequestsAdapter.ViewHolder> {
    private ArrayList<Request> requests;
    private Context context;
    private MyrequestsFragment fragment;





    public MyRequestsAdapter(ArrayList<Request> Dataset, Context context, MyrequestsFragment fragment) {
        this.context = context;
        this.requests = Dataset;
        this.fragment = fragment;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        public TextView username;
        public TextView date;
        public TextView titre;
        public TextView description;
        public ImageView image;

        public CircleImageView imageuser;

        ImageView btndelete;

        ViewHolder(View v) {
            super(v);

            username = v.findViewById(R.id.myrequest_username);
            date = v.findViewById(R.id.myrequest_date);
            titre = v.findViewById(R.id.myrequest_titre);
            description = v.findViewById(R.id.myrequest_description);
            image = v.findViewById(R.id.myrequest_layout_image);
            btndelete = v.findViewById(R.id.deletebutton);

            imageuser = (CircleImageView) v.findViewById(R.id.myrequest_image_user);

        }
    }



    @NonNull
    @Override
    public MyRequestsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_my_requests, parent, false);

        return new MyRequestsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyRequestsAdapter.ViewHolder holder, final int position) {
        final RequestService ds = new RequestService() ;
        UserServices us = new UserServices();
        us.getUser(context, requests.get(position).getUser_id(), new UserServices.CallBack() {
            @Override
            public void onSuccess(User user) {
                holder.username.setText(user.getFirstName() + " "+user.getLastName());

            }
        });
        holder.description.setText(requests.get(position).getDescription());
        holder.date.setText(requests.get(position).getDate_request());
        //holder.titre.setText(requests.get(position).getCityRequest_lan());

        /*holder.btnDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("nzelt button delete ");
                RequestService ds = new RequestService();
                ds.SupprimerRequest(context,requests.get(position).getId());

                holder.btnDonate.setVisibility(View.GONE);
            }
        });*/
        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("nzelt button delete ");
                ds.SupprimerRequest(context,requests.get(position).getId(),fragment);
                System.out.println("fasakh ml base !");

                //holder.btndelete.setVisibility(View.GONE);


            }
        });



       /* holder.btnDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("nzelt button donate");
                DonationServices ds = new DonationServices();
                ds.AddDonation(context,requests.get(position).getId());

                holder.btnDonate.setVisibility(View.GONE);
            }
        });

/*

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailAnnonceActivity.class);
               // intent.putExtra("titre", requests.get(position).getTitre());
                intent.putExtra("description", annonces.get(position).getDescription());
                intent.putExtra("date", annonces.get(position).getDate());
                intent.putExtra("type", annonces.get(position).getType());
                intent.putExtra("id", annonces.get(position).getId());
                intent.putExtra("lat", annonces.get(position).getEmplacement_lat());
                intent.putExtra("long", annonces.get(position).getEmplacement_long());
                context.startActivity(intent);
            }
        });*/


    }

    @Override
    public int getItemCount() {
        return requests.size();
    }
}
