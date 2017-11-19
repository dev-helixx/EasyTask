package com.easytask.easytask.frontend.views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.easytask.easytask.R;
import com.easytask.easytask.frontend.controllers.RVAdapter;
import com.easytask.easytask.frontend.controllers.Task;
import com.google.android.gms.tasks.Tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Silas on 05-11-2017.
 */

public class FindTasksFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayout recyclerViewLayout;
    private List<Task> tasks;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_tasks, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        initializeCardViewData();

        recyclerViewLayout = (LinearLayout) view.findViewById(R.id.recyclerviewLayout);

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);

        RVAdapter adapter = new RVAdapter(tasks);
        recyclerView.setAdapter(adapter);
    }

    private void initializeCardViewData(){

        tasks = new ArrayList<>();

        tasks.add(new Task("Slå græs", "Jeg skal have slået min græsplæne"));
        tasks.add(new Task("Vaske bil", "Min bil skal vaskes!"));
        tasks.add(new Task("Vaske terasse", "Skal vaskes hurtigt, er meget beskidt"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));
        tasks.add(new Task("Hjælpe med indkøb", "Har ikke tid til at gøre det selv"));



    }


}
