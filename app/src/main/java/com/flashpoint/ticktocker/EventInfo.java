package com.flashpoint.ticktocker;

public class EventInfo {
    private String event;
    private int hour;
    private int minute;
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
}
