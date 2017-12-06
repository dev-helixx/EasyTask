package com.easytask.easytask.frontend.views;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.easytask.easytask.R;
import com.easytask.easytask.frontend.controllers.LVAdapter;
import com.easytask.easytask.frontend.controllers.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * Created by Silas on 15-11-2017.
 */
public class MyTasksFragment extends Fragment {

    private ListView my_tasks_listview;
    private DatabaseReference userRef, taskRef, test, database;
    private FirebaseAuth firebaseAuth;
    private String userId;
    private List<Task> taskList, descriptionArray;
    private ProgressDialog progressDialog;
    private LVAdapter myTasksAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_tasks, container, false);
        return view;

    }


    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {

        getActivity().setTitle("Mine Opgaver");

        progressDialog = ProgressDialog.show(getContext(), "Henter alle dine opgaver", "Vent venligst", false, false);

        taskList = new ArrayList<>();
        myTasksAdapter = new LVAdapter(getActivity(), taskList);


        my_tasks_listview = (ListView) view.findViewById(R.id.my_tasks_listview);

        firebaseAuth = FirebaseAuth.getInstance();
        test = FirebaseDatabase.getInstance().getReference();
        userRef = FirebaseDatabase.getInstance().getReference();
        taskRef = FirebaseDatabase.getInstance().getReference();
        database = FirebaseDatabase.getInstance().getReference();

        if(firebaseAuth.getCurrentUser() == null){
            getActivity().finish();
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
        else {
            userId = firebaseAuth.getCurrentUser().getUid();
        }


        addDataToListView();



        database.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot taskData, String s) {
                addDataToListView();
            }

            @Override
            public void onChildChanged(DataSnapshot taskData, String s) {
                addDataToListView();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                addDataToListView();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                addDataToListView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    private void addDataToListView() {


        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot taskData) {

                myTasksAdapter.clear();

                for(DataSnapshot globalTasks : taskData.child("tasks").getChildren()) {

                    for(DataSnapshot userTasks : taskData.child("users").child(userId).child("tasks").getChildren())


                        if (globalTasks.getKey().equals(userTasks.getKey())) {

//                            taskList.add(new Task(globalTasks.child("title").getValue().toString(), globalTasks.child("description").getValue().toString(), globalTasks.getKey(), Integer.parseInt(globalTasks.child("payment").getValue().toString()), globalTasks.child("")));
                            taskList.add(new Task(globalTasks.child("title").getValue().toString(), globalTasks.child("description").getValue().toString(), globalTasks.getKey(), globalTasks.child("payment").getValue().toString(), globalTasks.child("creatorID").getValue().toString()));

                        }
                }



                my_tasks_listview.setAdapter(myTasksAdapter);
                progressDialog.dismiss();

            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
