package com.example.kontakttagebuch;

import android.content.Intent;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

public class CardViewFragment extends Fragment {


    //TODO: 2 Buttons hinzufügen Symptomchecker und ich bin erkrankt
    //TODO: Suchfunktion Letzte treffen
    //Todo: letzte Treffen machen
    //Todo: infos Covid formatieren


    GridLayout mainGrid;
    private AppDatabase db;
    private RecyclerView recyclerView;
    private AppViewModel mAppViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_cardview, container, false);

        //---------------------------------------start Spinner

        Spinner spin = (Spinner) view.findViewById(R.id.spinner_landing);
        ArrayAdapter<CharSequence> adapterSpin = ArrayAdapter.createFromResource(requireActivity(),
                R.array.spinner_landing_array, android.R.layout.simple_spinner_item);
        adapterSpin.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spin.setAdapter(adapterSpin);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //TODO: Add what happens if selected
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //----------------------------------------start Recycler View
        recyclerView = view.findViewById(R.id.recyclerview);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        final PersonListAdapter adapter = new PersonListAdapter(new PersonListAdapter.PersonDiff());
        recyclerView.setAdapter(adapter);

        mAppViewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);

        mAppViewModel.getAllPersons().observe(getViewLifecycleOwner(), persons -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(persons);
        });

        mainGrid = (GridLayout) view.findViewById(R.id.mainGrid);

        //Set Event
        setSingleEvent(mainGrid);
       // setToggleEvent(mainGrid);

        // Inflate the layout for this fragment
        return view;
    }

    private void setToggleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
                       // Toast.makeText(CardViewFragment.this, "State : True", Toast.LENGTH_SHORT).show();

                    } else {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                      //  Toast.makeText(CardViewFragment.this, "State : False", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    switch (finalI) {
                        case 0:  NavHostFragment.findNavController(CardViewFragment.this)
                                .navigate(R.id.action_cardViewFragment_to_addPerson);;
                            break;
                        case 1:  NavHostFragment.findNavController(CardViewFragment.this)
                                .navigate(R.id.action_cardViewFragment_to_addContact);;
                            break;
                        case 2: NavHostFragment.findNavController(CardViewFragment.this)
                                .navigate(R.id.action_cardViewFragment_to_showInfo);;
                            break;
                        case 3:  NavHostFragment.findNavController(CardViewFragment.this)
                                .navigate(R.id.action_cardViewFragment_to_showContacts);;
                            break;
                        case 4:
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://symptomchecker.fsw.at/")));
                            break;
                        case 5:  NavHostFragment.findNavController(CardViewFragment.this)
                                .navigate(R.id.action_cardViewFragment_to_testedPositive);;
                            break;
                    }
//                    Intent intent = new Intent(getActivity().getApplication(),ActivityOne.class);
//                    intent.putExtra("info","This is activity from card item index  "+finalI);
//                    startActivity(intent);

                }
            });
        }
    }
}
