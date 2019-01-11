package com.example.pc.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class one extends Fragment {
    RecyclerView personrecyclerv;
    List<PersonUtils> personUtilsList;
    CustomRecyclerAdapter crAdapter;


    public one() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        personutilsf();
        personrecyclerv = (RecyclerView) view.findViewById(R.id.recy);

        crAdapter = new CustomRecyclerAdapter(getActivity(), personUtilsList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        personrecyclerv.setLayoutManager(layoutManager);
        personrecyclerv.setAdapter(crAdapter);

        return view;
    }

    void personutilsf() {
        personUtilsList = new ArrayList<>();
        personUtilsList.add(new PersonUtils("1", "one"));
        personUtilsList.add(new PersonUtils("2", "two"));
        personUtilsList.add(new PersonUtils("3", "three"));
        personUtilsList.add(new PersonUtils("4", "four"));
        personUtilsList.add(new PersonUtils("5", "five"));
        personUtilsList.add(new PersonUtils("6", "six"));
        personUtilsList.add(new PersonUtils("7", "seven"));


    }


    }


