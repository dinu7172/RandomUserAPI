package com.example.recycler;

import static android.view.Gravity.apply;

import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


//import com.example.recycler.User;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private List<User> users;
    private Context context;


    public RecyclerViewAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    // Where to get the single card as viewholder Object
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    // What will happen after we create the viewholder object
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        User user = users.get(position);
        Log.d("dinesh", String.valueOf(user));
        String  uName = user.getName().getFirst()+user.getName().getLast();
        holder.userName.setText(uName);
        holder.gender.setText(user.getGender());

        Glide.with(context)

                .load(user.getImageUrl())
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .into(holder.iconButton);



    }

    // How many items?
    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView userName;
        public TextView gender;
        public ImageView iconButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            userName = itemView.findViewById(R.id.name);
            gender = itemView.findViewById(R.id.phone_number);
            iconButton = itemView.findViewById(R.id.icon_button);

            iconButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d("ClickFromViewHolder", "Clicked");

            int position = getAdapterPosition();
            User user = users.get(position);
            String user_name = user.getName().getFirst()+"  "+user.getName().getLast();
            String location = user.getLocation();



            Intent intent = new Intent(context, UserDetail.class);
            intent.putExtra("user_name", user_name);
            intent.putExtra("email", user.getEmail());
            intent.putExtra("image",user.getImageUrl());
            intent.putExtra("location", user.getLocation());
            intent.putExtra("phone", user.getPhone());
            intent.putExtra("dob", user.getDate());
//            intent.putExtra("user", user);
            context.startActivity(intent);
        }
    }
}

