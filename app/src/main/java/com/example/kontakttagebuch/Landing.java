package com.example.kontakttagebuch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Landing extends Fragment {
    private RecyclerView recyclerView;
    private AppViewModel mAppViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final PersonListAdapter adapter = new PersonListAdapter(new PersonListAdapter.PersonDiff());
        recyclerView.setAdapter(adapter);

        mAppViewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);

        mAppViewModel.getAllPersons().observe(getViewLifecycleOwner(), persons -> {
                    // Update the cached copy of the words in the adapter.
             adapter.submitList(persons);
        });

        // Inflate the layout for this fragment
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_new_person).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(Landing.this)
                        .navigate(R.id.action_cardViewFragment_to_addPerson);
            }
        });
        //The following code implements the changing of action after pressing a button
        view.findViewById(R.id.button_new_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(Landing.this)
                        .navigate(R.id.action_cardViewFragment_to_addContact);
            }
        });
    }
}