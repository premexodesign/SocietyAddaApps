package com.example.navigation_drawer.complaints;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.navigation_drawer.R;

import java.util.ArrayList;

public class Complaints extends AppCompatActivity {

    Spinner complain_type;
    LinearLayout linearLayout;
    ImageButton btncreatetextbox;
    EditText number_of_person;
    RecyclerView recyclerViewlist;
    ArrayList<ComplainGroupPersonModel> groupdata;
    ComplainRecyclerListAdapter complainAdapter;
    String [] complain_type_data={"Individual","Group","Society"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);
        setTitle("Complaints");

        initView();

        ArrayAdapter<String> complain_type_Adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,complain_type_data);
        complain_type.setAdapter(complain_type_Adapter);
        complain_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    linearLayout.setVisibility(View.GONE);
                }
                if (position==1){
                    linearLayout.setVisibility(View.VISIBLE);
                }
                if (position==2){
                    linearLayout.setVisibility(View.GONE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btncreatetextbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!number_of_person.getText().toString().equalsIgnoreCase("")){
                    int num=Integer.parseInt(number_of_person.getText().toString());
                    for (int i=0;i<num;i++){
                        ComplainGroupPersonModel cg=new ComplainGroupPersonModel();
                        cg.setName("");
                        cg.setHouseno("");
                        groupdata.add(cg);
                    }
                }
                complainAdapter=new ComplainRecyclerListAdapter(Complaints.this,groupdata);
                recyclerViewlist.setHasFixedSize(true);
                recyclerViewlist.setLayoutManager(new LinearLayoutManager(Complaints.this,RecyclerView.VERTICAL,false));
                recyclerViewlist.setAdapter(complainAdapter);
            }
        });

    }

    public void initView(){
        complain_type=findViewById(R.id.complaints_complain_type);
        linearLayout=findViewById(R.id.complaints_dynamicView);
        btncreatetextbox=findViewById(R.id.complaints_number_of_person_button);
        recyclerViewlist=findViewById(R.id.complaints_number_of_person_list);
        number_of_person=findViewById(R.id.complaints_number_of_person);
        groupdata=new ArrayList<>();

    }
}
