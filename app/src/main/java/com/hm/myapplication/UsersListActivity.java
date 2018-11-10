package com.hm.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hm.myapplication.models.UserModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UsersListActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<UserModel> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        if(getIntent().hasExtra("users")) {

            users = (ArrayList<UserModel>) getIntent().getSerializableExtra("users");
        }

        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new Adapter());

        recyclerView.getAdapter().notifyDataSetChanged();
    }

    public class Adapter extends RecyclerView.Adapter<Adapter.CustomViewHolder> {


        public Adapter() {

        }

        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_user, null);

            CustomViewHolder viewHolder = new CustomViewHolder(view);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {

            UserModel user = users.get(i);

            Picasso.get().load(user.photoUrl).into(customViewHolder.imageView);
            customViewHolder.usernameTextView.setText(user.name);
        }

        @Override
        public int getItemCount() {
            return users.size();
        }

        class CustomViewHolder extends RecyclerView.ViewHolder {
            protected ImageView imageView;
            protected TextView usernameTextView;

            public CustomViewHolder(View view) {
                super(view);
                this.imageView = (ImageView) view.findViewById(R.id.user_image_view);
                this.usernameTextView = (TextView) view.findViewById(R.id.username_text_view);
            }
        }
    }
}
