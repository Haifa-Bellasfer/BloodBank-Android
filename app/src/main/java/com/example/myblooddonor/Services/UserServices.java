package com.example.myblooddonor.Services;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myblooddonor.Bloodrequest;
import com.example.myblooddonor.Entity.User;
import com.example.myblooddonor.Fragments.MyrequestsFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserServices {
    public static String IP ="http://192.168.68.1:3000/";
    private String mailf;
    private String passwordf;
    SharedPreferences sharedPref;
    String username123,password123;
    User u=new User();
    String tokenuser;
    public static User userconnecte;

    public void Login(Context c, String email, String password)
    {
        final  Context context=c;
        ArrayList<String> resultat=new ArrayList<>();
        resultat.add("");
        boolean resultatlogin1;

        mailf=email;
        passwordf=password;
        RequestQueue rq;
        String url=IP+"login";
        System.out.println("nzelt 3al bouttn login 11111");

        rq= Volley.newRequestQueue(context);


        StringRequest sreq=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("s7i7");
                try {
                    boolean resultatlogin;


                    System.out.println("dkhl fil parsing");

                    JSONObject obj=new JSONObject(response);

                    if(obj.getString("result").equalsIgnoreCase("success"))
                    {

                        JSONObject userobj= obj.getJSONObject("user");
                        User user= new User();
                        user.setId(userobj.getInt("id"));
                        user.setFirstName(userobj.getString("firstName"));
                        user.setLastName(userobj.getString("lastName"));
                        user.setEmail(userobj.getString("email"));
                        //user.setPassword(userobj.getString("password"));
                        user.setPhone(userobj.getString("phone"));
                        user.setCity(userobj.getString("city"));
                        user.setBloodType(userobj.getString("bloodType"));
                        user.setAge(userobj.getInt("age"));
                        user.setLast_donation(userobj.getString("last_donation").substring(0,userobj.getString("last_donation").indexOf('T')));
                        userconnecte=user;
                        //addtoken





                        System.out.println("salut: "+user.toString());


                        Toast.makeText(context,"Welcome !",Toast.LENGTH_LONG).show();

                        sharedPref = context.getSharedPreferences("bloodStorage", Context.MODE_PRIVATE);

                        username123 = sharedPref.getString("email", "");
                        password123 = sharedPref.getString("password", "");
                        SharedPreferences.Editor e = sharedPref.edit();
                        e.putString("email", userconnecte.getEmail());
                        e.putString("password", userconnecte.getPassword());
                        //e.commit();
                        e.apply();
                        Intent intent=new Intent(context, Bloodrequest.class);
                      //  intent.putExtra("email",sharedPref.getString("email", ""));
                        context.startActivity(intent);

                    }
                    else{
                        Toast.makeText(context,obj.getString("error"),Toast.LENGTH_LONG).show();
                        System.out.println("donnees invalides");
                        resultatlogin=false;


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
                params.put("email",mailf);
                params.put("password",passwordf);
                return params;
            }
        };



/*

                StringRequest sreq=new StringRequest(Request.Method.POST, URL1, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("bech yeb3ath response result");
                        System.out.println(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> params=new HashMap<String,String>();
                        params.put("mail",mail.getText().toString());
                        params.put("password",mdp.getText().toString());
                        return params;
                    }
                };

rq.add(sreq);
*/
        //youfa
        rq.add(sreq);



    }


    public void getUser(Context c,final int id,final CallBack onCallBack )
    {
        int cd;
        final  Context context=c;
        ArrayList<String> resultat=new ArrayList<>();
        resultat.add("");
        boolean resultatlogin1;

        RequestQueue rq;
        String url=UserServices.IP+"user/"+id;
        System.out.println("nzelt 3al bouttn login");

        rq= Volley.newRequestQueue(context);


        StringRequest sreq=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("s7i7");
                try {
                    boolean resultatlogin;


                    System.out.println("dkhl fil parsing");


                    JSONArray array = new JSONArray(response);

                    JSONObject userobj=array.getJSONObject(0);





                    User user= new User();
                    user.setId(userobj.getInt("id"));
                    user.setFirstName(userobj.getString("firstName"));
                    user.setLastName(userobj.getString("lastName"));
                    user.setEmail(userobj.getString("email"));
                    //user.setPassword(userobj.getString("password"));
                    user.setPhone(userobj.getString("phone"));
                    user.setCity(userobj.getString("city"));
                    user.setBloodType(userobj.getString("bloodType"));
                    user.setAge(userobj.getInt("age"));
                    user.setLast_donation(userobj.getString("last_donation").substring(0,userobj.getString("last_donation").indexOf('T')));
                    user.setCount_donation(userobj.getInt("count_donation"));




                    onCallBack.onSuccess(user);




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
    public void  update(Context c, final  int id, final String email, final  String phone){
        final  Context context=c;
        RequestQueue rq;
        String url=UserServices.IP+"user/update";

        rq= Volley.newRequestQueue(context);


        StringRequest sreq=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("s7i7");
                try {
                    boolean resultatlogin;


                    System.out.println("dkhl fil parsing");



                    JSONObject obj=new JSONObject(response);

                    if(obj.getString("result").equalsIgnoreCase("success")) {


                        JSONObject userobj = obj.getJSONObject("user");
                     /*   String name1=userobj.getString("firstname");
                        String email1=userobj.getString("email");
                        String phone1=userobj.getString("phone");
                        User user= new User();
                       // user.setId(userobj.getInt("id"));
                        user.setFirstName(name1);
                        user.setLastName(email1);
                        user.setEmail(phone1);*/

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
                params.put("email",email);
                params.put("phone",phone);
                return params;
            }
        };
        rq.add(sreq);

    }

    public void updateUserDate(Context c, final int id )
    {
        final  Context context=c;


        RequestQueue rq;
        String url=UserServices.IP+"userDate/update";
        System.out.println("nzelt 3al bouttn");

        rq= Volley.newRequestQueue(context);


        StringRequest sreq=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("s7i7");
                try {



                    System.out.println("dkhl fil parsing");
                    System.out.println("badel el count ");




                    if (response.equalsIgnoreCase("update success !")){
                        System.out.println("updata :p !");
                    }


                } catch (Exception e) {
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
                UserServices.userconnecte.setLast_donation(str);
                params.put("id",String.valueOf(id));
                params.put("last_donation",str);
                params.put("count_donation",String.valueOf(UserServices.userconnecte.getCount_donation() + 1));


                return params;
            }
        };




        rq.add(sreq);




    }


    public  void  AfficherDonors(Context c, final UserServices.CallBack2 onCallBack )
    {

        final  Context context=c;
        RequestQueue rq;
        String url=UserServices.IP+"donors";

        System.out.println("nzelt 3al bouttn donors");

        rq= Volley.newRequestQueue(context);


        StringRequest sreq=new StringRequest(com.android.volley.Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("s7i7");

                ArrayList<User> users = new ArrayList<>();
                try {




                    JSONArray ja=new JSONArray(response);
                    for(int j=0;j<ja.length();j++)
                    {

                        User r=new User();
                        JSONObject requestobj=ja.getJSONObject(j);







                        r.setId(requestobj.getInt("id"));

                        r.setFirstName( requestobj.getString("firstName"));
                        r.setLastName( requestobj.getString("lastName"));
                        r.setEmail(requestobj.getString("email"));

                        r.setLast_donation( requestobj.getString("last_donation").substring(0,requestobj.getString("last_donation").indexOf('T')));
                        r.setCount_donation(requestobj.getInt("count_donation"));



                        System.out.println("users : "+ j);
                        System.out.println(r.toString());

                        if (r.getId() != UserServices.userconnecte.getId() ){
                            users.add(r);
                        }


                    }




                    System.out.println("succes");


                    onCallBack.onSuccess(users);


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
        void onSuccess(User user);


    }
    public interface CallBack2 {
        void onSuccess(ArrayList<User> users);


    }

}
