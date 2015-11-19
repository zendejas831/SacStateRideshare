package com.teamnoob.sacstaterideshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileActivity extends AppCompatActivity {

    private static Button button_rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        OnClickButtonListener();
    }

    public void OnClickButtonListener(){
        button_rating = (Button)findViewById(R.id.button_Rating);
        button_rating.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.aleksander.rideshare.RatingActivity");
                        //intent.putExtra(profile int);
                        startActivity(intent);
                    }
                }
        );
    }
}
