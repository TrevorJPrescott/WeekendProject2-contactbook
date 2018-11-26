package com.trevorpc.weekendwarrior2_contactbook;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import static com.trevorpc.weekendwarrior2_contactbook.Constants.ADDRESS;
import static com.trevorpc.weekendwarrior2_contactbook.Constants.FIRST_NAME;
import static com.trevorpc.weekendwarrior2_contactbook.Constants.LAST_NAME;
import static com.trevorpc.weekendwarrior2_contactbook.Constants.PHONE;
import static com.trevorpc.weekendwarrior2_contactbook.Constants.PRIMARY_KEY_EMAIL;
import static com.trevorpc.weekendwarrior2_contactbook.Constants.SKYPE;
import static com.trevorpc.weekendwarrior2_contactbook.Constants.TABLE_NAME;


public class Specific extends AppCompatActivity {

    TextView tvFirst;
    TextView tvLast;
    TextView tvEmail;
    TextView tvPhone;
    TextView tvAddress;
    TextView tvSkype;

    SQLhelper sqlz = new SQLhelper(this,null);




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d("TAG", "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific);

        tvFirst = findViewById(R.id.tvFirstName);
        tvLast = findViewById(R.id.tvLastName);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);
        tvAddress = findViewById(R.id.tvAddress);
        tvSkype = findViewById(R.id.tvSkypeID);




        Intent toSpec = getIntent();
        String emailer = toSpec.getStringExtra("EMAIL");
        Log.d("TAG", "onCreate: "+emailer);
        Cursor cur= sqlz.getContactsByEmail(emailer);
        cur.moveToFirst();

        tvFirst.setText(cur.getString(cur.getColumnIndex(FIRST_NAME)));
        tvLast.setText(cur.getString(cur.getColumnIndex(LAST_NAME)));
        tvAddress.setText(cur.getString(cur.getColumnIndex(ADDRESS)));
        tvPhone.setText(cur.getString(cur.getColumnIndex(PHONE)));
        tvSkype.setText(cur.getString(cur.getColumnIndex(SKYPE)));
        tvEmail.setText(cur.getString(cur.getColumnIndex(PRIMARY_KEY_EMAIL)));


    }

    public void Delete(View view)
    {
        String temp=tvEmail.getText().toString();
        Log.d("TAG", "Delete: "+temp);
        sqlz.deleteContact(temp);
        Log.d("TAG", "Delete: after boolean");

        Intent toInput = new Intent(this,Display.class);
        startActivity(toInput);


    }

    public void toDisplay(View view) {
        Intent toInput = new Intent(this, Display.class);
        startActivity(toInput);
    }

    public void startCall(View view)
    {
        Log.d("TAG", "startCall: ");
        String phoneNumber = String.format("tel: %s",tvPhone.getText().toString());

        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        // Set the data for the intent as the phone number.
        dialIntent.setData(Uri.parse(phoneNumber));
        // If package resolves to an app, send intent.
        if (dialIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(dialIntent);
        } else {
            Log.e("TAG", "Can't resolve app for ACTION_DIAL Intent.");
        }
    }


    public void startEmail(View view) {
        Log.d("TAG", "startEmail: ");
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"+tvEmail.getText().toString()));
        startActivity(emailIntent);
    }
}
