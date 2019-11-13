package com.example.cats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class FaveRecyclerFragment extends Fragment {

    private RecyclerView faveRecyclerView;
    public static ArrayList<Cats> favourites = new ArrayList<>();

    public FaveRecyclerFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final FaveAdapter faveAdapter = new FaveAdapter();


        //set Recycler View
        View view = inflater.inflate(R.layout.fragment_fave_recycler, container, false);
        faveRecyclerView = view.findViewById(R.id.rv_fave);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        faveRecyclerView.setLayoutManager(layoutManager);
        faveAdapter.setData(favourites);
        faveRecyclerView.setAdapter(faveAdapter);
        return view;
    }

}
