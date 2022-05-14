package com.example.upvoluntaryo.objects;

public class Event {
    String eventName, eventAddress, eventDetails, eventDate;
    int eventId, eventType, target, orgId,imageId;

////constructors
    public Event(int eventId, int eventType, String eventName, String eventAddress, String eventDetails, String eventDate, int target, int orgId, int imageId){
        this.eventId = eventId;
        this.eventType = eventType;
        this.eventName = eventName;
        this.eventAddress = eventAddress;
        this.eventDetails = eventDetails;
        this.eventDate = eventDate;
        this.target = target;
        this.orgId = orgId;
        this.imageId = imageId;
    }

    public Event(String eventName, String eventDate, String eventAddress, String eventDetails, int orgId, int imageId){
        this.eventName = eventName;
        this.eventAddress = eventAddress;
        this.eventDate = eventDate;
        this.eventDetails = eventDetails;
        this.orgId = orgId;
        this.imageId = imageId;
    }
/////

    public int getEventId() {
        return eventId;
    }

    public int getEventType() {
        return eventType;
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

    public String getEventDate() {
        return eventDate;
    }

    public int getEventTarget() {
        return target;
    }

    public int getOrgId() {
        return orgId;
    }

    public int getImageId() {
        return imageId;
    }
}
