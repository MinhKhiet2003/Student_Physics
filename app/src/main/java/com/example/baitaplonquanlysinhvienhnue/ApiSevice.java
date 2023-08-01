package com.example.baitaplonquanlysinhvienhnue;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
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
    @PUT("students/{id}")
    Call<Void> updateUser(@Path("id") String studentId, @Body User updatedUser);


    @POST("register")
    Call<User> register(@Body User user);
    @POST("login")
    Call<Void> login(@Body User user);
    @GET("tkmk")
    Call<List<User>> getUser();
    @PUT("fogotpassword")
    Call<Void> fogotpassword(@Body User user);
    @GET("profile")
    Call<User> getProfile(@Header("Authorization") String token);

}
