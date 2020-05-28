package com.example.cse.ayush;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.cse.ayush.Login.user;

public class DoctorChat extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView textView;
    EditText editText;
    Spinner spinner;
    String doctors[]={"Kalyan Jyothula","Venkat"};
    String Client,msg,msg2,yes,yes2;
    DatabaseReference databaseReference,databaseReference2,databaseReference3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_chat);
        textView = (TextView) findViewById(R.id.text);
        editText = (EditText) findViewById(R.id.editText);
        spinner=(Spinner)findViewById(R.id.lastdoctor);
        databaseReference = FirebaseDatabase.getInstance().getReference("Chat");
        SetSpinner();
    }

    private void SetSpinner() {
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,doctors);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
    }

    public void sending(View view) {
        msg2=editText.getText().toString();
        Clala();
        goo();
        Back();
    }

    private void Back() {
        if(yes != null){
            textView.setText(user.getStringName()+": "+yes+"\n"+Client+" : "+yes2);
            textView.setGravity(Gravity.RIGHT);
        }
        else{
            textView.setText(Client+" : "+yes2);
            textView.setGravity(Gravity.RIGHT);
        }
    }

    private void goo() {
        //databaseReference3=FirebaseDatabase.getInstance().getReference("Chat");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                yes2=(String)dataSnapshot.child(Client).child(user.getStringName()).getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void Clala() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                msg=(String)dataSnapshot.child(user.getStringName()).child(Client).getValue(String.class);
                SetMessage(msg,msg2);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void SetMessage(final String last, final String current) {
        if(last ==null) yes=current;
        else yes=last+"\n"+current;
        if(!current.equalsIgnoreCase("")){
            //databaseReference2=FirebaseDatabase.getInstance().getReference("Chat");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    databaseReference .child(user.getStringName()).child(Client).setValue(yes);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            Toast toast=Toast.makeText(getApplicationContext(),yes,Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,Gravity.TOP,3);
            toast.show();
            editText.setText("");
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Client=doctors[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
