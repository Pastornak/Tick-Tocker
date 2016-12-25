package com.flashpoint.ticktocker;

import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.model.LatLng;

public class EventInfo {
    private String event;
    private Long hour;
    private Long minute;
    public LatLng location;

    public EventInfo(){

    }

    public String getEvent(){
        return event;
    }

    public void setEvent(String a){
        this.event = a;
    }

    public Long getHour(){
        return hour;
    }

    public void setHour(Long a){
        this.hour = a;
    }

    public Long getMinute(){
        return minute;
    }

    public void setMinute(Long a){
        this.minute = a;
    }

    public void setLocation(LatLng a)
    {
        this.location = a;
    }

}
