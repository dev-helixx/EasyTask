package com.easytask.easytask.frontend.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.easytask.easytask.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import es.dmoral.toasty.Toasty;


public class EditMyProfileFragment extends Fragment implements View.OnClickListener {

    private DatabaseReference database;
    private FirebaseAuth firebaseAuth;
    private Button saveProfileButton;
    private String name, address, zipCode, city;
    private EditText nameET, addressET, zipCodeET, cityET;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_my_profil, container, false);
        return view;

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        getActivity().setTitle("My Profile");

        nameET = (EditText) view.findViewById(R.id.edit_profile_name);
        addressET = (EditText) view.findViewById(R.id.edit_my_profile_address1);
        zipCodeET = (EditText) view.findViewById(R.id.edit_my_profile_zipCode);
        cityET = (EditText) view.findViewById(R.id.edit_my_profile_city);

        saveProfileButton = (Button) view.findViewById(R.id.edit_my_profile_savebutton);

        saveProfileButton.setOnClickListener(this);


        database.addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot snap) {

                nameET.setText(snap.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("name").getValue().toString());
                addressET.setText(snap.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("address").getValue().toString());
                zipCodeET.setText(snap.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("zipCode").getValue().toString());
                cityET.setText(snap.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("city").getValue().toString());
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }


    @Override
    public void onClick(View view) {

        if (view == saveProfileButton){

            name = nameET.getText().toString();
            address = addressET.getText().toString();
            zipCode = zipCodeET.getText().toString();
            city = cityET.getText().toString();

            firebaseAuth = FirebaseAuth.getInstance();

            if(firebaseAuth.getCurrentUser() == null){
                Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent1);
                getActivity().finish();
            }

            if (name != "" && address != "" && zipCode != ""&& city != "") {

                database.addListenerForSingleValueEvent(new ValueEventListener() {



                    @Override
                    public void onDataChange(DataSnapshot snap) {

                        saveProfile(name, address, zipCode, city);

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            } else {
                Toast.makeText(getContext(), "Ups Husk at udfyld alle felter", Toast.LENGTH_LONG).show();
            }


        }


    }

    private void saveProfile(String name, String address, String zipCode, String city) {
        if(firebaseAuth.getCurrentUser()!= null) {
            database.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("name").setValue(name);
            database.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("address").setValue(address);
            database.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("zipCode").setValue(zipCode);
            database.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("city").setValue(city,  new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError != null) {
                        Toasty.error(getContext(), "Kunne ikke gemme data! ", Toast.LENGTH_SHORT, true).show();
                    } else {
                        Toasty.success(getContext(), "Oplysninger opdateret! ", Toast.LENGTH_SHORT, true).show();

                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.setCustomAnimations(R.anim.push_up_in, R.anim.push_up_out);
                        ft.replace(R.id.fragment_container_main, new MyProfileFragment());
                        ft.commit();
                    }
                }
            });



        }
    }
}
