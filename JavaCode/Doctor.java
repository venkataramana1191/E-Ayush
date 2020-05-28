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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends AppCompatActivity {
    private List<PatientListIcons> adding=new ArrayList<PatientListIcons>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        SetTitles();
        Action();
    }
    private void SetTitles() {
        adding.add(new PatientListIcons("Profile",R.drawable.user123));
        adding.add(new PatientListIcons("Appointment",R.drawable.appoint));
        adding.add(new PatientListIcons("Suggestion",R.drawable.chat));
        adding.add(new PatientListIcons("SignOut",R.drawable.signout));
        ArrayAdapter<PatientListIcons> adapter=new CustomList();
        ListView listView=(ListView)findViewById(R.id.listdoctor);
        listView.setAdapter(adapter);
    }


    private class CustomList extends ArrayAdapter<PatientListIcons> {
        public CustomList() {
            super(Doctor.this,R.layout.frame_style,adding);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View itemView=convertView;
            if(itemView == null){
                itemView=getLayoutInflater().inflate(R.layout.frame_style,parent,false);
            }
            PatientListIcons pop=adding.get(position);
            ImageView imageView=(ImageView) itemView.findViewById(R.id.Image1);
            imageView.setImageResource(pop.getIconID());
            TextView title=(TextView)itemView.findViewById(R.id.ListTitle);
            title.setText(pop.getTitle());
            return itemView;
        }
    }
    private void Action() {
        final ListView listView= (ListView)findViewById(R.id.listdoctor);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i)
                {
                    case 0: {
                        startActivity(new Intent(getApplicationContext(),DoctorProfile.class));
                        break;
                    }
                    case 1:{
                        startActivity(new Intent(getApplicationContext(), DoctorAppointment.class));
                        break;
                    }
                    case 2:
                    {
                        startActivity(new Intent(getApplicationContext(), DoctorChat.class));
                        break;
                    }
                    case 3:{
                        startActivity(new Intent(getApplicationContext(),Login.class));
                        finish();
                        break;
                    }
                }
            }
        });
    }

}

