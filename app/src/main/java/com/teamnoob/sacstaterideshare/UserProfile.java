package com.teamnoob.sacstaterideshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity implements View.OnClickListener{//, AdapterView.OnItemSelectedListener{

    private static String userGender;
    private static final String[] genderSpinnerArray1 ={"Gender:", "Female", "Male", "Other"};

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

        create_and_fill_Spinners();
    }

    private void create_and_fill_Spinners(){

        Spinner spinner = (Spinner) findViewById(R.id.register_gender_spinner);//spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        /*//gender spinner
        Spinner genderSpinner = (Spinner) findViewById(R.id.register_gender_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(UserProfile.this,
                android.R.layout.simple_spinner_item, genderSpinnerArray1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);
        genderSpinner.setOnItemSelectedListener(this);*/
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

    /*@Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id){
        switch(parent.getId()){
            case R.id.register_gender_spinner: //spinner3: //gender
                switch(position){
                    case 0://Gender:
                        break;
                    case 1://female
                        userGender = "female";
                        break;
                    case 2: //male
                        userGender = "male";
                        break;
                    case 3: //other
                        userGender = "other";
                        break;
                }
                break;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0){

    }*/

}
