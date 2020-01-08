package com.example.navigation_drawer.tenant;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.navigation_drawer.R;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;

public class TenantActivity extends AppCompatActivity {

    EditText startdate,enddate,firstname,numberofperson;
    ImageButton agreement_choose,agreement_capture,aadhar_choose,aadhar_capture,pancard_choose,pancard_capture,btnnumberofperson;
    ImageView agreementimg,aadharimg,pancardimg;
    final int AGREEMENTCODE =1200, AADHARCODE =1201, PANCARDCODE =1202,FILE_CHOOOSER_CODE_AGREEMENT=1203,FILE_CHOOOSER_CODE_AADHAR=1204,FILE_CHOOOSER_CODE_PANCARD=1205;
    private int mYear, mMonth, mDay;
    Bitmap agreementbitmap,aadharbitmap,pancardbitmap;
    RecyclerView bachelor_details;
    AutoCompleteTextView house;
    ArrayList<String> housedata;
    private Spinner tenanttype;
    private String[] tenanttypedata={"Family","Bachelor"};
    ArrayList<TenantBachelorDetailsModel> BachelorData;
    BachelorListAdapter bachelorListAdapter;
    Button btnsubmit;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant);
        setTitle("Tenant");
        initView();

        housedata.add("A 101");
        housedata.add("A 102");
        housedata.add("A 103");
        housedata.add("A 104");
        housedata.add("A 201");
        housedata.add("A 202");
        housedata.add("A 203");
        housedata.add("A 204");
        housedata.add("A 301");
        housedata.add("A 302");
        housedata.add("A 303");
        housedata.add("A 304");
        housedata.add("A 401");
        housedata.add("A 402");
        housedata.add("A 403");
        housedata.add("A 404");

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (TenantBachelorDetailsModel data:BachelorData) {
                    Toast.makeText(TenantActivity.this, "No:"+data.getNo()+" Name:"+data.getName()+" Pan No:"+data.getPanno()+" Aadhar No:"+data.getAadharno(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        ArrayAdapter<String> houseidadapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,housedata);
        house.setThreshold(1);
        house.setAdapter(houseidadapter);
        ArrayAdapter<String> tenanttypeAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,tenanttypedata);
        tenanttype.setAdapter(tenanttypeAdapter);
        tenanttype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    btnnumberofperson.setVisibility(View.GONE);
                    bachelor_details.setVisibility(View.GONE);
                }else if(position==1){
                    btnnumberofperson.setVisibility(View.VISIBLE);
//                    bachelor_details.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnnumberofperson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!numberofperson.getText().toString().equals("")){
                    BachelorData.clear();
                    bachelor_details.setVisibility(View.VISIBLE);
                    for (int i=0;i<Integer.parseInt(numberofperson.getText().toString());i++){
                        TenantBachelorDetailsModel dataModel=new TenantBachelorDetailsModel();
                        dataModel.setNo("Person "+(i+1));
                        BachelorData.add(dataModel);
                    }
                    bachelorListAdapter=new BachelorListAdapter(TenantActivity.this,BachelorData);
                    bachelor_details.setHasFixedSize(true);
                    bachelor_details.setLayoutManager(new LinearLayoutManager(TenantActivity.this,RecyclerView.VERTICAL,false));
                    bachelor_details.setAdapter(bachelorListAdapter);
                }else
                    Toast.makeText(TenantActivity.this, "Enter Number of Person", Toast.LENGTH_SHORT).show();
            }
        });
        startdate.setShowSoftInputOnFocus(false);
        startdate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
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
            }
        });

        enddate.setShowSoftInputOnFocus(false);
        enddate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
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

        agreement_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent agreement=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(agreement, AGREEMENTCODE);
            }
        });
        agreement_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialFilePicker()
                        .withActivity(TenantActivity.this)
                        .withRequestCode(FILE_CHOOOSER_CODE_AGREEMENT)
                        .start();
            }
        });

        aadhar_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aadharintent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(aadharintent, AADHARCODE);
            }
        });
        aadhar_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //file chooser material
                new MaterialFilePicker()
                        .withActivity(TenantActivity.this)
                        .withRequestCode(FILE_CHOOOSER_CODE_AADHAR)
                        .start();
            }
        });

        pancard_capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pancardintent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(pancardintent, PANCARDCODE);
            }
        });
        pancard_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialFilePicker()
                        .withActivity(TenantActivity.this)
                        .withRequestCode(FILE_CHOOOSER_CODE_PANCARD)
                        .start();
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== AGREEMENTCODE && resultCode==RESULT_OK){
            if (data.getExtras().get("data") != null){
                agreementbitmap= (Bitmap) data.getExtras().get("data");
                agreementimg.setImageBitmap(agreementbitmap);
            }
        }
        if (requestCode==FILE_CHOOOSER_CODE_AGREEMENT && resultCode==RESULT_OK){
            String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            agreementimg.setImageBitmap(getImageFileFromSDCard(filePath));
        }
        if (requestCode== AADHARCODE && resultCode==RESULT_OK){
            if (data.getExtras().get("data") != null){
                aadharbitmap= (Bitmap) data.getExtras().get("data");
                aadharimg.setImageBitmap(aadharbitmap);
            }
        }
        if (requestCode==FILE_CHOOOSER_CODE_AADHAR && resultCode==RESULT_OK){
            String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            aadharimg.setImageBitmap(getImageFileFromSDCard(filePath));
        }
        if (requestCode== PANCARDCODE && resultCode==RESULT_OK){
            if (data.getExtras().get("data") != null){
                pancardbitmap= (Bitmap) data.getExtras().get("data");
                pancardimg.setImageBitmap(pancardbitmap);
            }
        }
        if (requestCode==FILE_CHOOOSER_CODE_PANCARD && resultCode==RESULT_OK){
            String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            pancardimg.setImageBitmap(getImageFileFromSDCard(filePath));
        }

    }

    private Bitmap getImageFileFromSDCard(String filepath){
        Bitmap bitmap = null;
        File imageFile = new File(filepath);
        Log.d("Path",imageFile.getAbsolutePath());
        try {
            FileInputStream fis = new FileInputStream(imageFile);
            bitmap = BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    private void initView(){
        firstname=findViewById(R.id.tenant_firstname);
        startdate=findViewById(R.id.tenant_rentstart);
        enddate=findViewById(R.id.tenant_rentend);
        agreement_choose=findViewById(R.id.tenant_rentagreement_choose);
        agreement_capture=findViewById(R.id.tenant_rentagreement_capture);
        agreementimg=findViewById(R.id.tenant_agreementimg);
        aadhar_choose=findViewById(R.id.tenant_aadharcard_choose);
        aadhar_capture=findViewById(R.id.tenant_aadharcard_capture);
        aadharimg=findViewById(R.id.tenant_aadharimg);
        pancard_choose=findViewById(R.id.tenant_pancard_choose);
        pancard_capture=findViewById(R.id.tenant_pancard_capture);
        pancardimg=findViewById(R.id.tenant_pancardimg);
        house=findViewById(R.id.tenant_houseid);
        tenanttype=findViewById(R.id.tenant_tenanttype);
        housedata=new ArrayList<>();
        numberofperson=findViewById(R.id.tenant_numberofperson);
        btnnumberofperson=findViewById(R.id.tenant_submitnumberofperson);
        bachelor_details=findViewById(R.id.tenant_bachelordetailsview);
        BachelorData=new ArrayList<>();
        btnsubmit=findViewById(R.id.tenant_btnsubmit);
    }

}
