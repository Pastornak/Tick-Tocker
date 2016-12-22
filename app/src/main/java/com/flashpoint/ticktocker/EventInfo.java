package com.flashpoint.ticktocker;

import com.google.android.gms.maps.model.LatLng;

public class EventInfo {
    private String event;
    private int hour;
    private int minute;
    private LatLng location;

    public EventInfo(){

    }

    public String getEvent(){
        return event;
    }

    public void setEvent(String a){
        this.event = a;
    }

    public int getHour(){
        return hour;
    }

    public void setHour(int a){
        this.hour = a;
    }

    public int getMinute(){
        return minute;
    }

    public void setMinute(int a){
        this.minute = a;
    }

    public LatLng getLocation()
    {
        return location;
    }

    public void setLocation(LatLng a)
    {
        location = a;
    }
}
