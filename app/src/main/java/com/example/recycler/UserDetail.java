package com.example.recycler;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class UserDetail extends AppCompatActivity {

    private ImageView imageView;
    private TextView locationTextView, emailTextView, dobTextView, phoneTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        imageView = findViewById(R.id.imageView);
        locationTextView = findViewById(R.id.locationTextView);
        emailTextView = findViewById(R.id.emailTextView);
        dobTextView = findViewById(R.id.dobTextView);
        phoneTextView = findViewById(R.id.phoneTextView);

//        User user = (User) getIntent().getSerializableExtra("user");
        Intent intent = getIntent();
//        String name = intent.getStringExtra("user_name");

//        User user = intent.getParcelableExtra("user");
            String image = intent.getStringExtra("image");
            String location = intent.getStringExtra("location");
            String dob = intent.getStringExtra("dob");
            String phone = intent.getStringExtra("phone");
            String email = intent.getStringExtra("email");


            Picasso.get().load(image).into(imageView);
            locationTextView.setText(location);
            emailTextView.setText(email);
            dobTextView.setText(dob);
            phoneTextView.setText(phone);


    }
}