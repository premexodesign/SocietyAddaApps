package com.example.navigation_drawer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class RegistrationOption extends Activity {
    RadioButton so,indi;
    Button btnreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_option);

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width*0.9),(int)(height*0.5));
        WindowManager.LayoutParams params=getWindow().getAttributes();
        params.gravity= Gravity.CENTER;
        params.x=0;
        params.y=-20;
        getWindow().setAttributes(params);

        btnreg=findViewById(R.id.btn_res_op_page);
        so=findViewById(R.id.rbtnsociety);
        indi=findViewById(R.id.rbtnindividual);

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (indi.isChecked()){
                    Intent intent=new Intent(RegistrationOption.this,RegistrationIndividual.class);
                    startActivity(intent);
                }
                else if (so.isChecked()){
                    Intent intent=new Intent(RegistrationOption.this,RegistrationSociety.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(RegistrationOption.this, "Plaese Select the Any Radio Button", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
