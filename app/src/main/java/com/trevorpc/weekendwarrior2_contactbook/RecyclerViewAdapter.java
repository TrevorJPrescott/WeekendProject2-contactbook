package com.trevorpc.weekendwarrior2_contactbook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    Context context;
    ArrayList<ContactInfo> list;


    public RecyclerViewAdapter(Context context, ArrayList<ContactInfo> passedList)
    {
        this.context = context;
        this.list = passedList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewHolder viewHolder = new ViewHolder(inflater.inflate(R.layout.input,viewGroup,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i)
    {
        viewHolder.tvFirstName.setText(list.get(i).firstName);
        viewHolder.tvFirstName.setTag(list.get(i).email);
        viewHolder.tvLastName.setText(list.get(i).lastName);
        viewHolder.tvLastName.setTag(list.get(i).email);
        viewHolder.tvAddress.setText(list.get(i).address);
        viewHolder.tvPhone.setText(list.get(i).phone);
        viewHolder.tvSkypeID.setText(list.get(i).skype);
        viewHolder.tvEmail.setText(list.get(i).email);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvFirstName;
        TextView tvLastName;
        TextView tvAddress;
        TextView tvPhone;
        TextView tvSkypeID;
        TextView tvEmail;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tvFirstName = itemView.findViewById(R.id.tvFirstName);
            tvLastName = itemView.findViewById(R.id.tvLastName);
            tvAddress = itemView.findViewById((R.id.tvAddress));
            tvPhone = itemView.findViewById(R.id.tvPhone);
            tvSkypeID = itemView.findViewById(R.id.tvSkypeID);
            tvEmail = itemView.findViewById(R.id.tvEmail);

        }
    }
}
