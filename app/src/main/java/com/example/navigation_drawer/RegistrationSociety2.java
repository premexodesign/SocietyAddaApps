package com.example.navigation_drawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class RegistrationSociety2 extends AppCompatActivity {
    TextView previous,tnc;
    EditText address,mno,email,website,otherDetails;
    CheckBox checkBox;
    Button btnsubmit;
    HashMap<String,String> hashMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_society2);
        setTitle("Society Registration");
        previous=findViewById(R.id.textscreprevious2);
        address=findViewById(R.id.sretaddress);
        mno=findViewById(R.id.sretphoneno);
        email=findViewById(R.id.sretemailcontact);
        website=findViewById(R.id.sretwebsite);
        otherDetails=findViewById(R.id.sretotherdetail);
        checkBox=findViewById(R.id.srcheckbox);
        btnsubmit=findViewById(R.id.srbtnsignup);
        tnc=findViewById(R.id.srtxttnc);
        hashMap=new HashMap<>();

        Intent intent=getIntent();
        hashMap= (HashMap<String, String>) intent.getSerializableExtra("form1and2data");

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()){
                    Toast.makeText(RegistrationSociety2.this, "Data Sending....", Toast.LENGTH_SHORT).show();
//                    DataBaseHelper db=new DataBaseHelper(RegistrationSociety2.this);
//
//                    boolean checkdata= db.insertsocietydata(hashMap.get("email"),hashMap.get("pass"),hashMap.get("soname"),
//                            hashMap.get("sotype"),hashMap.get("wing"),hashMap.get("about"),
//                            hashMap.get("country"),hashMap.get("state"),hashMap.get("city"),
//                            hashMap.get("pincode"),address.getText().toString(),mno.getText().toString(),
//                            email.getText().toString(),website.getText().toString(),
//                            otherDetails.getText().toString());
//                    if (checkdata){
//                        Toast.makeText(RegistrationSociety2.this, "Data Inserted", Toast.LENGTH_SHORT).show();
//                    }else{
//                        Toast.makeText(RegistrationSociety2.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
//                    }
                }
            }
        });

        tnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(RegistrationSociety2.this,TermsAndCondition.class);
                startActivity(i);
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(RegistrationSociety2.this,RegistrationSociety1.class);
                startActivity(i);
            }
        });
    }
}
