package com.example.cse.ayush;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.HorizontalScrollView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

import static com.example.cse.ayush.Login.user;
import static com.example.cse.ayush.Login.userlist;

public class PatientAppointment extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,AdapterView.OnItemSelectedListener{
    TimePicker  timePicker;
    Spinner spinner;
    String Client,Date1;
    String HOUR,MIN;
    int year,month,dat;
    DoctorAppointListStatus doctorAppointListStatus;
    DatabaseReference databaseReference;
    String doctors[]={"Dr Ram","Dr Rajesh","Dr Lakshmi","Dr Vani","Dr Sridhar"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_appointment);
        //GetDoctor();
        databaseReference= FirebaseDatabase.getInstance().getReference("AppointMent");

        spinner=(Spinner)findViewById(R.id.spinner);
        SetSpinner();
        timePicker =(TimePicker)findViewById(R.id.timepicker);
        SetTimer();


    }

    private void SetTimer() {
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                if(i>=1 && i<=9) HOUR="0"+i;
                else HOUR=""+i;
                if(i1>=1 && i1<=9) MIN="0"+i1;
                else MIN=""+i1;
            }
        });
    }

    private void SetSpinner() {
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,doctors);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);
    }

   /* private void GetDoctor() {
        Iterator it=userlist.iterator();
        UserDetails userDetails=null;
        int i=0;
        while(it.hasNext())
        {
            userDetails=(UserDetails)it.next();
            if(userDetails.getStringType().equalsIgnoreCase("Doctor")){
                doctors[i]=userDetails.getStringName();
                i++;
            }
        }
    }*/

    public void SetDate(View view) {
       DatePickerFragment fragment =new DatePickerFragment();
       fragment.show(getSupportFragmentManager(),"Data");

    }
    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        //Calendar calendar=new GregorianCalendar(i,i1,i2);
        year=i;
        month=i1;
        dat=i2;
        if(dat>=1 && dat<=9) Date1="0"+dat+"-";
        else Date1=dat+"-";
        if(month>=1 && month<=9) Date1=Date1+"0"+month+"-";
        else Date1+=month+"-";
        Date1+=""+year;
        //Toast.makeText(getApplicationContext(),""+i+" "+i1+" "+i2,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            Client=doctors[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void RequestSend(View view) {
        doctorAppointListStatus=new DoctorAppointListStatus(HOUR+":"+MIN,Date1,Client,"0",user.getStringName(),user.getStringMobilNo());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                databaseReference.child(user.getStringName()).setValue(doctorAppointListStatus);
                Toast.makeText(getApplicationContext(),"Request Sended Sucessfully",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Patient.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static class DatePickerFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar=Calendar.getInstance();
            int year=calendar.get(Calendar.YEAR);
            int month=calendar.get(Calendar.MONTH);
            int date=calendar.get(Calendar.DATE);
            return new DatePickerDialog(getActivity(),(DatePickerDialog.OnDateSetListener)getActivity(),year,month,date);
        }
    }
}
