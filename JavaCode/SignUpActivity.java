package com.example.cse.ayush;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {

    EditText EAadherNumber,EName,EMobileNo,EEmail,EVillage,EMandal,EDistrict,EState,EPincode,EPassword;
    String StringAadher,StringName,StringMobilNo,StringEmail,StringVillage,StringMandal,StringDistrict,StringState,StringPincode,StringPassword;

    FirebaseDatabase firebasedatabase;
    DatabaseReference databaseReference;
    UserDetails userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setEdittext();
        firebasedatabase = FirebaseDatabase.getInstance();
        databaseReference=firebasedatabase.getReference();
    }

    private void setEdittext() {
        EAadherNumber = (EditText) findViewById(R.id.Aadher_number);
        EName = (EditText) findViewById(R.id.Name);
        EMobileNo = (EditText) findViewById(R.id.MobileNo);
        EEmail = (EditText) findViewById(R.id.Email);
        EVillage = (EditText) findViewById(R.id.Village);
        EMandal = (EditText) findViewById(R.id.Mandal);
        EDistrict = (EditText) findViewById(R.id.District);
        EState = (EditText) findViewById(R.id.State);
        EPincode = (EditText) findViewById(R.id.Pincode);
        EPassword=(EditText) findViewById(R.id.Password);
    }

    public void CreateAcount(View view) {
        UserData();
        if(StringAadher.isEmpty() ||  StringName.isEmpty() || StringMobilNo.isEmpty() || StringPassword.isEmpty() ){
            Toast.makeText(getApplicationContext(),"Fill the required fileds",Toast.LENGTH_SHORT).show();
        }
        else{
            userDetails=new UserDetails(StringAadher,"Patient",StringName,StringMobilNo,StringEmail,StringVillage,StringMandal,StringDistrict,StringState,StringPincode,"Nothing","Nothing","Nothing","Healthy","Nothing","Nothing",StringPassword);
            InsertIntoFireBase();
        }

    }

    private void InsertIntoFireBase() {

       databaseReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                databaseReference.child("UserDetails").child(userDetails.getStringMobilNo()).setValue(userDetails);
                startActivity(new Intent(SignUpActivity.this, Login.class));
               Toast x=Toast.makeText(SignUpActivity.this,"Account Created Sucessfull",Toast.LENGTH_SHORT);
               x.setGravity(Gravity.TOP,Gravity.CENTER,5);
               x.show();
               finish();
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {
               Toast x=Toast.makeText(SignUpActivity.this,"Failed to Create Account",Toast.LENGTH_SHORT);
               x.setGravity(Gravity.TOP,Gravity.CENTER,5);
               x.show();
           }
       });

    }

    private void UserData() {
        StringAadher = EAadherNumber.getText().toString().trim();
        StringName=EName.getText().toString().trim();
        StringMobilNo=EMobileNo.getText().toString().trim();
        StringEmail=EEmail.getText().toString().trim();
        StringVillage=EVillage.getText().toString().trim();
        StringMandal=EMandal.getText().toString().trim();
        StringDistrict=EDistrict.getText().toString().trim();
        StringState=EState.getText().toString().trim();
        StringPassword=EPassword.getText().toString().trim();
    }

}
