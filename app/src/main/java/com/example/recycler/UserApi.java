package com.example.recycler;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserApi {
    @GET("/api/?inc=gender,name,location,email,dob,phone,picture&results=20")
    Call<UserData> getUsersData();
}
