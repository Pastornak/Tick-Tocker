package com.flashpoint.ticktocker;

import android.os.Bundle;
import android.util.Log;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

public class CreateEventFragment extends Fragment {

    private EditText event;
    private TimePicker time;
    private Button event_create;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.create_event_fragment, container, false);
        event = (EditText) view.findViewById(R.id.event_name);
        time = (TimePicker) view.findViewById(R.id.TimePicker);
        event_create = (Button) view.findViewById(R.id.create_event);
        time.setIs24HourView(true);
        //TODO
        return view;

    }
}
