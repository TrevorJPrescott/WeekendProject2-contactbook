package com.trevorpc.weekendwarrior2_contactbook;

import android.content.Intent;
import android.database.Cursor;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;

import java.util.ArrayList;

import static com.trevorpc.weekendwarrior2_contactbook.Constants.ADDRESS;
import static com.trevorpc.weekendwarrior2_contactbook.Constants.FIRST_NAME;
import static com.trevorpc.weekendwarrior2_contactbook.Constants.LAST_NAME;
import static com.trevorpc.weekendwarrior2_contactbook.Constants.PHONE;
import static com.trevorpc.weekendwarrior2_contactbook.Constants.PRIMARY_KEY_EMAIL;
import static com.trevorpc.weekendwarrior2_contactbook.Constants.SKYPE;
import static com.trevorpc.weekendwarrior2_contactbook.Constants.TABLE_NAME;


public class Display extends AppCompatActivity
{

    SQLhelper sqlz = new SQLhelper(this,null);
    EditText etSearch;






    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);
        RecyclerView recyclerView = findViewById(R.id.rvView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        ArrayList<ContactInfo> list = contactBook();
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, list);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);

        etSearch = findViewById(R.id.etDisplay);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

//        sqlz.insert("trevor@prescotts.net","Trevor","Prescott","19103 Yearling Meadows"
//                ,"prescotttj93.skype","713-405-0445");
//        sqlz.insert("prescotttj93@gmail.com","Trevor","Prescott","19103 Yearling Meadows"
//                ,"tprescott.skype","713-405-0445");


    }

    private void filter(String text)
    {
        ArrayList<ContactInfo> filteredList = new ArrayList<>();

        for (ContactInfo item : contactBook())
        {
            if(item.firstName.toLowerCase().contains(text.toLowerCase()))
            {
                filteredList.add(item);
            }
        }

        RecyclerView recyclerView = findViewById(R.id.rvView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, filteredList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);



    }

    // Note, I probably didn't need an array list

    public ArrayList<ContactInfo> contactBook() {

        ArrayList<ContactInfo> returnList = new ArrayList<>();
        Cursor cur = sqlz.getAllContacts();
        Log.d("The sound of Starbucks", "contactBook:  We got this far");
        while(cur.moveToNext())
        {
            returnList.add(new ContactInfo(
                    cur.getString(cur.getColumnIndex(FIRST_NAME)),
                    cur.getString(cur.getColumnIndex(LAST_NAME)),
                    cur.getString(cur.getColumnIndex(ADDRESS)),
                    cur.getString(cur.getColumnIndex(PHONE)),
                    cur.getString(cur.getColumnIndex(SKYPE)),
                    cur.getString(cur.getColumnIndex(PRIMARY_KEY_EMAIL))));
        }
        cur.close();

        return returnList;
    }

    public void toInputScreen(View view)
    {
        Intent toInput = new Intent(this,InputScreen.class);
        startActivity(toInput);
    }

//    public ArrayList<ContactInfo> Search()
//    {
//        ArrayList<ContactInfo> returnList1 = new ArrayList<>();
//        Cursor cur = sqlz.getContactsByFirstName(etSearch.getText().toString());
//        Log.d("The sound of Starbucks", "contactBook:  We got this far");
//        while(cur.moveToNext())
//        {
//            returnList1.add(new ContactInfo(
//                    cur.getString(cur.getColumnIndex(FIRST_NAME)),
//                    cur.getString(cur.getColumnIndex(LAST_NAME)),
//                    cur.getString(cur.getColumnIndex(ADDRESS)),
//                    cur.getString(cur.getColumnIndex(PHONE)),
//                    cur.getString(cur.getColumnIndex(SKYPE)),
//                    cur.getString(cur.getColumnIndex(PRIMARY_KEY_EMAIL))));
//        }
//        cur.close();
//        Log.d("TAG", "Search: "+returnList1);
//
//        return returnList1;
//
//
//    }

    public void toSpecific(View view)
    {
        Intent toSpec = new Intent(this, Specific.class);
        toSpec.putExtra("EMAIL",view.getTag().toString());
        Log.d("TAG", "toSpecific: end");
        startActivity(toSpec);
    }


}
