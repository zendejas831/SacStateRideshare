package com.teamnoob.sacstaterideshare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener{

    public static final String USER_NAME = "username";
    public static final String PASSWORD = "PASSWORD";

    // School server
    private static final String LOGIN_URL = "http://athena.ecs.csus.edu/~zendejaj/rideshare/UserRegistration/login.php";

    private EditText editTextUserName;
    private EditText editTextPassword;
    private TextView textViewForgotPassword;

    private Button buttonLogin;
    private Button buttonRegisterRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUserName = (EditText) findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.password);

        buttonLogin = (Button) findViewById(R.id.buttonUserLogin);
        buttonLogin.setOnClickListener(this);

        textViewForgotPassword = (TextView) findViewById(R.id.changePassword);
        textViewForgotPassword.setOnClickListener(this);

        buttonRegisterRequest = (Button) findViewById(R.id.buttonRegisterRequest);
        buttonRegisterRequest.setOnClickListener(this);
    }

    private void login(){
        String username = editTextUserName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        userLogin(username,password);
    }

    private void userLogin(final String username, final String password){
        class UserLoginClass extends AsyncTask<String,Void,String>{
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
                    Toast.makeText(ActivityLogin.this,s,Toast.LENGTH_LONG).show();
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

    @Override
    public void onClick(View v) {
        if(v == buttonLogin){
            login();
            //startActivity(new Intent(this, UserProfile.class));
        }
        if(v == buttonRegisterRequest) {
            startActivity(new Intent(this, ActivityRegister.class));
        }
        if(v == textViewForgotPassword) {
            startActivity(new Intent(this,ActivityChangePassword.class));
        }
    }
}
