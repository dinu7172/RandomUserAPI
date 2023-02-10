package com.example.recycler;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.Serializable;
import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private static final String BASE_URL = "https://randomuser.me/";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recyclerview initialization
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//        UserAPI userAPI = retrofit.create(UserAPI.class);
//        Log.d("data","it is running");
        UserAPI userAPI = retrofit.create(UserAPI.class);
        Call<UserData> call = userAPI.getUsersData();
//        Call<UserData> call = userAPI.getUsersData("gender,name,location,email,dob,phone,picture", 20);
        call.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                if (!response.isSuccessful()) {
                    Log.e("MainActivity", "Code: " + response.code());
                    return;
                }
                UserData userData = response.body();
                List<User> users = userData.getResults();
                //        Use your recyclerView
                recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, users);
                recyclerView.setAdapter(recyclerViewAdapter);

                for (User user : users) {
                    Log.i("MainActivity", "First Name: " + user.getName().getFirst());
                    Log.i("MainActivity", "Gender: " + user.getGender());
                    Log.i("MainActivity", "Email: " + user.getEmail());
                }
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {

                Log.e("MainActivity", "Error: " + t.getMessage());
            }
        });


        // Get all contacts
//        List<User> users = users.getResults();



        Log.d("dbharry", "Bro you have  contacts in your database");



    }
}
//__________________________________________________________________________________________________________________________________________________________________
interface UserAPI {
//    @GET("/api/?inc=gender,name,location,email,dob,phone,picture&results=20")
//    Call<UserData> getUsersData();
//    @GET("api/")
//    Call<UserData> getUsersData(@Query("?inc") String inc, @Query("results") int results);
}
//__________________________________________________________________________________________________________________________________________________________________
class UserData {
    private List<User> results;

    public List<User> getResults() {
        return results;
    }

    public void setResults(List<User> results) {
        this.results = results;
    }
}


class User{
    private Name name;
    private String gender;
    private String email;
    private Picture picture;
    private Dob dob;
   com.example.recycler.Location location;
    private String phone;


    public String getLocation(){
        return location.getStreet().name+"  "+location.getCity()+"  "+location.getState();
    }

    public String getDate(){
        return dob.getDate();
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageUrl(){
        return picture.getLarge();
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}

class Picture{
    private String large;
    public String getLarge(){
        return large;
    }
    public void setLarge(String large){
        this.large = large;
    }


}
// Get the names of the random user
class Name {
    private String first;
    private String last;
    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }
    public String getLast(){ return last;}
    public void setLast(String last) {
        this.last = last;
    }

}

class Dob {
    private String date;
    private int age;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}