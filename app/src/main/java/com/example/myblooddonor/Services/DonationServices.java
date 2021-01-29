package com.example.myblooddonor.Services;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myblooddonor.Entity.Donation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DonationServices {

    Donation dn = new Donation();

    public void AddDonation(Context c, final int idR )
    {
        final  Context context=c;

        System.out.println("annonce from entries : "+dn.toString() );
        RequestQueue rq;
        String url=UserServices.IP+"donation/add";
        System.out.println("nzelt 3al bouttn");

        rq= Volley.newRequestQueue(context);


        StringRequest sreq=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("s7i7");
                try {



                    System.out.println("dkhl fil parsing");




                    JSONObject obj=new JSONObject(response);
                    if(obj.getString("success").equalsIgnoreCase("1"))
                    {
                        System.out.println("succes");


                        Toast.makeText(context,"add with succes",Toast.LENGTH_LONG).show();



                    }
                    else{
                        System.out.println("failed");


                        Toast.makeText(context,"add failed",Toast.LENGTH_LONG).show();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params=new HashMap<String,String>();

                Date date = new Date();

                String str = new SimpleDateFormat("yyyy-MM-dd").format(date);
                params.put("id_request",String.valueOf(idR));
                params.put("id_user_donation",String.valueOf(UserServices.userconnecte.getId()));
                params.put("date_donation",str);
                UserServices.userconnecte.setCount_donation(UserServices.userconnecte.getCount_donation()+1);




                return params;
            }
        };




        rq.add(sreq);




    }

    public  void  AfficherDonation(Context c, final DonationServices.CallBack onCallBack )
    {

        final  Context context=c;
        RequestQueue rq;
        String url=UserServices.IP+"myDonation";

        System.out.println("nzelt 3al bouttn 1");

        rq= Volley.newRequestQueue(context);


        StringRequest sreq=new StringRequest(com.android.volley.Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("s7i7");

                ArrayList<com.example.myblooddonor.Entity.Donation> donations = new ArrayList<>();
                try {



                    // JSONObject userobj=obj.getJSONObject("users");
                    JSONArray ja=new JSONArray(response);
                    for(int j=0;j<ja.length();j++)
                    {

                        com.example.myblooddonor.Entity.Donation r=new com.example.myblooddonor.Entity.Donation();
                        JSONObject requestobj=ja.getJSONObject(j);


                       // r.setId(requestobj.getInt("id"));
                        r.setId_request(requestobj.getInt("id_request"));
                        r.setId_user_donation(requestobj.getInt("id_user_donation"));
                        r.setDate_donation( requestobj.getString("date_donation").substring(0,requestobj.getString("date_donation").indexOf('T')));



                                /*JSONArray userarray=requestobj.getJSONArray("user");
                                for (int k=0;k<userarray.length();k++)
                                {
                                    JSONObject userobj=userarray.getJSONObject(k);
                                    User user=new User(userobj.getInt("id"),
                                            userobj.getString("nom"),userobj.getString("prenom"),
                                            userobj.getString("mail"),userobj.getString("password"),
                                            userobj.getString("phone"),userobj.getString("gender"),
                                            userobj.getString("date_naissance"),userobj.getString("photo"));
                                    a.setUser(user);
                                }*/


                        System.out.println("request : "+ j);
                        System.out.println(r.toString());

                        if (r.getId_user_donation() == UserServices.userconnecte.getId() ){
                            donations.add(r);
                        }


                    }




                    System.out.println("succes");


                    onCallBack.onSuccess(donations);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });




        rq.add(sreq);




    }

    public interface CallBack {
        void onSuccess(ArrayList<com.example.myblooddonor.Entity.Donation> donations);

        void onFail(String msg);
    }

}
