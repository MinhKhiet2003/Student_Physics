package com.example.baitaplonquanlysinhvienhnue;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiSevice {


    @POST("students")
    Call<Void> addNewUser(@Body User newUser);
    @GET("students")
    Call<List<User>> getUsers();
    @DELETE("students/{id}")
    Call<Void> deleteUser(@Path("id") String studentId);

    @POST("register")
    Call<Void> register(@Body User user);
    @GET("login")
    Call<List<User>> getUser();
    @PUT("fogotpassword")
    Call<Void> fogotpassword(@Body User user);

}
