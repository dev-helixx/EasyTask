package com.easytask.easytask.frontend.views;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.easytask.easytask.R;
import com.easytask.easytask.frontend.controllers.LVAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyTasksFragment extends Fragment {

    private ListView my_tasks_listview;
    private DatabaseReference userRef, taskRef;
    private FirebaseAuth firebaseAuth;
    private String userId;
    private List<String> subjectArray, descriptionArray;
    private ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_tasks, container, false);
        return view;

    }


    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {

        progressDialog = ProgressDialog.show(getContext(), "Fetching all of your tasks", "Please wait", false, false);

        subjectArray = new ArrayList<String>();
        descriptionArray = new ArrayList<String>();

//        subjectArray.add("Test1");
//        subjectArray.add("Test2");
//        subjectArray.add("Test3");
//
//        descriptionArray.add("Des1");
//        descriptionArray.add("Des2");
//        descriptionArray.add("Des3");


        firebaseAuth = FirebaseAuth.getInstance();
        userRef = FirebaseDatabase.getInstance().getReference();
        taskRef = FirebaseDatabase.getInstance().getReference();

        if(firebaseAuth.getCurrentUser() == null){
            getActivity().finish();
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
        else {
            userId = firebaseAuth.getCurrentUser().getUid();
        }

        taskRef.child("tasks").addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(final DataSnapshot snap1) {


                userRef.child("users").child(userId).child("tasks").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snap2) {

                        // tasks indeholder alle tasks IDs
                        for (DataSnapshot tasks : snap1.getChildren()) {

                            // userTasks indeholder alle brugerens taskIDs
                            for (DataSnapshot userTasks : snap2.getChildren()) {

                                if(userTasks.getKey().equals(tasks.getKey())) {
                                    // In this if statement, add all relevant information to my tasks

                                    subjectArray.add("Task ID " + tasks.getKey());
                                    descriptionArray.add("Beskrivelse af opgaven med ID " + tasks.getKey());

                                }
                            }
                        }

                        LVAdapter myTasksAdapter = new LVAdapter(getActivity(), subjectArray, descriptionArray);
                        my_tasks_listview = (ListView) view.findViewById(R.id.my_tasks_listview);
                        my_tasks_listview.setAdapter(myTasksAdapter);

                        progressDialog.dismiss();


                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });




            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }

}
