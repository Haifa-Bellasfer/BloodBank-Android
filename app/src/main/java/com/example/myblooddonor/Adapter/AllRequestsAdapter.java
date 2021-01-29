package com.example.myblooddonor.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myblooddonor.DetailsAllRequest;
import com.example.myblooddonor.Entity.Donation;
import com.example.myblooddonor.Entity.Request;
import com.example.myblooddonor.Entity.User;
import com.example.myblooddonor.Fragments.AllRequestFragment;
import com.example.myblooddonor.R;
import com.example.myblooddonor.SendMail;
import com.example.myblooddonor.Services.DonationServices;
import com.example.myblooddonor.Services.UserServices;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class AllRequestsAdapter extends RecyclerView.Adapter<AllRequestsAdapter.ViewHolder> {
    private ArrayList<Request> requests;
    private Context context;
    private AllRequestFragment fragment;
   // private ArrayList<Donation> donations;
private String mail;




    public AllRequestsAdapter(ArrayList<Request> Dataset, Context context, AllRequestFragment fragment) {
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
    public AllRequestsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_all_requests, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder,final int position) {

        UserServices us = new UserServices();
        us.getUser(context, requests.get(position).getUser_id(), new UserServices.CallBack() {
            @Override
            public void onSuccess(User user) {
                holder.username.setText(user.getFirstName() + " "+user.getLastName());
                mail=user.getEmail();

            }
        });
        holder.description.setText(requests.get(position).getBloodTypeRequest());
        holder.date.setText(requests.get(position).getDate_request());
       // holder.titre.setText(requests.get(position).getCityRequest());

        if (!requests.get(position).getBloodTypeRequest().equalsIgnoreCase(UserServices.userconnecte.getBloodType())){
            holder.btnDonate.setVisibility(View.GONE);


        }









        holder.btnDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (compare(UserServices.userconnecte.getLast_donation())){

                    System.out.println("nzelt button donate");
                    DonationServices ds = new DonationServices();
                    ds.AddDonation(context,requests.get(position).getId());

                    //holder.btnDonate.setVisibility(View.GONE);
                    UserServices us= new UserServices();
                    us.updateUserDate(context,UserServices.userconnecte.getId());


                    us.getUser(context,requests.get(position).getUser_id(),new UserServices.CallBack(){
                        @Override
                        public void onSuccess(User user) {
                           mail = user.getEmail();
                                }
                            });





                    sendEmail(mail,"Donnation",UserServices.userconnecte.getFirstName()+" "+UserServices.userconnecte.getLastName()+" has donated for you, for contact call : "+UserServices.userconnecte.getPhone());
                }
                else
                {
                    Toast.makeText(fragment.getActivity(), "You have to wait 3 months after your last donation", Toast.LENGTH_LONG).show();

                }

            }
        });


        System.out.println("detail :"+ requests.get(position).getCityRequest_lan() +", "+ requests.get(position).getCityRequest_lon());

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailsAllRequest.class);
               // intent.putExtra("titre", requests.get(position).getTitre());
                intent.putExtra("description", requests.get(position).getDescription());
                intent.putExtra("phone_request", requests.get(position).getPhoneRequest());
                intent.putExtra("lat", requests.get(position).getCityRequest_lan());
                intent.putExtra("long", requests.get(position).getCityRequest_lon());
                intent.putExtra("bloodTypeRequest", requests.get(position).getBloodTypeRequest());
                intent.putExtra("firstNameRequest", requests.get(position).getFirstNameRequest()+" "+requests.get(position).getLastNameRequest());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return requests.size();
    }

    public boolean compare( String dateUser){

        Date date = new Date();

        String str = new SimpleDateFormat("yyyy-MM-dd").format(date);

        String yearReq = str.substring(0,4);
        String moisReq = str.substring(5,7);
        String jourReq =str.substring(8,9);

        String yearUser =dateUser.substring(0,4);;
        String moisUser = dateUser.substring(5,7); ;
        String jourUser =dateUser.substring(8,9);;

        int dateR = Integer.valueOf(yearReq)*10000 + Integer.valueOf(moisReq)*100 + Integer.valueOf(jourReq);
        int dateU = Integer.valueOf(yearUser)*10000 + Integer.valueOf(moisUser)*100 + Integer.valueOf(jourUser);

        int def = dateR - dateU;
        if(def > 300){
            return true;
        }

        return  false;
    }


    private void sendEmail(String to,String subject1,String message1) {
        //Getting content for email
        String email = to;
        String subject = subject1;
        String message = message1;

        //Creating SendMail object
        SendMail sm = new SendMail(context, email, subject, message);

        //Executing sendmail to send email
        sm.execute();
    }
}
