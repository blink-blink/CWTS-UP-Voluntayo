package com.example.upvoluntaryo.objects;

public class Event {
    String eventName, eventAddress, eventDetails;
    int eventId,orgId,imageId;

////constructors
    public Event(int eventId, String eventName, String eventAddress, String eventDetails, int orgId, int imageId){
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventAddress = eventAddress;
        this.eventDetails = eventDetails;
        this.orgId = orgId;
        this.imageId = imageId;
    }

    public Event(String eventName, String eventAddress, String eventDetails, int orgId, int imageId){
        this.eventName = eventName;
        this.eventAddress = eventAddress;
        this.eventDetails = eventDetails;
        this.orgId = orgId;
        this.imageId = imageId;
    }
/////

    public int getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public String getEventDetails() {
        return eventDetails;
    }

    public int getOrgId() {
        return orgId;
    }

    public int getImageId() {
        return imageId;
    }
}
