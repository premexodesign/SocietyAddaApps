package com.example.navigation_drawer.visitorinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigation_drawer.R;
import com.example.navigation_drawer.database.DataBaseHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckInPageDetailsActivity extends AppCompatActivity {

    private TextView name,num,refcode,vehicleno,reason,relation,wing,flat,indate,intime;
    private ImageView img;
    int vid;
    Bitmap imgsrc;
    Button sendbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_page_details);
        initView();
        Intent intent=getIntent();
        vid=intent.getIntExtra("id",0);
        DataBaseHelper db=new DataBaseHelper(this);
        Cursor cursor= db.getCheckinPersonData(vid);

        if (cursor.getCount()>0 && cursor.moveToFirst()){
            name.setText(cursor.getString(0));
            num.setText(""+cursor.getLong(1));
            refcode.setText(cursor.getString(2));
            vehicleno.setText(cursor.getString(3));
            reason.setText(cursor.getString(4));
            relation.setText(cursor.getString(5));
            wing.setText(cursor.getString(6));
            flat.setText(""+cursor.getInt(7));
            indate.setText(cursor.getString(8));
            intime.setText(cursor.getString(9));
            if (cursor.getBlob(10)!=null){
                imgsrc=getImage(cursor.getBlob(10));
                img.setImageBitmap(imgsrc);
            }else{
                img.setImageResource(R.drawable.noprofileimg);
            }
        }

        sendbtn.setOnClickListener(senddata);
    }

    View.OnClickListener senddata=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String partFilename = currentDateFormat();
            File outputFile = new File(Environment.getExternalStorageDirectory(), "/"+"photo_" + partFilename + ".jpeg");
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
                imgsrc.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Uri imguri = Uri.parse(outputFile.getAbsoluteFile().toString());
            Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
            whatsappIntent.setType("text/plain");
//            whatsappIntent.setPackage("com.whatsapp");
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, "The text you wanted to share");
            Log.d("filename", outputFile.getName());
            Log.d("filenamepath", outputFile.getAbsolutePath());
            if (outputFile != null) {
                whatsappIntent.putExtra(Intent.EXTRA_STREAM, imguri);
            } else {
                Toast.makeText(CheckInPageDetailsActivity.this, "Please Select The Image", Toast.LENGTH_SHORT).show();
            }
            whatsappIntent.setType("image/jpeg");
            whatsappIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            try {
                startActivity(whatsappIntent);
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(CheckInPageDetailsActivity.this, "Whatsapp is not Installed", Toast.LENGTH_SHORT).show();

            }
        }
    };

    private String currentDateFormat(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
        String  currentTimeStamp = dateFormat.format(new Date());
        return currentTimeStamp;
    }


    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    private void initView(){
        name=findViewById(R.id.checkinproname);
        num=findViewById(R.id.checkinpronumber);
        refcode=findViewById(R.id.checkinprorefcode);
        vehicleno=findViewById(R.id.checkinprovehcleno);
        reason=findViewById(R.id.checkinproreason);
        relation=findViewById(R.id.checkinprorelation);
        wing=findViewById(R.id.checkinprowing);
        flat=findViewById(R.id.checkinproflatno);
        indate=findViewById(R.id.checkinprocheckindate);
        intime=findViewById(R.id.checkinprocheckintime);
        img=findViewById(R.id.checkinproimg);
        sendbtn=findViewById(R.id.btnsend);

    }
}
