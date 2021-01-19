package com.example.kontakttagebuch;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;



public class ContactListAdapter extends ListAdapter<ContactWithName, ContactViewHolder> {
    private AppViewModel mAppViewModel;

    public ContactListAdapter(@NonNull ContactDiff diffCallback) {
        super(diffCallback);
       // mAppViewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ContactViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        ContactWithName current = getItem(position);
        holder.bindPerson(current.getName());
        holder.bindTime(current.getTimeOfContact().toString());

        switch (current.getTypeOfContact()) {
            case 1:
                holder.bindType("K1");
                break;
            case 2:
                holder.bindType("K2");
                break;
            case 3:
                holder.bindType("Sonstige");
                break;
        }

    }


    static class ContactDiff extends DiffUtil.ItemCallback<ContactWithName> {

        @Override
        public boolean areItemsTheSame(@NonNull ContactWithName oldItem, @NonNull ContactWithName newItem) {
            return oldItem==newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ContactWithName oldItem, @NonNull ContactWithName newItem) {
            return oldItem.getName().equals(newItem.getName());
        }

    }
    private OnEntryClickListener mOnEntryClickListener;

    public interface OnEntryClickListener {
        void onEntryClick(View view, int position);
    }

    public void setOnEntryClickListener(OnEntryClickListener onEntryClickListener) {
        mOnEntryClickListener = onEntryClickListener;
    }
}
