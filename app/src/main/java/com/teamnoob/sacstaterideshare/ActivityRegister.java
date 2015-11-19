package com.teamnoob.sacstaterideshare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.HashMap;

public class ActivityRegister extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextName;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextEmail;

    private Button buttonRegisterUser;
    private Button buttonBack;

    // School server
    private static final String REGISTER_URL = "http://athena.ecs.csus.edu/~zendejaj/rideshare/UserRegistration/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextUsername = (EditText) findViewById(R.id.editTextUserName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);

        buttonRegisterUser = (Button) findViewById(R.id.buttonRegisterUser);
        buttonRegisterUser.setOnClickListener(this);

        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonRegisterUser){
            registerUser();
        }
        if(v == buttonBack){
            startActivity(new Intent(this,ActivityLogin.class));
        }
    }

    private void registerUser() {
        String name = editTextName.getText().toString().trim().toLowerCase();
        String username = editTextUsername.getText().toString().trim().toLowerCase();
        String password = editTextPassword.getText().toString().trim().toLowerCase();
        String email = editTextEmail.getText().toString().trim().toLowerCase();

        register(name,username,password,email);
    }

    private void register(String name, String username, String password, String email) {
        class RegisterUser extends AsyncTask<String, Void, String>{
            ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ActivityRegister.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
                if(s.equalsIgnoreCase("successfully registered")) {
                    finish();
                }
            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<>();
                data.put("name",params[0]);
                data.put("username",params[1]);
                data.put("password",params[2]);
                data.put("email",params[3]);

                //String result = ruc.sendPostRequest(REGISTER_URL,data);
                //return  result;
                return ruc.sendPostRequest(REGISTER_URL,data);
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(name, username, password, email);
    }
}
