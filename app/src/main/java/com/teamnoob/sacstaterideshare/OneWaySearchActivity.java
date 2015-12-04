package com.teamnoob.sacstaterideshare;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class OneWaySearchActivity extends AppCompatActivity implements View.OnClickListener{
//public class OneWaySearchActivity extends AppCompatActivity implements View.OnClickListener{
    private static TextView oneWayHeader;
    private static Switch userToggle;
    private static boolean userToSchool;
    private static TextView userToFromText;
    private static TextView userDateText;
    private static TextView userTimeText;
    private static Button searchExecute_button;
    private static Button pickDate_button;
    private static Button pickTime_button;
    private static EditText userZipEditText;
    private static TextView tellUser_zip;
    private static String weekDayTo, userGender, userStatus, userDisable;




    private static int mYear, mMonth, mDay, mHour, mMinute;
    private static int userYear, userMonth, userDay, userHour, userMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onewaysearch_activity_search);

        //create Search Header
        oneWayHeader = (TextView)findViewById(R.id.roundtripTextView);

        //Create switch
        createSwitch();
        //Create Date fields
        userDateText = (TextView)findViewById(R.id.txtDate);
        pickDate_button = (Button)findViewById(R.id.btnDate);
        pickDate_button.setOnClickListener(this);
        //Create Time fields
        userTimeText = (TextView)findViewById(R.id.txtTime);
        pickTime_button = (Button)findViewById(R.id.btnTime);
        pickTime_button.setOnClickListener(this);
        //Create Location Fields
        tellUser_zip = (TextView)findViewById(R.id.textView4);
        userZipEditText = (EditText)findViewById(R.id.editZip);
        //Create Search Execute Button
        searchExecute_button = (Button)findViewById(R.id.oneWayExecute);
        searchExecute_button.setOnClickListener(this);
    }

    public void createSwitch(){
        //Create textView for switch
        userToFromText = (TextView)findViewById(R.id.fromHeader);
        userToFromText.setText("To School ");
        //Create Switch
        userToggle = (Switch)findViewById(R.id.toFromSwitch);
        userToggle.setChecked(true);
        userToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getApplicationContext(), "To School", Toast.LENGTH_SHORT).show();
                    userToFromText.setText("To School");
                    userToSchool = true;
                }
                else{
                    Toast.makeText(getApplicationContext(), "From School", Toast.LENGTH_SHORT).show();
                    userToFromText.setText("From School");
                    userToSchool = false;
                }
            }
        });
    }

    @Override
    public void onClick(View v){
        if (v == pickDate_button) {

            // get a calendar with a current date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            // launch picker
            DatePickerDialog dpd = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            //save user chosen date
                            userDay = dayOfMonth;
                            userMonth = monthOfYear+1;
                            userYear = year;

                            // display date chosen
                            weekDayTo = DateFormat.format("EE", new Date(year, userMonth, userDay+3)).toString();
                            userDateText.setText(weekDayTo+"   "+userMonth+"/"+userDay+"/"+userYear);

                        }
                    }, mYear, mMonth, mDay);
            dpd.show();

        }
        if (v == pickTime_button) {

            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            TimePickerDialog tpd = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            if(hourOfDay>12)
                                hourOfDay-=12;
                            // display to user
                            userTimeText.setText(hourOfDay + ":" + minute);
                            userHour = hourOfDay;
                            userMinute = minute;
                        }
                    }, mHour, mMinute, false);
            tpd.show();
        }
        if(v == searchExecute_button){
            //TODO if user hits execute button
            String userZip = userZipEditText.getText().toString().trim();

            ProgressDialog loading;
            loading = ProgressDialog.show(OneWaySearchActivity.this, "Searching...", null, true, true);


            Intent intentSearch = new Intent(OneWaySearchActivity.this,SearchResultActivity.class);
            if(userToSchool==true){

                userGender = getIntent().getExtras().getString("gender");
                userStatus= getIntent().getExtras().getString("status");
                userDisable = getIntent().getExtras().getString("disable");
                //optionals
                intentSearch.putExtra("gender", userGender);
                intentSearch.putExtra("status", userStatus);
                intentSearch.putExtra("disable", userDisable);

                intentSearch.putExtra("Trip", "oneway");
                intentSearch.putExtra("way", "to");
                intentSearch.putExtra("toSchool", true);
                intentSearch.putExtra("Zip Code",userZip);
                intentSearch.putExtra("To Day",weekDayTo);
                intentSearch.putExtra("To hour",userHour);
                intentSearch.putExtra("To minute",userMinute);
            }
            else{
                userGender = getIntent().getExtras().getString("gender");
                userStatus= getIntent().getExtras().getString("status");
                userDisable = getIntent().getExtras().getString("disable");
                //optionals
                intentSearch.putExtra("gender", userGender);
                intentSearch.putExtra("status", userStatus);
                intentSearch.putExtra("disable", userDisable);

                intentSearch.putExtra("Trip", "oneway");
                intentSearch.putExtra("way", "from");
                intentSearch.putExtra("toSchool", false);
                intentSearch.putExtra("Zip Code",userZip);
                intentSearch.putExtra("From Day",weekDayTo);
                intentSearch.putExtra("From hour",userHour);
                intentSearch.putExtra("From minute",userMinute);
            }
            startActivity(intentSearch);
            loading.dismiss();
        }

    }
}//END CLASS