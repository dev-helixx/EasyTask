package com.easytask.easytask.frontend.views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.easytask.easytask.R;
import com.easytask.easytask.frontend.controllers.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.dmoral.toasty.Toasty;

public class ContactInfoFragment extends Fragment implements View.OnClickListener {

    /**
     * Created by Silas on 05-12-2017.
     */

    private DatabaseReference database;
    private Button call_btn, sms_btn, return_btn;
    private TextView name_label, address_label, zip_label, city_label, phonenumber_label;
    private String creatorID;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_info, container, false);
        return view;

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


        database = FirebaseDatabase.getInstance().getReference();
        creatorID = getArguments().getString("creatorID");

        /* Instantiate views */
        call_btn = (Button) view.findViewById(R.id.contact_call_btn);
        sms_btn = (Button) view.findViewById(R.id.contact_sms_btn);
        return_btn = (Button) view.findViewById(R.id.contact_return_btn);
        name_label = (TextView) view.findViewById(R.id.contact_name_label);
        address_label = (TextView) view.findViewById(R.id.contact_address_label);
        zip_label = (TextView) view.findViewById(R.id.contact_zip_label);
        city_label = (TextView) view.findViewById(R.id.contact_city_label);
        phonenumber_label = (TextView) view.findViewById(R.id.contact_phone_label);

        return_btn.setOnClickListener(this);
        call_btn.setOnClickListener(this);
        sms_btn.setOnClickListener(this);

        database.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot db) {



                name_label.setText(db.child("users").child(creatorID).child("name").getValue().toString());
                address_label.setText(db.child("users").child(creatorID).child("address").getValue().toString());
                zip_label.setText(db.child("users").child(creatorID).child("zipCode").getValue().toString());
                city_label.setText(db.child("users").child(creatorID).child("city").getValue().toString());
                phonenumber_label.setText(db.child("users").child(creatorID).child("phonenumber").getValue().toString());


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });




    }

    @Override
    public void onClick(View v) {
        if(v == call_btn) {
            Toasty.info(getActivity(), "Not implemented yet!", Toast.LENGTH_SHORT, true).show();
        }else if(v == sms_btn) {
            Toasty.info(getActivity(), "Not implemented yet!", Toast.LENGTH_SHORT, true).show();

        }else if(v == return_btn) {
            hideFragment();
        }
    }

    public void hideFragment() {
        if (getFragmentManager().findFragmentById(R.id.fragment_container_detailed).isVisible()) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.push_up_in, R.anim.push_up_out);
            ft.remove(getFragmentManager().findFragmentById(R.id.fragment_container_detailed));
            ft.commit();

        }

    }
}
