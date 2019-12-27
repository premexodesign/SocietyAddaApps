package com.example.navigation_drawer.visitorinfo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.navigation_drawer.R;
import com.example.navigation_drawer.database.DataBaseHelper;

import java.util.ArrayList;

public class VisitorListAdapter extends BaseAdapter {

    Activity activity;
    ArrayList<Integer> vid;
    ArrayList<String> name;
    ArrayList<Long> num;
    Fragment fm;
    public VisitorListAdapter(Activity activity, ArrayList<Integer> id, ArrayList<String> name, ArrayList<Long> num, Fragment fm) {
        this.activity=activity;
        this.vid=id;
        this.name=name;
        this.num=num;
        this.fm=fm;
    }

    @Override
    public int getCount() {
        return vid.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater=activity.getLayoutInflater();
        View view=inflater.inflate(R.layout.visitor_list_adapter_layout,null,true);
        LinearLayout linearLayout=view.findViewById(R.id.checkinllid);
        TextView nametv=view.findViewById(R.id.adaptervisitorname);
        TextView numtv=view.findViewById(R.id.adaptervisitornum);
        Button btncheckout=view.findViewById(R.id.btncheckout);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity,CheckInPageDetailsActivity.class);
                intent.putExtra("id",vid.get(position));
                activity.startActivity(intent);
            }
        });

        btncheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dataBaseHelper=new DataBaseHelper(activity);
                boolean check=dataBaseHelper.checkout(vid.get(position));
                if (check){
//                    if (fm instanceof VisitorListFragment){
                        ((VisitorListFragment) fm).notifydatasetchanged();
//                    }
                }else{
                    Toast.makeText(activity, "The Data is not Changed", Toast.LENGTH_SHORT).show();
                }

            }
        });

        nametv.setText(name.get(position));
        numtv.setText(""+num.get(position));

        return view;
    }
}
