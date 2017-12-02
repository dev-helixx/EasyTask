package com.easytask.easytask.frontend.controllers;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.easytask.easytask.R;

import java.util.List;

/**
 * Created by Silas on 16-11-2017.
 */

public class LVAdapter extends ArrayAdapter {

    //to reference the Activity
    private Activity context;
//    private Integer[] imageIDarray;
    private List<String> subjectArray;
    private List<String> descriptionArray;

    public LVAdapter(Activity context, List<String> subjectArrayParam, List<String> descriptionArrayParam/*, Integer[] imageIDArrayParam*/){
        super(context, R.layout.listview_row_layout , subjectArrayParam);

        this.context = context;
//        this.imageIDarray = imageIDArrayParam;
        this.subjectArray = subjectArrayParam;
        this.descriptionArray = descriptionArrayParam;

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
        subjectField.setText(subjectArray.get(position));
        descriptionField.setText(descriptionArray.get(position));
//        imageView.setImageResource(imageIDarray[position]);


        delete_my_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, subjectArray.get(position), Toast.LENGTH_SHORT).show();
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
