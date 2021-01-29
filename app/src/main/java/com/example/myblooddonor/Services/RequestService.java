package com.example.myblooddonor.Services;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myblooddonor.Entity.Request;
import com.example.myblooddonor.Entity.User;
import com.example.myblooddonor.Fragments.FragmentRequest;
import com.example.myblooddonor.Fragments.MyrequestsFragment;
import com.example.myblooddonor.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RequestService {

    Request req=new Request();

    public  void  AfficherRequest(Context c, final CallBack onCallBack )
    {

        final  Context context=c;
        RequestQueue rq;
        String url=UserServices.IP+"allRequest";

        System.out.println("nzelt 3al bouttn 1");

        rq= Volley.newRequestQueue(context);


        StringRequest sreq=new StringRequest(com.android.volley.Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("s7i7");

                ArrayList<Request> requests = new ArrayList<>();
                try {



                            // JSONObject userobj=obj.getJSONObject("users");
                            JSONArray ja=new JSONArray(response);
                            for(int j=0;j<ja.length();j++)
                            {

                                  Request r=new Request();
                                JSONObject requestobj=ja.getJSONObject(j);


                                r.setId(requestobj.getInt("id"));
                                r.setUser_id(requestobj.getInt("user_id"));
                                r.setFirstNameRequest( requestobj.getString("firstNameRequest"));
                                r.setLastNameRequest( requestobj.getString("lastNameRequest"));
                                r.setDescription( requestobj.getString("description"));
                                r.setDate_request( requestobj.getString("date_request").substring(0,requestobj.getString("date_request").indexOf('T')));
                                r.setCityRequest_lan(requestobj.getString("cityRequest_lat"));
                                r.setCityRequest_lon(requestobj.getString("cityRequest_lon"));
                                r.setBloodTypeRequest(requestobj.getString("bloodTypeRequest"));
                                r.setPhoneRequest(requestobj.getString("phone_request"));


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

                                if (r.getUser_id() != UserServices.userconnecte.getId() ){
                                    requests.add(r);
                                }


                            }




                            System.out.println("succes");


                            onCallBack.onSuccess(requests);


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
    //myrequests

    public  void  AfficherMesRequests(Context c1, final CallBack onCallBack)
    {

        final  Context context1=c1;
        RequestQueue rq1;
        String url=UserServices.IP+"myRequest/"+UserServices.userconnecte.getId();

        System.out.println("nzelt 3al bouttn my request");

        rq1= Volley.newRequestQueue(context1);


        StringRequest sreq1=new StringRequest(com.android.volley.Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("s7i7");

                ArrayList<Request> requests1 = new ArrayList<>();
                try {



                    // JSONObject userobj=obj.getJSONObject("users");
                    JSONArray ja=new JSONArray(response);
                    for(int j=0;j<ja.length();j++)
                    {

                        Request r=new Request();
                        JSONObject requestobj1=ja.getJSONObject(j);


                        r.setId(requestobj1.getInt("id"));
                        r.setUser_id(requestobj1.getInt("user_id"));
                        // r.setTitre( requestobj.getString("titre"));
                        r.setDescription( requestobj1.getString("description"));
                        r.setDate_request( requestobj1.getString("date_request").substring(0,requestobj1.getString("date_request").indexOf('T')));
                       // r.setCityRequest(requestobj1.getString("cityRequest"));
                        r.setBloodTypeRequest(requestobj1.getString("bloodTypeRequest"));
                        r.setPhoneRequest(requestobj1.getString("phone_request"));
                        r.setCityRequest_lan(requestobj1.getString("cityRequest_lat"));
                        r.setCityRequest_lon(requestobj1.getString("cityRequest_lon"));



                        System.out.println("request : "+ j);
                        System.out.println(r.toString());

                        if (r.getUser_id() == UserServices.userconnecte.getId()){
                            requests1.add(r);
                        }


                    }




                    System.out.println("succes");


                    onCallBack.onSuccess(requests1);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });





        rq1.add(sreq1);




    }
/*

    public void AjouterAnnonce( Context c, Annonce a )
    {
        final  Context context=c;
        an=a;
        System.out.println("annonce from entries : "+an.toString() );
        RequestQueue rq;
        String url=UserService.adresse+"/annonce/ajouter_annonce.php";
        System.out.println("nzelt 3al bouttn");

        rq= Volley.newRequestQueue(context);


        StringRequest sreq=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("s7i7");
                try {

                    JSONArray array=new JSONArray(response);

                    System.out.println("dkhl fil parsing");




                    JSONObject obj=array.getJSONObject(0);
                    if(obj.getString("result").equalsIgnoreCase("succes"))
                    {
                        System.out.println("succes");


                        Toast.makeText(context,"add with succes",Toast.LENGTH_LONG).show();

                        Intent intent=new Intent(context,HomeActivity.class);
                        context.startActivity(intent);

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

                params.put("titre",an.getTitre());
                params.put("date",an.getDate());
                params.put("type",an.getType());
                params.put("lat",an.getEmplacement_lat());
                params.put("description",an.getDescription());
                params.put("long",an.getEmplacement_long());
                params.put("user",String.valueOf(an.getUser().getId()));
                return params;
            }
        };




        rq.add(sreq);




    }

*/
    public void SupprimerRequest(Context c, final int id,MyrequestsFragment fragment)
    {
        final  Context context=c;
        final   MyrequestsFragment f=fragment;

        RequestQueue rq;
        String url=UserServices.IP+"request/delete/"+id;
        System.out.println("nzelt 3al bouttn");

        rq= Volley.newRequestQueue(context);


        StringRequest sreq=new StringRequest(com.android.volley.Request.Method.DELETE, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("s7i7");
                try {



                        if(response.equalsIgnoreCase("Deleted successfuly"))
                        {
                            System.out.println("succes");


                            Toast.makeText(context,"Delete with succes",Toast.LENGTH_LONG).show();
                           // f.getFragmentManager().beginTransaction().replace(R.id.viewp, new MyrequestsFragment()).commit();
                            f.getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frmlyt1,new FragmentRequest()).commit();

                            // MyrequestsFragment.homeSwipe.refreshDrawableState();
                        }

                } catch (Exception  e) {
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

                params.put("id",String.valueOf(id));
                return params;
            }
        };




        rq.add(sreq);




    }
/*
    public void SupprimerAnnonceDetail(Context c, final int id)
    {
        final  Context context=c;

        RequestQueue rq;
        String url=UserService.adresse+"/annonce/supprimer_annonce.php";
        System.out.println("nzelt 3al bouttn");

        rq= Volley.newRequestQueue(context);


        StringRequest sreq=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("s7i7");
                try {

                    JSONArray array=new JSONArray(response);

                    System.out.println("dkhl fil parsing");



                    for(int i=0;i<array.length();i++){
                        JSONObject obj=array.getJSONObject(i);
                        if(obj.getString("result").equalsIgnoreCase("succes"))
                        {
                            System.out.println("succes");


                            Toast.makeText(context,"Delete with succes",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(context, HomeActivity.class);
                            context.startActivity(intent);
                        }
                        else{
                            System.out.println("failed");


                            Toast.makeText(context,"Delete failed",Toast.LENGTH_LONG).show();


                        }

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

                params.put("id",String.valueOf(id));
                return params;
            }
        };




        rq.add(sreq);




    }

    public void ModifierAnnonce( Context c, Annonce a )
    {
        final  Context context=c;
        an=a;

        RequestQueue rq;
        String url=UserService.adresse+"/annonce/modifier_annonce.php";
        System.out.println("nzelt 3al bouttn");

        rq= Volley.newRequestQueue(context);


        StringRequest sreq=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("s7i7");
                try {

                    JSONArray array=new JSONArray(response);

                    System.out.println("dkhl fil parsing");



                    for(int i=0;i<array.length();i++){
                        JSONObject obj=array.getJSONObject(i);
                        if(obj.getString("result").equalsIgnoreCase("succes"))
                        {
                            System.out.println("succes");


                            Toast.makeText(context,"Edit with succes",Toast.LENGTH_LONG).show();

                            Intent intent=new Intent(context, HomeActivity.class);
                            context.startActivity(intent);
                        }
                        else{
                            System.out.println("failed");


                            Toast.makeText(context,"Edit failed",Toast.LENGTH_LONG).show();

                        }

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

                params.put("titre",an.getTitre());
                params.put("date",an.getDate());
                params.put("type",an.getType());
                params.put("lat",an.getEmplacement_lat());
                params.put("description",an.getDescription());
                params.put("long",an.getEmplacement_long());
                params.put("id",String.valueOf(an.getId()));
                return params;
            }
        };




        rq.add(sreq);




    }


*/

    public void getRequest(Context c2,final int id,final RequestService.CallBack2 onCallBack )
    {
        final  Context context2=c2;
        ArrayList<String> resultat2=new ArrayList<>();
        resultat2.add("");
        boolean resultatRequest2;


        RequestQueue rq2;
        String url=UserServices.IP+"getRequest/"+id;
        System.out.println("nzelt 3al bouttn my request");

        rq2= Volley.newRequestQueue(context2);


        StringRequest sreq2=new StringRequest(com.android.volley.Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("s7i7");


                try {



                    // JSONObject userobj=obj.getJSONObject("users");
                    JSONArray ja1=new JSONArray(response);

                        Request r2=new Request();
                        JSONObject requestobj2=ja1.getJSONObject(0);






                        r2.setId(requestobj2.getInt("id"));
                    r2.setUser_id(requestobj2.getInt("user_id"));
                    // r.setTitre( requestobj.getString("titre"));
                    r2.setDescription( requestobj2.getString("description"));
                    r2.setDate_request( requestobj2.getString("date_request").substring(0,requestobj2.getString("date_request").indexOf('T')));
                   // r2.setCityRequest(requestobj2.getString("cityRequest"));
                    r2.setBloodTypeRequest(requestobj2.getString("bloodTypeRequest"));
                    r2.setPhoneRequest(requestobj2.getString("phone_request"));

                    r2.setCityRequest_lan(requestobj2.getString("cityRequest_lat"));
                    r2.setCityRequest_lon(requestobj2.getString("cityRequest_lon"));





                    onCallBack.onSuccess(r2);




                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        rq2.add(sreq2);



    }
    public interface CallBack {
        void onSuccess(ArrayList<Request> requests);

        void onFail(String msg);
    }

    public interface CallBack2 {
        void onSuccess(Request request);

        void onFail(String msg);
    }
}


