package com.flashpoint.ticktocker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShowEventFragment extends Fragment {

    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.show_event_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton createNewEventButton = (ImageButton) view.findViewById(R.id.create_new_event);
        createNewEventButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                FragmentActivity activity = getActivity();
                MainActivity mainActivity = (MainActivity) activity;
                mainActivity.showFragment(new CreateEventFragment());
            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.events);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        eventAdapter = new EventAdapter();
        recyclerView.setAdapter(eventAdapter);
        showEvents();

                /**
                 * TODO remove this once firebase data can be displayed.
                 */

        /*EventInfo eventInfo1 = new EventInfo();
        eventInfo1.setEvent("Go shopping");

        EventInfo eventInfo2 = new EventInfo();
        eventInfo2.setEvent("Visit dentist");

        EventInfo eventInfo3 = new EventInfo();
        eventInfo3.setEvent("Check out new band album");

        eventAdapter.events.add(eventInfo1);
        eventAdapter.events.add(eventInfo2);
        eventAdapter.events.add(eventInfo3);

        eventAdapter.notifyDataSetChanged();*/
    }

    private void showEvents() {
        MainActivity mainActivity = (MainActivity) getActivity();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        DatabaseReference dayReference = database
                .child(mainActivity.getUser())
                .child("day" + "_" + mainActivity.getDay() + "_" + mainActivity.getMonth() + "_" + mainActivity.getYear());

        dayReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<EventInfo> dayEvents = new ArrayList<EventInfo>();

                for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                    final HashMap map = (HashMap) eventSnapshot.getValue();

                    EventInfo eventInfo = new EventInfo();
                    eventInfo.setEvent((String) map.get("event"));
                    eventInfo.setHour((Long) map.get("hour"));
                    eventInfo.setMinute((Long) map.get("minute"));

                    //LatLng location = new LatLng((Double) map.get("latitude"), (Double) map.get("longitude"));
                    HashMap locationMap = (HashMap) map.get("location");
                    LatLng location = new LatLng((Double) locationMap.get("latitude"), (Double) locationMap.get("longitude"));
                    eventInfo.setLocation(location);

                    dayEvents.add(eventInfo);
                }

                eventAdapter.events.clear();
                eventAdapter.events.addAll(dayEvents);
                eventAdapter.notifyDataSetChanged();
                /*List<EventInfo> dayEvents = new ArrayList<EventInfo>();

                for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                    //EventInfo eventInfo = (EventInfo) eventSnapshot.getValue();
                    Toast.makeText(getContext(), eventSnapshot.toString(), Toast.LENGTH_SHORT).show();
                    //dayEvents.add(eventInfo);
                }

                eventAdapter.events.clear();
                eventAdapter.events.addAll(dayEvents);
                eventAdapter.notifyDataSetChanged();*/
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private class EventViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView date;


        public EventViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.event_title);
            date = (TextView)itemView.findViewById(R.id.event_date);
        }
    }

    private class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {

        private List<EventInfo> events = new ArrayList<>();

        @Override
        public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(getContext()).inflate(R.layout.event_info, parent, false);

            EventViewHolder holder = new EventViewHolder(itemView);

            return holder;
        }

        @Override
        public void onBindViewHolder(EventViewHolder holder, int position) {

            final EventInfo eventInfo = events.get(position); 
            holder.itemView.setOnClickListener(new OnClickListener() { 
                
        @Override 
	    public void onClick(View view) { 
		    ChosenEventMapFragment fragment = new ChosenEventMapFragment(); 
		    fragment.setEventInfo(eventInfo); 
    		FragmentActivity activity = getActivity(); 
	    	MainActivity mainActivity = (MainActivity) activity; 
		    mainActivity.showFragment(fragment); 
	        } 
        }); 


            holder.title.setText(eventInfo.getEvent());
            //holder.date.setText(...);
        }

        @Override
        public int getItemCount() {
            return events.size();
        }
    }
}
