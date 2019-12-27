package com.example.navigation_drawer.visitorinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.navigation_drawer.R;
import com.example.navigation_drawer.database.DataBaseHelper;

import java.util.ArrayList;

public class VisitorListActivity extends AppCompatActivity {

//    private ListView lv;
//    private VisitorListAdapter vla;
//    private ArrayList<Integer> idarray;
//    private ArrayList<String> namearray;
//    private ArrayList<Long> numarray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_list);
        setTitle("VISITOR CHECK IN & CHECK OUT LIST");
        initViews();
//        boolean check=getDatafromDatabase();
//        if (check){
//            vla=new VisitorListAdapter(this,idarray,namearray,numarray);
//            lv.setAdapter(vla);
//
//        }
    }

    private void initViews() {
//        lv=findViewById(R.id.visitorlistview);
//        idarray = new ArrayList<>();
//        namearray = new ArrayList<>();
//        numarray = new ArrayList<>();
    }

//    private boolean getDatafromDatabase(){
//        DataBaseHelper  dbh=new DataBaseHelper(this);
//        Cursor cursor= dbh.getVisitorData();
//        if (cursor.getCount()>0 && cursor.moveToFirst()){
//            idarray.clear();
//            namearray.clear();
//            numarray.clear();
//            for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
//                idarray.add(cursor.getInt(0));
//                namearray.add(cursor.getString(1));
//                numarray.add(cursor.getLong(2));
//            }
//            return true;
//        }else{
//            idarray.clear();
//            namearray.clear();
//            numarray.clear();
//            Toast.makeText(this, "There is no data", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//    }
//
//    public void notifydatasetchanged(){
//        Toast.makeText(this, "Called", Toast.LENGTH_SHORT).show();
//        getDatafromDatabase();
//            Toast.makeText(this, "Notify", Toast.LENGTH_SHORT).show();
//            vla.notifyDataSetChanged();
//    }
}
