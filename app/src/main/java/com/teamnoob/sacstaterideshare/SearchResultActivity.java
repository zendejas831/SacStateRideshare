package com.teamnoob.sacstaterideshare;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class SearchResultActivity extends AppCompatActivity {
    private static String userTrip, userZip, userWeekDayTo, userWeekDayFrom, toHour, fromHour, toMinute, fromMinute, userWay;
    private static TextView[] resultText;
    private static TextView banner, tellUser;
    private static Button[] resultButtons;
    private static Button button1;
    private static int searchResultCount;
    private static String userStatus, userGender, userDisable, userNameSearch;
    String resultString;
    private static EditText userResult1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        banner = (TextView)findViewById(R.id.searchResultBanner);
        tellUser = (TextView)findViewById(R.id.tellUser);
        button1 = (Button) findViewById(R.id.button1);
        userResult1 = (EditText)findViewById(R.id.userResult1);
        userTrip = getIntent().getExtras().getString("Trip");

        userNameSearch = getIntent().getExtras().getString("username");
        userResult1.setText(userNameSearch);




        if(userNameSearch !=null){//searching by username
            //TODO IF We are searching by username
            //userNameSearchFunction(userNameSearch);
        }
        else{
            userZip = getIntent().getExtras().getString("Zip Code");
            userStatus = getIntent().getExtras().getString("status");
            userGender = getIntent().getExtras().getString("gender");
            userDisable = getIntent().getExtras().getString("disable");

            if(userTrip.equals("oneway")){//One Way
                userWay = getIntent().getExtras().getString("way");
                if(userWay.equals("from")){//From School
                    userWeekDayFrom = getIntent().getExtras().getString("From Day");
                    fromHour = getIntent().getExtras().getString("From hour");
                    fromMinute = getIntent().getExtras().getString("From minute");
                }
                else{ //To School
                    userWeekDayFrom = getIntent().getExtras().getString("To Day");
                    toHour = getIntent().getExtras().getString("To hour");
                    toMinute = getIntent().getExtras().getString("To minute");
                }

            }
            else{//RoundTrip
                userWeekDayTo = getIntent().getExtras().getString("To Day");//weekday ex: Mon
                userWeekDayFrom = getIntent().getExtras().getString("From Day");
                toHour = getIntent().getExtras().getString("To hour");
                fromHour = getIntent().getExtras().getString("From hour");
                toMinute = getIntent().getExtras().getString("To minute");
                fromMinute = getIntent().getExtras().getString("From minute");

            }
        }



    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        if(userNameSearch == null) {

            if(userTrip.equals("oneway")){
                if(userWay.equals("from")){
                    //resultString = searchFrom();
                }
                else{
                    //resultString = searchTo();
                }
                //TODO one way search

            }
            else{
                //TODO round trip search
            }

            while(searchResultCount>0){
                //create button
                //fill with id of returned result
            }

        }

    }//end onPostCreate()


/*
    private String[] searchFrom(){
        class userSearchClass extends AsyncTask<String,Void,String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                loading = ProgressDialog.show(SearchResultActivity.this,"Searching...",null,true,true);
            }
            @Override
            protected void  onPostExecute(String s){
                super.onPostExecute(s);
                loading.dismiss();
                if(s.equalsIgnoreCase("0")){

                }
                else{

                }
            }
        }

    }
    private void UserLogin(final String username, final String password){

        class UserLoginClass extends AsyncTask<String,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ActivityLogin.this,"Please Wait",null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if(s.equalsIgnoreCase("success")){
                    Intent intent = new Intent(ActivityLogin.this,UserProfile.class);
                    intent.putExtra(USER_NAME,username);
                    startActivity(intent);
                }else{
                    Toast.makeText(ActivityLogin.this, s, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String,String> data = new HashMap<>();
                data.put("username",params[0]);
                data.put("password",params[1]);

                RegisterUserClass ruc = new RegisterUserClass();

                //String result = ruc.sendPostRequest(LOGIN_URL,data);
                //return result;
                return ruc.sendPostRequest(LOGIN_URL,data);
            }
        }

        UserLoginClass ulc = new UserLoginClass();
        ulc.execute(username,password);
    }

    public String sendPostRequest(String requestURL, HashMap<String, String> postDataParams) {

        URL url;
        String response = "";
        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();
            int responseCode=conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));

                response = br.readLine();
            }
            else {
                response="Error Registering";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

*/
}//End class
