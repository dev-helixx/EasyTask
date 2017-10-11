package com.easytask.easytask.frontend.views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.EdgeEffectCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.easytask.easytask.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

/**
 * Created by Silas on 27-09-2017.
 */

public class CreateUserFragment extends Fragment implements View.OnClickListener {

    private Button return_btn, create_user_btn;
    private EditText usernameET, passwordET, confirmPassET;
    private String username, password;
    private FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_user, container, false);
        return view;

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        /* Makes sure that the fragment isn't pushed up by the screen keyboard*/
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        firebaseAuth = FirebaseAuth.getInstance();

        usernameET = (EditText) view.findViewById(R.id.create_email_tbox);
        passwordET = (EditText) view.findViewById(R.id.create_pwd1_tbox);
        confirmPassET = (EditText) view.findViewById(R.id.create_pwd2_tbox);


        return_btn = (Button) view.findViewById(R.id.create_return_btn);
        create_user_btn = (Button) view.findViewById(R.id.create_user_btn);

        /* Add button handlers */
        return_btn.setOnClickListener(this);
        create_user_btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view == return_btn) {
            if (getFragmentManager().findFragmentById(R.id.fragment_container_login).isVisible()) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.push_up_in, R.anim.push_up_out);
                ft.hide(getFragmentManager().findFragmentById(R.id.fragment_container_login));
                ft.commit();
                LoginActivity.mainLoginBtn.setEnabled(true);

            }
        }else if(view == create_user_btn ) {
            Toast.makeText(this.getContext(), "User created click", Toast.LENGTH_SHORT).show();

            username = usernameET.getText().toString();

            if(passwordET.getText().toString().equals(confirmPassET.getText().toString())){
                Toast.makeText(getContext(), "Password match", Toast.LENGTH_LONG).show();
                password = passwordET.getText().toString();

//                createUser(username, password);
            }
            else{
                Toast.makeText(getContext(), "Password does not match!", Toast.LENGTH_LONG).show();
            }

        }
    }



    public void createUser(String username, String password) {
        /* Add firebase method to create new user*/


        firebaseAuth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "createUserWithEmail:success");
//                            FirebaseUser user = firebaseAuth.getCurrentUser();
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                            Toast.makeText(getContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                    }
                });

    }

}
