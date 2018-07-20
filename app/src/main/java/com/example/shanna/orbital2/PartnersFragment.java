package com.example.shanna.orbital2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PartnersFragment extends Fragment {


    public PartnersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        /*
        View view =  inflater.inflate(R.layout.activity_partners_fragment, container, false);

        String[] menuItems = {"Do something", "Do something else","Hi"};

        //Look within this layout for the list view
        //by its id
        ListView listView = (ListView) view.findViewById(R.id.RequestsMenu);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                menuItems

        );

        listView.setAdapter(listViewAdapter);
        // Inflate the layout for this fragment

        return view;

        */
        return  inflater.inflate(R.layout.activity_partners_fragment, container, false);
    }

}