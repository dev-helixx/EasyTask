package com.easytask.easytask.frontend.controllers;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.easytask.easytask.R;

import java.util.List;

/**
 * Created by Silas on 16-11-2017.
 */

public class LVAdapter extends ArrayAdapter {

    //to reference the Activity
    private Activity context;

    //to store the animal images
//    private Integer[] imageIDarray;

    //to store the list of countries
    private List<String> subjectArray;

    //to store the list of countries
    private List<String> descriptionArray;

    public LVAdapter(Activity context, List<String> subjectArrayParam, List<String> descriptionArrayParam/*, Integer[] imageIDArrayParam*/){
        super(context, R.layout.listview_row_layout , subjectArrayParam);

        this.context = context;
//        this.imageIDarray = imageIDArrayParam;
        this.subjectArray = subjectArrayParam;
        this.descriptionArray = descriptionArrayParam;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview_row_layout, null,true);

        //this code gets references to objects in the listview_row.xml file
        TextView subjectField = (TextView) rowView.findViewById(R.id.my_tasks_subject);
        TextView descriptionField = (TextView) rowView.findViewById(R.id.my_tasks_description);
//        ImageView imageView = (ImageView) rowView.findViewById(R.id.my_tasks_imageview);

        //this code sets the values of the objects to values from the arrays
        subjectField.setText(subjectArray.get(position));
        descriptionField.setText(descriptionArray.get(position));
//        imageView.setImageResource(imageIDarray[position]);

        return rowView;

    }
}
