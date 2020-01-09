package com.example.navigation_drawer.tenant;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.navigation_drawer.R;

import java.util.ArrayList;

public class BachelorListAdapter extends RecyclerView.Adapter<BachelorListAdapter.viewHolder> {

    Activity activity;
    ArrayList<TenantBachelorDetailsModel> dataModels;
    public BachelorListAdapter(Activity activity, ArrayList<TenantBachelorDetailsModel> dataModels) {
        this.activity=activity;
        this.dataModels=dataModels;
    }

    @Override
    public viewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.tenant_bachelor_list_item, parent, false);
        viewHolder viewHolder = new viewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final viewHolder holder,final int position) {
        holder.no.setText(dataModels.get(position).getNo());
        holder.name.setText(dataModels.get(position).getName());
        holder.panno.setText(dataModels.get(position).getPanno());
        holder.aadharno.setText(dataModels.get(position).getAadharno());

        holder.name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dataModels.get(position).setName(holder.name.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        holder.panno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dataModels.get(position).setPanno(holder.panno.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        holder.aadharno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dataModels.get(position).setAadharno(holder.aadharno.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }


    public static class viewHolder extends RecyclerView.ViewHolder{
        public TextView no;
        public EditText name,panno,aadharno;

        public viewHolder(View itemView) {
            super(itemView);
            no=itemView.findViewById(R.id.bachelortxtno);
            name=itemView.findViewById(R.id.bachelor_name);
            panno=itemView.findViewById(R.id.bachelor_panno);
            aadharno=itemView.findViewById(R.id.bachelor_aadharno);
        }
    }
}
