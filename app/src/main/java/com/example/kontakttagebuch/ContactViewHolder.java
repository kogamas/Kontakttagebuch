package com.example.kontakttagebuch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private final TextView contactItemViewPerson;
    private final TextView contactItemViewType;
    private final TextView contactItemViewTime;

    private ContactViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        contactItemViewPerson = itemView.findViewById(R.id.textView_recycler_contact_person);
        contactItemViewType = itemView.findViewById(R.id.textView_recycler_contact_type);
        contactItemViewTime = itemView.findViewById(R.id.textView_recycler_contact_time);
    }

    public void bindPerson(String text) {contactItemViewPerson.setText(text);}
    public void bindType(String text) {contactItemViewType.setText(text);}
    public void bindTime(String text) {contactItemViewTime.setText(text);}

    static ContactViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_contacts, parent, false);
        return new ContactViewHolder(view);
    }
    @Override
    public void onClick(View view) {
        //Todo: add what to do on click
    }
}
