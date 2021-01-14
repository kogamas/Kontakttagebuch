package com.example.kontakttagebuch;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

public class AddPerson extends Fragment implements View.OnClickListener{

    private AppDatabase db;
    private EditText firstnameEditText;
    private EditText lastnameEditText;
    private EditText phoneEditText;
    private EditText emailEditText;
    private EditText textEditText;
    private AppViewModel mAppViewModel;



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        Log.d("ONCREATE", "onCreate AddPerson is executed!");



        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_person, container, false);
    }

    @Override
    public void onClick(View view) {
        Log.d("ONCLICK", "onClick switch AddPerson is executed!");
        // default method for handling onClick Events..
        switch (view.getId()) {
            case R.id.button_back_to_main:
                // do your code
                Log.d("ONCLICK", "onClick case backButton is executed!");
                NavHostFragment.findNavController(AddPerson.this)
                        .navigate(R.id.action_addPerson_to_Landing);
                break;
            case R.id.send_add_person:
                Log.d("ONCLICK", "onClick case newPerson is executed!");
                // do your code
                String firstname = firstnameEditText.getText().toString();
                Log.d("FIRSTNAME", firstname);  //hauts in die DB
                String lastname = lastnameEditText.getText().toString();
                Log.d("LASTNAME", lastname);
                String phone = phoneEditText.getText().toString();
                Log.d("PHONE", phone);
                String email = emailEditText.getText().toString();
                Log.d("EMAIL", email);
                String text = textEditText.getText().toString();
                Log.d("Text", text);

                if (!firstname.isEmpty()) {
                    mAppViewModel = new ViewModelProvider(this).get(AppViewModel.class);
                    Person person = new Person(firstname,lastname,phone,email,text);
                    mAppViewModel.insertPerson(person);//do nothing, because it would only add an empty person
                //TODO: because the insertion is done in another thread navigate action is called before data is inserted, which leeds to new data not beeing displayed in landing
                } else {
                    //do nothing, because it would only add an empty person
                }

                //after dealing with the input sends user back to landing page
                NavHostFragment.findNavController(AddPerson.this)
                        .navigate(R.id.action_addPerson_to_Landing);
                break;
            default:
                break;
        }
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("ONCREATED", "onCreated AddPerson is executed!");

        Button one = (Button) view.findViewById(R.id.button_back_to_main);
        one.setOnClickListener(this); // calling onClick() method
        Button two = (Button) view.findViewById(R.id.send_add_person);
        two.setOnClickListener(this);


        firstnameEditText = (EditText) view.findViewById(R.id.editTextFirstname);
        lastnameEditText = (EditText) view.findViewById(R.id.editTextLastname);
        phoneEditText = (EditText) view.findViewById(R.id.editTextPhone);
        emailEditText = (EditText) view.findViewById(R.id.editTextEmail);
        textEditText = (EditText) view.findViewById(R.id.editTextText);

/*
        view.findViewById(R.id.button_back_to_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ONCLICK", "onClick old AddPerson is executed!");
                NavHostFragment.findNavController(AddPerson.this)
                        .navigate(R.id.action_addPerson_to_Landing);
            }
        });

 */
    }

}