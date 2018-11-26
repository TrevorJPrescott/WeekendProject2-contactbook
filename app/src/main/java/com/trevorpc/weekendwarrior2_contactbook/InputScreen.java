package com.trevorpc.weekendwarrior2_contactbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class InputScreen extends AppCompatActivity {

    EditText etFirst;
    EditText etLast;
    EditText etEmail;
    EditText etAddress;
    EditText etSkype;
    EditText etPhone;


    SQLhelper sqlz = new SQLhelper(this,null);


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_screen);

        etFirst = findViewById(R.id.etFirst);
        etLast = findViewById(R.id.etLast);
        etEmail = findViewById(R.id.etEmail);
        etAddress = findViewById(R.id.etAddress);
        etSkype = findViewById(R.id.etSkype);
        etPhone = findViewById(R.id.etPhone);



    }


    public void toDisplay(View view) {
        Intent toInput = new Intent(this, Display.class);
        startActivity(toInput);
    }

    public void Submit(View view)
    {
        sqlz.insert(etEmail.getText().toString(),etFirst.getText().toString(),etLast.getText().toString(),etAddress.getText().toString(),
                etSkype.getText().toString(),etPhone.getText().toString());
        toDisplay(view);
    }
}

