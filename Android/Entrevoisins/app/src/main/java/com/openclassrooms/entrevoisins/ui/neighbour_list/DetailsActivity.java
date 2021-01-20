package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.activity_avatar_details)
    ImageView avatarDetails;
    @BindView(R.id.activity_name_details)
    TextView nameDetails;
    @BindView(R.id.activity_address_details)
    TextView addressDetails;
    @BindView(R.id.activity_number_details)
    TextView numberDetails;
    @BindView(R.id.activity_mail_details)
    TextView emailDetails;
    @BindView(R.id.activity_aboutMe_details)
    TextView aboutMeDetails;
    @BindView(R.id.activity_name_users)
    TextView nameUsers;
    @BindView(R.id.floatingActionButton_favorites)
    FloatingActionButton addFavorites;
    @BindView(R.id.activity_button_back)
    ImageButton buttonRet;

    private NeighbourApiService mApiService;
    private Neighbour mNeighbour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        mApiService = DI.getNeighbourApiService();

        getSupportActionBar().hide();

        Intent intent = getIntent();
        Gson gson = new Gson();
        mNeighbour = gson.fromJson(intent.getStringExtra("neighbour"), Neighbour.class);

        addFavorites();
        configButtonFav();
        create();
        onBackHome();
    }

    private void create () {
        Glide.with(this)
                .load(mNeighbour.getAvatarUrl())
                .into(avatarDetails);
        nameUsers.setText
                (mNeighbour.getName());
        nameDetails.setText
                (mNeighbour.getName());
        addressDetails.setText
                (mNeighbour.getAddress());
        numberDetails.setText
                (mNeighbour.getPhoneNumber());
        aboutMeDetails.setText
                (mNeighbour.getAboutMe());
        addText();
    }
    private void addText() {
        emailDetails
                .setText("www.facebook.fr/" + mNeighbour.getName());
    }

    private void addFavorites () {
        addFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mApiService.getFavorites().contains(mNeighbour)) {
                    mApiService.addFavorites(mNeighbour);

                } else {
                    mApiService.deleteFavorites(mNeighbour);
                }
                configButtonFav();
            }
        });
    }

    private void configButtonFav() {
        if (!mApiService.getFavorites().contains(mNeighbour)) {
            addFavorites.setImageResource(R.drawable.ic_star_border_white_24dp);

        } else {
            addFavorites.setImageResource(R.drawable.ic_star_white_24dp);
            Toast.makeText(this, "is added in the favorites", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private void onBackHome() {
        buttonRet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, ListNeighbourActivity.class);
                startActivity(intent);
            }
        });
    }
}