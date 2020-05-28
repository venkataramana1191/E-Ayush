package com.example.cse.ayush;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Login extends AppCompatActivity {
    static String StringLoginMobileNumber=" ",StringPassword;
    EditText LoginMobileNumber,LoginPassword;
    DatabaseReference databaseReference;
    public static UserDetails user;
    public static  List<UserDetails> userlist=new ArrayList<UserDetails>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginPassword=(EditText)findViewById(R.id.Password);
        LoginMobileNumber=(EditText)findViewById(R.id.EdittextLoginMobileNo);
        databaseReference= FirebaseDatabase.getInstance().getReference("UserDetails");
    }

    public void ButtonLoginToApp(View view) {
        StringLoginMobileNumber=LoginMobileNumber.getText().toString().trim();
        StringPassword=LoginPassword.getText().toString().trim();
        if(StringLoginMobileNumber.length()==10)
        {
            Iterator it=userlist.iterator();
            UserDetails userDetails=null;
            while(it.hasNext())
            {
                userDetails= (UserDetails) it.next();
                if(((userDetails.getStringMobilNo()).equals(StringLoginMobileNumber)) && ((userDetails.getStringPassword()).equals(StringPassword)))
                {

                    user=userDetails;
                    if(user.getStringType().equals("Patient")) {
                        startActivity(new Intent(getApplicationContext(),Patient.class));
                        finish();
                        break;
                    }
                    else if(user.getStringType().equals("Doctor")){
                        startActivity(new Intent(getApplicationContext(),Doctor.class));
                        finish();
                        break;
                    }
                    else if (user.getStringType().equals("Worker")){
                        startActivity(new Intent(getApplicationContext(),Worker.class));
                        finish();
                        break;
                    }
                    else if(user.getStringType().equalsIgnoreCase("Employ")){
                        startActivity(new Intent(getApplicationContext(),Employ.class));
                        finish();
                        break;
                    }

                }
            }

        }else
        {
            Toast toast=Toast.makeText(getApplicationContext(),"Enter Correct Mobile Number/Password ",Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP,Gravity.CENTER,4);
            toast.show();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()){
                  UserDetails userDetails= userSnapshot.getValue(UserDetails.class);
                  userlist.add(userDetails);
                }
                Toast toast1 = Toast.makeText(getApplicationContext(),"Internet connected", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.TOP, Gravity.CENTER, 4);
                toast1.show();

        }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void ForgotPasword(View view) {
        Alert("Enter the Mobile Number");
        if(StringLoginMobileNumber.length() ==10)
        {
            //otp code
        }
    }
    public void Alert(String title) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Login.this);
        alertDialog.setTitle("\t" + title + "");
        final EditText input = new EditText(Login.this);
        input.setText(StringLoginMobileNumber);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);
        alertDialog.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                StringLoginMobileNumber = input.getText().toString().trim();
            }
        });
        alertDialog.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        alertDialog.show();
    }

}
