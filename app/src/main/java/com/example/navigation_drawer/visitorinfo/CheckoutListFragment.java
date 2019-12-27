package com.example.navigation_drawer.visitorinfo;


import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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


public class CheckoutListFragment extends Fragment {

    private ListView lv;
    private ArrayList<String> namearray;
    private ArrayList<Long> numarray;
    private ArrayList<String> datearray;
    private ArrayList<String> timearray;
    private ArrayList<Integer> idarray;
    private View view;
    private Activity activity;
    private CheckOutAdapter coa;
    private boolean frag=false;

    public CheckoutListFragment(Activity activity) {
        this.activity=activity;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_checkout_list, container, false);
        initView();
        boolean check= mygetData();
        if (check){
            coa=new CheckOutAdapter(activity,namearray,numarray,datearray,timearray,idarray,this);
            lv.setAdapter(coa);
        }
        return view;
    }

    private void initView(){
        lv=view.findViewById(R.id.checkoutlistview);
        namearray=new ArrayList<>();
        numarray=new ArrayList<>();
        datearray=new ArrayList<>();
        timearray=new ArrayList<>();
        idarray=new ArrayList<>();
    }

    private boolean mygetData(){
        DataBaseHelper db=new DataBaseHelper(activity);
        Cursor cursor=db.getallcheckoutdata();
        if(cursor.getCount()>0 && cursor.moveToFirst() && namearray != null){
            namearray.clear();
            numarray.clear();
            datearray.clear();
            timearray.clear();
            idarray.clear();
            for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                namearray.add(cursor.getString(0));
                numarray.add(cursor.getLong(1));
                idarray.add(cursor.getInt(2));
                datearray.add(cursor.getString(3));
                timearray.add(cursor.getString(4));
            }
            frag=true;
            return true;
        }else{
            namearray.clear();
            numarray.clear();
            datearray.clear();
            timearray.clear();
            idarray.clear();
            Toast.makeText(activity, "There is no data", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            mygetData();
            if (frag){
                if (coa==null){
                    coa=new CheckOutAdapter(activity,namearray,numarray,datearray,timearray,idarray,this);
                    lv.setAdapter(coa);
                }else{
                    coa.notifyDataSetChanged();
                }

            }

        }
    }



}
