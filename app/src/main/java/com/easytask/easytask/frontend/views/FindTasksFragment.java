package com.easytask.easytask.frontend.views;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.easytask.easytask.R;
import com.easytask.easytask.frontend.controllers.LVAdapter;
import com.easytask.easytask.frontend.controllers.RVAdapter;
import com.easytask.easytask.frontend.controllers.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Silas on 05-11-2017.
 */

public class FindTasksFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Task> task_list;
    private ProgressDialog progressDialog;
    private DatabaseReference taskRef;
    private FirebaseAuth firebaseAuth;
    private String userId;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_tasks, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        getActivity().setTitle("Alle Opgaver");

        progressDialog = ProgressDialog.show(getContext(), "Henter alle tilgængelige opgaver", "Vent venligst", false, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        initializeAvailableTasks();


    }

    private void initializeAvailableTasks(){

        task_list = new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();
        taskRef = FirebaseDatabase.getInstance().getReference();

        if(firebaseAuth.getCurrentUser() == null){
            getActivity().finish();
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }else {
            userId = firebaseAuth.getCurrentUser().getUid();
//            Toast.makeText(getActivity(), userId, Toast.LENGTH_SHORT).show();
        }

        taskRef.child("tasks").addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot snap1) {

                // tasks indeholder alle tasks IDs
                for (DataSnapshot tasks : snap1.getChildren()) {

//                    task_list.add(new Task(tasks.child("title").getValue().toString(), tasks.child("description").getValue().toString()));
                    task_list.add(new Task(tasks.child("title").getValue().toString(), tasks.child("description").getValue().toString(), tasks.getKey(), Integer.parseInt(tasks.child("payment").getValue().toString())));

                }

                LinearLayoutManager llm = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(llm);
                RVAdapter adapter = new RVAdapter(task_list);
                recyclerView.setAdapter(adapter);

                progressDialog.dismiss();

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
