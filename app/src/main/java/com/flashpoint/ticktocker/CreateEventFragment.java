package com.flashpoint.ticktocker;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.ContentValues.TAG;


public class CreateEventFragment extends Fragment {

    private EditText event;
    private TimePicker time;
    private Button event_create;
    private DatabaseReference database;
    private EventInfo eventInfo;
    private Button set_place;
    private long currentTime;
    private String timeStamp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.create_event_fragment, container, false);
        getActivity().setTitle("New event");

        //opening google maps
        set_place = (Button) view.findViewById(R.id.set_place_map);
        set_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentActivity activity = getActivity();
                MainActivity mainActivity = (MainActivity) activity;
                mainActivity.showFragment(new GoogleMapFragment());
            }
        });

        //pushing info to firebase
        event = (EditText) view.findViewById(R.id.event_name);
        time = (TimePicker) view.findViewById(R.id.TimePicker);
        event_create = (Button) view.findViewById(R.id.create_event);
        time.setIs24HourView(true);
        database = FirebaseDatabase.getInstance().getReference();
        eventInfo = new EventInfo();
        event_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentActivity activity = getActivity();
                MainActivity mainActivity = (MainActivity) activity;
                currentTime = System.currentTimeMillis();
                timeStamp = String.valueOf(currentTime);
                eventInfo.setEvent(event.getText().toString().trim());
                eventInfo.setHour(Long.valueOf(time.getHour()));
                eventInfo.setMinute(Long.valueOf(time.getMinute()));
                eventInfo.location = mainActivity.getPosition();
                //eventInfo.setLocation(mainActivity.getPosition());
                database.child(mainActivity.getUser()).child("day"+"_"+mainActivity.getDay()+"_"
                        +mainActivity.getMonth()+"_"+mainActivity.getYear())
                        .child(timeStamp).setValue(eventInfo);
                mainActivity.showFragment(new ShowEventFragment());
                Toast.makeText(getContext(), "Event created", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
