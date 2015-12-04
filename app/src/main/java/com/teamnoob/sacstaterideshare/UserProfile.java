package com.teamnoob.sacstaterideshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity implements View.OnClickListener{

    private TextView textView;

    private Button buttonProfile;
    private Button buttonNewSearch;
    private Button buttonRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        textView = (TextView) findViewById(R.id.textViewUserName);

        Intent intent = getIntent();

        String username = intent.getStringExtra(ActivityLogin.USER_NAME);

        textView.setText("Welcome User " + username);

        /*buttonSearchLanding = (Button) findViewById(R.id.buttonSearchLanding);
        buttonSearchLanding.setOnClickListener(this);*/

        buttonProfile = (Button) findViewById(R.id.buttonProfile);
        buttonProfile.setOnClickListener(this);

        buttonNewSearch = (Button) findViewById(R.id.button_new_search);
        buttonNewSearch.setOnClickListener(this);

        buttonRating = (Button) findViewById(R.id.buttonRating);
        buttonRating.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonNewSearch){
            startActivity(new Intent(this, newSearch.class));
        }
        if(v == buttonProfile ) {
            startActivity(new Intent(this, ProfileActivity.class));
        }
        if(v == buttonRating ) {
            startActivity(new Intent(this, RatingActivity.class));
        }
    }

}
