package com.teamnoob.sacstaterideshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class newSearch extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{
    private static boolean userRoundTrip = false;
    private static String userStatus;
    private static String userGender;
    private static String userDisable;
    private static TextView searchHeader;


    private static final int spinnerWidth = 280;
    private static SearchView barSearch = null;// = new SearchView();
    private static final String[] rtSpinnerArray ={"One Way", "Round Trip"};
    private static final String[] statusSpinnerArray ={"Status:", "Student", "Faculty"};
    private static final String[] genderSpinnerArray ={"Gender:", "Female", "Male", "Other"};
    private static final String[] disableSpinnerArray ={"Disability:", "YES", "NO"};
    private static Button nextButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_search);

        //create Search Header
        searchHeader = (TextView)findViewById(R.id.searchTextView);
        //Create Search Bar for username
        barSearch = (SearchView)findViewById(R.id.searchView);
        barSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String userQuery){
                Intent userNameSearch = new Intent(newSearch.this, SearchResultActivity.class);
                userNameSearch.putExtra("username", userQuery);
                startActivity(userNameSearch);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText){
                //do nothing
                return true;
            }

        });
        //**************************Create Spinners
        create_and_fill_Spinners();

        //create next button
        nextButton = (Button)findViewById(R.id.nextButton);
        nextButton.setOnClickListener(this);



    }

    private void create_and_fill_Spinners(){

        //Round trip spinner
        Spinner roundTripSpinner = (Spinner) findViewById(R.id.tripSpinner);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(newSearch.this,
                android.R.layout.simple_spinner_item, rtSpinnerArray);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roundTripSpinner.setAdapter(adapter1);
        //roundTripSpinner.setDropDownWidth(spinnerWidth);
        roundTripSpinner.setOnItemSelectedListener(this);

        // status spinner
        Spinner statusSpinner = (Spinner) findViewById(R.id.statusSpinner);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(newSearch.this,
                android.R.layout.simple_spinner_item, statusSpinnerArray);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(adapter2);
        //statusSpinner.setDropDownWidth(spinnerWidth);
        statusSpinner.setOnItemSelectedListener(this);

        //gender spinner
        Spinner genderSpinner = (Spinner) findViewById(R.id.genderSpinner);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(newSearch.this,
                android.R.layout.simple_spinner_item, genderSpinnerArray);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter3);
    //**********************************************************************************
        //genderSpinner.setDropDownWidth(spinnerWidth);
        //*******************************************************************************
        genderSpinner.setOnItemSelectedListener(this);

        //disable spinner
        Spinner disableSpinner = (Spinner) findViewById(R.id.disableSpinner);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(newSearch.this,
                android.R.layout.simple_spinner_item, disableSpinnerArray);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        disableSpinner.setAdapter(adapter4);
        //disableSpinner.setDropDownWidth(spinnerWidth);
        disableSpinner.setOnItemSelectedListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v == nextButton){
            if(userRoundTrip==true){
                //TODO start roundTrip intent
                Intent rtSearch = new Intent(newSearch.this,RoundTripSearchActivity.class);
                //zip,
                rtSearch.putExtra("status", userStatus);
                rtSearch.putExtra("gender",userGender);
                rtSearch.putExtra("disable",userDisable);
                startActivity(rtSearch);
            }
            else{
                //TODO start oneWay trip intent
                Intent owSearch = new Intent(newSearch.this,OneWaySearchActivity.class);
                //zip,
                owSearch.putExtra("status", userStatus);
                owSearch.putExtra("gender",userGender);
                owSearch.putExtra("disable", userDisable);
                startActivity(owSearch);

            }
        }
        /*if(v ==  ){
            startActivity(new Intent(this,ActivityLogin.class));
        }*/
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id){
        switch(parent.getId()){
            case R.id.tripSpinner: //roundTrip
                switch(position){
                    case 0: //"One Way"
                        userRoundTrip = false;
                        break;
                    case 1: //RoundTrip
                        userRoundTrip = true;
                        break;
                }
                break;
            case R.id.statusSpinner: //school status
                switch(position){
                    case 0: //Status
                        userStatus=null;
                        break;
                    case 1://Student
                        userStatus = "student";
                        break;
                    case 2://faculty
                        userStatus = "faculty";
                        break;
                }
                break;
            case R.id.genderSpinner: //spinner3: //gender
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
            case R.id.disableSpinner://spinner4: //disability
                switch(position){
                    case 0://Disable
                        break;
                    case 1://yes
                        userDisable = "true";
                        break;
                    case 2: //no
                        userDisable = "no";
                        break;
                }
                break;
        }


    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0){

    }

}
