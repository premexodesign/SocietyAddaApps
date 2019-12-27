package com.example.navigation_drawer.visitorinfo;


import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.navigation_drawer.R;
import com.example.navigation_drawer.database.DataBaseHelper;

import java.util.ArrayList;


public class VisitorListFragment extends Fragment {

    private ListView lv;
    private VisitorListAdapter vla;
    private ArrayList<Integer> idarray;
    private ArrayList<String> namearray;
    private ArrayList<Long> numarray;
    private View view;
    Activity activity;

    public VisitorListFragment(Activity activity) {
        this.activity=activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_visitor_list, container, false);
        initViews();
        boolean check=getDatafromDatabase();
        if (check){
            vla=new VisitorListAdapter(activity,idarray,namearray,numarray,this);
            lv.setAdapter(vla);
        }
        return view;
    }

    private void initViews() {
        lv=view.findViewById(R.id.visitorlistview);
        idarray = new ArrayList<>();
        namearray = new ArrayList<>();
        numarray = new ArrayList<>();
    }

    private boolean getDatafromDatabase(){
        DataBaseHelper dbh=new DataBaseHelper(activity);
        Cursor cursor= dbh.getVisitorData();
        if (cursor.getCount()>0 && cursor.moveToFirst()){
            idarray.clear();
            namearray.clear();
            numarray.clear();
            for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                idarray.add(cursor.getInt(0));
                namearray.add(cursor.getString(1));
                numarray.add(cursor.getLong(2));
            }
            return true;
        }else{
            idarray.clear();
            namearray.clear();
            numarray.clear();
            Toast.makeText(activity, "There is no data", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void notifydatasetchanged(){
        Toast.makeText(activity, "Called", Toast.LENGTH_SHORT).show();
        getDatafromDatabase();
        Toast.makeText(activity, "Notify", Toast.LENGTH_SHORT).show();
        vla.notifyDataSetChanged();
    }

}
