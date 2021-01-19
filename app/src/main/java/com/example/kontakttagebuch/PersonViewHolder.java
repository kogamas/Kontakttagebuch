package com.example.kontakttagebuch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private final TextView personItemView;

    private PersonViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        personItemView = itemView.findViewById(R.id.textView_recycler);

    }

    public void bind(String text) {
        personItemView.setText(text);
    }

    static PersonViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new PersonViewHolder(view);
    }
    @Override
    public void onClick(View view) {
        //Todo: add what to do on click
    }
}
