package com.easytask.easytask.frontend.views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.easytask.easytask.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MyProfilFragment extends Fragment implements View.OnClickListener {

    private DatabaseReference database;
    private FirebaseAuth firebaseAuth;
    private Button changeProfileButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_profil, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        getActivity().setTitle("My Profile");

        final TextView name = (TextView) view.findViewById(R.id.my_profile_name);
        final TextView address = (TextView) view.findViewById(R.id.my_profile_address);
        final TextView zipCode = (TextView) view.findViewById(R.id.my_profile_zipcode);
        final TextView city = (TextView) view.findViewById(R.id.my_profile_city);

        changeProfileButton = (Button) view.findViewById(R.id.my_profile_changeProfileBtn);

        changeProfileButton.setOnClickListener(this);


        database.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot snap) {

                name.setText(snap.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("name").getValue().toString());
                address.setText(snap.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("address").getValue().toString());
                zipCode.setText(snap.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("zipCode").getValue().toString());
                city.setText(snap.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("city").getValue().toString());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }


    @Override
    public void onClick(View view) {

        if (view == changeProfileButton){
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.push_up_in, R.anim.push_up_out);
            ft.replace(R.id.fragment_container_main, new EditMyProfilFragment());
            ft.commit();

        }

    }
}
