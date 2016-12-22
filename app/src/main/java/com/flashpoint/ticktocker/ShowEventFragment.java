package com.flashpoint.ticktocker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ShowEventFragment extends Fragment {

    //private DatabaseReference database;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.show_event_fragment, container, false);
        Button CreateNewEventButton = (Button) view.findViewById(R.id.create_new_event);
        CreateNewEventButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                FragmentActivity activity = getActivity();
                MainActivity mainActivity = (MainActivity) activity;
                mainActivity.showFragment(new CreateEventFragment());
            }
        });
           /* database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String value = dataSnapshot.child("test").getValue(String.class);
                    Log.d(TAG, "Value is: " + value);
                }
            });*/
        return view;
    }

}
