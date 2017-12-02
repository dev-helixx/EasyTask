package com.easytask.easytask.frontend.controllers;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.easytask.easytask.R;
import com.easytask.easytask.frontend.views.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * Created by Silas on 16-11-2017.
 */

public class LVAdapter extends ArrayAdapter {

    //to reference the Activity
    private Activity context;
    //    private Integer[] imageIDarray;
    private List<Task> taskList;
    private List<Task> descriptionArray;
    private DatabaseReference database;
    private FirebaseAuth firebaseAuth;
    private String userID;

    public LVAdapter(Activity context, List<Task> taskList/*, List<Task> descriptionArrayParam, Integer[] imageIDArrayParam*/){
        super(context, R.layout.listview_row_layout , taskList);

        this.context = context;
//        this.imageIDarray = imageIDArrayParam;
        this.taskList = taskList;
//        this.descriptionArray = descriptionArrayParam;


        /* Get database references */
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        if(firebaseAuth.getCurrentUser() == null){
            context.finish();
            context.startActivity(new Intent(context, LoginActivity.class));
        }
        else {
            userID = firebaseAuth.getCurrentUser().getUid();
        }

    }

    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview_row_layout, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView subjectField = (TextView) rowView.findViewById(R.id.my_tasks_subject);
        TextView descriptionField = (TextView) rowView.findViewById(R.id.my_tasks_description);
        ImageButton delete_my_task = (ImageButton) rowView.findViewById(R.id.my_tasks_delete_button);
        ImageButton edit_my_task = (ImageButton) rowView.findViewById(R.id.my_tasks_edit_button);
//        ImageView imageView = (ImageView) rowView.findViewById(R.id.my_tasks_imageview);


        //this code sets the values of the objects to values from the arrays
        subjectField.setText(taskList.get(position).card_subject);
        descriptionField.setText(taskList.get(position).card_description);
//        imageView.setImageResource(imageIDarray[position]);


        delete_my_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot taskData) {


                        taskData.getRef().removeValue()


//                        for(DataSnapshot globalTasks : taskData.child("tasks").getChildren()) {
//
//                            for (DataSnapshot userTasks : taskData.child("users").child(userID).child("tasks").getChildren()) {
//
//                                if (globalTasks.getKey().equals(userTasks.getKey())) {
                                    Toast.makeText(context, taskList.get(position).taskID, Toast.LENGTH_SHORT).show();
//                                }
//                            }
////
//                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

//                Toast.makeText(context, subjectArray.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        edit_my_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return rowView;

    }

}
