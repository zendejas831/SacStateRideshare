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
import java.util.Locale;

public class RoundTripSearchActivity extends AppCompatActivity implements View.OnClickListener{
    //public class OneWaySearchActivity extends AppCompatActivity implements View.OnClickListener{
    private static TextView RoundTripHeader;
    private static Switch userToggle;
    private static boolean userToSchool;
    private static TextView fromHeader;
    private static TextView toHeader;
    private static Button searchExecute_button;
    private static TextView userDateText, userDateTextFrom;
    private static TextView userTimeText, userTimeTextFrom;
    private static String userGender, userStatus, userDisable;


    String weekDayFrom, weekDayTo;
    private static Button pickDate_button, pickDate_buttonFrom;
    private static Button pickTime_button, pickTime_buttonFrom;
    private static EditText userZipEditText;
    private static TextView tellUser_zip;


    private static int mYear, mMonth, mDay, mHour, mMinute;
    private static int userYear, userMonth, userDay, userHour, userMinute;
    private static int userYearFrom, userMonthFrom, userDayFrom, userHourFrom, userMinuteFrom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_trip_search);
        //TODO get and pass optional arguments   from newSearch to SearchResult

        //create Headers
        RoundTripHeader = (TextView)findViewById(R.id.roundtripTextView);
        toHeader = (TextView)findViewById(R.id.fromHeader);
        fromHeader = (TextView)findViewById(R.id.toHeader);


        //Create Date fields
        userDateText = (TextView)findViewById(R.id.txtDate);
        pickDate_button = (Button)findViewById(R.id.btnDate);
        pickDate_button.setOnClickListener(this);
                //from
        userDateTextFrom = (TextView)findViewById(R.id.txtDateFrom);
        pickDate_buttonFrom = (Button)findViewById(R.id.btnDateFrom);
        pickDate_buttonFrom.setOnClickListener(this);

        //Create Time fields
        userTimeText = (TextView)findViewById(R.id.txtTime);
        pickTime_button = (Button)findViewById(R.id.btnTime);
        pickTime_button.setOnClickListener(this);
                //from
        userTimeTextFrom = (TextView)findViewById(R.id.txtTimeFrom);
        pickTime_buttonFrom = (Button)findViewById(R.id.btnTimeFrom);
        pickTime_buttonFrom.setOnClickListener(this);


        //Create Location Fields
        tellUser_zip = (TextView)findViewById(R.id.textView4);
        userZipEditText = (EditText)findViewById(R.id.editZip);
        //Create Search Execute Button
        searchExecute_button = (Button)findViewById(R.id.roundtripExecute);
        searchExecute_button.setOnClickListener(this);
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
                            // display date chosen
                            userDateText.setText(dayOfMonth + "-"
                                    + (monthOfYear + 1) + "-" + year);
                            //save user chosen date
                            userDay = dayOfMonth;
                            userMonth = monthOfYear+1;
                            userYear = year;
                            weekDayTo = DateFormat.format("EE", new Date(year, userMonth, userDay + 3)).toString();
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
        if(v==pickDate_buttonFrom){
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
                            userDayFrom = dayOfMonth;
                            userMonthFrom= monthOfYear+1;
                            userYearFrom = year;
                            // display date chosen
                            weekDayFrom = DateFormat.format("EE", new Date(userYearFrom, userMonthFrom, userDayFrom+3)).toString();
                            userDateTextFrom.setText(weekDayFrom+"   "+userMonthFrom+"/"+userDayFrom+"/"+userYearFrom);
                        }
                    }, mYear, mMonth, mDay);
            dpd.show();
        }
        if(v==pickTime_buttonFrom){
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
                            userTimeTextFrom.setText(hourOfDay + ":" + minute);
                            userHourFrom = hourOfDay;
                            userMinuteFrom = minute;
                        }
                    }, mHour, mMinute, false);
            tpd.show();
        }
        if(v == searchExecute_button){
            //TODO if user hits execute button
            String userZip = userZipEditText.getText().toString().trim();

            Intent intentSearch = new Intent(RoundTripSearchActivity.this,SearchResultActivity.class);

            userGender = getIntent().getExtras().getString("gender");
            userStatus= getIntent().getExtras().getString("status");
            userDisable = getIntent().getExtras().getString("disable");
            //optionals
            intentSearch.putExtra("gender", userGender);
            intentSearch.putExtra("status", userStatus);
            intentSearch.putExtra("disable", userDisable);
            //required
            intentSearch.putExtra("Trip", "roundtrip");
            intentSearch.putExtra("Zip Code",userZip);
            intentSearch.putExtra("To Day",weekDayTo);
            intentSearch.putExtra("From Day",weekDayFrom);
            intentSearch.putExtra("To hour",userHour);
            intentSearch.putExtra("To minute",userMinute);
            intentSearch.putExtra("From hour",userHourFrom);
            intentSearch.putExtra("From minute",userMinuteFrom);
            startActivity(intentSearch);

        }

    }
}//END CLASS