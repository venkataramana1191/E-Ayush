package com.example.cse.ayush;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.cse.ayush.Login.user;

public class PatientProfile extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextView Evillage,Emandal,Edistric,Estate,Emobile,Eemail,Ename;
    EditText Avillage,Amandal,Adistrict,Astate,AEmail;
    Button BEvillage,BEmandal,BEdistrict,BEState,BEemail;
    Button BAvillage,BAmandal,BAdistrict,BAState,BAemail;
    String Editing=" ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        set();
        SetEdits();
        setButtons();
    }

    private void setButtons() {
        BEvillage=(Button)findViewById(R.id.PVillageButton);
        BEmandal=(Button)findViewById(R.id.PMandalButton);
        BEdistrict=(Button)findViewById(R.id.PDistricButton);
        BEState=(Button)findViewById(R.id.PStateButton);
        BEemail=(Button)findViewById(R.id.PEmailButtion);

        BAvillage=(Button)findViewById(R.id.PVillageButton2);
        BAmandal=(Button)findViewById(R.id.PMandalButton2);
        BAdistrict=(Button)findViewById(R.id.PDistricButton2);
        BAState=(Button)findViewById(R.id.PStateButton2);
        BAemail=(Button)findViewById(R.id.PEmailButtion2);
        BAState.setVisibility(View.INVISIBLE);
        BAvillage.setVisibility(View.INVISIBLE);
        BAmandal.setVisibility(View.INVISIBLE);
        BAdistrict.setVisibility(View.INVISIBLE);
        BAemail.setVisibility(View.INVISIBLE);

    }

    private void SetEdits() {
        Avillage=(EditText)findViewById(R.id.editvillage);
        Amandal=(EditText) findViewById(R.id.editmandal);
        Adistrict=(EditText)findViewById(R.id.editdistrict);
        Astate=(EditText) findViewById(R.id.editstate);
        AEmail=(EditText)findViewById(R.id.editemail);

        Avillage.setVisibility(View.INVISIBLE);
        Amandal.setVisibility(View.INVISIBLE);
        AEmail.setVisibility(View.INVISIBLE);
        Adistrict.setVisibility(View.INVISIBLE);
        Astate.setVisibility(View.INVISIBLE);
    }

    private void set() {
        Evillage=(TextView) findViewById(R.id.PVillage);
        Emandal=(TextView) findViewById(R.id.PMandal);
        Edistric=(TextView) findViewById(R.id.PDistric);
        Estate=(TextView) findViewById(R.id.PState);
        Emobile=(TextView)findViewById(R.id.PMobile);
        Eemail=(TextView)findViewById(R.id.PEmail);
        Ename=(TextView)findViewById(R.id.PName);

        Evillage.setText("Village :  "+user.getStringVillage().toString());
        Emandal.setText("Mandal : "+user.getStringMandal().toString());
        Edistric.setText("District : "+user.getStringDistrict().toString());
        Estate.setText("State : "+user.getStringState().toString());
        Emobile.setText("Mobile No: "+user.getStringMobilNo().toString());
        Eemail.setText("Email : "+user.getStringEmail().toString());
        Ename.setText(user.getStringName().toString());
    }
    public void EditVillage(View view) {
        Evillage.setVisibility(View.INVISIBLE);
        BEvillage.setVisibility(View.INVISIBLE);
        Avillage.setText(user.getStringVillage());
        Avillage.setVisibility(View.VISIBLE);
        BAvillage.setVisibility(View.VISIBLE);

    }
    public void EditMandal(View view) {
        Emandal.setVisibility(View.INVISIBLE);
        BEmandal.setVisibility(View.INVISIBLE);
        Amandal.setText(user.getStringMandal());
        Amandal.setVisibility(View.VISIBLE);
        BAmandal.setVisibility(View.VISIBLE);
    }
    public void EditDistrict(View view) {
        Edistric.setVisibility(View.INVISIBLE);
        BEdistrict.setVisibility(View.INVISIBLE);
        Adistrict.setText(user.getStringDistrict());
        Adistrict.setVisibility(View.VISIBLE);
        BAdistrict.setVisibility(View.VISIBLE);

    }
    public void EditState(View view) {
        Estate.setVisibility(View.INVISIBLE);
        BEState.setVisibility(View.INVISIBLE);
        Astate.setText(user.getStringState());
        Astate.setVisibility(View.VISIBLE);
        BAState.setVisibility(View.VISIBLE);
}
    public void EditEmail(View view) {
        Eemail.setVisibility(View.INVISIBLE);
        BEemail.setVisibility(View.INVISIBLE);
        AEmail.setText(user.getStringEmail());
        AEmail.setVisibility(View.VISIBLE);
        BAemail.setVisibility(View.VISIBLE);

    }
    public void EditSave(View view) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                databaseReference.child("UserDetails").child(user.getStringMobilNo()).setValue(user);
                Toast x=Toast.makeText(PatientProfile.this,"Data Updated",Toast.LENGTH_SHORT);
                x.setGravity(Gravity.TOP,Gravity.CENTER,5);
                x.show();
        }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast x=Toast.makeText(PatientProfile.this,"Failed to Update",Toast.LENGTH_SHORT);
                x.setGravity(Gravity.TOP,Gravity.CENTER,5);
                x.show();
            }
        });
    }

    public void EditEmailok(View view) {
        user.setStringEmail(AEmail.getText().toString());
        Eemail.setText("Email : "+AEmail.getText().toString());
        Eemail.setVisibility(View.VISIBLE);
        BEemail.setVisibility(View.VISIBLE);
        AEmail.setVisibility(View.INVISIBLE);
        BAemail.setVisibility(View.INVISIBLE);
    }

    public void EditStateOk(View view) {
        user.setStringState(Astate.getText().toString());
        Estate.setText("State : "+Astate.getText().toString());
        Estate.setVisibility(View.VISIBLE);
        BEState.setVisibility(View.VISIBLE);
        Astate.setVisibility(View.INVISIBLE);
        BAState.setVisibility(View.INVISIBLE);
    }

    public void EditDistrictOk(View view) {
        user.setStringDistrict(Adistrict.getText().toString());
        Edistric.setText("District : "+Adistrict.getText().toString());
        Edistric.setVisibility(View.VISIBLE);
        BEdistrict.setVisibility(View.VISIBLE);
        Adistrict.setVisibility(View.INVISIBLE);
        BAdistrict.setVisibility(View.INVISIBLE);
    }

    public void EditMandalOk(View view) {
        user.setStringMandal(Amandal.getText().toString());
        Emandal.setText("Mandal : "+Amandal.getText().toString());
        Emandal.setVisibility(View.VISIBLE);
        BEmandal.setVisibility(View.VISIBLE);
        Amandal.setVisibility(View.INVISIBLE);
        BAmandal.setVisibility(View.INVISIBLE);
    }

    public void EditVillageok(View view) {
        user.setStringVillage(Avillage.getText().toString());
        Evillage.setText("Village : "+Avillage.getText().toString());
        Evillage.setVisibility(View.VISIBLE);
        BEvillage.setVisibility(View.VISIBLE);
        Avillage.setVisibility(View.INVISIBLE);
        BAvillage.setVisibility(View.INVISIBLE);

    }
}
