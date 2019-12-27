package com.example.navigation_drawer.wings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navigation_drawer.R;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WingsActivity extends AppCompatActivity {

    private EditText from, to, nooffloors, noofflats, addwing, addfloor, addflat;
    private ImageButton btnwings;
    private ListView listlv;
    private ArrayList<WingsModel> wingData;
    List_Adapter list_adapter;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wings);
        setTitle("Wings");
        initView();

        btnwings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(from.getText().toString().equalsIgnoreCase("")) && !(to.getText().toString().equalsIgnoreCase("")) &&
                        !(nooffloors.getText().toString().equalsIgnoreCase("")) && !(noofflats.getText().toString().equalsIgnoreCase(""))) {

                    Matcher ms, ms1;
                    Pattern ps = Pattern.compile("^[A-Z]+$");
                    ms = ps.matcher(from.getText().toString());
                    ms1 = ps.matcher(to.getText().toString());

                    char fromchar = from.getText().charAt(0);
                    char tochar = to.getText().charAt(0);
                    int intchar = fromchar;
                    int intcharto = tochar;

                    if (ms.matches() && ms1.matches()) {

                        if (intchar > intcharto) {
                            Toast.makeText(WingsActivity.this, "The wing are not in Sequence", Toast.LENGTH_SHORT).show();
                        } else {
                            wingData.clear();
                            wingData = setDatainModel();
                            list_adapter = new List_Adapter(WingsActivity.this, wingData);
                            listlv.setAdapter(list_adapter);
                            Toast.makeText(WingsActivity.this, "View the data below", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(WingsActivity.this, "Must be Capital Character and No White Space", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(WingsActivity.this, "Please Fill all Fields", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!addwing.getText().toString().equalsIgnoreCase("") &&
                        !addfloor.getText().toString().equalsIgnoreCase("") &&
                        !addflat.getText().toString().equalsIgnoreCase("")) {

                    WingsModel dataModels = new WingsModel();
                    dataModels.setWing(addwing.getText().toString());
                    dataModels.setFloors(addfloor.getText().toString());
                    dataModels.setNoofflats(addflat.getText().toString());
                    wingData.add(dataModels);
                    list_adapter = new List_Adapter(WingsActivity.this, wingData);
                    listlv.setAdapter(list_adapter);

                } else
                    Toast.makeText(WingsActivity.this, "Please Fill all Fields", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private ArrayList<WingsModel> setDatainModel() {

        ArrayList<WingsModel> arrayList = new ArrayList<>();
        char fromchar = from.getText().charAt(0);
        char tochar = to.getText().charAt(0);
        int intchar = fromchar;
        int intcharto = tochar;

        for (int i = intchar; i <= intcharto; i++) {
            String str = Character.toString((char) i);
            WingsModel dataModels = new WingsModel();
            dataModels.setWing(str);
            dataModels.setFloors(nooffloors.getText().toString());
            dataModels.setNoofflats(noofflats.getText().toString());
            arrayList.add(dataModels);
        }

        return arrayList;
    }

    private void initView() {
        from = findViewById(R.id.wings_wingsfrom);
        to = findViewById(R.id.wings_wingsto);
        nooffloors = findViewById(R.id.wings_nooffloor);
        noofflats = findViewById(R.id.wings_noofflat);
        listlv = findViewById(R.id.wings_listview);
        wingData = new ArrayList<>();
        btnwings = findViewById(R.id.wings_wingsbtn);
        addwing = findViewById(R.id.wings_addwing);
        addfloor = findViewById(R.id.wings_addfloor);
        addflat = findViewById(R.id.wings_addflat);
        btn = findViewById(R.id.btnclick);
    }

    public void deleteitem(WingsModel wingsModel){
        Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
        wingData.remove(wingsModel);
        list_adapter.notifyDataSetChanged();
    }

}
