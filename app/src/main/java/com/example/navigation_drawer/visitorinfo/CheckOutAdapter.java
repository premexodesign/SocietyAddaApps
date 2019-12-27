package com.example.navigation_drawer.visitorinfo;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.navigation_drawer.R;

import java.util.ArrayList;

public class CheckOutAdapter extends BaseAdapter {

    Activity activity;
    ArrayList<String> name;
    ArrayList<Long> num;
    ArrayList<String> date;
    ArrayList<String> time;
    ArrayList<Integer> ids;
    public CheckOutAdapter(Activity activity, ArrayList<String> name, ArrayList<Long> num, ArrayList<String> date, ArrayList<String> time,ArrayList<Integer> ids, Fragment fm) {
        this.activity=activity;
        this.name=name;
        this.num=num;
        this.date=date;
        this.time=time;
        this.ids=ids;
    }

    @Override
    public int getCount() {
        return name.size();
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
        View v=convertView;
        ListviewHolder listviewHolder;
        if (convertView==null){
            LayoutInflater li=activity.getLayoutInflater();
            v=li.inflate(R.layout.checkoutapdaterlayout,null,true);
            listviewHolder=new ListviewHolder(v);
            v.setTag(listviewHolder);
        }else{
            listviewHolder=(ListviewHolder) v.getTag();
        }
        listviewHolder.tvname.setText(name.get(position));
        listviewHolder.tvnum.setText(""+num.get(position));
        listviewHolder.tvdate.setText(date.get(position));
        listviewHolder.tvtime.setText(time.get(position));
        listviewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity,CheckoutPageDetailsActivity.class);
                intent.putExtra("id",ids.get(position));
                activity.startActivity(intent);
            }
        });

        return v;
    }
    class ListviewHolder{

        TextView tvname,tvnum,tvdate,tvtime;
        LinearLayout linearLayout;
        public ListviewHolder(View view) {
            linearLayout=view.findViewById(R.id.checkoutll);
            tvname=view.findViewById(R.id.txtcheckoutpersonname);
            tvnum=view.findViewById(R.id.txtcheckoutpersonnum);
            tvdate=view.findViewById(R.id.txtcheckoutpersondate);
            tvtime=view.findViewById(R.id.txtcheckoutpersontime);

        }
    }

}
