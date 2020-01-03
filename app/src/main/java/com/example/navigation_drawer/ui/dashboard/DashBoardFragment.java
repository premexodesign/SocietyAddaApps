package com.example.navigation_drawer.ui.dashboard;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.navigation_drawer.R;


public class DashBoardFragment extends Fragment {

    private View view;
    private GridView gv;
    GridCustomAdapter gca;
    private String[] names={"Society","Member","Tenant","Wings","Parking","Complaints","Events","Emergency","Facility","Services","Account",
            "Profile","Notice Board","Visitor Info","Message","Balance Manager"};
    private int [] imgs={R.mipmap.society,R.mipmap.member3,R.mipmap.tenant,R.mipmap.wings1,R.mipmap.parking,R.mipmap.complain,R.mipmap.events1,
            R.mipmap.emergency,R.mipmap.facilieties,R.mipmap.services,R.mipmap.account,
            R.mipmap.profile,R.mipmap.notice_board,R.mipmap.visitersinfp,R.mipmap.message,R.mipmap.balance_manager};
    public DashBoardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_dash_board, container, false);
        gv=view.findViewById(R.id.mygridview);

        gca=new GridCustomAdapter(getActivity(),names,imgs);
        gv.setAdapter(gca);
        isReadStoragePermissionGranted();
        isWriteStoragePermissionGranted();
        return view;

    }

    public  boolean isReadStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (getContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("Permission","Permission is granted1");
                return true;
            } else {

                Log.v("Permission","Permission is revoked1");
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("Permission","Permission is granted1");
            return true;
        }
    }

    public  boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (getContext().checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("Permission","Permission is granted2");
                return true;
            } else {

                Log.v("Permission","Permission is revoked2");
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("Permission","Permission is granted2");
            return true;
        }
    }

}
