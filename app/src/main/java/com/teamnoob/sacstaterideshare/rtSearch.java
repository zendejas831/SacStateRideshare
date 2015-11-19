package com.teamnoob.sacstaterideshare;


import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.*;

/**
 * A login screen that offers login via email/password.
 */


public class rtSearch extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    //private static final String[] rtSpinnerArray ={"One Way", "Round Trip"};
    private static final String[] statusSpinnerArray ={"Student", "Faculty"};
    private static final String[] genderSpinnerArray ={"Female", "Male"};
    private static final String[] disableSpinnerArray ={"Require Disability Services"};

    private Button searchButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rt_activity_search);

        // Set up the login form.
//TODO fucking connect it to the network!!

      //  SearchView searchBar = (SearchView)findViewById(R.id.search_execute_button);


//**************************Create Spinners/drop downs
        //Round trip spinner
       /* Spinner roundTripSpinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(rtSearch.this,
                android.R.layout.simple_spinner_item, rtSpinnerArray);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roundTripSpinner.setAdapter(adapter1);
        roundTripSpinner.setOnItemSelectedListener(this);
        */

        // status spinner
        Spinner statusSpinner = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(rtSearch.this,
                android.R.layout.simple_spinner_item, statusSpinnerArray);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(adapter2);
        statusSpinner.setOnItemSelectedListener(this);

        /*statusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3){

            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0){

            }
        });*/


        //gender spinner
        Spinner genderSpinner = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(rtSearch.this,
                android.R.layout.simple_spinner_item, genderSpinnerArray);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter3);
        genderSpinner.setOnItemSelectedListener(this);


        //disable spinner
        Spinner disableSpinner = (Spinner) findViewById(R.id.spinner4);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(rtSearch.this,
                android.R.layout.simple_spinner_item, disableSpinnerArray);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        disableSpinner.setAdapter(adapter4);
        disableSpinner.setOnItemSelectedListener(this);

//**************************Create Buttons
        searchButton = (Button)findViewById(R.id.search_execute_button); //create button
        searchButton.setOnClickListener(this); //if I click the search button


        //TODO create 2 date pickers
        //TODO create 2 buttons for setting to/from time

    } // END ONCREATE



    @Override
    public void onClick(View v) {
        if(v == searchButton){
            //TODO add search mysql call or php script
        }
        /*if(v == ){
            startActivity(new Intent(this,ActivityLogin.class));
        }*/
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id){
        switch(parent.getId()){
            /*case R.id.spinner: //roundTrip
                switch(position){
                    case 0:
                        break;
                    case 1:
                        break;
                }
                break;*/
            case R.id.spinner2: //school status
                switch(position){
                    case 0:
                        break;
                    case 1:
                        break;
                }
                break;
            case R.id.spinner3: //gender
                switch(position){
                    case 0:
                        break;
                    case 1:
                        break;
                }
                break;
            case R.id.spinner4: //disability
                switch(position){
                    case 0:
                        break;
                    case 1:
                        break;
                }
                break;
        }


    }
@Override
public void onNothingSelected(AdapterView<?> arg0){

}


}