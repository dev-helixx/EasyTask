package com.easytask.easytask.frontend.controllers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.easytask.easytask.R;

import java.util.List;

/**
 * Created by Silas on 12-10-2017.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.TaskViewHolder>{

    List<Task> tasks;

    public RVAdapter(List<Task> tasks){
        this.tasks = tasks;
    }



    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_item_layout, viewGroup, false);
        TaskViewHolder tvh = new TaskViewHolder(v);
        return tvh;
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int i) {


        holder.card_subject.setText(tasks.get(i).card_subject);
        holder.card_description.setText(tasks.get(i).card_description);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView card_subject;
        TextView card_description;

        TaskViewHolder(final View itemView) {
            super(itemView);
            card_subject = (TextView)itemView.findViewById(R.id.card_subject);
            card_description = (TextView)itemView.findViewById(R.id.card_description);

            /* Handles card clicks*/
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(),card_subject.getText(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }


}