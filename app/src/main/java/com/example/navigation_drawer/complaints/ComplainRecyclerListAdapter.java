package com.example.navigation_drawer.complaints;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigation_drawer.R;
import com.example.navigation_drawer.tenant.BachelorListAdapter;

import java.util.ArrayList;

public class ComplainRecyclerListAdapter extends RecyclerView.Adapter<ComplainRecyclerListAdapter.viewHolder> {


    Activity activity;
    ArrayList<ComplainGroupPersonModel> groupdata;
    ArrayList<String> housenodata;
    public ComplainRecyclerListAdapter(Activity activity, ArrayList<ComplainGroupPersonModel> groupdata) {
        this.activity=activity;
        this.groupdata=groupdata;
        housenodata=new ArrayList<>();
        housenodata.add("A-101");
        housenodata.add("A-102");
        housenodata.add("A-103");
        housenodata.add("A-104");
        housenodata.add("B-101");
        housenodata.add("B-102");
        housenodata.add("B-103");
        housenodata.add("B-104");
        housenodata.add("C-101");
        housenodata.add("C-102");
        housenodata.add("C-103");
        housenodata.add("C-104");
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.complain_adapter_list_item, parent, false);
        viewHolder viewHolder=new viewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final viewHolder holder, final int position) {
        holder.no.setText("Person "+(position+1));
        holder.name.setText(groupdata.get(position).getName());
        holder.houseno.setText(groupdata.get(position).getName());
        ArrayAdapter<String> houseAdapter=new ArrayAdapter<>(activity,android.R.layout.simple_list_item_1,housenodata);
        holder.houseno.setThreshold(1);
        holder.houseno.setAdapter(houseAdapter);
        holder.name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                groupdata.get(position).setName(holder.name.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        holder.houseno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                groupdata.get()
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return groupdata.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {

        EditText name;
        AutoCompleteTextView houseno;
        TextView no;
        public viewHolder(View itemView) {
            super(itemView);
            no=itemView.findViewById(R.id.complaint_group_person_no);
            name=itemView.findViewById(R.id.complaint_group_person_name);
            houseno=itemView.findViewById(R.id.complaint_group_person_houseno);

        }
    }
}
