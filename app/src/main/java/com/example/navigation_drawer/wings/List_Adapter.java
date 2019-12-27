package com.example.navigation_drawer.wings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.navigation_drawer.MainActivity;
import com.example.navigation_drawer.R;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class List_Adapter extends BaseAdapter {

    ArrayList<WingsModel> arrayList;
    Activity activity;
    LayoutInflater inflater;


    public List_Adapter(Activity context, ArrayList<WingsModel> arrayList) {
        activity=context;
        this.arrayList=arrayList;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view=convertView;
        final EditText editWing,editfloor,editflat;
        final ImageButton btnedit,btndelete;

        if (convertView==null){
            inflater=activity.getLayoutInflater();
            view=inflater.inflate(R.layout.list_items,null,true);
        }
        editWing=view.findViewById(R.id.etliwings);
        editfloor=view.findViewById(R.id.etlifloor);
        editflat=view.findViewById(R.id.etlinoofflat);
        btnedit=view.findViewById(R.id.wings_list_btnedit);
        btndelete=view.findViewById(R.id.wings_delete_item);

        editWing.setText(arrayList.get(position).getWing());
        editfloor.setText(arrayList.get(position).getFloors());
        editflat.setText(arrayList.get(position).getNoofflats());

        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(activity, ""+arrayList.get(position).getWing(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(activity,WingDetailsActivity.class);
                intent.putExtra("Wing",arrayList.get(position).getWing());
                intent.putExtra("Floor",arrayList.get(position).getFloors());
                intent.putExtra("Flat",arrayList.get(position).getNoofflats());
                activity.startActivity(intent);
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activity instanceof WingsActivity){
                    WingsModel wingsModel=arrayList.get(position);
                    ((WingsActivity) activity).deleteitem(wingsModel);
                }
            }
        });

        editWing.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                arrayList.get(position).setWing(editWing.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editfloor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Pattern pattern=Pattern.compile("[0123456789]*");
                Matcher matcher=pattern.matcher(editfloor.getText().toString());
                if (matcher.matches())
                arrayList.get(position).setFloors(editfloor.getText().toString());
                else
                    Toast.makeText(activity, "Number only", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editflat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                arrayList.get(position).setNoofflats(editflat.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }
}
