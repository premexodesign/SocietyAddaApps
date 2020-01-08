package com.example.navigation_drawer.members;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigation_drawer.R;
import com.example.navigation_drawer.tenant.TenantActivity;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MembersFormActivity extends AppCompatActivity {

    private TextView textView;
    private Button browsebtn;
    private ImageView imageView;
    private Spinner prefixspinner;
    private final int FILE_CHOOOSER_CODE=7;
    String [] prefixdata={"Mr.","Ms.","Mrs"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members_form);
        setTitle("Member Form");
        browsebtn=findViewById(R.id.browsefileinstorage);
        textView=findViewById(R.id.imagepath);
        imageView=findViewById(R.id.imgset);
        prefixspinner=findViewById(R.id.member_prefix);
        browsebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialFilePicker()
                        .withActivity(MembersFormActivity.this)
                        .withRequestCode(FILE_CHOOOSER_CODE)
                        .start();
            }
        });
        ArrayAdapter<String> spinneradapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,prefixdata);
        prefixspinner.setAdapter(spinneradapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){

            case FILE_CHOOOSER_CODE:

                if(resultCode==RESULT_OK){

                    String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
//                    textView.setText(filePath);
                    imageView.setImageBitmap(getImageFileFromSDCard(filePath));
//                    String FilePath = data.getData().getPath();
//                    String FileName = data.getData().getLastPathSegment();
//                    int lastPos = FilePath.length() - FileName.length();
//                    String Folder = FilePath.substring(0, lastPos);
//                    String [] originalpath=FilePath.split(":");

//                    Log.d("Filepath",FilePath);
//                    Log.d("Filepath",originalpath[0]);
////                    Log.d("Filepath",originalpath[1]);
////                    Log.d("Filepath",""+originalpath.length);
//
//
//                    File imgfile= new File(Environment.getExternalStorageDirectory()+"/"+originalpath[1]);
//                    Log.d("Filepath",imgfile.getAbsolutePath());
//                    try {
//                        FileInputStream fis=new FileInputStream(imgfile);
//                        Bitmap bitmap= BitmapFactory.decodeStream(fis);
//                        imageView.setImageBitmap(bitmap);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                    textView.setText(FilePath);


                }
                break;

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
