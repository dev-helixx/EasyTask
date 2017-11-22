package com.easytask.easytask.frontend.views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
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

        getActivity().setTitle("Om os");

        about_label = (TextView) view.findViewById(R.id.about_label);

        about_label.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Nam tincidunt fringilla erat. Integer euismod malesuada metus, non efficitur libero egestas ac. " +
                "Curabitur sed tempus urna. Vivamus tempus consectetur turpis, vitae blandit mi dapibus nec. " +
                "Quisque tempor, ex at ornare maximus, metus nisl facilisis nisl, ac gravida ante felis quis lectus. " +
                "Sed ullamcorper lectus mi, nec auctor lorem pretium ac. Donec lobortis, nulla at porta molestie, " +
                "sem turpis ullamcorper ante, vitae condimentum nibh ante sit amet justo. Integer sapien lacus, " +
                "ultrices eu mi quis, dignissim sollicitudin enim. Proin ante lectus, vehicula ac efficitur at, " +
                "porta fringilla eros. Mauris sodales sed metus sodales viverra. Integer viverra pretium ipsum, id " +
                "efficitur orci eleifend id. Aliquam id gravida ipsum. Nullam congue magna non tristique convallis. " +
                "Pellentesque dapibus accumsan orci ac porttitor. In hac habitasse platea dictumst. Morbi commodo " +
                "elementum lacus ut imperdiet. Donec pellentesque sem tellus, accumsan feugiat purus mattis sed. " +
                "Vestibulum commodo placerat elementum. In tincidunt porta suscipit. Donec vitae fermentum erat, " +
                "et scelerisque erat. Vestibulum ultricies gravida nulla, a aliquet massa commodo ac. Aliquam ac " +
                "consequat nulla. Sed vel massa a nibh hendrerit feugiat. Nullam tempor, mi nec interdum tempor, " +
                "diam libero volutpat sapien, et venenatis mi diam non nisl.");
    }


}
