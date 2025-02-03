package com.example.ex01_recycleview.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView; // Correct import

import com.example.ex01_recycleview.activitys.CustomeAdapter;
import com.example.ex01_recycleview.activitys.DataModel;
import com.example.ex01_recycleview.activitys.MyData;
import com.example.ex01_recycleview.R;

import java.util.ArrayList;

public class oneFragment extends Fragment {

    private ArrayList<DataModel> dataset;
    private RecyclerView recyclerView;
    private CustomeAdapter adapter;
    private SearchView searchView;

    public oneFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        recyclerView = view.findViewById(R.id.recycleViewResult);
        searchView = view.findViewById(R.id.searchView); // No casting needed

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        dataset = new ArrayList<>();

        for (int i = 0; i < MyData.nameArray.length; i++) {
            dataset.add(new DataModel(
                    MyData.nameArray[i],
                    MyData.drawbleArray[i],
                    MyData.descArray[i],
                    MyData.id_[i]
            ));
        }

        adapter = new CustomeAdapter(dataset, getContext());
        recyclerView.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return view;
    }
}
