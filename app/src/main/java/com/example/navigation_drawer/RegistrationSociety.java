package com.example.navigation_drawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;

public class RegistrationSociety extends AppCompatActivity {
    Spinner srsotype;
    TextView tvnext;
    String [] spinnerdata={"Residential","Office","Guest House","Paying Guest"};
    EditText email,pass,cnfmpass,soname,wing,about;
    HashMap<String,String> hashMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_society);
        setTitle("Society Registration");
        srsotype=findViewById(R.id.srspinner);
        tvnext=findViewById(R.id.textscrenext);
        email=findViewById(R.id.sretemail);
        pass=findViewById(R.id.sretpassword);
        cnfmpass=findViewById(R.id.sretpasswordcnfm);
        soname=findViewById(R.id.sretsocityname);
        wing=findViewById(R.id.sretwingandflat);
        about=findViewById(R.id.sretabout);
        hashMap=new HashMap<String, String>();

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,spinnerdata);
        srsotype.setAdapter(arrayAdapter);

        tvnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hashMap.put("email",email.getText().toString());
                hashMap.put("pass",pass.getText().toString());
                hashMap.put("cnfmpass",cnfmpass.getText().toString());
                hashMap.put("soname",soname.getText().toString());
                hashMap.put("sotype",srsotype.getSelectedItem().toString());
                hashMap.put("wing",wing.getText().toString());
                hashMap.put("about",about.getText().toString());
                Intent i=new Intent(RegistrationSociety.this,RegistrationSociety1.class);
                i.putExtra("form1data",hashMap);
                startActivity(i);
            }
        });
    }
}
