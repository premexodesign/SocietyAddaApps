package com.example.navigation_drawer.wings;


import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.navigation_drawer.R;

import java.util.ArrayList;

public class ListViewAdapter extends BaseExpandableListAdapter {

    Activity activity;
    ArrayList<ParentItems> parentItems;
    ArrayList<ChildItems> childItems;

    public ListViewAdapter(Activity activity, ArrayList<ParentItems> parentItems, ArrayList<ChildItems> childItems) {
        this.activity = activity;
        this.parentItems = parentItems;
        this.childItems = childItems;
    }

    @Override
    public int getGroupCount() {
        return parentItems.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childItems.get(groupPosition).getGroupdata().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parentItems.get(groupPosition).getGroupname();
    }

    @Override
    public ArrayList<String> getChild(int groupPosition, int childPosition) {
        ArrayList<String> childdata=new ArrayList<>();
        childdata.add(childItems.get(groupPosition).getGroupdata().get(childPosition));
        childdata.add(childItems.get(groupPosition).getGroup2wheeldata().get(childPosition));
        childdata.add(childItems.get(groupPosition).getGroup4wheeldata().get(childPosition));
        childdata.add(childItems.get(groupPosition).getGroupHouseType().get(childPosition));
        return childdata;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_row_group, null);
        }
        TextView textViewGroup = convertView
                .findViewById(R.id.textViewGroup);
        textViewGroup.setTypeface(null, Typeface.BOLD);
        textViewGroup.setText(headerTitle);

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {

//        final String childText = (String) getChild(groupPosition, childPosition);
        final  ArrayList<String> childData=getChild(groupPosition,childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_row_child, null);
        }

        TextView textViewChild = convertView
                .findViewById(R.id.wings_details_flatno);
        EditText wheeler2edittext=convertView.findViewById(R.id.wings_details_list_twowheeler);
        EditText wheeler4edittext=convertView.findViewById(R.id.wings_details_list_fourwheeler);
        EditText houseType=convertView.findViewById(R.id.wings_details_list_housetype);
        ImageButton imgbtn = convertView.findViewById(R.id.delete_item);

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((WingDetailsActivity)activity).deleteitem(groupPosition,childPosition);
            }
        });

//        wheeler2edittext.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                childItems.get(groupPosition).getGroup2wheeldata().set(childPosition,wheeler2edittext.getText().toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

        textViewChild.setText(childData.get(0));
        wheeler2edittext.setText(childData.get(1));
        wheeler4edittext.setText(childData.get(2));
        houseType.setText(childData.get(3));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
