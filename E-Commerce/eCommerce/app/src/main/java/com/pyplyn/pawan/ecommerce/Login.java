package com.pyplyn.pawan.ecommerce;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Login extends AppCompatActivity {

    Toolbar toolbar;
    TextView signup;
    EditText user_email,user_password;
    String email,password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbar = (Toolbar) findViewById(R.id.login_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        user_email = (EditText) findViewById(R.id.email_signIn);
        user_password = (EditText) findViewById(R.id.password_signIn);

        login = (Button) findViewById(R.id.login_btn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginMethod();
            }
        });


        signup = (TextView) findViewById(R.id.loginSignup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });
    }

    public void loginMethod(){
        intialize(); //intialuze the input to string variable
        if (!validate())
        {
            Toast.makeText(this,"Log In faild", Toast.LENGTH_SHORT).show();
        }
        else {
            ContinueLogin(null);
        }
    }


   /* private void onSigninSuccess() {

        this.selfDestruct(null);
        //Toast.makeText(this,"Sign up Successfull",Toast.LENGTH_SHORT).show();
    }*/

    public boolean validate()
    {
        boolean valid= true;
        if (email.isEmpty())
        {
            user_email.setError("Please Enter Email");
            valid = false;

        }
        if (password.isEmpty())
        {
            user_password.setError("Please Enter password");
            valid = false;
        }

        return valid;

    }

    void intialize()
    {

        email = user_email.getText().toString();
        password= user_password.getText().toString();


    }

    public void ContinueLogin(View view)
    {
        if (true) {
            BackgroundLogin backgroundLogin = new BackgroundLogin(this);
            backgroundLogin.execute(email, password);
        }
    }



    public class BackgroundLogin extends AsyncTask<String,Void,String> {

        Context context;
        AlertDialog alertDialog;
        String login_user_email,login_user_password;
        BackgroundLogin (Context ctx){
            context = ctx;
        }

        @Override
        protected String doInBackground(String... params) {

            login_user_email = params[0];
            login_user_password = params[1];
            String login_url="http://pyplyn.co/pawan_ecommerce/login.php";
            if (true)
            {
                try {
                    URL url = new URL(login_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter
                            (outputStream, "UTF-8"));
                    String post_data =  URLEncoder.encode("identifier_email","UTF-8") +"="+URLEncoder
                            .encode(login_user_email,"UTF-8")+"&"+URLEncoder.encode("identifier_password", "UTF-8")
                            +"="+URLEncoder.encode(login_user_password,"UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader
                            (inputStream,"iso-8859-1"));
                    String result = "";
                    String line;
                    while ((line = bufferedReader.readLine())!= null){
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            alertDialog = new AlertDialog.Builder(context).create();
            alertDialog.setTitle("Login Status");

        }
        @Override
        protected void onPostExecute(String result) {
//            String res = result.trim();
            if (result.equals("Login successfull")){
                Intent intent = new Intent(context,Home.class);
                context.startActivity(intent);
            }
            else {
                alertDialog.setMessage(result);
                alertDialog.show();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

    }
}
