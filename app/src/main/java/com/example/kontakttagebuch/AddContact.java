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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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


    private EditText editTextTime;
    private EditText editTextDate;
    private EditText editTextTextMultiLine;
    private RadioButton radio1;
    private RadioButton radio2;
    private RadioButton radio3;
    private int radioType;
    private Calendar calendar = Calendar.getInstance();
    private Date time = calendar.getTime();;
    private Date date;
    private int mPid =-1;



    public AddContact() {
        // Required empty public constructor
        calendar.setTimeInMillis(System.currentTimeMillis());
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
                        .navigate(R.id.action_addContact_to_cardViewFragment);
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
        View view = inflater.inflate(R.layout.fragment_add_contact, container, false);
        Spinner spin = (Spinner) view.findViewById(R.id.spinner);
        mAppViewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);

        DateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");
        DateFormat timeFormat = new SimpleDateFormat("hh:mm");
        Date dateNow = calendar.getTime();

        String strDate = dateFormat.format(dateNow);
        String strTime = timeFormat.format(dateNow);

        editTextTime = view.findViewById(R.id.editTextTime);
        editTextTime.setText(strTime);
        editTextDate = view.findViewById(R.id.editTextDate);
        editTextDate.setText(strDate);
        editTextTextMultiLine = view.findViewById(R.id.editTextTextMultiLine);
        radio1 = view.findViewById(R.id.radioK1);
        radio2 = view.findViewById(R.id.radioK2);
        radio3 = view.findViewById(R.id.radioExtra);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item) ;
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        //manage buttons
        radio1 = view.findViewById(R.id.radioK1);
        radio2 = view.findViewById(R.id.radioK2);
        radio3 = view.findViewById(R.id.radioExtra);

        Button one = view.findViewById(R.id.button_back_to_main2);
        one.setOnClickListener(this); // calling onClick() method

        Button two = view.findViewById(R.id.send_add_contacts);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radio1.isChecked()) {
                    radioType = 1;
                } else if (radio2.isChecked()) {
                    radioType = 2;
                } else if (radio3.isChecked()) {
                    radioType = 3;
                }

                Log.d("ONCLICK", "onClick case newContact is executed!");
                //int pid = firstnameEditText.getText().toString();
              //  Log.d("FIRSTNAME", pid);  //hauts in die DB


                String timeStr = editTextTime.getText().toString();
                DateFormat formatter = new SimpleDateFormat("hh:mm a");
                try {
                    Date time = formatter.parse(timeStr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String extra = editTextTextMultiLine.getText().toString();
                Log.d("Text", extra);

                if (mPid!=-1) {
                    mAppViewModel = new ViewModelProvider(requireActivity()).get(AppViewModel.class);
                    Contact contact = new Contact(mPid,time,radioType,extra);
                    mAppViewModel.insertContact(contact);//do nothing, because it would only add an empty person
                    //after dealing with the input sends user back to landing page
                    NavHostFragment.findNavController(AddContact.this)
                            .navigate(R.id.action_addContact_to_cardViewFragment);
                } else {
                    //do nothing, because it would only add an empty person
                }

            }
        });

        //do following inside observer or it wont be done
        mAppViewModel.getAllPersonNames().observe(getViewLifecycleOwner(), persons -> {
            mNameTupleList = persons;

            List<String> nameTupleStringList= new ArrayList<>(mNameTupleList.size());
            List<Integer> pidList= new ArrayList<>(mNameTupleList.size());
            //this converts the nameTuple list to a String list
            for (NameTuple currentTuple : mNameTupleList) {
                nameTupleStringList.add(currentTuple.toString());
                pidList.add(currentTuple.getPid());
            }
            adapter.addAll(nameTupleStringList);
            spin.setAdapter(adapter);
            spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    mPid = pidList.get(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        });




        // Inflate the layout for this fragment
        return view;
    }



    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getActivity(), "Selected User: " ,Toast.LENGTH_SHORT).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO - Custom Code
    }
}