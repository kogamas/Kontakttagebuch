package com.example.kontakttagebuch;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddContact#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddContact extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    //private AppRepository mAppRepository;
    private PersonDao mPersonDao;
    private LiveData<List<NameTuple>> mNameTupleLiveDataList;
    private List<NameTuple> mNameTupleList;
    private AppRepository mRepository;
    private AppViewModel mAppViewModel;


    //String[] persons = mAppRepository.getAllPersonNames();
    String[] persons = {"Hans Heinrich", "Berta von Suttner", "Henry Dunant"};


    public AddContact() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddContact.
     */
    // TODO: Rename and change types and number of parameters
    public static AddContact newInstance(String param1, String param2) {
        AddContact fragment = new AddContact();
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
            case R.id.button_back_to_main2:
                // do your code
                Log.d("ONCLICK", "onClick case backButton is executed!");
                NavHostFragment.findNavController(AddContact.this)
                        .navigate(R.id.action_addContact_to_Landing);
                break;
            case R.id.send_add_contacts:
                Log.d("ONCLICK", "onClick case newContact is executed!");
                // do your code
                break;
            default:
                break;
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_contact, container, false);
        Spinner spin = (Spinner) view.findViewById(R.id.spinner);
        mAppViewModel = new ViewModelProvider(getActivity()).get(AppViewModel.class);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item) ;
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        //do following inside observer or it wont be done
        mAppViewModel.getAllPersonNames().observe(getViewLifecycleOwner(), persons -> {
            mNameTupleList = persons;

            List<String> nameTupleStringList= new ArrayList<>(mNameTupleList.size());
            //this converts the nameTuple list to a String list
            for (NameTuple currentTuple : mNameTupleList) {
                nameTupleStringList.add(currentTuple.toString());
            }
            adapter.addAll(nameTupleStringList);
            spin.setAdapter(adapter);
                });




        // Inflate the layout for this fragment
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("ONCREATED", "onCreated AddPerson is executed!");

        Button one = (Button) view.findViewById(R.id.button_back_to_main2);
        one.setOnClickListener(this); // calling onClick() method
        Button two = (Button) view.findViewById(R.id.send_add_contacts);
        two.setOnClickListener(this);
    }


    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getActivity(), "Selected User: " ,Toast.LENGTH_SHORT).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO - Custom Code
    }
}