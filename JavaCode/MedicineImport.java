package com.example.cse.ayush;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.cse.ayush.Worker.Adno1;

public class MedicineImport extends AppCompatActivity {
    static int can=-1;
    EditText mname,mquantity,mtime;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_import);
        mname=(EditText)findViewById(R.id.MedicineName);
        mquantity=(EditText)findViewById(R.id.MedicineQuantity);
        mtime=(EditText)findViewById(R.id.MedicineTime);
        databaseReference = FirebaseDatabase.getInstance().getReference("MedicineDetails");
    }

    public void ADD(View view) {
        final String Name=mname.getText().toString().trim();
        final String Quantity=mquantity.getText().toString().trim();
        final String Time=mtime.getText().toString().trim();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                databaseReference.child(Adno1).child(String.valueOf(can)).setValue(Name+" "+Quantity+" "+Time);
                Toast x=Toast.makeText(MedicineImport.this,"Updated Sucessfully",Toast.LENGTH_SHORT);
                x.setGravity(Gravity.TOP,Gravity.CENTER,5);
                x.show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast x=Toast.makeText(MedicineImport.this,"Failed to Update",Toast.LENGTH_SHORT);
                x.setGravity(Gravity.TOP,Gravity.CENTER,5);
                x.show();
            }
        });
        can++;
}

    public void Back(View view) {
        startActivity(new Intent(getApplicationContext(),Worker.class));
        finish();
    }
}
