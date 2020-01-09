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

public class RegistrationSociety1 extends AppCompatActivity {

    Spinner country,state,city;
    EditText pincode;
    TextView next,previous;
    HashMap<String,String> hashMap;
    String[] countrydata={"India","UK","UN"};
    String [] statedata={"Gujarat","London","Maxico"};
    String [] citydata={"Ahedabad","Surat","Gandhinagar"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_society1);
        setTitle("Society Registration");
        country=findViewById(R.id.srspinner_country);
        state=findViewById(R.id.srspinner_state);
        city=findViewById(R.id.srspinner_city);
        pincode=findViewById(R.id.sretpincode);
        next=findViewById(R.id.textscrenext1);
        previous=findViewById(R.id.textscreprevious1);
        hashMap=new HashMap<>();

        ArrayAdapter<String> countryAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,countrydata);
        ArrayAdapter<String> stateAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,statedata);
        ArrayAdapter<String> cityAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,citydata);

        country.setAdapter(countryAdapter);
        state.setAdapter(stateAdapter);
        city.setAdapter(cityAdapter);

        Intent intent=getIntent();
        hashMap= (HashMap<String, String>) intent.getSerializableExtra("form1data");




        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hashMap.put("country",country.getSelectedItem().toString());
                hashMap.put("state",state.getSelectedItem().toString());
                hashMap.put("city",city.getSelectedItem().toString());
                hashMap.put("pincode",pincode.getText().toString());
                Intent i=new Intent(RegistrationSociety1.this,RegistrationSociety2.class);
                i.putExtra("form1and2data",hashMap);
                startActivity(i);
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(RegistrationSociety1.this,RegistrationSociety.class);
                startActivity(i);
            }
        });
    }
}
