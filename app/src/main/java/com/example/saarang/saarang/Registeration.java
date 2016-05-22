package com.example.saarang.saarang;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Keerthi Suresh on 22-05-2016.
 */
public class Registeration extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_registeration, container, false);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.Events, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        Spinner spinner = (Spinner) v.findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner) v.findViewById(R.id.spinner2);
        Spinner spinner3 = (Spinner) v.findViewById(R.id.spinner3);
        Spinner spinner4 = (Spinner) v.findViewById(R.id.spinner4);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);
        spinner4.setAdapter(adapter);
        spinner.setAdapter(adapter);
        return v;
    }
}