package com.example.navigation_drawer.tenant;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.navigation_drawer.R;

import java.util.Calendar;

public class TenantActivity extends AppCompatActivity {

    EditText startdate,enddate,firstname;
    private int mYear, mMonth, mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant);
        setTitle("Tenant");
        firstname=findViewById(R.id.tenant_firstname);
        startdate=findViewById(R.id.tenant_rentstart);
        enddate=findViewById(R.id.tenant_rentend);
        firstname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TenantActivity.this, "This is Click", Toast.LENGTH_SHORT).show();
            }
        });
        startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog=new DatePickerDialog(TenantActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        startdate.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }
                },mYear,mMonth,mDay);
                datePickerDialog.show();
                Toast.makeText(TenantActivity.this, "Start Date", Toast.LENGTH_SHORT).show();
            }
        });

        enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog=new DatePickerDialog(TenantActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        enddate.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }
                },mYear,mMonth,mDay);
                datePickerDialog.show();
                Toast.makeText(TenantActivity.this, "End Date", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
