package com.example.myblooddonor.Retrofit;



import java.util.Date;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ServiceNode {
    @FormUrlEncoded
    @POST("/singUp")
    Call<ResponseBody> registerUser(
            @Field("firstName") String firstname,
            @Field("lastName") String lastname,
            @Field("email") String email,
            @Field("password") String password,
            @Field("phone") String phone,
            @Field("city") String city,
            @Field("bloodType") String bloodtype,
            @Field("age") int age,
            @Field("last_donation")  String date);



    @FormUrlEncoded
    @POST("/login")
    Observable<String> loginUser(@Field("email") String email,
                                 @Field("password") String password);


    @FormUrlEncoded
    @POST("/request/add")
    Call<ResponseBody> addrequest(@Field("firstNameRequest") String firstNameRequest,
                                  @Field("lastNameRequest") String lastNameRequest,
                                  @Field("cityRequest_lat") String cityRequest_lat,
                                  @Field("cityRequest_lon") String cityRequest_lon,
                                  @Field("bloodTypeRequest") String bloodTypeRequest,
                                  @Field("description") String description,
                                  @Field("user_id") int user_id,
                                  @Field("phone_request") String phone,
                                  @Field("date_request")  String date,
                                  @Field("count_donation")  int count_donation);

}
