package com.example.navigation_drawer.wings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigation_drawer.R;

import java.util.ArrayList;

public class WingDetailsActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private ArrayList<ParentItems> groupitems;
    private ArrayList<ChildItems> childItems;
    private ArrayList<String> childsdata;
    private ListViewAdapter listViewAdapter;
    private String wings;
    private int floor,flat;
    private TextView wingtextview;
    private Spinner housetype;
    private EditText wheel2,wheel4;
    ImageButton btnsubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wing_details);
        setTitle("Wing Details");

        initView();

        wingtextview.setText("Wing: "+wings);
        ArrayAdapter<String> spinneradapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,new String[] {"Residential","Commercial"});
        housetype.setAdapter(spinneradapter);
        for (int i=1;i<=floor;i++){
            int flatno=i*100;
            ParentItems parentItems=new ParentItems();
            parentItems.setGroupname(i+" Floor");
            groupitems.add(parentItems);
            childsdata=new ArrayList<>();
            for (int j=1;j<=flat;j++){
                childsdata.add((flatno+j)+"");
            }
            ChildItems childItem=new ChildItems();
            childItem.setGroupname(groupitems.get(i-1).getGroupname());
            childItem.setGroupdata(childsdata);
            childItems.add(childItem);
        }

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (ChildItems child:childItems) {
                    ArrayList<String> data2wheel=new ArrayList<>();
                    ArrayList<String> data4wheel=new ArrayList<>();
                    ArrayList<String> houseType=new ArrayList<>();
                        for (int i=0;i < child.getGroupdata().size();i++){
                            data2wheel.add(wheel2.getText().toString());
                            data4wheel.add(wheel4.getText().toString());
                            houseType.add(housetype.getSelectedItem().toString());
                        }
                        child.setGroup2wheeldata(data2wheel);
                        child.setGroup4wheeldata(data4wheel);
                        child.setGroupHouseType(houseType);
                }

                listViewAdapter.notifyDataSetChanged();
            }
        });

        listViewAdapter=new ListViewAdapter(this,groupitems,childItems);
        expandableListView.setAdapter(listViewAdapter);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

                for (int i=0;i<groupitems.size();i++){
                    if (i!=groupPosition)
                        expandableListView.collapseGroup(i);
                }
            }
        });
    }

    public void initView(){
        Intent intent=getIntent();
        wings=intent.getStringExtra("Wing");
        floor=Integer.parseInt(intent.getStringExtra("Floor")) ;
        flat=Integer.parseInt(intent.getStringExtra("Flat"));
        wingtextview=findViewById(R.id.wingsDetails_wingTitle);
        expandableListView=findViewById(R.id.wingsDetails_expanlistview);
        groupitems=new ArrayList<>();
        childItems=new ArrayList<>();
        housetype=findViewById(R.id.wings_details_houseType);
        wheel2=findViewById(R.id.wings_details_nooftwowheelerpaking);
        wheel4=findViewById(R.id.wings_details_nooffourwheelerpaking);
        btnsubmit=findViewById(R.id.wings_details_submitbtn);
    }

    public void deleteitem(int groupPosition,int itemPosition){
        childItems.get(groupPosition).getGroupdata().remove(itemPosition);
        listViewAdapter.notifyDataSetChanged();
    }

}
