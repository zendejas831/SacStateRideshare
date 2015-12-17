package com.teamnoob.sacstaterideshare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.HashMap;

public class ActivityRegister extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    private EditText editTextName;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextEmail;

    private Button buttonRegisterUser;
    private Button buttonBack;

    private CheckBox userAgreementCheckBox;

    private static String userGender;
    private static String userStatus;

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

        userAgreementCheckBox = (CheckBox) findViewById(R.id.userAgreementCheckBox);
        userAgreementCheckBox.setOnClickListener(this);

        buttonRegisterUser = (Button) findViewById(R.id.buttonRegisterUser);
        buttonRegisterUser.setOnClickListener(this);

        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(this);

        create_and_fill_Spinners();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonRegisterUser){// && userAgreementCheckBox.isChecked()){
            //Toast.makeText(getApplicationContext(), "Checked", Toast.LENGTH_LONG).show();
            registerUser();
        } //else {
        //        Toast.makeText(getApplicationContext(), "Please Check Terms of Service", Toast.LENGTH_LONG).show();
        //}
        if(v == buttonBack){
            startActivity(new Intent(this, ActivityLogin.class));
        }
    }

    private void create_and_fill_Spinners() {

        // Status Spinner
        Spinner statusSpinner = (Spinner) findViewById(R.id.register_status_spinner);//spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.status_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter1 to the spinner
        statusSpinner.setAdapter(adapter1);
        statusSpinner.setOnItemSelectedListener(this);

        // Gender Spinner
        Spinner genderSpinner = (Spinner) findViewById(R.id.register_gender_spinner);//spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter2 to the spinner
        genderSpinner.setAdapter(adapter2);
        genderSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(ActivityRegister.this, "before spinner init", Toast.LENGTH_SHORT).show();
        switch(parent.getId()){
            case R.id.register_gender_spinner: //spinner3: //gender
                switch(position){
                    case 0://Gender:
                        userGender = "undefined";
                        break;
                    case 1://male
                        userGender = "male";
                        break;
                    case 2: //female
                        userGender = "female";
                        break;
                    case 3: //other
                        userGender = "other";
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
                Toast.makeText(ActivityRegister.this, "userStatus = " + userStatus + "\nuserGender = " + userGender, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
