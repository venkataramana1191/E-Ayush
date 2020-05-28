package com.example.cse.ayush;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.cse.ayush.Login.StringLoginMobileNumber;
import static com.example.cse.ayush.Login.user;

public class Patient extends AppCompatActivity {

    NotificationCompat.Builder notification;
    private static final int id=10001;
    private List<PatientListIcons> listicon= new ArrayList<PatientListIcons>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        notification=new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);
        SetTitles();
        Action();
        Note();
    }

    private void Note() {
        Date today= Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd MMM yyyy");
        String today1=simpleDateFormat.format(today);
        //Toast.makeText(getApplicationContext(),String.valueOf(mo),Toast.LENGTH_SHORT).show();
        if(user.getMustReport().equalsIgnoreCase("5 Aug 2018")){
            notification.setSmallIcon(R.drawable.alert);
            long vb[]={500,500,500,500,500,500,500,500,500};
            // notification.setSound(R.drawable.)
            notification.setWhen(System.currentTimeMillis());
            notification.setVibrate(vb);
            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            notification.setSound(alarmSound);
            notification.setContentTitle("Report Date");
            notification.setContentText("You Should Report to hospital On "+user.getMustReport());
            Intent intent=new Intent(getApplicationContext(),PatientCurrent.class);
            PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            notification.setContentIntent(pendingIntent);
            NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(id,notification.build());
        }
    }

    private void SetTitles() {
       listicon.add(new PatientListIcons("Profile",R.drawable.user123));
        listicon.add(new PatientListIcons("CurrentSession",R.drawable.cses));
        listicon.add(new PatientListIcons("Appointment",R.drawable.appoint));
        listicon.add(new PatientListIcons("Medicines",R.drawable.tablets));
        listicon.add(new PatientListIcons("Suggestion",R.drawable.chat));
        listicon.add(new PatientListIcons("SignOut",R.drawable.signout  ));
        ArrayAdapter<PatientListIcons> adapter =new MyListViewAdapter();

        ListView listView= (ListView)findViewById(R.id.PatientListView);
        listView.setAdapter(adapter);
    }
    private void Action() {
        final ListView listView= (ListView)findViewById(R.id.PatientListView);
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               switch(i)
               {
                   case 0: {
                       startActivity(new Intent(getApplicationContext(), PatientProfile.class));
                       break;
                   }
                   case 1:{
                       startActivity(new Intent(getApplicationContext(), PatientCurrent.class));
                       break;
                   }
                   case 2:
                   {
                       startActivity(new Intent(getApplicationContext(), PatientAppointment.class));
                       break;
                   }
                   case 3:{
                       startActivity(new Intent(getApplicationContext(), PatientMedicine.class));
                       break;
                   }
                   case 4:{
                       startActivity(new Intent(getApplicationContext(),PatientChat.class));
                       break;
                   }
                   case 5:{
                       StringLoginMobileNumber=" ";
                       startActivity(new Intent(getApplicationContext(),StartActivity1.class));
                       finish();
                       break;
                   }
               }
           }
       });
    }
    class MyListViewAdapter extends ArrayAdapter<PatientListIcons> {

        public MyListViewAdapter() {
            super(Patient.this,R.layout.frame_style,listicon);

        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View itemView =convertView;
            if(itemView==null) {
                itemView=getLayoutInflater().inflate(R.layout.frame_style,parent,false);
            }
            PatientListIcons current= listicon.get(position);
          ImageView imageView=(ImageView)itemView.findViewById(R.id.Image1);
           imageView.setImageResource(current.getIconID());
            TextView title=(TextView)itemView.findViewById(R.id.ListTitle);
            title.setText(current.getTitle());
            return itemView;
        }
    }
}
