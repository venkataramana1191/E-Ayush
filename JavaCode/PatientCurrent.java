package com.example.cse.ayush;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ListIterator;

import static com.example.cse.ayush.Login.user;

public class PatientCurrent extends AppCompatActivity {

    TextView TDescription,TDoctor,THospital,TCurrentIssue,TLast,TMust;
    //String elements[]=new String[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_current);

        set();
        ListItems();
    }

    private void set() {
        TDescription=(TextView)findViewById(R.id.Description);
        TDoctor=(TextView)findViewById(R.id.DoctorName);
        THospital=(TextView)findViewById(R.id.HospitalName);
        TCurrentIssue=(TextView)findViewById(R.id.Issue);
        TLast=(TextView)findViewById(R.id.LastReport);
        TMust=(TextView)findViewById(R.id.MustReport);
    }

    private void ListItems() {
        TDescription.setText(user.getDescription());
        TDoctor.setText(user.getDocotorName());
        THospital.setText(user.getHospitalName());
        TCurrentIssue.setText(user.getCurrentIssue());
        TLast.setText(user.getLastReport());
        TMust.setText(user.getMustReport());

    }


}
