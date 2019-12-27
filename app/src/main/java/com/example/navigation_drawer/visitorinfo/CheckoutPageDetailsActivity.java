package com.example.navigation_drawer.visitorinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigation_drawer.R;
import com.example.navigation_drawer.database.DataBaseHelper;

public class CheckoutPageDetailsActivity extends AppCompatActivity {

    private TextView name,num,refcode,vehicleno,reason,relation,wing,flat,indate,intime,outdate,outtime;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_page_details);
        initView();
        Intent i=getIntent();
        int id=i.getIntExtra("id",0);
        DataBaseHelper db=new DataBaseHelper(this);
        Cursor cursor=db.getDataByVisitorListId(id);

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
            outdate.setText(cursor.getString(10));
            outtime.setText(cursor.getString(11));
            Bitmap imgsrc=getImage(cursor.getBlob(12));
            img.setImageBitmap(imgsrc);
        }

    }

    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
    private void initView(){
        name=findViewById(R.id.checkoutproname);
        num=findViewById(R.id.checkoutpronumber);
        refcode=findViewById(R.id.checkoutprorefcode);
        vehicleno=findViewById(R.id.checkoutprovehcleno);
        reason=findViewById(R.id.checkoutproreason);
        relation=findViewById(R.id.checkoutprorelation);
        wing=findViewById(R.id.checkoutprowing);
        flat=findViewById(R.id.checkoutproflatno);
        indate=findViewById(R.id.checkoutprocheckindate);
        intime=findViewById(R.id.checkoutprocheckintime);
        outdate=findViewById(R.id.checkoutprocheckoutdate);
        outtime=findViewById(R.id.checkoutprocheckouttime);
        img=findViewById(R.id.checkoutproimg);


    }
}
