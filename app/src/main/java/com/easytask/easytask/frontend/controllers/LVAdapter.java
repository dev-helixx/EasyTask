package com.easytask.easytask.frontend.controllers;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeInfoDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.easytask.easytask.R;
import com.easytask.easytask.frontend.views.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * Created by Silas on 16-11-2017.
 */

public class LVAdapter extends ArrayAdapter{

    public interface EditTaskFragmentInterface { void openFragment(String taskID);}


    private Activity context;
    private List<Task> taskList;
    private DatabaseReference database;
    private FirebaseAuth firebaseAuth;
    private String userID;
    private EditTaskFragmentInterface fragmentInterface;



    public LVAdapter(Activity context, List<Task> taskList/*, List<Task> descriptionArrayParam, Integer[] imageIDArrayParam*/){
        super(context, R.layout.listview_row_layout , taskList);
        this.context = context;
        this.taskList = taskList;

        fragmentInterface = (EditTaskFragmentInterface) context;

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
        TextView payField = (TextView) rowView.findViewById(R.id.my_tasks_payment);
        TextView subjectField = (TextView) rowView.findViewById(R.id.my_tasks_subject);
        TextView descriptionField = (TextView) rowView.findViewById(R.id.my_tasks_description);
        ImageButton delete_my_task = (ImageButton) rowView.findViewById(R.id.my_tasks_delete_button);
        ImageButton edit_my_task = (ImageButton) rowView.findViewById(R.id.my_tasks_edit_button);

//        ImageView imageView = (ImageView) rowView.findViewById(R.id.my_tasks_imageview);


        //this code sets the values of the objects to values from the arrays
        subjectField.setText(taskList.get(position).getCard_subject());
        descriptionField.setText(taskList.get(position).getCard_description());
        payField.setText(taskList.get(position).getTaskReward() + " kr.");
//        imageView.setImageResource(imageIDarray[position]);



        delete_my_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AwesomeInfoDialog(context)
                        .setTitle("Advarsel!")
                        .setMessage("Du er ved at slette din opgave. Er du sikker på du vil fortsætte?")
                        .setColoredCircle(R.color.blåFarve1)
                        .setDialogIconAndColor(R.drawable.ic_dialog_info, R.color.white)
                        .setCancelable(true)
                        .setPositiveButtonText("Ja")
                        .setPositiveButtonbackgroundColor(R.color.blåFarve7)
                        .setPositiveButtonTextColor(R.color.white)
                        .setNegativeButtonText("Nej")
                        .setNegativeButtonbackgroundColor(R.color.blåFarve3)
                        .setNegativeButtonTextColor(R.color.white)
                        .setPositiveButtonClick(new Closure() {
                            @Override
                            public void exec() {


                                final Query globalTaskRef =  database.child("tasks").child(taskList.get(position).getTaskID());
                                final Query usersTasksRef =  database.child("users").child(userID).child("tasks").child(taskList.get(position).getTaskID());


                                database.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot taskData) {


                                        globalTaskRef.getRef().removeValue();
                                        usersTasksRef.getRef().removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> task) {
                                                Toasty.success(context, "Success! ", Toast.LENGTH_SHORT, true).show();
                                            }
                                        });

                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });




                            }
                        })
                        .setNegativeButtonClick(new Closure() {
                            @Override
                            public void exec() {
                                /* Dismisses dialog box */
                            }
                        })
                        .show();

            }
        });

        edit_my_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentInterface.openFragment(taskList.get(position).getTaskID());

            }
        });


        return rowView;

    }



}
