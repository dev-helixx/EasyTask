package com.easytask.easytask.frontend.views;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.easytask.easytask.R;

/**
 * Created by Silas on 13-11-2017.
 */

public class DetailedTaskActivity extends AppCompatActivity implements View.OnClickListener {

    private Button contact_btn;
    private String subject;
    private String description;
    private String payment;
    private String creatorID;
//    private int imageID;

    private TextView detailed_task_subject;
    private TextView detailed_task_description;
    private TextView detailed_task_payment;
//    private ImageView detailed_task_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_task);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Opgave detaljer");



        /* Instantiate views */
        detailed_task_description = (TextView) findViewById(R.id.detailed_task_description);
        detailed_task_subject = (TextView) findViewById(R.id.detailed_task_subject);
        detailed_task_payment = (TextView) findViewById(R.id.detailed_task_payment);
        contact_btn = (Button) findViewById(R.id.detailed_task_contact_btn);
//        imageID = (ImageView) findViewById(R.id.detailed_task_image);

        // gets the previously created intent and the values passed from it
        Intent myIntent = getIntent();
        subject = myIntent.getStringExtra("task_subject");
        description = myIntent.getStringExtra("task_description");
        payment = myIntent.getStringExtra("task_payment");
        creatorID = myIntent.getStringExtra("task_creator");
//        imageID = myIntent.getIntExtra("detailed_task_image");

        /* Add values to textviews */
        detailed_task_subject.setText(subject);
        detailed_task_description.setText(description);
        detailed_task_payment.setText(payment);

        contact_btn.setOnClickListener(this);


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

    @Override
    public void onClick(View v) {
        if(v == contact_btn) {
            Bundle bundle = new Bundle();
            bundle.putString("creatorID", creatorID);

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.push_up_in, R.anim.push_up_out);
            Fragment contactFragment = new ContactInfoFragment();
            contactFragment.setArguments(bundle);
            ft.add(R.id.fragment_container_detailed, contactFragment, "contactfragment");
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}
