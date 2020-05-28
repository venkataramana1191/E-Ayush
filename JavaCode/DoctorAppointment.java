package com.example.cse.ayush;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.example.cse.ayush.Login.user;

public class DoctorAppointment extends AppCompatActivity {

    static List<DoctorAppointListStatus> listapp2=new ArrayList<DoctorAppointListStatus>();
    DatabaseReference databaseReference;
    DoctorAppointListStatus doctorAppointListStatus=null;
    Switch simpleToggleButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appointment);
        listapp2.clear();
        Data();
        ArrayAdapter<DoctorAppointListStatus> adapter=new CustomList1();
        ListView listView=(ListView)findViewById(R.id.listappoint);
        listView.setAdapter(adapter);
    }

    private void Data() {
        databaseReference= FirebaseDatabase.getInstance().getReference("AppointMent");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    doctorAppointListStatus=userSnapshot.getValue(DoctorAppointListStatus.class);
                    //Toast.makeText(getApplicationContext(),doctorAppointListStatus.getPName(),Toast.LENGTH_SHORT).show();
                    if(doctorAppointListStatus.getDoctorName().equalsIgnoreCase(user.getStringName())){
                        listapp2.add(doctorAppointListStatus);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private class CustomList1 extends ArrayAdapter<DoctorAppointListStatus> {
        public CustomList1(){
            super(DoctorAppointment.this,R.layout.doctor_appointlist,listapp2);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View itemView=convertView;
            if(itemView==null){
                itemView=getLayoutInflater().inflate(R.layout.doctor_appointlist,parent,false);
            }
            final DoctorAppointListStatus doctorAppointClass=listapp2.get(position);
            TextView textView=(TextView) itemView.findViewById(R.id.name);
            TextView textView1=(TextView) itemView.findViewById(R.id.datetime);
            TextView textView2=(TextView) itemView.findViewById(R.id.mobileNo);
            textView.setText("Patient Name : "+doctorAppointClass.getPName());
            textView1.setText(doctorAppointClass.getDate());
            textView2.setText("Contact Details : "+doctorAppointClass.getContactNo());
            simpleToggleButton= (Switch) itemView.findViewById(R.id.onoff);
            if(doctorAppointClass.getStatus().equalsIgnoreCase("1")) simpleToggleButton.setChecked(true);
            else simpleToggleButton.setChecked(false);
            simpleToggleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(doctorAppointClass.getStatus().equalsIgnoreCase("1")) doctorAppointClass.setStatus("0");
                    else doctorAppointClass.setStatus("1");
                    ABCD(doctorAppointClass);
                }
            });
            return itemView;
        }
    }
    private void ABCD(final DoctorAppointListStatus doctorAppointListStatus){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                databaseReference.child(doctorAppointListStatus.getPName()).setValue(doctorAppointListStatus);
                Toast.makeText(getApplicationContext(),"Appointment Updated Sucessfully ",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Failed to Update",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
