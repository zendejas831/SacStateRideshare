package com.teamnoob.sacstaterideshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class searchLanding extends AppCompatActivity implements View.OnClickListener {

    private TextView textTellUser;
    private Button OneWayButton;
    private Button RoundTripButton;
    private EditText searchUserField;
    private String usernameSearchFor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_landing);
//*********************Create Text Editor for Search Function

        searchUserField = (EditText) findViewById(R.id.search_username);
        searchUserField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handeled =false;
                if(actionId == EditorInfo.IME_ACTION_SEND);{

                    usernameSearchFor = searchUserField.getText().toString().trim();
                    //TODO add search by username
                    handeled = true;
                }
                return handeled;
            }

        });

        // search for this username ->    usernameSearchFor

//*********************Create TextView to tell user how to use page
        textTellUser = (TextView) findViewById(R.id.tellUser);


//*********************Create Both buttons
        OneWayButton = (Button)findViewById(R.id.oneWay_button); //create button
        OneWayButton.setOnClickListener(this); //if I click the button

        RoundTripButton = (Button)findViewById(R.id.roundTrip_button); //create button
        RoundTripButton.setOnClickListener(this); //if I click the button
    }

   /* @Override
    public boolean OnEditorAction(TextView v, int actionId, KeyEvent event){
        boolean handeled =false;
        if(actionId == EditorInfo.IME_ACTION_SEND);{

            String usernameSearchFor = searchUserField.getText().toString().trim();
            //TODO add search by username
            handeled = true;
        }
        return handeled;
    }
*/


    public void onClick(View v) {
        if(v == RoundTripButton){
            startActivity(new Intent(this, rtSearch.class));
        }
        if(v == OneWayButton){
            startActivity(new Intent(this,owSearch.class));
        }
    }

}