package com.example.navigation_drawer.visitorinfo;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.navigation_drawer.R;
import com.example.navigation_drawer.database.DataBaseHelper;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class VisitorInfoFormActivity extends AppCompatActivity{

    private EditText vname,mno,refencecode,vehicleno,reason,relation,prebooket;
    private Spinner wing,flatno;
    private Button btnsubmit,btnprebook,capturebtn;
    private String [] wingarray={"A","B","C","D","E","F"};
    private Integer [] flatnoarray={101,102,103,104,201,202,203,204,301,302,303,304,401,402,403,404};
    private RadioButton prebook;
    private LinearLayout llhide;
    private ArrayAdapter<String> wingadapter;
    private ArrayAdapter<Integer> flatadapter;
    private ImageView imageView;
    private Bitmap bitmap;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_info);
        setTitle("Visitor Info");
        initView();

        wingadapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,wingarray);
        flatadapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,flatnoarray);
        wing.setAdapter(wingadapter);
        flatno.setAdapter(flatadapter);

        btnprebook.setOnClickListener(btnprebookcheck);
        prebook.setOnCheckedChangeListener(checkradio);
        btnsubmit.setOnClickListener(submitClick);
        capturebtn.setOnClickListener(myhandaler);
    }

    View.OnClickListener btnprebookcheck=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (prebooket.getText().toString() != ""){
                    DataBaseHelper db=new DataBaseHelper(VisitorInfoFormActivity.this);
                    Cursor cursor=db.getDatabyReferenceCode(Integer.parseInt(prebooket.getText().toString()));
                    if (cursor.getCount()>0 && cursor.moveToFirst()){
                            vname.setText(cursor.getString(0));
                            mno.setText(""+cursor.getLong(1));
                            refencecode.setText(prebooket.getText());
                            reason.setText(cursor.getString(3));
                            relation.setText(cursor.getString(4));
                            int wingspnrposition=wingadapter.getPosition(cursor.getString(5));
                            wing.setSelection(wingspnrposition);
                            int flatspnrposition=flatadapter.getPosition(cursor.getInt(6));
                            flatno.setSelection(flatspnrposition);

                    }else{
                        Toast.makeText(VisitorInfoFormActivity.this, "There is no data Similar to this", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(VisitorInfoFormActivity.this, "Please Enter the PreBook Reference code", Toast.LENGTH_SHORT).show();
                }
        }
    };

    CompoundButton.OnCheckedChangeListener checkradio=new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked){
                    llhide.setVisibility(View.VISIBLE);
                    vname.setKeyListener(null);
                    mno.setKeyListener(null);
                    refencecode.setKeyListener(null);
                    reason.setKeyListener(null);
                    relation.setKeyListener(null);
                    wing.setEnabled(false);
                    flatno.setEnabled(false);
                }
        }
    };

        View.OnClickListener submitClick=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper db=new DataBaseHelper(VisitorInfoFormActivity.this);
                String sendname,sendvehicleno,sendreason,sendrelation,sendwing,senddate,sendtime;
                byte [] sendimg;
                if (bitmap !=null){
                    sendimg=getBytes(bitmap);
                }else{
                    sendimg=null;
                    Toast.makeText(VisitorInfoFormActivity.this, "The Bitmap is Null", Toast.LENGTH_SHORT).show();
                }


                sendname=vname.getText().toString();
                long sendmno=Long.parseLong(mno.getText().toString());
                int sendreferencecode=Integer.parseInt(refencecode.getText().toString());
                int sendflatno=Integer.parseInt(flatno.getSelectedItem().toString());
                sendvehicleno=vehicleno.getText().toString();
                sendreason=reason.getText().toString();
                sendrelation=relation.getText().toString();
                sendwing=wing.getSelectedItem().toString();
                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                senddate= df.format(c);

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                sendtime= sdf.format(new Date());

                boolean check=db.RegisterVisitor(sendname,sendmno,1);
                if (check){
                    Toast.makeText(VisitorInfoFormActivity.this, "Visitor info Added", Toast.LENGTH_SHORT).show();
                   boolean checkvisitorinsrt= db.insertvisitor(sendmno,sendreferencecode,sendvehicleno,sendreason,
                           sendrelation,sendwing,sendflatno,senddate,sendtime,sendimg);
                   if (!checkvisitorinsrt){
                       Toast.makeText(VisitorInfoFormActivity.this, "Visitor Not Inserted", Toast.LENGTH_SHORT).show();
                   }else{
                       Intent intent=new Intent(VisitorInfoFormActivity.this,CheckInOutActivity.class);
                        startActivity(intent);
                   }

                }else{
                    Toast.makeText(VisitorInfoFormActivity.this, "Visitor Info not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        };

        View.OnClickListener myhandaler=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
            }
        };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data.getExtras().get("data") != null){
            bitmap= (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }


    private void initView(){
        vname=findViewById(R.id.vf_visitorname);
        mno=findViewById(R.id.vf_visitormno);
        refencecode=findViewById(R.id.vf_referencecode);
        vehicleno=findViewById(R.id.vf_vehicleno);
        reason=findViewById(R.id.vf_reasonforvisit);
        relation=findViewById(R.id.vf_relationwithperson);
        wing=findViewById(R.id.vf_wing);
        flatno=findViewById(R.id.vf_flat_house_no);
        btnsubmit=findViewById(R.id.vf_submitbtn);
        prebook=findViewById(R.id.vf_prebook);
        prebooket=findViewById(R.id.vf_prebookreference);
        llhide=findViewById(R.id.vf_prebookhidell);
        btnprebook=findViewById(R.id.btnprebook);
        capturebtn=findViewById(R.id.vf_picbtn);
        imageView=findViewById(R.id.vf_imgview);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.visitor_list_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== R.id.visitorlist){
            Intent intent=new Intent(this,CheckInOutActivity.class);
            startActivity(intent);
        }
        if (item.getItemId()==R.id.visitorlistdelete){
            DataBaseHelper db=new DataBaseHelper(this);
            db.deletealldataofdatabase();
            Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (bitmap != null){
            imageView.setImageBitmap(bitmap);
        }
    }

}
