package com.easytask.easytask.frontend.views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.easytask.easytask.R;

/**
 * Created by Silas on 05-11-2017.
 */

public class AboutFragment extends Fragment {

    TextView about_label;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        getActivity().setTitle("Om os");

        about_label = (TextView) view.findViewById(R.id.about_label);

        about_label.setText("Hej og velkommen til EasyTask - App'en der gør din hverdag nemmere!" +
                "Med denne app kan du nemt og hurtigt oprette opgaver som du ikke selv kan overskue at skulle lave.");
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_addTask).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }



}
