package com.easytask.easytask.frontend.views;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.easytask.easytask.R;

public class DetailedTaskActivity extends AppCompatActivity {


    private String subject;
    private String description;
//    private int imageID;

    private TextView detailed_task_subject;
    private TextView detailed_task_description;
//    private ImageView detailed_task_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_task);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Task Overview");



        /* Instantiate views */
        detailed_task_description = (TextView) findViewById(R.id.detailed_task_description);
        detailed_task_subject = (TextView) findViewById(R.id.detailed_task_subject);
//        imageID = (ImageView) findViewById(R.id.detailed_task_image);

        // gets the previously created intent and the values passed from it
        Intent myIntent = getIntent();
        subject = myIntent.getStringExtra("task_subject");
        description = myIntent.getStringExtra("task_description");
//        imageID = myIntent.getIntExtra("detailed_task_image");

        /* Add values to textviews */
        detailed_task_subject.setText(subject);
        detailed_task_description.setText(description);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
//                Intent intent = new Intent(CurrentActivity.this, MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
