package com.example.kontakttagebuch;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShowContacts#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowContacts extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerView;
    private AppViewModel mAppViewModel;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ShowContacts() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShowContacts.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowContacts newInstance(String param1, String param2) {
        ShowContacts fragment = new ShowContacts();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onClick(View view) {
        Log.d("ONCLICK", "onClick switch addContact is executed!");
        // default method for handling onClick Events..
        switch (view.getId()) {
            case R.id.button_back_to_main3:
                // do your code
                Log.d("ONCLICK", "onClick case backButton is executed!");
                NavHostFragment.findNavController(ShowContacts.this)
                        .navigate(R.id.action_showContacts_to_cardViewFragment);
                break;
            case R.id.send_add_contacts:

                break;
            default:
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_contacts, container, false);

        Button one = view.findViewById(R.id.button_back_to_main3);
        one.setOnClickListener(this); // calling onClick() method

        recyclerView = view.findViewById(R.id.recyclerview_contacts);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        final ContactListAdapter adapter = new ContactListAdapter(new ContactListAdapter.ContactDiff());
        recyclerView.setAdapter(adapter);

        mAppViewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);

        mAppViewModel.getAllContactsWithName().observe(getViewLifecycleOwner(), contacts -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(contacts);
        });

        // Inflate the layout for this fragment
        return view;
    }
}