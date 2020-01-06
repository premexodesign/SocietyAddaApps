package com.example.navigation_drawer.tenant;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.navigation_drawer.R;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Calendar;

public class TenantActivity extends AppCompatActivity {

    EditText startdate,enddate,firstname;
    ImageButton agreement_choose,agreement_capture,aadhar_choose,aadhar_capture,pancard_choose,pancard_capture;
    ImageView agreementimg,aadharimg,pancardimg;
    final int AGREEMENTCODE =1200, AADHARCODE =1201, PANCARDCODE =1202;
    private int mYear, mMonth, mDay;
    Bitmap agreementbitmap,aadharbitmap,pancardbitmap;
    final int FILE_CHOOOSER_CODE_AGREEMENT=1203,FILE_CHOOOSER_CODE_AADHAR=1204,FILE_CHOOOSER_CODE_PANCARD=1205;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant);
        setTitle("Tenant");
        initView();

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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== AGREEMENTCODE && resultCode!=0){
            if (data.getExtras().get("data") != null){
                agreementbitmap= (Bitmap) data.getExtras().get("data");
                agreementimg.setImageBitmap(agreementbitmap);
            }
        }
        if (requestCode==FILE_CHOOOSER_CODE_AGREEMENT && resultCode!=0){
            String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            agreementimg.setImageBitmap(getImageFileFromSDCard(filePath));
        }
        if (requestCode== AADHARCODE && resultCode!=0){
            if (data.getExtras().get("data") != null){
                aadharbitmap= (Bitmap) data.getExtras().get("data");
                aadharimg.setImageBitmap(aadharbitmap);
            }
        }
        if (requestCode==FILE_CHOOOSER_CODE_AADHAR && resultCode!=0){
            String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
            aadharimg.setImageBitmap(getImageFileFromSDCard(filePath));
        }
        if (requestCode== PANCARDCODE && resultCode!=0){
            if (data.getExtras().get("data") != null){
                pancardbitmap= (Bitmap) data.getExtras().get("data");
                pancardimg.setImageBitmap(pancardbitmap);
            }
        }
        if (requestCode==FILE_CHOOOSER_CODE_PANCARD && resultCode!=0){
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

}
