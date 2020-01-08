package com.example.navigation_drawer.tenant;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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



//    public View getView(final int position, View convertView, ViewGroup parent) {
//        final LayoutInflater inflater=activity.getLayoutInflater();
//        View view=inflater.inflate(R.layout.tenant_bachelor_list_item,null,true);
//        TextView txtno=view.findViewById(R.id.bachelortxtno);
//        final EditText name=view.findViewById(R.id.bachelor_name);
//        final EditText panno=view.findViewById(R.id.bachelor_panno);
//        final EditText aadharno=view.findViewById(R.id.bachelor_aadharno);
//
//        txtno.setText(dataModels.get(position).getNo());
//        name.setText(dataModels.get(position).getName());
//        panno.setText(dataModels.get(position).getPanno());
//        aadharno.setText(dataModels.get(position).getAadharno());
//
//        name.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                dataModels.get(position).setName(name.getText().toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        panno.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                dataModels.get(position).setPanno(panno.getText().toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        aadharno.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                dataModels.get(position).setAadharno(aadharno.getText().toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//        return view;
//    }
}
