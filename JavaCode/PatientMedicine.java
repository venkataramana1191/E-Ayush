package com.example.cse.ayush;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.cse.ayush.Login.StringLoginMobileNumber;
import static com.example.cse.ayush.Login.user;

public class PatientMedicine extends AppCompatActivity {

    static public List<MedicineList> listmedicine=new ArrayList<MedicineList>();
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_medicine);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("MedicineDetails");
        Medicin();
        setTitle();
    }

    private void Medicin() {
        for(int i=0;i<5;i++)
        {
            DatabaseReference databaseReference1=databaseReference.child("123456789010").child(String.valueOf(i));
            //Toast.makeText(getApplicationContext(),"opop",Toast.LENGTH_SHORT).show();
            databaseReference1.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String medic=dataSnapshot.getValue(String.class);
                    String kalyan[]=medic.split(" ");
                    listmedicine.add(new MedicineList(kalyan[0],kalyan[1],kalyan[2]));
                    //Toast.makeText(getApplicationContext(), medic, Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    private void setTitle() {
        ArrayAdapter<MedicineList> adapter =new MyListViewAdapter();
        ListView listView= (ListView)findViewById(R.id.ListMedicines);
        listView.setAdapter(adapter);
        listmedicine.clear();
    }

    private class MyListViewAdapter extends ArrayAdapter<MedicineList> {
        public MyListViewAdapter()
        {
            super(PatientMedicine.this,R.layout.medicine_style,listmedicine);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View itemView=convertView;
            if(itemView==null)
            {
                itemView=getLayoutInflater().inflate(R.layout.medicine_style,parent,false);
            }
            MedicineList m=listmedicine.get(position);
            TextView textView=(TextView) itemView.findViewById(R.id.MedicineName);
            textView.setText(m.getMedicineName());
            TextView textView1=(TextView) itemView.findViewById(R.id.qunatity);
            textView1.setText(m.getQuantity());
            TextView textView2=(TextView) itemView.findViewById(R.id.timetake);
            textView2.setText(m.getTime());
            return itemView;
        }
    }
}
