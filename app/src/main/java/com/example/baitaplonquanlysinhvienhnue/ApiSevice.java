package com.example.baitaplonquanlysinhvienhnue;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
public interface ApiSevice {


    @POST("students")
    Call<Void> addNewUser(@Body User newUser);
    @GET("students")
    Call<List<User>> getUsers();
}
