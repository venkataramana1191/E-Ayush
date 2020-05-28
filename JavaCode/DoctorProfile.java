package com.example.cse.ayush;

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

import java.security.interfaces.DSAKey;

import static com.example.cse.ayush.Login.user;

public class DoctorProfile extends AppCompatActivity {

    TextView village,mandal,district,state,email,mobile,name;
    EditText EVillage,Emandal,EDistrict,EState,EEmail;
    Button CVillage,CMandal,CDistrict,CState,CEmail;
    Button BVillage,BMandal,BDistrict,BState,BEmail;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);
        databaseReference= FirebaseDatabase.getInstance().getReference("UserDetails");
        EditSet();
        ButtonSet();
        set();
    }

    private void ButtonSet() {
        CVillage=(Button)findViewById(R.id.EditVillage);
        CMandal=(Button)findViewById(R.id.EditMandal);
        CDistrict=(Button)findViewById(R.id.EditDistrict);
        CState=(Button)findViewById(R.id.EditState);
        CEmail=(Button)findViewById(R.id.EditEmail);
        CVillage.setVisibility(View.INVISIBLE);
        CMandal.setVisibility(View.INVISIBLE);
        CDistrict.setVisibility(View.INVISIBLE);
        CState.setVisibility(View.INVISIBLE);
        CEmail.setVisibility(View.INVISIBLE);
        BVillage=(Button)findViewById(R.id.DBvillage);
        BMandal=(Button)findViewById(R.id.DBMandal);
        BDistrict=(Button)findViewById(R.id.DBDistrict);
        BState=(Button)findViewById(R.id.DBstate);
        BEmail=(Button)findViewById(R.id.DBEmail);
    }

    private void EditSet() {
        EVillage=(EditText)findViewById(R.id.EditTextVillage);
        Emandal=(EditText)findViewById(R.id.EditTextMandal);
        EDistrict=(EditText)findViewById(R.id.EditTextDistrict);
        EState=(EditText)findViewById(R.id.EditTextState);
        EEmail=(EditText)findViewById(R.id.EditTextEmail);
        EVillage.setVisibility(View.INVISIBLE);
        Emandal.setVisibility(View.INVISIBLE);
        EDistrict.setVisibility(View.INVISIBLE);
        EState.setVisibility(View.INVISIBLE);
        EEmail.setVisibility(View.INVISIBLE);
    }

    private void set() {
        village=(TextView)findViewById(R.id.Dvillage);
        mandal=(TextView)findViewById(R.id.DMandal);
        district=(TextView)findViewById(R.id.DDistrict);
        state=(TextView)findViewById(R.id.DState);
        email=(TextView)findViewById(R.id.DEmail);
        mobile=(TextView)findViewById(R.id.DMobile);
        name=(TextView)findViewById(R.id.DName);
        village.setText("Village : "+user.getStringVillage());
        mandal.setText("Mandal : "+user.getStringMandal());
        district.setText("District : "+user.getStringDistrict());
        state.setText("State : "+user.getStringState());
        email.setText("Email : "+user.getStringEmail());
        mobile.setText("Mobile No: "+user.getStringMobilNo());
        name.setText(user.getStringName());

    }

    public void SaveData(View view) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                databaseReference.child(user.getStringMobilNo()).setValue(user);
                Toast.makeText(getApplicationContext(),"Profile Updated SucessFully",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Failed To Update Profile",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void EditEmail(View view) {
        email.setVisibility(View.INVISIBLE);
        EEmail.setVisibility(View.VISIBLE);
        BEmail.setVisibility(View.INVISIBLE);
        CEmail.setVisibility(View.VISIBLE);
        EEmail.setText(user.getStringEmail());

    }

    public void EditState(View view) {
        state.setVisibility(View.INVISIBLE);
        EState.setVisibility(View.VISIBLE);
        BState.setVisibility(View.INVISIBLE);
        CState.setVisibility(View.VISIBLE);
        EState.setText(user.getStringState());
    }

    public void EditDistrict(View view) {
        district.setVisibility(View.INVISIBLE);
        EDistrict.setVisibility(View.VISIBLE);
        BDistrict.setVisibility(View.INVISIBLE);
        CDistrict.setVisibility(View.VISIBLE);
        EDistrict.setText(user.getStringDistrict());
    }

    public void EditMandal(View view) {
        mandal.setVisibility(View.INVISIBLE);
        Emandal.setVisibility(View.VISIBLE);
        BMandal.setVisibility(View.INVISIBLE);
        CMandal.setVisibility(View.VISIBLE);
        Emandal.setText(user.getStringMandal());
    }

    public void EditVillage(View view) {
        village.setVisibility(View.INVISIBLE);
        EVillage.setVisibility(View.VISIBLE);
        BVillage.setVisibility(View.INVISIBLE);
        CVillage.setVisibility(View.VISIBLE);
        EVillage.setText(user.getStringVillage());

    }

    public void EditVillage1(View view) {
        village.setText("Village : "+EVillage.getText().toString());
        village.setVisibility(View.VISIBLE);
        EVillage.setVisibility(View.INVISIBLE);
        CVillage.setVisibility(View.INVISIBLE);
        BVillage.setVisibility(View.VISIBLE);
        user.setStringVillage(EVillage.getText().toString());
    }

    public void EditMandal1(View view) {
        mandal.setText("Mandal : "+Emandal.getText().toString());
         mandal.setVisibility(View.VISIBLE);
        Emandal.setVisibility(View.INVISIBLE);
        CMandal.setVisibility(View.INVISIBLE);
        BMandal.setVisibility(View.VISIBLE);
        user.setStringMandal(Emandal.getText().toString());
    }

    public void EditDistrict1(View view) {
        district.setText("District: "+EDistrict.getText().toString());
        district.setVisibility(View.VISIBLE);
        EDistrict.setVisibility(View.INVISIBLE);
        CDistrict.setVisibility(View.INVISIBLE);
        BDistrict.setVisibility(View.VISIBLE);
        user.setStringDistrict(EDistrict.getText().toString());

    }

    public void EditState1(View view) {
        state.setText("State : "+EState.getText().toString());
        state.setVisibility(View.VISIBLE);
        EState.setVisibility(View.INVISIBLE);
        CState.setVisibility(View.INVISIBLE);
        BState.setVisibility(View.VISIBLE);
        user.setStringState(EState.getText().toString());

    }

    public void EditEmail1(View view) {
       email.setText("Email : "+EEmail.getText().toString());
        email.setVisibility(View.VISIBLE);
        EEmail.setVisibility(View.INVISIBLE);
        CEmail.setVisibility(View.INVISIBLE);
        BEmail.setVisibility(View.VISIBLE);
        user.setStringEmail(EEmail.getText().toString());
    }
}
