package com.example.navigation_drawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class RegistrationIndividual extends AppCompatActivity {

    EditText fname,lname,dob,email,mno,password,cnfmpass,soname,wing;
    RadioButton male,female;
    CheckBox checkBox;
    Button btnsubmit;
    TextView tnc;
    boolean inserted=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_individual);

        setTitle("Individual Registration");
        fname=findViewById(R.id.iretfirstname);
        lname=findViewById(R.id.iretlastname);
        dob=findViewById(R.id.iretdob);
        email=findViewById(R.id.iretemail);
        mno=findViewById(R.id.iretmobileno);
        password=findViewById(R.id.iretpassword);
        cnfmpass=findViewById(R.id.iretpasswordcnfm);
        soname=findViewById(R.id.iretsocityname);
        wing=findViewById(R.id.iretwingandflat);
        tnc=findViewById(R.id.irtxttnc);

        male=findViewById(R.id.radiomale);
        female=findViewById(R.id.radiofemale);

        checkBox=findViewById(R.id.ircheckbox);

        btnsubmit=findViewById(R.id.irbtnsignup);


        tnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(RegistrationIndividual.this,TermsAndCondition.class);
                startActivity(i);
            }
        });



        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegistrationIndividual.this, "Data Sending....", Toast.LENGTH_SHORT).show();
//                String fname1,lname1,dob1,email1,mno1,pass1,cnfmpass1,soname1,wing1,gender1="";
//                fname1=fname.getText().toString();
//                lname1=lname.getText().toString();
//                dob1=dob.getText().toString();
//                email1=email.getText().toString();
//                mno1=mno.getText().toString();
//                pass1=password.getText().toString();
//                cnfmpass1=cnfmpass.getText().toString();
//                soname1=soname.getText().toString();
//                wing1=wing.getText().toString();
//                if (checkBox.isChecked()){
//                    if (male.isChecked()){
//                        gender1="Male";
//                    }else if(female.isChecked()){
//                        gender1="Female";
//                    }else{
//                        Toast.makeText(RegistrationIndividual.this, "Please Select Gender", Toast.LENGTH_SHORT).show();
//                    }
//                    DataBaseHelper db=new DataBaseHelper(RegistrationIndividual.this);
//                    inserted=db.insertIndividualData(fname1,lname1,dob1,email1,mno1,pass1,soname1,wing1,gender1);
//                    if(inserted){
//                        Toast.makeText(RegistrationIndividual.this, "The Data is added", Toast.LENGTH_SHORT).show();
//                    }else{
//                        Toast.makeText(RegistrationIndividual.this, "There is some Problem", Toast.LENGTH_SHORT).show();
//                    }
//                }

            }
        });


    }
}
