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

/**
 * Created by Silas on 27-09-2017.
 */

public class CreateUserFragment extends Fragment implements View.OnClickListener {

    Button return_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_user, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        return_btn = (Button) view.findViewById(R.id.create_return_btn);
        return_btn.setOnClickListener(this);
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
        }
    }

}
