package com.easytask.easytask.frontend.views;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easytask.easytask.R;
import com.easytask.easytask.frontend.controllers.RVAdapter;
import com.easytask.easytask.frontend.controllers.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Silas on 05-11-2017.
 */

public class FindTasksFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Task> taskList;
    private ProgressDialog progressDialog;
    private DatabaseReference database;
    private FirebaseAuth firebaseAuth;
    private RVAdapter adapter;
    private String userId;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_tasks, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        getActivity().setTitle("Alle Opgaver");

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        progressDialog = ProgressDialog.show(getContext(), "Henter alle tilgængelige opgaver", "Vent venligst", false, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);


        taskList = new ArrayList<>();
        adapter = new RVAdapter(taskList);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);


        if(firebaseAuth.getCurrentUser() == null){
            getActivity().finish();
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }else {
            userId = firebaseAuth.getCurrentUser().getUid();
        }


        addDataToRecyclerView();


        database.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                addDataToRecyclerView();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                addDataToRecyclerView();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                addDataToRecyclerView();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                addDataToRecyclerView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    private void addDataToRecyclerView() {

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot db) {

                taskList.clear();

                for (DataSnapshot globalTasks : db.child("tasks").getChildren()) {

                    taskList.add(new Task(globalTasks.child("title").getValue().toString(), globalTasks.child("description").getValue().toString(), globalTasks.getKey(), globalTasks.child("payment").getValue().toString(), globalTasks.child("creatorID").getValue().toString()));

                }

                recyclerView.setAdapter(adapter);
                progressDialog.dismiss();

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
