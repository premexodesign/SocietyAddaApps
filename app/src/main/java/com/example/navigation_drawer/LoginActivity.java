package com.example.navigation_drawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username,password;
    TextView regbtn;
    Button btnsubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");
        regbtn=findViewById(R.id.txtregistration);
        btnsubmit=findViewById(R.id.btnsubmitLogin);
        username=findViewById(R.id.etusername);
        password=findViewById(R.id.etpassword);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!username.getText().toString().equalsIgnoreCase("") && !password.getText().toString().equalsIgnoreCase("")){
                    if (username.getText().toString().equals("premexo") && password.getText().toString().equals("Premexo")){
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(LoginActivity.this, "Wrong Username or Password", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "Please Enter the username and password", Toast.LENGTH_SHORT).show();
                }


            }
        });
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),RegistrationOption.class);
                startActivity(intent);
            }
        });
    }
}
