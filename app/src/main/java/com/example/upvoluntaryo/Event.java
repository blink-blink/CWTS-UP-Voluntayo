package com.example.upvoluntaryo;

public class Event {
    String eventName, eventAddress, eventDetails;
    int imageId;

    public Event(String eventName, String eventAddress, String eventDetails, int imageId){
        this.eventName = eventName;
        this.eventAddress = eventAddress;
        this.eventDetails = eventDetails;
        this.imageId = imageId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDetails() {
        return eventDetails;
    }
}
