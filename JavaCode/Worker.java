package com.example.cse.ayush;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

import static com.example.cse.ayush.Login.user;
import static com.example.cse.ayush.Login.userlist;

public class Worker extends AppCompatActivity {

     UserDetails userDetails;
     EditText ANo;
     Button Update,list;
     static  String Adno1;
     EditText editdesc,editcs,editname;
     TextView textdesc,textcs,textname,textdidesc,textdics,textdiname;
     DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);
        ANo = (EditText)findViewById(R.id.Adno);
        databaseReference= FirebaseDatabase.getInstance().getReference("UserDetails");
        Set();
    }

    private void Set() {
        editcs=(EditText)findViewById(R.id.editcurrent);
        editdesc=(EditText)findViewById(R.id.editdesc);
        editname=(EditText)findViewById(R.id.editdoctor);
        Update=(Button)findViewById(R.id.Sava);
        list=(Button)findViewById(R.id.medicine);

        //text titles
        textdesc=(TextView)findViewById(R.id.textdesc);
        textcs=(TextView)findViewById(R.id.textcurrent);
        textname=(TextView)findViewById(R.id.textdoctor);
        textdidesc=(TextView)findViewById(R.id.didesc);
        textdics=(TextView)findViewById(R.id.dicurrent);
        textdiname=(TextView)findViewById(R.id.didoctor);
        Invisible();
    }
    private void Invisible(){
        Update.setVisibility(View.INVISIBLE);
        list.setVisibility(View.INVISIBLE);
        editname.setVisibility(View.INVISIBLE);
        editdesc.setVisibility(View.INVISIBLE);
        editcs.setVisibility(View.INVISIBLE);

        //text titles
        textdesc.setVisibility(View.INVISIBLE);
        textcs.setVisibility(View.INVISIBLE);
        textname.setVisibility(View.INVISIBLE);
        textdidesc.setVisibility(View.INVISIBLE);
        textdics.setVisibility(View.INVISIBLE);
        textdiname.setVisibility(View.INVISIBLE);
    }

    public void GetUser(View view) {
        Adno1=ANo.getText().toString();
        Iterator it=userlist.iterator();
        userDetails=null;
        int u=0;
        while(it.hasNext())
        {
            userDetails= (UserDetails) it.next();
            if(userDetails.getStringAadher().equalsIgnoreCase(Adno1))
            {
                u=1;
                break;
            }
        }
        if(u==0)
        {
            Invisible();
            Toast.makeText(getApplicationContext(),"Data Not Available",Toast.LENGTH_SHORT).show();
        }
        else {
            textdics.setText(userDetails.getCurrentIssue());
            textdidesc.setText(userDetails.getDescription());
            textdiname.setText(userDetails.getMustReport());
            Update.setVisibility(View.VISIBLE);
            list.setVisibility(View.VISIBLE);
            textdesc.setVisibility(View.VISIBLE);
            textcs.setVisibility(View.VISIBLE);
            textname.setVisibility(View.VISIBLE);
            textdidesc.setVisibility(View.VISIBLE);
            textdics.setVisibility(View.VISIBLE);
            textdiname.setVisibility(View.VISIBLE);
        }
    }

    public void Edit_Descript(View view) {
        editdesc.setText(userDetails.getDescription());
        textdidesc.setVisibility(View.INVISIBLE);
        editdesc.setVisibility(View.VISIBLE);
        String yes=editdesc.getText().toString();
        userDetails.setDescription(yes);
        textdidesc.setText(userDetails.getDescription());

    }

    public void Edit_Current(View view) {
        editcs.setText(userDetails.getCurrentIssue());
        textdics.setVisibility(View.INVISIBLE);
        editcs.setVisibility(View.VISIBLE);
        String yes=editcs.getText().toString();
        userDetails.setCurrentIssue(yes);
        textdics.setText(userDetails.getCurrentIssue());


    }

    public void Edit_Doctor(View view) {
        editname.setText(userDetails.getMustReport());
        textdiname.setVisibility(View.INVISIBLE);
        editname.setVisibility(View.VISIBLE);
        String yes=editname.getText().toString();
        userDetails.setMustReport(yes);
        textdiname.setText(userDetails.getMustReport());
    }

    public void Update(View view) {
        editname.setVisibility(View.INVISIBLE);
        editdesc.setVisibility(View.INVISIBLE);
        editcs.setVisibility(View.INVISIBLE);
        textdidesc.setVisibility(View.VISIBLE);
        textdics.setVisibility(View.VISIBLE);
        textdiname.setVisibility(View.VISIBLE);
        Data();

    }

    private void Data() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                databaseReference.child(userDetails.getStringMobilNo()).setValue(userDetails);
                Toast.makeText(getApplicationContext(),"Updated",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Failed to Update",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void Medicines(View view) {
        startActivity(new Intent(getApplicationContext(),MedicineImport.class));
    }

    public void SignOut(View view) {
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }
}

