package com.example.cse.ayush;

import android.media.ToneGenerator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.example.cse.ayush.Login.userlist;

public class Employ extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText dname;
    Spinner spinner;
    TextView textView;
    String disease1;
    String [] listof={"Fever","Cancer","Tipoid","Fungs"};
    // Get the Database service for the default app
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employ);
        dname=(EditText)findViewById(R.id.DistrictName);
        spinner=(Spinner)findViewById(R.id.disease);
        textView=(TextView)findViewById(R.id.listcount);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter aa= new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,listof);
        spinner.setAdapter(aa);
    }

    public void EmployDuty(View view) {
        int count=0,no=0;
        String District=dname.getText().toString().trim();
        Iterator it=userlist.iterator();
        UserDetails userDetails=null;
        while(it.hasNext()) {
            userDetails = (UserDetails) it.next();

            if((userDetails.getStringDistrict().equalsIgnoreCase(District)) && (userDetails.getStringType().equalsIgnoreCase("Patient")))
            {
                count++;
                if(userDetails.getCurrentIssue().equalsIgnoreCase(disease1)) no++;
            }
            userDetails=null;

        }
        textView.setText(disease1+" "+no+"/"+count);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        disease1=listof[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
