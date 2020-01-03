package com.example.navigation_drawer.ui.dashboard;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigation_drawer.R;
import com.example.navigation_drawer.members.MemberListActivity;
import com.example.navigation_drawer.society.SocietyFormActivity;
import com.example.navigation_drawer.tenant.TenantActivity;
import com.example.navigation_drawer.visitorinfo.VisitorInfoFormActivity;
import com.example.navigation_drawer.wings.WingsActivity;


public class GridCustomAdapter extends ArrayAdapter<String> {

    Activity context;
    String[]names;
    int [] imgs;

    public GridCustomAdapter(Activity context, String[] names, int[] imgs) {
        super(context, R.layout.custom_adapter,names);
        this.context=context;
        this.names=names;
        this.imgs=imgs;
    }


    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View ca=inflater.inflate(R.layout.custom_adapter,null,true);
        ImageView img=ca.findViewById(R.id.myimgview);
        TextView txtview=ca.findViewById(R.id.mycustomtxtview);
        LinearLayout  ll=ca.findViewById(R.id.dashboarditemll);
        img.setImageResource(imgs[position]);
        txtview.setText(names[position]);

        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemname=names[position];
                switch (itemname)
                {
                    case "Visitor Info":
                        Intent intent=new Intent(context, VisitorInfoFormActivity.class);
                        context.startActivity(intent);
                        break;
                    case "Member":
                        Intent intent1=new Intent(context, MemberListActivity.class);
                        context.startActivity(intent1);
                        break;
                    case "Society":
                        Intent intent2=new Intent(context, SocietyFormActivity.class);
                        context.startActivity(intent2);
                        break;
                    case "Wings":
                        Intent intent3=new Intent(context, WingsActivity.class);
                        context.startActivity(intent3);
                        break;
                    case "Tenant":
                        Intent intent4=new Intent(context, TenantActivity.class);
                        context.startActivity(intent4);
                        break;

                }

            }
        });


        return ca;
    }
}
