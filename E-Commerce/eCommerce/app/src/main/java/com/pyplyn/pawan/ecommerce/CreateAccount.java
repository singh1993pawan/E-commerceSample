package com.pyplyn.pawan.ecommerce;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class CreateAccount extends AppCompatActivity {

    Toolbar toolbar;
    private EditText userEmail,userPassword,userName,userMobileNumber;
    private String email,password,name,mobile;
    private Button createAccount;
    boolean checked;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        toolbar = (Toolbar) findViewById(R.id.create_account_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");


        userEmail = (EditText) findViewById(R.id.email_signUp);
        userPassword = (EditText) findViewById(R.id.password_signUp);
        userName = (EditText) findViewById(R.id.name_signUp);
        userMobileNumber = (EditText) findViewById(R.id.mobile_number_signUp);


        createAccount = (Button) findViewById(R.id.create_account_button);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    public void register(){
        intialize(); //intialuze the input to string variable
        if (!validate())
        {
            Toast.makeText(this,"Sign up faild",Toast.LENGTH_SHORT).show();
        }
        else {
            this.userReg(null);
            Toast.makeText(this,"Sign up Successfull",Toast.LENGTH_SHORT).show();
        }
    }



    public boolean validate()
    {
        boolean valid= true;

        if ((email.isEmpty()) || (!Patterns.EMAIL_ADDRESS.matcher(email).matches()))
        {
            userEmail.setError("Please Enter valid Email");
            valid = false;

        }

        if (password.isEmpty())
        {
            userPassword.setError("Please Enter password");
            valid = false;
        }

        if ((name.isEmpty()))
        {
            userName.setError("Please Enter name");
            valid = false;
        }


        if (mobile.isEmpty())
        {
            userMobileNumber.setError("Please Enter moblie number");
            valid = false;
        }

        return valid;
    }

    void intialize()
    {

        email = userEmail.getText().toString();
        password = userPassword.getText().toString();
        name = userName.getText().toString();
        mobile = userMobileNumber.getText().toString();
    }



    public void genderFunction(View view) {

            checked = ((RadioButton) view).isChecked();


            switch (view.getId())
            {

                case R.id.femaleSelected:
                    if(checked) {
                       gender = "Female";
                        break;
                    }
                case R.id.maleSelected:
                    if(checked)
                    {
                        gender = "Male";
                        break;
                    }

            }


    }


    private void userReg(View view) {

        BackgroundSignUP backgroundSignUP = new BackgroundSignUP(this);
        backgroundSignUP.execute(email,password,name,mobile,gender);

    }



    public class  BackgroundSignUP extends AsyncTask<String,Void,String> {

        Context ctx;
        String email,password,name,mobile,gender;
        BackgroundSignUP(Context ctx)
        {
            this.ctx=ctx;
        }

        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        protected String doInBackground(String... params)
        {

            email = params[0];
            password = params[1];
            name = params[2];
            mobile = params[3];
            gender = params[4];

            String reg_url="http://pyplyn.co/pawan_ecommerce/Registration.php";

            if (true)
            {
                try
                {
                    URL url = new URL(reg_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream os = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os,
                            "UTF-8"));
                    String data = URLEncoder.encode("identifier_email","UTF-8") +"="+URLEncoder.encode
                            (email,"UTF-8")+"&"+URLEncoder.encode("identifier_password","UTF-8")
                            +"="+URLEncoder.encode(password,"UTF-8")+"&"+URLEncoder.encode
                            ("identifier_name","UTF-8") +"="+URLEncoder.encode(name,"UTF-8")
                            +"&"+URLEncoder.encode("identifier_mobile","UTF-8") +"="+URLEncoder.encode
                            (mobile,"UTF-8")+"&"+URLEncoder.encode("identifier_gender","UTF-8")
                            +"="+URLEncoder.encode(gender,"UTF-8");

                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    os.close();
                    InputStream is=httpURLConnection.getInputStream();
                    is.close();
                    return "Registration Success";
                } catch (Exception e) {
                    Log.e("error msg", e.toString());
                    // e.printStackTrace();
                }

            }
            return null;
        }
        protected void onProgressUpdate(Void... values){

        }
        protected void onPostExecute(String result)
        {
//            Toast.makeText(ctx,result, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ctx,Login.class);
            ctx.startActivity(intent);
            //finish();
        }
    }
}
